
/// <reference types="@types/googlemaps" />
import { Component, OnInit, ViewChild, ElementRef, NgZone } from '@angular/core';
import { FormControl, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MapsAPILoader } from '@agm/core';
//import { } from 'googlemaps';
import { MapsService } from '../../maps.service';
import { LocationsService } from '../../locations.service';
import { Marker } from '../../../models/marker';
import { Router } from '@angular/router';
import { ShopCategory } from 'src/models/shop.category';
import { Observable } from 'rxjs';
import { ShopDetails } from 'src/models/shop-details';

@Component({
  selector: 'app-add-panel',
  templateUrl: './add-panel.component.html',
  styleUrls: ['./add-panel.component.scss']
})
export class AddPanelComponent implements OnInit {

  @ViewChild('search') public searchElementRef: ElementRef;
   // Initilize Shop Category Class
   categoryList: ShopCategory[] = [
    { id: 1, name: 'General Store' },
    { id: 2, name: 'Mall' },
    { id: 3, name: 'Super Market' },
    { id: 4, name: 'Medical Store' }
  ];
  public latitude: number;
  public longitude: number;
	public zoom: number;
	public city: string; 
	public country: string;
	public street: string; 
	public postCode: string; 
	public state: string;
  addShopForm: FormGroup;
  savedDate: any;
  submitted = false;
  address: any;
  successMsg: any;

    constructor(
        private formBuilder: FormBuilder,
        private router: Router,
        private mapApiLoader: MapsAPILoader,
        private ngZone: NgZone,
        private mapsService: MapsService,
        private locationsService: LocationsService
    ) {}

    ngOnInit() {
        this.addShopForm = this.formBuilder.group({
            shopName: ['', Validators.required],
            category: ['', Validators.required],
            address: ['', Validators.required],
            ownerName: ['', Validators.required]
        });

        this.mapApiLoader.load().then(() => {
          debugger;
          const autocomplete = new google.maps.places.Autocomplete(this.searchElementRef.nativeElement, {
            types: ['address']
          });
    
          autocomplete.addListener('place_changed', () => {
            this.ngZone.run(() => {
              // get place result
              const place: google.maps.places.PlaceResult = autocomplete.getPlace();
    
              // verify result
              if (place.geometry === undefined || place.geometry === null) {
                return;
              }
              this.address = place.formatted_address.split(',');
              this.extractAddressComponents(this.address);
              this.zoomToNewLocation(place.geometry.location.lat(), place.geometry.location.lng());
    
            });
          });
        });
    }
    extractAddressComponents(adressArr){
      debugger;
      this.street = adressArr[0];
      this.city = adressArr[1];
      const cty: string[] = adressArr[2].split(' ');
      this.postCode = cty[cty.length-1];
      this.state = adressArr[2].replace(this.postCode, '');
      this.country = adressArr[3];
    }

    //Get Long and lat from address
    getGeoLocation(address: string): Observable<any> {
      debugger;
      console.log('Getting address: ', address);
      let geocoder = new google.maps.Geocoder();
      return Observable.create(observer => {
          geocoder.geocode({
              'address': address
          }, (results, status) => {
              if (status == google.maps.GeocoderStatus.OK) {
                  observer.next(results[0].geometry.location);
                  observer.complete();
              } else {
                  console.log('Error: ', results, ' & Status: ', status);
                  observer.error();
              }
          });
      });
  }
    zoomToNewLocation(lat: number, lng: number) {
      // set new latitude, longtitude
      this.mapsService.lat = lat;
      this.mapsService.lng = lng;
      debugger;
      this.mapsService.newCoordinators.next({
        lat: this.mapsService.lat,
        lng: this.mapsService.lng,
        zoom: 10
      });
    }
    // convenience getter for easy access to form fields
    get f() { return this.addShopForm.controls; }

    onSubmit() {
        this.submitted = true;

        // stop here if form is invalid
        if (this.addShopForm.invalid) {
            return;
        }
        debugger;
        const object: ShopDetails = {
          'lat' : this.mapsService.lat,
          'lng': this.mapsService.lng,
          'shopName' : this.addShopForm.controls.shopName.value,
          'ownerName' : this.addShopForm.controls.ownerName.value,
          'category' : this.addShopForm.controls.category.value,
          'street' :  this.street,
          'state' :  this.state,
          'city' :  this.city,
          'postCode' :  this.postCode,
          'country' :  this.country,
          'icon' : 'https://www.ftsgps.com/wp-content/uploads/2017/05/icon-location-100.png'
        }
        this.locationsService.addNewShop(object)
            .subscribe(
                data => {
                  this.successMsg = data.body.message;
                  this.router.navigate(['/search']);
                },
                error => {
                    console.log('Error occurred in saving SHop details');
                });
    }
}