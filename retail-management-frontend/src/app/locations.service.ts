import { Injectable } from '@angular/core';
import { Marker } from '../models/marker';
import { environment } from 'src/environments/environment';
import { HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LocationsService {

  baseUrl: string = environment.apiUrl;
  constructor(private httpClient: HttpClient) { }

  listAllShops(){
    return this.httpClient.get<Marker[]>(`${this.baseUrl}/listAllShops`);
  }

  findShop() {
    return this.httpClient.get(`${this.baseUrl}/findShop`);
  }

  addNewShop(object) {
    return this.httpClient.post<any>(`${this.baseUrl}/addNewShop`, object, {observe: 'response'});
  }

  getMarkers() {
    return this.httpClient.get<Marker[]>(`${this.baseUrl}/listAllShops`);
  }

}