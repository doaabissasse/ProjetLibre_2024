import { Component, OnInit } from '@angular/core';
import { Laboratoire } from '../Entite_labo/laboratoire';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ServiceLaboratoireService } from '../service_labo/service-laboratoire.service';
import { ConfirmDialogComponent } from '../confirm-dialog/confirm-dialog.component';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { LaboratoireEditComponent } from '../laboratoire-edit/laboratoire-edit.component';
import { ContactsDialogComponent } from '../../Contactes/contacts-dialog/contacts-dialog.component';



@Component({
  selector: 'app-laboratoire-list',
  standalone: false,
  templateUrl: './laboratoire-list.component.html',
  styleUrls: ['./laboratoire-list.component.css'],
  
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

  private getLaboratoires() {
    this.laboratoireService.getLaboratoiresList().subscribe({
      next: (data) => {
        console.log('Laboratoires récupérés :', data);  // Vérifiez si les données sont bien récupérées
        if (Array.isArray(data)) {
          this.laboratoires = data;
          this.filteredLaboratoires = [...data];
        } else {
          console.error('Les données ne sont pas un tableau :', data);
        }
      },
      error: (error) => {
        console.error('Erreur lors de la récupération des laboratoires :', error);
      }
    });
  }
  onSearchChange(searchValue: string): void {
    const trimmedValue = searchValue.trim(); // Évitez les espaces superflus
    if (!trimmedValue) {
      this.filteredLaboratoires = [...this.laboratoires]; // Réinitialisez si vide
    } else {
      this.filteredLaboratoires = this.laboratoires.filter(laboratoire =>
        laboratoire.nom.toLowerCase().includes(trimmedValue.toLowerCase())
      );
    }
  }

  modifier(laboratoire: Laboratoire): void {
    const dialogRef = this.dialog.open(LaboratoireEditComponent, {
      width: '800px', // Augmente la largeur de la boîte de dialogue
      height: '490px', // (Optionnel) Définit une hauteur pour la boîte de dialogue
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
            // Supprimez le laboratoire des deux listes : laboratoires et filteredLaboratoires
            this.laboratoires = this.laboratoires.filter(l => l.id !== laboratoire.id);
            this.filteredLaboratoires = this.filteredLaboratoires.filter(l => l.id !== laboratoire.id);
            
            // Affichez une notification de succès
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
  
  afficherContacts(laboratoireId: number): void {
    this.router.navigate(['/contacts-laboratoire', laboratoireId]); // Route vers la page des contacts
  }

  afficherAnalyses(laboratoireId: number): void {
    this.router.navigate(['/analyses-laboratoire', laboratoireId]); // Route vers la page des contacts
  }
  
}
