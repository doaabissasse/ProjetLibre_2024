import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ServiceLaboratoireService } from '../../Laboratoire/service_labo/service-laboratoire.service';
import { Laboratoire } from '../../Laboratoire/Entite_labo/laboratoire'; 
import { MatDialog } from '@angular/material/dialog';
import { ConfirmationDialogComponent } from '../../Adresses/confirmation-dialog/confirmation-dialog.component';
import { AnalyseService } from '../../Analyses/services/analyse.service';
import { AddAnalyseDialogComponent } from '../../Analyses/add-analyse-dialog/add-analyse-dialog.component';

@Component({
  selector: 'app-analyse',
  standalone: false,
  
  templateUrl: './analyse.component.html',
  styleUrl: './analyse.component.css'
})
export class AnalyseComponent implements OnInit{
laboratoireId: number = 0;
  laboratoire: Laboratoire | undefined;
  analyses: Array<any> = [];

  constructor(
    private route: ActivatedRoute,
    private laboratoireService: ServiceLaboratoireService,
    private router: Router,
    private dialog: MatDialog,
    private analyseService: AnalyseService,
  ) {}

  ngOnInit(): void {
    // Récupérer l'ID du laboratoire dans l'URL
    this.route.paramMap.subscribe((params) => {
      this.laboratoireId = +params.get('id')!;  // Conversion de l'ID en nombre
      if (this.laboratoireId) {
        this.getAnalyses();
      } else {
        console.error('ID de laboratoire invalide.');
      }
    });
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


  // Méthode pour revenir à la liste des laboratoires
  retourLaboratoires() {
    this.router.navigate(['/laboratoires']);
  }

 

  visualiserTests(analyseId: number): void {
    this.router.navigate(['/test-epreuve', analyseId]); // Route vers la page des contacts
  }

}

