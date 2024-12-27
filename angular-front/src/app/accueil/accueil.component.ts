import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-accueil',
  templateUrl: './accueil.component.html',
  standalone: false,
  styleUrl: './accueil.component.css'
})
export class AccueilComponent {
  constructor(private router: Router) {}

  goToDetails() {
    this.router.navigate(['/laboratoires']); // Navigation vers DétailsLaboratoire
  }
  goToforum() {
    this.router.navigate(['/ajouter-laboratoire']); // Navigation vers formulaire
  }

}
