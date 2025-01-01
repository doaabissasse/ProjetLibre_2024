import { Component, Inject,OnInit } from '@angular/core';
import { TestAnalyse } from '../TestAnalyse';
import { ActivatedRoute, Router } from '@angular/router';
import { AnalyseService } from '../../Analyses/services/analyse.service';
import { Analyse} from '../../Analyses/analyse.model';
import { MatDialog } from '@angular/material/dialog';
import { TestService } from '../test.service';
import { AjouterTestDialogComponent} from '../ajouter-test-dialog/ajouter-test-dialog.component';
import { ConfirmationDialogComponent } from '../../Adresses/confirmation-dialog/confirmation-dialog.component';
import { Epreuve } from '../../epreuves/Epreuve';
import { EpreuveService } from '../../epreuves/epreuve.service';
import { AjouterEpreuveDialogComponent } from '../../epreuves/ajouter-epreuve-dialog/ajouter-epreuve-dialog.component';


@Component({
  selector: 'app-tests',
  standalone: false,
  
  templateUrl: './tests.component.html',
  styleUrl: './tests.component.css'
})
export class TestsComponent implements OnInit {
  analyseId: number = 0; 
  analyse: Analyse | undefined;
  epreuves: Epreuve[] = [];
  tests: TestAnalyse[] = [];
  displayedColumns: string[] = ['nomTest', 'sousEpreuve', 'intervalle', 'details', 'actions'];
  displayedEpreuveColumns: string[] = ['nom', 'actions'];


  constructor(
    private dialog: MatDialog,
    private testAnalyseService: TestService,
    private EpreuveService :EpreuveService,
    private route: ActivatedRoute,
    private router: Router,
    private analyseService: AnalyseService
  ) {}

  ngOnInit(): void {
    this.analyseId = +this.route.snapshot.paramMap.get('id')!;
    console.log('analyseId récupéré:', this.analyseId);
    
    this.gettest();
    this.getanalyseDetails();
    this.getEpreuves();
  }

  private getanalyseDetails() {
    this.analyseService.getanalyseById(this.analyseId).subscribe(
      data => {
        this.analyse = data;
        console.log('Détails du analyse récupérés:', this.analyse);  // Log des détails du laboratoire
      },
      error => {
        console.error('Erreur lors de la récupération des détails du analysee:', error);
      }
    );
  }

  gettest(): void {
    this.analyseService.visualiserTests(this.analyseId).subscribe(
      (tests: TestAnalyse[]) => {
        this.tests = tests;
        console.log('Tests récupérés:', this.tests);
      },
      (error) => {
        console.error('Erreur lors de la récupération des tests:', error);
      }
    );
  }

   // Méthode pour revenir à la liste des laboratoires
  retourAnalyses() {
    const laboratoireId = this.route.snapshot.paramMap.get('id'); // Récupérer l'ID de l'URL actuelle
    if (laboratoireId) {
      this.router.navigate([`/analyses-laboratoire/${laboratoireId}`]);
    } else {
      console.error('ID du laboratoire introuvable pour la navigation.');
    }
  }


  ajouterTest(): void {
    const dialogRef = this.dialog.open(AjouterTestDialogComponent, {
      width: '700px',
      data: { analyseId: this.analyseId }
    });
  
    dialogRef.afterClosed().subscribe(result => {
      if (result) { 
        console.log('Le dialogue a été fermé avec succès, recharge des analyses');
        this.gettest();  // Rechargez les analyses après l'ajout d'une nouvelle
      } else {
        console.log('Le dialogue a été fermé sans modifications');
      }
    });
  }

  ajouterEpreuve(): void {
    const dialogRef = this.dialog.open(AjouterEpreuveDialogComponent, {
      width: '700px',
      data: { analyseId: this.analyseId }
    });
  
    dialogRef.afterClosed().subscribe(result => {
      if (result) { 
        console.log('Le dialogue a été fermé avec succès, recharge des analyses');
        this.getEpreuves();  // Rechargez les analyses après l'ajout d'une nouvelle
      } else {
        console.log('Le dialogue a été fermé sans modifications');
      }
    });
  }


  supprimerTest(testId: number): void {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      width: '400px',
      data: { message: 'Êtes-vous sûr de vouloir supprimer ce test ?' }
    });
  
    dialogRef.afterClosed().subscribe((result: boolean) => {
      if (result) {
        this.testAnalyseService.supprimerTestAnalyse(testId).subscribe(
          () => {
            console.log(`Test avec ID ${testId} supprimé avec succès.`);
            this.gettest(); // Rafraîchir la liste des tests
          },
          (error) => {
            console.error('Erreur lors de la suppression du test :', error);
          }
        );
      }
    });
  }


  getEpreuves(): void {
    this.analyseService.visualiserEpeuves(this.analyseId).subscribe(
      (epreuves: Epreuve[]) => {
        this.epreuves = epreuves;
        console.log('Épreuves récupérées:', this.epreuves);
      },
      error => {
        console.error('Erreur lors de la récupération des épreuves:', error);
      }
    );
  }

  supprimerEpreuve(epreuveId: number): void {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      width: '400px',
      data: { message: 'Êtes-vous sûr de vouloir supprimer cette épreuve ?' }
    });

    dialogRef.afterClosed().subscribe((result: boolean) => {
      if (result) {
        this.EpreuveService.deleteEpreuve(epreuveId).subscribe(
          () => {
            console.log(`epreuve avec ID ${epreuveId} supprimé avec succès.`);
            this.getEpreuves(); // Rafraîchir la liste des tests
          },
          (error) => {
            console.error('Erreur lors de la suppression du epreuve :', error);
          }
        );
      }
    });
  }
}