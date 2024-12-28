import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { AnalyseService } from '../services/analyse.service';
import { Analyse } from '../analyse.model';

@Component({
  selector: 'app-ajout-analyses',
  standalone: false,
  templateUrl: './ajout-analyses.component.html',
  styleUrls: ['./ajout-analyses.component.css'], // Fix styleUrl -> styleUrls
})
export class AjoutAnalysesComponent {
  analyse: Analyse = { id: 0, fkIdLaboratoire: 0, nom: '', description: '' };

  constructor(
    private analyseService: AnalyseService,
    public dialogRef: MatDialogRef<AjoutAnalysesComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    if (data && data.idLaboratoire) {
      this.analyse.fkIdLaboratoire = data.idLaboratoire;
    }
  }

  ajouterAnalyse() {
    if (this.analyse.nom && this.analyse.description) {
      console.log('Données envoyées :', this.analyse);
  
      this.analyseService.ajouterAnalyse(this.analyse).subscribe(
        (result) => {
          console.log('Analyse ajoutée avec succès :', result);
          this.dialogRef.close(result);
        },
        (error) => {
          console.error('Erreur lors de l\'ajout de l\'analyse :', error);
          console.error('Statut HTTP :', error.status);
          console.error('Message d\'erreur :', error.message);
          console.error('Erreur complète :', error);
  
          // Affiche un message d'erreur à l'utilisateur
          alert(
            'Une erreur s\'est produite lors de l\'ajout de l\'analyse. ' +
              'Veuillez vérifier vos données ou réessayer plus tard.'
          );
        }
      );
    } else {
      alert('Veuillez remplir tous les champs.');
    }
  }
  

  closeDialog() {
    this.dialogRef.close();
  }
}
