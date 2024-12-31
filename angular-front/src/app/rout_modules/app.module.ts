import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http'; 
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
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { ContactsDialogComponent } from '../Contactes/contacts-dialog/contacts-dialog.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { AjouterContactDialogComponent } from '../../app/Contactes/ajouter-contact-dialog/ajouter-contact-dialog.component'
import { MatRadioModule } from '@angular/material/radio';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatSelectModule } from '@angular/material/select';
import { ConfirmationDialogComponent } from '../Adresses/confirmation-dialog/confirmation-dialog.component';
import { AnalysesDialogComponent } from '../Analyses/analyses-dialog/analyses-dialog.component';
import { AddAnalyseDialogComponent } from '../Analyses/add-analyse-dialog/add-analyse-dialog.component';
import { TestsComponent } from '../tests/tests/tests.component';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { AjouterTestDialogComponent } from '../tests/ajouter-test-dialog/ajouter-test-dialog.component';
import { AjouterEpreuveDialogComponent } from '../epreuves/ajouter-epreuve-dialog/ajouter-epreuve-dialog.component';



@NgModule({
  declarations: [
    AppComponent,
    ConfirmDialogComponent,
    LaboratoireEditComponent ,
    LaboratoireListComponent,
    AjouterLaboratoireComponent,
    MenuComponent,
    AccueilComponent,
    ContactsDialogComponent,
    AjouterContactDialogComponent,
    ConfirmationDialogComponent,
    AnalysesDialogComponent,
    AddAnalyseDialogComponent,
    TestsComponent,
    AjouterTestDialogComponent,
    AjouterEpreuveDialogComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ToastrModule.forRoot({
      positionClass: 'toast-top-center',
      timeOut: 3000,
      progressBar: true,
      progressAnimation: 'increasing'
    }),
    MatInputModule,
    MatCardModule ,
    MatDialogModule,
    MatButtonModule,
    BrowserAnimationsModule, // Obligatoire pour Angular Material
    FormsModule, // Pour [(ngModel)]
    ReactiveFormsModule ,// Pour les formulaires réactifs
    CommonModule,
    RouterModule, 
    MatFormFieldModule,
    MatRadioModule,
    MatCheckboxModule,
    MatSelectModule,
    MatTableModule
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],  // Facultatif si vous utilisez des éléments personnalisés
  providers: [
    provideAnimationsAsync(),
    provideHttpClient(withInterceptorsFromDi())
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}