import { Component, OnInit } from '@angular/core';
import { Patient } from '../Entite_labo/Patient.model';
import { Dossier } from '../Entite_labo/dossier.model';
import { User } from '../../Acceuil General/models/user';
import { UserService } from '../../Acceuil General/services/user.service';
import { DossierService } from '../services/dossier.service';
import { PatientService } from '../services/patient.service';
import { ActivatedRoute } from '@angular/router';
import jsPDF from 'jspdf';

@Component({
  selector: 'app-dossier-details',
  standalone: false,
  templateUrl: './dossier-details.component.html',
  styleUrls: ['./dossier-details.component.css']
})
export class DossierDetailsComponent implements OnInit {
  userId!: number;
  dossierId!: number;
  patientId!: number;

  user!: User;
  patient!: Patient;
  dossier!: Dossier;

  constructor(
    private userService: UserService,
    private dossierService: DossierService,
    private patientService: PatientService,
    private route: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.userId = Number(this.route.snapshot.paramMap.get('idUser'));
    this.dossierId = Number(this.route.snapshot.paramMap.get('id'));
    this.patientId = Number(this.route.snapshot.paramMap.get('idPatient'));

    // Charger les données depuis les services
    this.loadUser();
    this.loadPatient();
    this.loadDossier();
  }

  private loadUser(): void {
    this.userService.getUserById(this.userId).subscribe(
      (user) => {
        this.user = user;
        console.log('Utilisateur chargé:', this.user);
      },
      (error) => {
        console.error('Erreur lors du chargement de l\'utilisateur', error);
      }
    );
  }

  private loadPatient(): void {
    this.patientService.getPatientById(this.patientId).subscribe(
      (patient) => {
        this.patient = patient;
        console.log('Patient chargé:', this.patient);
      },
      (error) => {
        console.error('Erreur lors du chargement du patient', error);
      }
    );
  }

  private loadDossier(): void {
    this.dossierService.getDossierById(this.dossierId).subscribe(
      (dossier) => {
        this.dossier = dossier;
        console.log('Dossier chargé:', this.dossier);
      },
      (error) => {
        console.error('Erreur lors du chargement du dossier', error);
      }
    );
  }

  generatePDF(): void {
    const doc = new jsPDF();

    // Ajouter des détails de l'utilisateur
    doc.setFontSize(16);
    doc.text('Détails de l\'Utilisateur, Patient et Dossier', 10, 10);

    doc.setFontSize(12);
    if (this.user) {
      doc.text(`Nom Utilisateur: ${this.user.nomComplet}`, 10, 30);
      doc.text(`Email Utilisateur: ${this.user.email}`, 10, 40);
    }

    // Ajouter des détails du patient
    if (this.patient) {
      doc.text(`Nom Patient: ${this.patient.nom} ${this.patient.prenom}`, 10, 60);
      doc.text(`Date de Naissance: ${this.patient.dateNaissance}`, 10, 70);
      doc.text(`Adresse: ${this.patient.adresse}`, 10, 80);
    }

    // Ajouter des détails du dossier
    if (this.dossier) {
      doc.text('Dossier:', 10, 100);
      doc.text(`id: ${this.dossier.id}`, 10, 110);
      doc.text(`Date: ${this.dossier.date}`, 10, 120);
    }

    doc.save('details.pdf');
  }
}
