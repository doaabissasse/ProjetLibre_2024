import { Component, OnInit } from '@angular/core';
import { Laboratoire } from '../Entite_labo/laboratoire';
import { CommonModule } from '@angular/common';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent } from '../confirm-dialog/confirm-dialog.component';
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

  constructor(
    private laboratoireService: ServiceLaboratoireService,
    private dialog: MatDialog
  ) {}

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

  modifier(laboratoire: Laboratoire): void {
    console.log('Modifier', laboratoire);
    // Ajoutez votre logique pour ouvrir un formulaire de modification ou autre
  }

  supprimer(laboratoire: Laboratoire): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      data: { nom: laboratoire.nom }, // Transmettre le nom du laboratoire
      width: '400px', // Largeur de la fenêtre modale
      height: '250px', // Hauteur de la fenêtre modale
      position: { top: '20px' }, // Optionnel: positionnement personnalisé
      disableClose: true, // Désactive la fermeture en cliquant en dehors de la fenêtre
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        // Appeler la méthode pour supprimer le laboratoire après confirmation
        this.laboratoireService.supprimerLaboratoire(laboratoire.id).subscribe({
          next: () => {
            console.log(`Laboratoire supprimé : ${laboratoire.nom}`);
            // Mettre à jour la liste après suppression
            this.laboratoires = this.laboratoires.filter(l => l.id !== laboratoire.id);
          },
          error: (err) => console.error('Erreur lors de la suppression du laboratoire :', err),
        });
      } else {
        console.log('Suppression annulée');
      }
    });
  }
}
