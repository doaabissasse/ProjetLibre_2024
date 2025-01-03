import { Component, OnInit } from '@angular/core';
import { Patient } from '../Entite_labo/Patient.model';
import { Dossier } from '../Entite_labo/dossier.model';
import { User } from '../../Acceuil General/models/user';
import { UserService } from '../../Acceuil General/services/user.service';
import { DossierService } from '../services/dossier.service';
import { PatientService } from '../services/patient.service';
import { ActivatedRoute } from '@angular/router';
import jsPDF from 'jspdf';
import {Examen} from '../../EspaceUser/examen/examen.model';
import {ExamenService} from '../../EspaceUser/examen/examen.service';
import {TestService} from '../../tests/test.service';
import {EpreuveService} from '../../epreuves/epreuve.service';
import {TestAnalyse} from '../../tests/TestAnalyse';
import {Epreuve} from '../../epreuves/Epreuve';

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
  examens: Examen[] = [];
  epreuves: Epreuve[] = []; // Ajouter une propriété pour les épreuves
  testsAnalyse: TestAnalyse[] = []; // Ajouter une propriété pour les tests d'analyse



  constructor(
    private userService: UserService,
    private dossierService: DossierService,
    private patientService: PatientService,
    private examenService: ExamenService,
    private epreuveService: EpreuveService, // Injecter le service Epreuve
    private testService: TestService, // Injecter le service Test
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
    this.loadExamens(); // Charger les examens
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

  private loadExamens(): void {
    this.examenService.getExamensByIdDossier(this.dossierId).subscribe(
      (examens) => {
        this.examens = examens;
        console.log('Examens chargés:', this.examens);
        this.loadEpreuvesAndTests();
      },
      (error) => {
        console.error('Erreur lors du chargement des examens', error);
      }
    );
  }

  private loadEpreuvesAndTests(): void {
    this.examens .forEach(examen => {
      // Charger les épreuves et tests d'analyse pour chaque examen
      this.epreuveService.getEpreuves().subscribe(epreuves => {
        const epreuve = epreuves.find(e => e.id === examen.idEpreuve);
        if (epreuve) {
          this.epreuves.push(epreuve);
        }
      });
      this.testService.getTest().subscribe(tests => {
        const test = tests.find(t => t.id === examen.idTestAnalyse);
        if (test) {
          this.testsAnalyse.push(test);
        }
      });
    });
  }

  generatePDF(): void {
    const doc = new jsPDF();

    // Titre
    doc.setFontSize (16);
    doc.setTextColor(40, 40, 200); // Couleur bleue
    doc.text('Détails de Votre Dossier', 70, 30); // Centrer le titre

    // Détails de l'utilisateur
    doc.setFontSize(12);
    doc.setTextColor(0, 0, 0); // Couleur noire
    doc.text('Utilisateur:', 10, 50);
    doc.setFont("helvetica", "bold"); // Mettre en gras
    doc.text(`Nom Utilisateur: ${this.user.nomComplet}`, 10, 60);
    doc.text(`Email Utilisateur: ${this.user.email}`, 10, 70);
    doc.setFont("helvetica", "normal"); // Revenir à normal

    // Ligne de séparation
    doc.setDrawColor(200, 200, 200); // Couleur grise
    doc.line(10, 75, 200, 75); // Ligne horizontale

    // Détails du patient
    doc.setFontSize(12);
    doc.setTextColor(0, 0, 0);
    doc.text('Patient:', 10, 90);
    doc.setFont("helvetica", "bold");
    doc.text(`Nom Patient: ${this.patient.nom} ${this.patient.prenom}`, 10, 100);
    doc.text(`Date de Naissance: ${this.patient.dateNaissance}`, 10, 110);
    doc.text(`Adresse: ${this.patient.adresse}`, 10, 120);
    doc.setFont("helvetica", "normal");

    // Ligne de séparation
    doc.line(10, 125, 200, 125);

    // Détails du dossier
    doc.setFontSize(12);
    doc.setTextColor(0, 0, 0);
    doc.text('Dossier:', 10, 140);
    doc.setFont("helvetica", "bold");
    doc.text(`ID: ${this.dossier.id}`, 10, 150);
    doc.text(`Date: ${this.dossier.date}`, 10, 160);
    doc.setFont("helvetica", "normal");

    // Ligne de séparation
    doc.line(10, 165, 200, 165);

    // Détails des examens
    if (this.examens.length > 0) {
      doc.setFontSize(12);
      doc.setTextColor(0, 0, 0);
      doc.text('Examens:', 10, 180);
      this.examens.forEach((examen, index) => {
        doc.setFont("helvetica", "bold");
        doc.text(`Examen ${index + 1}:`, 10, 190 + (index * 10));
        doc.setFont("helvetica", "normal");
        doc.text(`Résultat: ${examen.resultat}`, 20, 200 + (index * 10));
        const epreuve = this.epreuves.find(e => e.id === examen.idEpreuve);
        if (epreuve) {
          doc.text(`Épreuve: ${epreuve.nom}`, 20, 210 + (index * 10));
        }
        const test = this.testsAnalyse.find(t => t.id === examen.idTestAnalyse);
        if (test) {
          doc.text(`Test d'Analyse: ${test.nomTest}`, 20, 220 + (index * 10));
          doc.text(`Intervalle de Référence: ${test.intervalMinDeReference} - ${test.intervalMaxDeReference} ${test.uniteDeReference}`, 20, 230 + (index * 10));
        }
      });
    }

    // Section de signature
    doc.setFontSize(12);
    doc.setTextColor(0, 0, 0); // Couleur noire
    doc.text('Signature:', 10, 250);
    doc.line(10, 255, 100, 255); // Ligne pour la signature
    doc.text(`utilisateur Responsable : ${this.user.nomComplet} `, 10, 260); // Remplacez par le nom

    // Sauvegarder le PDF
    doc.save('details.pdf');
  }
}
