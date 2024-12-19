import { Component, OnInit } from '@angular/core';
import { Laboratoire } from '../Entite_labo/laboratoire';
import { CommonModule } from '@angular/common';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ServiceLaboratoireService } from '../service_labo/service-laboratoire.service';
import { ConfirmDialogComponent } from '../confirm-dialog/confirm-dialog.component';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { FormsModule } from '@angular/forms';
import { LaboratoireEditComponent } from '../laboratoire-edit/laboratoire-edit.component';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';


@Component({
  selector: 'app-laboratoire-list',
  standalone: true,
  templateUrl: './laboratoire-list.component.html',
  styleUrls: ['./laboratoire-list.component.css'],
  imports: [CommonModule ,FormsModule,HttpClientModule,RouterModule] // Conservez uniquement CommonModule ici
})
export class LaboratoireListComponent implements OnInit {
  laboratoires: Laboratoire[] = [];
  filteredLaboratoires: Laboratoire[] = [];
  nomRecherche: string = '';

  constructor(
    private laboratoireService: ServiceLaboratoireService,
    private snackBar: MatSnackBar,
    private dialog: MatDialog,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getLaboratoires();
  }

  getLaboratoires(): void {
    this.laboratoireService.getLaboratoiresList().subscribe({
      next: (data) => {
        console.log('Laboratoires récupérés :', data);  // Ajoutez un log pour vérifier les données
        this.laboratoires = data;
        this.filteredLaboratoires = [...this.laboratoires]; // Copie initiale
      },
      error: (error) => {
        console.error('Erreur lors de la récupération des laboratoires :', error);
      }
    });
  }

  onSearchChange(searchValue: string): void {
    if (!searchValue.trim()) {
      // Si la recherche est vide, réinitialiser la liste filtrée
      this.filteredLaboratoires = [...this.laboratoires];
    } else {
      // Filtrer selon le nom
      this.filteredLaboratoires = this.laboratoires.filter(laboratoire =>
        laboratoire.nom.toLowerCase().includes(searchValue.toLowerCase())
      );
    }
  }

  modifier(laboratoire: Laboratoire): void {
    const dialogRef = this.dialog.open(LaboratoireEditComponent, {
      width: '500px',
      data: { id: laboratoire.id }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.getLaboratoires(); // Recharger la liste après modification
      }
    });
  }

  supprimer(laboratoire: Laboratoire): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '400px',
      data: { nom: laboratoire.nom }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === true) { // Vérifiez explicitement si le résultat est `true`
        this.laboratoireService.supprimerLaboratoire(laboratoire.id).subscribe({
          next: () => {
            this.laboratoires = this.laboratoires.filter(l => l.id !== laboratoire.id);
            this.snackBar.open(
              `Laboratoire "${laboratoire.nom}" supprimé avec succès.`,
              'Succès',
              {
                duration: 3000,
                horizontalPosition: 'center',
                verticalPosition: 'top'
              }
            );
          },
          error: (err) => {
            console.error('Erreur lors de la suppression du laboratoire :', err);
            this.snackBar.open(
              'Erreur lors de la suppression.',
              'Erreur',
              {
                duration: 3000,
                horizontalPosition: 'center',
                verticalPosition: 'top'
              }
            );
          }
        });
      } else {
        console.log('Suppression annulée par l\'utilisateur.');
      }
    });
  }


  rechercher(): void {
    if (this.nomRecherche.trim() === '') {
      this.getLaboratoires(); // Recharge la liste complète si le champ est vide
      return;
    }
  
    this.laboratoireService.rechercherLaboratoires(this.nomRecherche).subscribe({
      next: (data) => {
        this.laboratoires = data;
      },
      error: (error) => {
        console.error('Erreur lors de la recherche des laboratoires :', error);
      }
    });
  }
  
  
}
