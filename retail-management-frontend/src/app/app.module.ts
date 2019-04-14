import { AddPanelComponent } from './panel/add-panel/add-panel.component';
import { SearchPanelComponent } from './panel/search-panel/search-panel.component';

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AgmCoreModule } from '@agm/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import { AppComponent } from './app.component';
import { MapComponent } from './map/map.component';
import { PanelComponent } from './panel/panel.component';

import { MapsService } from './maps.service';
import { LocationsService } from './locations.service';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';


@NgModule({
  declarations: [
    AppComponent,
    MapComponent,
    SearchPanelComponent,
    AddPanelComponent,
    PanelComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    AgmCoreModule.forRoot({
      apiKey: 'GOOGLE_MAP_API_KEY',
      libraries: ['places']
    }),
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [LocationsService, MapsService],
  bootstrap: [AppComponent]
})

export class AppModule { }
