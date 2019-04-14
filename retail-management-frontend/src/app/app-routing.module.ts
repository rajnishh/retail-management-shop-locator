import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddPanelComponent } from './panel/add-panel/add-panel.component';
import { SearchPanelComponent } from './panel/search-panel/search-panel.component';

export const routes: Routes = [
  { path: '', redirectTo: '/search', pathMatch: 'full' },
  
  { path: 'search', component: SearchPanelComponent },
  { path: 'add', component: AddPanelComponent }
];

@NgModule({
  imports: [ CommonModule, RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
  // ,
  // declarations: [AddPanelComponent, SearchPanelComponent]
})
export class AppRoutingModule { }
