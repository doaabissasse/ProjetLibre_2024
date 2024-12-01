import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';

// Composants
import { AppComponent } from '../app_comp/app.component';
import { LaboratoireListComponent } from '../Laboratoire/laboratoire-list/laboratoire-list.component';
import { AjouterLaboratoireComponent } from '../Laboratoire/ajouter-laboratoire/ajouter-laboratoire.component';
import { AccueilComponent } from '../accueil/accueil.component';
import { MenuComponent } from '../menu/menu.component';
import { ConfirmDialogComponent } from '../Laboratoire/confirm-dialog/confirm-dialog.component';

@NgModule({
  declarations: [  // Composants déclarés ici
    ConfirmDialogComponent
  ],
  imports: [  // Modules Angular importés ici
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    MatDialogModule,
    AppComponent,
    LaboratoireListComponent,
    AccueilComponent,
    MenuComponent,
    AjouterLaboratoireComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],  // Facultatif si vous utilisez des éléments personnalisés
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
