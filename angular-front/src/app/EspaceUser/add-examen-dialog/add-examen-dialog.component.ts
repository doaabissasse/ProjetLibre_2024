import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {ExamenService} from '../examen/examen.service';
import {EpreuveService} from '../../epreuves/epreuve.service';
import {Examen} from '../examen/examen.model';
import {TestService} from '../../tests/test.service';

@Component({
  selector: 'app-add-examen-dialog',
  standalone: false,

  templateUrl: './add-examen-dialog.component.html',
  styleUrl: './add-examen-dialog.component.css'
})
export class AddExamenDialogComponent implements OnInit{

  examen: Examen = { id: undefined, idDossier: 0, idEpreuve: 0, idTestAnalyse: 0, resultat: '' };
  epreuves: any[] = [];
  testsAnalyse: any[] = [];

  constructor(
    public dialogRef: MatDialogRef<AddExamenDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private examenService: ExamenService,
    private epreuveService: EpreuveService,
    private testAnalyseService: TestService,
  ) {}

  ngOnInit(): void {

    this.examen.idDossier = this.data.dossierId;
    // Charger les épreuves
    this.epreuveService.getEpreuves().subscribe(data => (this.epreuves = data));

    // Charger les tests d'analyse
    this.testAnalyseService.getTest().subscribe(data => (this.testsAnalyse = data));
    // Implémente une méthode similaire pour les tests analyse si nécessaire
  }

  saveExamen(): void {
    this.examenService.createExamen(this.examen).subscribe(result => {
      this.dialogRef.close(result);
    });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
}
