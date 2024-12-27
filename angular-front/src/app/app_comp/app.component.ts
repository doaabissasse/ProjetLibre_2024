import { Component } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { LaboratoireListComponent } from '../Laboratoire/laboratoire-list/laboratoire-list.component';
import { AjouterLaboratoireComponent } from "../Laboratoire/ajouter-laboratoire/ajouter-laboratoire.component";
import { RouterModule } from '@angular/router';
import { MenuComponent } from '../menu/menu.component';

@Component({
  selector: 'app-root',
  standalone: false,
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'angular-front';
  
}