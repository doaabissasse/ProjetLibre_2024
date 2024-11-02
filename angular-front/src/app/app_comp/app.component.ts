import { Component } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { LaboratoireListComponent } from '../Laboratoire/laboratoire-list/laboratoire-list.component';
import { AjouterLaboratoireComponent } from "../Laboratoire/ajouter-laboratoire/ajouter-laboratoire.component";
import { RouterModule } from '@angular/router';
import { MenuComponent } from '../menu/menu.component';

@Component({
  standalone: true,
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  imports: [LaboratoireListComponent,
    HttpClientModule,RouterModule,AjouterLaboratoireComponent,MenuComponent
  ]
})
export class AppComponent {
  title = 'angular-front';
}
