import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { DossierService } from '../../Laboratoire/services/dossier.service';
import { PatientService } from '../../Laboratoire/services/patient.service'; // Import du service PatientService
import { Dossier } from '../../Laboratoire/Entite_labo/dossier.model';
import { Patient } from '../../Laboratoire/Entite_labo/Patient.model';
import {AjoutDossierComponent} from '../../EspaceUser/ajout-dossier/ajout-dossier.component';
import { ConfirmationDialogComponent } from '../../Adresses/confirmation-dialog/confirmation-dialog.component';
import {AddExamenDialogComponent} from '../add-examen-dialog/add-examen-dialog.component';

@Component({
  selector: 'app-dossier',
  standalone: false,
  templateUrl: './dossier.component.html',
  styleUrls: ['./dossier.component.css'], // Correction du nom
})
export class DossierComponent implements OnInit {
  dossiers: any[] = []; // Stocker les dossiers récupérés
  patients: { [key: number]: Patient } = {}; // Un objet pour stocker les patients par ID
  user: any; // Contiendra les informations de l'utilisateur

  constructor(
    private route: ActivatedRoute,
    private dossierService: DossierService,
    private dialog: MatDialog,
    private patientService: PatientService,// Injection du service PatientService
    private router: Router
  ) {}

  ngOnInit(): void {
    // Récupérer les informations de l'utilisateur depuis le localStorage
    const userData = localStorage.getItem('user');
    if (userData) {
      this.user = JSON.parse(userData);
      console.log('Utilisateur récupéré :', this.user);

      // Vérifier si l'utilisateur a un ID valide
      if (this.user?.id) {
        this.getDossiers(); // Charger les dossiers pour cet utilisateur
      } else {
        console.error("L'utilisateur n'a pas d'ID valide.");
      }
    } else {
      console.error("Aucune donnée utilisateur trouvée dans le localStorage.");
    }
  }

  openAddExamenDialog(dossierId: number): void {
    const dialogRef = this.dialog.open(AddExamenDialogComponent, {
      width: '400px',
      data: { dossierId },// Passe l'ID du dossier à la boîte de dialogue
    });
  }

  // Méthode pour récupérer les dossiers
  getDossiers(): void {
    this.dossierService.getDossiersByUserId(this.user.id).subscribe({
      next: (data) => {
        this.dossiers = data;
        console.log('Dossiers récupérés :', this.dossiers);

        // Récupérer les informations des patients associés à chaque dossier
        this.dossiers.forEach((dossier) => {
          this.getPatientInfo(dossier.idPatient);
        });
      },
      error: (err) => console.error('Erreur lors de la récupération des dossiers :', err),
    });
  }

  // Méthode pour récupérer les informations du patient par ID
  getPatientInfo(patientId: number): void {
    this.patientService.getPatientById(patientId).subscribe({
      next: (patient) => {
        this.patients[patientId] = patient; // Stocker le patient par ID
      },
      error: (err) => console.error('Erreur lors de la récupération des informations du patient :', err),
    });
  }

  openAddDossierDialog(): void {
    const dialogRef = this.dialog.open(AjoutDossierComponent, {
      data: { userId: this.user.id }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.getDossiers(); // Recharger les dossiers après l'ajout
      }
    });
  }


 // Méthode de suppression avec confirmation
 deleteDossier(dossierId: number): void {
  const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
    data: {
      message: 'Êtes-vous sûr de vouloir supprimer ce dossier ?'
    }
  });



  dialogRef.afterClosed().subscribe(result => {
    if (result) {
      // L'utilisateur a confirmé, on supprime le dossier
      this.dossierService.deleteDossier(dossierId).subscribe({
        next: () => {
          console.log('Dossier supprimé avec succès');
          this.dossiers = this.dossiers.filter(dossier => dossier.id !== dossierId);
        },
        error: (err) => {
          console.error('Erreur lors de la suppression du dossier :', err);
          alert('Une erreur est survenue lors de la suppression du dossier.');
        }
      });
    }
  });
}


  viewDossier(id: number | undefined, idUser: number, idPatient: number): void {
    this.router.navigate(['/dossier-details', id, idUser, idPatient]);
  }
}

