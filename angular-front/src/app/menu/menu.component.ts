import { Component } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { LaboratoireListComponent } from '../Laboratoire/laboratoire-list/laboratoire-list.component';
import { AjouterLaboratoireComponent } from "../Laboratoire/ajouter-laboratoire/ajouter-laboratoire.component";
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-menu',
  standalone: false,
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent {

}
