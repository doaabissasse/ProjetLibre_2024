import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { LaboratoireListComponent } from "../Laboratoire/laboratoire-list/laboratoire-list.component";
import { AjouterLaboratoireComponent } from "../Laboratoire/ajouter-laboratoire/ajouter-laboratoire.component";
import { AccueilComponent } from '../accueil/accueil.component';
import { ContactsDialogComponent } from '../Contactes/contacts-dialog/contacts-dialog.component';
import { AnalysesDialogComponent } from '../Analyses/analyses-dialog/analyses-dialog.component';
import { TestsComponent } from '../tests/tests/tests.component';

const routes: Routes = [
    { path: 'accueil', component: AccueilComponent },
    { path: 'laboratoires', component: LaboratoireListComponent },
    { path: 'ajouter-laboratoire', component: AjouterLaboratoireComponent },
    { path: 'contacts-laboratoire/:id', component: ContactsDialogComponent }, 
    { path: 'analyses-laboratoire/:id', component: AnalysesDialogComponent }, 
    { path: 'tests-analyse/:id', component: TestsComponent }, 
    { path: '', redirectTo: 'accueil', pathMatch: 'full' },  // Redirection par défaut
    { path: '**', redirectTo: 'accueil', pathMatch: 'full' }  // Wildcard route pour les chemins non trouvés
  ];
@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {}