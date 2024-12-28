import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ServiceLaboratoireService } from '../../Laboratoire/service_labo/service-laboratoire.service';
import { Laboratoire } from '../../Laboratoire/Entite_labo/laboratoire'; 
import { MatDialog } from '@angular/material/dialog';
import { ConfirmationDialogComponent } from '../../Adresses/confirmation-dialog/confirmation-dialog.component';
import { AnalyseService } from '../services/analyse.service';
import { AddAnalyseDialogComponent } from '../add-analyse-dialog/add-analyse-dialog.component';

@Component({
  selector: 'app-analyses-dialog',
  standalone: false,
  templateUrl: './analyses-dialog.component.html',
  styleUrls: ['./analyses-dialog.component.css']
})
export class AnalysesDialogComponent implements OnInit {
  laboratoireId: number = 0;
  laboratoire: Laboratoire | undefined;
  analyses: any[] = [];

  constructor(
    private route: ActivatedRoute,
    private laboratoireService: ServiceLaboratoireService,
    private router: Router,
    private dialog: MatDialog,
    private analyseService: AnalyseService,
  ) {}

  ngOnInit(): void {
    this.laboratoireId = +this.route.snapshot.paramMap.get('id')!;
    console.log('laboratoireId récupéré:', this.laboratoireId);  // Log du laboratoireId
    this.getLaboratoireDetails();
    this.getAnalyses();
  }

  private getLaboratoireDetails() {
    this.laboratoireService.getLaboratoireById(this.laboratoireId).subscribe(
      data => {
        this.laboratoire = data;
        console.log('Détails du laboratoire récupérés:', this.laboratoire);  // Log des détails du laboratoire
      },
      error => {
        console.error('Erreur lors de la récupération des détails du laboratoire:', error);
      }
    );
  }

  private getAnalyses() {
    this.laboratoireService.getAnalysesByLaboratoire(this.laboratoireId).subscribe(
      data => {
        this.analyses = data;
        console.log('Analyses récupérées:', this.analyses);  // Log des analyses récupérées
      },
      error => {
        console.error('Erreur lors de la récupération des analyses:', error);
      }
    );
  }

  // Méthode pour ouvrir le dialogue "Ajouter une analyse"
  openAddAnalyseDialog(): void {
    console.log('Ouverture du dialogue avec laboratoireId:', this.laboratoireId);  // Log avant ouverture du dialogue
    const dialogRef = this.dialog.open(AddAnalyseDialogComponent, {
      width: '400px',
      data: { laboratoireId: this.laboratoireId }  // Assurez-vous que laboratoireId est bien transmis
    });
  
    dialogRef.afterClosed().subscribe(result => {
      if (result) { 
        console.log('Le dialogue a été fermé avec succès, recharge des analyses');
        this.getAnalyses();  // Rechargez les analyses après l'ajout d'une nouvelle
      } else {
        console.log('Le dialogue a été fermé sans modifications');
      }
    });
  }

  // Méthode pour revenir à la liste des laboratoires
  retourLaboratoires() {
    this.router.navigate(['/laboratoires']);
  }

  // Méthode pour supprimer une analyse
  supprimerAnalyse(id: number): void {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      width: '400px',
      data: { message: 'Êtes-vous sûr de vouloir supprimer cette analyse ?' }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        console.log('Suppression de l\'analyse avec id:', id);  // Log avant suppression
        this.analyseService.supprimerAnalyse(id).subscribe(() => {
          console.log('Analyse supprimée avec succès');
          this.getAnalyses();  // Rechargez les analyses après la suppression
        }, error => {
          console.error('Erreur lors de la suppression de l\'analyse:', error);  // Log d'erreur
        });
      } else {
        console.log('Suppression annulée');
      }
    });
  }
}
