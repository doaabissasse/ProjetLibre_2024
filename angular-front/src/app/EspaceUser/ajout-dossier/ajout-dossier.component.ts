import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { DossierService } from '../../Laboratoire/services/dossier.service';
import { Dossier } from '../../Laboratoire/Entite_labo/dossier.model';

@Component({
  selector: 'app-ajout-dossier',
  standalone: false,
  templateUrl: './ajout-dossier.component.html',
  styleUrl: './ajout-dossier.component.css'
})
export class AjoutDossierComponent {
  dossier: Dossier = {
    id: 0,
    idPatient: 0,
    idUtilisateur: 0,
    date: new Date().toISOString().split('T')[0],// Format ISO (YYYY-MM-DD)
  };

  constructor(
    public dialogRef: MatDialogRef<AjoutDossierComponent>,
    private dossierService: DossierService,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    // Récupérer l'ID de l'utilisateur et l'assigner à l'objet dossier
    this.dossier.idUtilisateur = data.userId;
  }

  

  addDossier(): void {
    console.log('Tentative d\'ajout de dossier:', this.dossier);  // Log avant d'ajouter l'analyse
    this.dossierService.addDossier(this.dossier).subscribe(() => {
      console.log('dossier ajoutée avec succès');  // Log de succès
      this.dialogRef.close(true);  // Fermer le dialogue et renvoyer le succès
    }, error => {
      console.error('Erreur lors de l\'ajout de dossier :', error);  // Log d'erreur
    });
  }
  

  onCancel(): void {
    this.dialogRef.close(false); // Fermer sans enregistrer
  }
}
