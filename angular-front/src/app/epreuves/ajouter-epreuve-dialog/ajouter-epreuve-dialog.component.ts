import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EpreuveService } from '../epreuve.service';

@Component({
  selector: 'app-ajouter-epreuve-dialog',
  standalone: false,
  
  templateUrl: './ajouter-epreuve-dialog.component.html',
  styleUrl: './ajouter-epreuve-dialog.component.css'
})
export class AjouterEpreuveDialogComponent {
  epreuvForm: FormGroup;
  analyseId: number;  // Variable pour stocker l'ID de l'analyse

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<AjouterEpreuveDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,  // Données injectées
    private epreuveService: EpreuveService
  ) {
    // Initialiser le formulaire de test
    this.epreuvForm = this.fb.group({
      nom: ['', [Validators.required]],
    });
    
    console.log('Données reçues dans le dialogue:', this.data);  // Affichage des données injectées
    this.analyseId = this.data.analyseId;  // Récupérer l'ID de l'analyse passé dans les données
    console.log('analyseId:', this.analyseId);  // Affichage de l'ID de l'analyse
  }

  // Méthode pour ajouter le test
  addEpreuve(): void {
    if (this.epreuvForm.valid) {
      const epreuvData = {
        ...this.epreuvForm.value,
        idAnalyse: this.analyseId
      };
  
      console.log('Tentative d\'ajout du epreuve:', epreuvData); // Log pour vérifier les données
  
      this.epreuveService.addEpreuve(epreuvData).subscribe(
        response => {
          console.log('epreuve ajouté avec succès:', response);
          this.dialogRef.close(true);
        },
        error => {
          console.error('Erreur lors de l\'ajout du epreuve:', error);
        }
      );
    }
  }
  

  // Méthode pour annuler le dialogue
  onCancel(): void {
    this.dialogRef.close(false);  // Fermer le dialogue sans changer quoi que ce soit
  }
}