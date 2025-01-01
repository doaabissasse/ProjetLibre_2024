import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TestService } from '../test.service';

@Component({
  selector: 'app-ajouter-test-dialog',
  standalone: false,
  templateUrl: './ajouter-test-dialog.component.html',
  styleUrls: ['./ajouter-test-dialog.component.css']
})
export class AjouterTestDialogComponent {
  testForm: FormGroup;
  analyseId: number;  // Variable pour stocker l'ID de l'analyse

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<AjouterTestDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,  // Données injectées
    private testService: TestService
  ) {
    // Initialiser le formulaire de test
    this.testForm = this.fb.group({
      nomTest: ['', [Validators.required]],
      sousEpreuve: ['', [Validators.required]],
      intervalMinDeReference: ['', [Validators.required, Validators.min(0)]],
      intervalMaxDeReference: ['', [Validators.required, Validators.min(0)]],
      uniteDeReference: ['', [Validators.required]],
      details: ['', [Validators.required]] // Ajout du champ "details"
    });
    

    console.log('Données reçues dans le dialogue:', this.data);  // Affichage des données injectées
    this.analyseId = this.data.analyseId;  // Récupérer l'ID de l'analyse passé dans les données
    console.log('analyseId:', this.analyseId);  // Affichage de l'ID de l'analyse
  }

  // Méthode pour ajouter le test
  addTest(): void {
    if (this.testForm.valid) {
      const testData = {
        ...this.testForm.value,
        idAnalyse: this.analyseId
      };
  
      console.log('Tentative d\'ajout du test:', testData); // Log pour vérifier les données
  
      this.testService.ajouterTestAnalyse(testData).subscribe(
        response => {
          console.log('Test ajouté avec succès:', response);
          this.dialogRef.close(true);
        },
        error => {
          console.error('Erreur lors de l\'ajout du test:', error);
        }
      );
    }
  }
  

  // Méthode pour annuler le dialogue
  onCancel(): void {
    this.dialogRef.close(false);  // Fermer le dialogue sans changer quoi que ce soit
  }
}
