import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ServiceLaboratoireService } from '../../Laboratoire/service_labo/service-laboratoire.service';
import { Laboratoire } from '../../Laboratoire/Entite_labo/laboratoire';
import { MatDialog } from '@angular/material/dialog';
import { ContactService } from '../../Contactes/contacte_sevice/contact.service';

@Component({
  selector: 'app-contacte',
  standalone: false,
  templateUrl: './contacte.component.html',
  styleUrl: './contacte.component.css'
})
export class ContacteComponent implements OnInit {
  laboratoireId: number = 0; // Initialisation de l'ID
  laboratoire: Laboratoire | null = null;
  contacts: Array<any> = [];

  constructor(
    private route: ActivatedRoute,
    private laboratoireService: ServiceLaboratoireService,
    private router: Router,
    private dialog: MatDialog,
    private contactService: ContactService
  ) {}

  ngOnInit(): void {
    // Récupérer l'ID du laboratoire dans l'URL
    this.route.paramMap.subscribe((params) => {
      this.laboratoireId = +params.get('id')!;  // Conversion de l'ID en nombre
      if (this.laboratoireId) {
        this.getContacts();
      } else {
        console.error('ID de laboratoire invalide.');
      }
    });
  }


  // Récupérer les contacts liés au laboratoire
  private getContacts(): void {
    this.laboratoireService.getContactsByLaboratoireId(this.laboratoireId).subscribe({
      next: (data) => {
        this.contacts = data;
        console.log('Contacts récupérés:', this.contacts);
      },
      error: (error) => console.error('Erreur lors de la récupération des contacts:', error),
    });
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
  
}
