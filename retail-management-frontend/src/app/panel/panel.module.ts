import { MapComponent } from './../map/map.component';
import { SearchPanelComponent } from './search-panel/search-panel.component';
import { AddPanelComponent } from './add-panel/add-panel.component';
import { PanelComponent } from './panel.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule
  ],
  exports: [ PanelComponent ],
  declarations: [PanelComponent, AddPanelComponent, SearchPanelComponent, MapComponent]
})
export class PanelModule { }
