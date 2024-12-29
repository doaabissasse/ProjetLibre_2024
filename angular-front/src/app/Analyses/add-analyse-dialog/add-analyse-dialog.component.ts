import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { AnalyseService } from '../services/analyse.service';

@Component({
  selector: 'app-add-analyse-dialog',
  standalone: false,
  templateUrl: './add-analyse-dialog.component.html',
  styleUrls: ['./add-analyse-dialog.component.css']
})
export class AddAnalyseDialogComponent {
  analyse: any;

  constructor(
    private analyseService: AnalyseService,
    public dialogRef: MatDialogRef<AddAnalyseDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    console.log('Données reçues dans le dialogue:', this.data);  // Afficher les données injectées
    this.analyse = { nom: '', description: '', fkIdLaboratoire: this.data.laboratoireId };
    console.log('Objet analyse initialisé:', this.analyse);  // Afficher l'objet analyse après initialisation
  }

  // Méthode pour ajouter l'analyse
  addAnalyse(): void {
    console.log('Tentative d\'ajout de l\'analyse:', this.analyse);  // Log avant d'ajouter l'analyse
    this.analyseService.addAnalyse(this.analyse).subscribe(() => {
      console.log('Analyse ajoutée avec succès');  // Log de succès
      this.dialogRef.close(true);  // Fermer le dialogue et renvoyer le succès
    }, error => {
      console.error('Erreur lors de l\'ajout de l\'analyse:', error);  // Log d'erreur
    });
  }

  // Méthode pour annuler le dialogue
  // Méthode pour annuler le dialogue
  onCancel(): void {
    this.dialogRef.close(false);  // Fermer le dialogue sans changement
  }
}
