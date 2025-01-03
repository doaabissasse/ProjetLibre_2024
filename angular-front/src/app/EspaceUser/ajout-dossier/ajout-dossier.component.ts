import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { DossierService } from '../../Laboratoire/services/dossier.service';
import { Dossier } from '../../Laboratoire/Entite_labo/dossier.model';
import {Patient} from '../../Laboratoire/Entite_labo/Patient.model';
import {PatientService} from '../../Laboratoire/services/patient.service';

@Component({
  selector: 'app-ajout-dossier',
  standalone: false,
  templateUrl: './ajout-dossier.component.html',
  styleUrl: './ajout-dossier.component.css'
})
export class AjoutDossierComponent {
  dossier: Dossier = {
    id: undefined,
    idPatient: 0,
    idUtilisateur: 0,
    date: new Date().toISOString().split('T')[0],// Format ISO (YYYY-MM-DD)
  };

  patient: Patient = {
    id: undefined,
    nom: '',
    prenom: '',
    dateNaissance: '',
    lieuDeNaissance: '',
    sexe: '',
    adresse: '',
    email: '',
    telephone: '',
    typePieceIdentite: '',
    numPieceIdentite: '',
    visiblePour: ''
  };

  constructor(
    public dialogRef: MatDialogRef<AjoutDossierComponent>,
    private dossierService: DossierService,
    private patientService: PatientService,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    // Récupérer l'ID de l'utilisateur et l'assigner à l'objet dossier
    this.dossier.idUtilisateur = data.userId;
  }



  addDossier(): void {
    console.log('Tentative d\'ajout de patient:', this.patient);  // Log avant d'ajouter le patient
    this.patientService.addPatient(this.patient).subscribe((createdPatient) => {
      if (createdPatient.id != null) {
        this.dossier.idPatient = createdPatient.id;
      } // Assigner l'ID du patient créé au dossier
      console.log('Patient ajouté avec succès:', createdPatient);  // Log de succès
      // Maintenant, ajoutez le dossier
      console.log('Tentative d\'ajout de dossier:', this.dossier);  // Log avant d'ajouter le dossier
      this.dossierService.addDossier(this.dossier).subscribe(() => {
        console.log('Dossier ajoutée avec succès');  // Log de succès
        this.dialogRef.close(true);  // Fermer le dialogue et renvoyer le succès
      }, error => {
        console.error('Erreur lors de l\'ajout de dossier :', error);  // Log d'erreur
      });
    }, error => {
      console.error('Erreur lors de l\'ajout de patient :', error);  // Log d'erreur
    });
  }


  onCancel(): void {
    this.dialogRef.close(false); // Fermer sans enregistrer
  }
}
