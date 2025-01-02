import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { LaboratoireListComponent } from "../Laboratoire/laboratoire-list/laboratoire-list.component";
import { AjouterLaboratoireComponent } from "../Laboratoire/ajouter-laboratoire/ajouter-laboratoire.component";
import { AccueilComponent } from '../accueil/accueil.component';
import { ContactsDialogComponent } from '../Contactes/contacts-dialog/contacts-dialog.component';
import { AnalysesDialogComponent } from '../Analyses/analyses-dialog/analyses-dialog.component';
import {AccueilLaboratoireComponent} from '../Acceuil General/accueil-laboratoire/accueil-laboratoire.component';
import {LoginModalComponent} from '../Acceuil General/login-modal/login-modal.component';
import {AdminDashboardComponent} from '../Espace Admin/admin-dashboard/admin-dashboard.component';
import {MesUtilisateursComponent} from '../Espace Admin/mes-utilisateurs/mes-utilisateurs.component';
import {ModifierUtilisateurComponent} from '../Espace Admin/modifier-utilisateur/modifier-utilisateur.component';
import {AddUserComponent} from '../Espace Admin/add-user/add-user.component';
import {MotPassModalComponent} from '../Acceuil General/mot-pass-modal/mot-pass-modal.component';
import {UserProfileComponent} from '../Espace Admin/user-profile/user-profile.component';
import {UserDashboardComponent} from '../EspaceUser/user-dashboard/user-dashboard.component';
import {TestsComponent} from '../tests/tests/tests.component';
import {UserListComponent} from '../Laboratoire/user-list/user-list.component';
import {DossiersListComponent} from '../Laboratoire/dossiers-users-list/dossiers-users-list.component';
import {DossierDetailsComponent} from '../Laboratoire/dossier-details/dossier-details.component';
import {ProfileComponent} from '../EspaceUser/profile/user-profile.component';
import { ContacteComponent } from '../EspaceUser/contacte/contacte.component';
import {AnalyseComponent  } from '../EspaceUser/analyse/analyse.component';
import {TestEpreuvesComponent } from '../EspaceUser/test-epreuves/test-epreuves.component';
import {DossierComponent } from '../EspaceUser/dossier/dossier.component';


const routes: Routes = [
    { path: 'accueil', component: AccueilComponent },
  { path: 'accueil-laboratoire', component: AccueilLaboratoireComponent },
    { path: 'laboratoires', component: LaboratoireListComponent },
    { path: 'ajouter-laboratoire', component: AjouterLaboratoireComponent },
    { path: 'contacts-laboratoire/:id', component: ContactsDialogComponent },
    { path: 'analyses-laboratoire/:id', component: AnalysesDialogComponent },
    { path: 'tests-analyse/:id', component: TestsComponent },
  { path: 'utilisateurs/:laboratoireId', component: UserListComponent },
  { path: 'login', component: LoginModalComponent },
  { path: 'admin-dashboard', component: AdminDashboardComponent },
  { path: 'user-profile', component: UserProfileComponent },
  { path: 'mdpoublie', component: MotPassModalComponent },
  { path: 'MesUtilisateurs', component: MesUtilisateursComponent },
  { path: 'modifier-utilisateur/:id', component: ModifierUtilisateurComponent },
  { path: 'add-user', component: AddUserComponent },
  { path: 'user-dashboard', component: UserDashboardComponent },
  { path: 'dossiers/:userId', component: DossiersListComponent },
  { path: 'dossier-details/:id/:idUser/:idPatient', component: DossierDetailsComponent },
  { path: 'contactes/:id', component: ContacteComponent },
  { path: 'analyse/:id', component: AnalyseComponent },
  { path: 'test-epreuve/:id', component: TestEpreuvesComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'dossier/:id', component: DossierComponent },

  { path: '', redirectTo: 'accueil-laboratoire', pathMatch: 'full' },  // Redirection par défaut
    { path: '**', redirectTo: 'accueil-laboratoire', pathMatch: 'full' }, // Wildcard route pour chemins non trouvés

];
@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {}
