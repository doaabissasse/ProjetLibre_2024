import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ServiceLaboratoireService } from '../../Laboratoire/service_labo/service-laboratoire.service';
import { Laboratoire } from '../../Laboratoire/Entite_labo/laboratoire'; 
import { MatDialog } from '@angular/material/dialog';
import { AjoutAnalysesComponent } from '../../Analyses/ajout-analyses/ajout-analyses.component'
import { ConfirmationDialogComponent } from '../../Adresses/confirmation-dialog/confirmation-dialog.component';
import { AnalyseService } from '../services/analyse.service';


@Component({
  selector: 'app-analyses-dialog',
  standalone: false,
  
  templateUrl: './analyses-dialog.component.html',
  styleUrl: './analyses-dialog.component.css'
})
export class AnalysesDialogComponent  implements OnInit {
  laboratoireId: number = 0;  // Initialiser avec une valeur par défaut
  laboratoire: Laboratoire | undefined;
  analyses: any[] = [];
  adresseExistante: number | undefined; // ID de l'adresse existante
  adressesExistantes: any[] = []; // Liste des adresses existantes
  adresseOption: string = 'nouvelleAdresse'; // Option sélectionnée par défaut

  constructor(
    private route: ActivatedRoute,
    private laboratoireService: ServiceLaboratoireService,
    private router: Router , // Injecter Router pour la navigation
    private dialog: MatDialog,
    private analyseService: AnalyseService,
  ) {}

  ngOnInit(): void {
    this.laboratoireId = +this.route.snapshot.paramMap.get('id')!; // Récupérer l'ID depuis l'URL
    console.log('Laboratoire ID:', this.laboratoireId); // Vérifiez si l'ID est bien récupéré
    this.getLaboratoireDetails();  // Récupérer les détails du laboratoire
    this.getAnalyses();  // Récupérer les contacts associés
  }

  private getLaboratoireDetails() {
    this.laboratoireService.getLaboratoireById(this.laboratoireId).subscribe(
      data => {
        this.laboratoire = data;
        console.log('Laboratoire details:', this.laboratoire); // Vérifiez les détails du laboratoire
      },
      error => {
        console.error('Error fetching laboratoire details:', error);
      }
    );
  }

  private getAnalyses() {
    this.laboratoireService.getAnalysesByLaboratoire(this.laboratoireId).subscribe(
      data => {
        this.analyses = data;
        console.log('analyses récupérés:', this.analyses);  // Vérifiez si chaque contact a un ID
      },
      error => {
        console.error('Erreur lors de la récupération des analyses:', error);
      }
    );
  }

   // Méthode pour revenir à la liste des laboratoires
   retourLaboratoires() {
    this.router.navigate(['/laboratoires']);  // Naviguer vers la liste des laboratoires
  }

  ouvrirDialogueAjoutAnalyses() {
    const dialogRef = this.dialog.open(AjoutAnalysesComponent, {
      width: '500px',
      data: { idLaboratoire: this.laboratoire?.id }
    });
  
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.getAnalyses(); // Recharge la liste après ajout
      }
    });
  }
  

}
