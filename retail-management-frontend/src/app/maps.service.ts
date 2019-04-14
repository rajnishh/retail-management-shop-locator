import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable()
export class MapsService {
  public lat: number = 28.4956687;
  public lng: number = 77.09017040000003;
  public zoom: number = 8;

  public newCoordinators = new Subject();

  public openWindow = new Subject();

  constructor() { }

}
