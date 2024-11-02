import { Component, OnInit } from '@angular/core';
import { Laboratoire } from '../Entite_labo/laboratoire';
import { CommonModule } from '@angular/common';
import { ServiceLaboratoireService } from '../service_labo/service-laboratoire.service';

@Component({
  selector: 'app-laboratoire-list',
  standalone: true,
  templateUrl: './laboratoire-list.component.html',
  styleUrls: ['./laboratoire-list.component.css'],
  imports: [CommonModule] // Conservez uniquement CommonModule ici
})
export class LaboratoireListComponent implements OnInit {
  laboratoires: Laboratoire[] = [];

  constructor(private laboratoireService: ServiceLaboratoireService) {}

  ngOnInit(): void {
    this.getLaboratoires();
  }

  private getLaboratoires() {
    this.laboratoireService.getLaboratoiresList().subscribe({
      next: (data) => {
        this.laboratoires = data;
      },
      error: (error) => {
        console.error('Erreur lors de la récupération des laboratoires :', error);
      }
    });
  }

  // Méthode pour modifier un laboratoire
  modifier(laboratoire: Laboratoire): void {
    // Logique pour modifier le laboratoire (par exemple, ouvrir un formulaire de modification)
    console.log('Modifier', laboratoire);
    // Vous pouvez ajouter votre logique ici pour naviguer vers un formulaire ou afficher un modal
  }

  // Méthode pour supprimer un laboratoire
  supprimer(laboratoire: Laboratoire): void {
    // Logique pour supprimer le laboratoire
    console.log('Supprimer', laboratoire);
    // Vous pouvez ajouter la logique pour appeler un service qui supprimera le laboratoire
  }
  
}
