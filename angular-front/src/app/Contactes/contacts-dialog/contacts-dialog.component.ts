import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ServiceLaboratoireService } from '../../Laboratoire/service_labo/service-laboratoire.service';
import { Laboratoire } from '../../Laboratoire/Entite_labo/laboratoire'; 
import { MatDialog } from '@angular/material/dialog';
import { AjouterContactDialogComponent } from '../../Contactes/ajouter-contact-dialog/ajouter-contact-dialog.component'
import { ContactService } from '../contacte_sevice/contact.service';
import { ConfirmationDialogComponent } from '../../Adresses/confirmation-dialog/confirmation-dialog.component';


@Component({
  selector: 'app-contacts-dialog',
  standalone: false,
  templateUrl: './contacts-dialog.component.html',
  styleUrls: ['./contacts-dialog.component.css']
})
export class ContactsDialogComponent implements OnInit {
  laboratoireId: number = 0;  // Initialiser avec une valeur par défaut
  laboratoire: Laboratoire | undefined;
  contacts: any[] = [];
  adresseExistante: number | undefined; // ID de l'adresse existante
  adressesExistantes: any[] = []; // Liste des adresses existantes
  adresseOption: string = 'nouvelleAdresse'; // Option sélectionnée par défaut

  constructor(
    private route: ActivatedRoute,
    private laboratoireService: ServiceLaboratoireService,
    private router: Router , // Injecter Router pour la navigation
    private dialog: MatDialog,
    private contactService: ContactService,
  ) {}

  ngOnInit(): void {
    this.laboratoireId = +this.route.snapshot.paramMap.get('id')!; // Récupérer l'ID depuis l'URL
    console.log('Laboratoire ID:', this.laboratoireId); // Vérifiez si l'ID est bien récupéré
    this.getLaboratoireDetails();  // Récupérer les détails du laboratoire
    this.getContacts();  // Récupérer les contacts associés
  }

  private getLaboratoireDetails() {
    this.laboratoireService.getLaboratoireById(this.laboratoireId).subscribe(
      data => {
        this.laboratoire = data;
        console.log('Laboratoire details:', this.laboratoire); // Vérifiez les détails du laboratoire
      },
      error => {
        console.error('Error fetching laboratoire details:', error);
      }
    );
  }

  private getContacts() {
    this.laboratoireService.getContactsByLaboratoireId(this.laboratoireId).subscribe(
      data => {
        this.contacts = data;
        console.log('Contacts récupérés:', this.contacts);  // Vérifiez si chaque contact a un ID
      },
      error => {
        console.error('Erreur lors de la récupération des contacts:', error);
      }
    );
  }

  afficherAdresse(contactId: number) {
    console.log('Appel de afficherAdresse avec Contact ID:', contactId);  // Vérification de l'ID du contact passé
  
    // Vérification si l'ID est valide
    if (!contactId) {
      console.error('L\'ID du contact est invalide ou manquant');
      return;
    }
  
    // Appel au service pour récupérer l'adresse du contact
    this.contactService.getAdresseContact(contactId).subscribe(
      (adresse) => {
        console.log('Réponse reçue pour l\'adresse:', adresse);  // Log de l'adresse récupérée
  
        const contact = this.contacts.find(c => c.id === contactId);
        if (contact) {
          console.log('Contact trouvé:', contact);  // Log du contact trouvé dans le tableau
          contact.adresse = adresse;
          console.log('Adresse ajoutée au contact:', contact);  // Vérification de l'ajout de l'adresse
        } else {
          console.error('Aucun contact trouvé pour l\'ID:', contactId);  // Log si le contact n'est pas trouvé
        }
      },
      (error) => {
        console.error('Erreur lors de la récupération de l\'adresse:', error);  // Log détaillé de l'erreur
        if (error.status === 403) {
          console.error('Erreur 403 - Accès refusé: Vérifiez les permissions ou le token d\'authentification');
        } else if (error.status === 404) {
          console.error('Erreur 404 - Contact non trouvé: Vérifiez que l\'ID du contact est correct');
        } else {
          console.error('Erreur inconnue:', error);  // Log pour toute autre erreur
        }
      }
    );
  }
  
  // Méthode pour revenir à la liste des laboratoires
  retourLaboratoires() {
    this.router.navigate(['/laboratoires']);  // Naviguer vers la liste des laboratoires
  }

  ouvrirDialogueAjoutContact() {
    if (this.laboratoire && this.laboratoire.id) {
      const dialogRef = this.dialog.open(AjouterContactDialogComponent, {
        width: '850px', // Largeur fixe du dialogue
        maxWidth: '90vw', // Limiter à 90% de la largeur de l'écran pour les petits écrans
        height: '500px',
        panelClass: 'custom-dialog-container', // Ajouter une classe personnalisée si nécessaire
        data: { idLaboratoire: this.laboratoire.id } // Passe l'ID du laboratoire
      });
  
      dialogRef.afterClosed().subscribe(result => {
        if (result) {
          this.getContacts(); // Recharge les contacts après l'ajout
        }
      });
    } else {
      console.error('ID Laboratoire est manquant.');
    }
  }

  supprimerContact(contactId: number): void {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      width: '400px',
      data: { message: 'Êtes-vous sûr de vouloir supprimer ce contact ?' }
    });
  
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.contactService.supprimerContact(contactId).subscribe(
          () => {
            console.log(`Contact avec ID ${contactId} supprimé avec succès.`);
            this.getContacts(); // Rafraîchir la liste des contacts
          },
          (error) => {
            console.error('Erreur lors de la suppression du contact:', error);
          }
        );
      }
    });
  }
}
