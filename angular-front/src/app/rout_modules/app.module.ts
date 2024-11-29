import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { importProvidersFrom } from '@angular/core';
import { LaboratoireListComponent } from '../Laboratoire/laboratoire-list/laboratoire-list.component';
import { AppComponent } from '../app_comp/app.component';
import { AjouterLaboratoireComponent } from '../Laboratoire/ajouter-laboratoire/ajouter-laboratoire.component';
import { FormsModule } from '@angular/forms';
import { AccueilComponent } from '../accueil/accueil.component';
import {MenuComponent } from '../menu/menu.component';
import { MatDialogModule } from '@angular/material/dialog';
import { ConfirmDialogComponent } from '../Laboratoire/confirm-dialog/confirm-dialog.component';

@NgModule({
  imports: [
    MatDialogModule,
    AccueilComponent,
    AppComponent,
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    LaboratoireListComponent,
    AjouterLaboratoireComponent ,
    FormsModule,
    MenuComponent,
    ConfirmDialogComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA], 
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}