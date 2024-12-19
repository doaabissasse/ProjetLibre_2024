import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AppComponent } from '../app_comp/app.component';
import { AccueilComponent } from '../accueil/accueil.component';
import { MenuComponent } from '../menu/menu.component';
import { LaboratoireListComponent } from '../Laboratoire/laboratoire-list/laboratoire-list.component';
import { AjouterLaboratoireComponent } from '../Laboratoire/ajouter-laboratoire/ajouter-laboratoire.component';
import { ConfirmDialogComponent } from '../Laboratoire/confirm-dialog/confirm-dialog.component';
import { LaboratoireEditComponent } from '../Laboratoire/laboratoire-edit/laboratoire-edit.component';
import { MatInputModule } from '@angular/material/input'; // Import MatInputModule
import { HttpClientTestingModule } from "@angular/common/http/testing";


@NgModule({
  declarations: [
    ConfirmDialogComponent,
    LaboratoireEditComponent ,
  ],
  imports: [
    AppComponent,
    BrowserModule,
    HttpClientTestingModule ,
    AccueilComponent,
    MenuComponent,
    LaboratoireListComponent,
    AjouterLaboratoireComponent,
    HttpClientModule,
    AppRoutingModule,
    ToastrModule.forRoot({
      positionClass: 'toast-top-center',
      timeOut: 3000,
      progressBar: true,
      progressAnimation: 'increasing'
    }),
    MatInputModule,
    MatDialogModule,
    MatButtonModule,
    BrowserAnimationsModule, // Obligatoire pour Angular Material
    FormsModule, // Pour [(ngModel)]
    ReactiveFormsModule ,// Pour les formulaires réactifs
    CommonModule,
    RouterModule, 
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}