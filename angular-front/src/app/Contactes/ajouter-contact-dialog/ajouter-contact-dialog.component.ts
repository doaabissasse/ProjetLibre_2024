import { Component, Inject, OnInit } from '@angular/core'; 
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Contacte } from '../Contacte';
import { ContactService } from '../contacte_sevice/contact.service';
import { Adresse } from '../../Adresses/Adresse';
import { AdresseService } from '../../Adresses/adresse.service';

@Component({
  selector: 'app-ajouter-contact-dialog',
  standalone: false,
  templateUrl: './ajouter-contact-dialog.component.html',
  styleUrls: ['./ajouter-contact-dialog.component.css'],
})
export class AjouterContactDialogComponent  {
  contact: Contacte = {
    id: 0,
    idLaboratoire: 0,
    idAdresse: 0,
    tel: '',
    fax: '',
    email: ''
  };

  adresse: Adresse = {
    id: 0,
    numVoie: '',
    nomVoie: '',
    codePostal: '',
    ville: '',
    commune: ''
  };

  adresseOption: string = 'nouvelleAdresse'; // Par défaut, choisir de créer une nouvelle adresse
  adressesExistantes: Adresse[] = []; // Liste des adresses existantes
  adresseExistante: Adresse | undefined;

  constructor(
    private dialogRef: MatDialogRef<AjouterContactDialogComponent>,
    private contactService: ContactService,
    private adresseService: AdresseService,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    if (data && data.idLaboratoire) {
      this.contact.idLaboratoire = data.idLaboratoire;
    }
  }

  
  ajouterContactAvecAdresse() {
    // Toujours créer une nouvelle adresse
    this.adresseService.ajouterAdresse(this.adresse).subscribe(
      (adresseResult) => {
        this.contact.idAdresse = adresseResult.id; // Associer l'adresse au contact
        this.saveContact(); // Sauvegarder le contact après l'adresse
      },
      (error) => {
        console.error("Erreur lors de l'ajout de l'adresse:", error);
      }
    );
  }

  private saveContact() {
    // Ajouter le contact après avoir associé l'adresse
    this.contactService.ajouterContact(this.contact).subscribe(contactResult => {
      console.log('Contact ajouté avec succès:', contactResult);
      this.dialogRef.close(contactResult); // Ferme le dialogue avec le résultat
    }, error => {
      console.error('Erreur lors de l\'ajout du contact:', error);
    });
  }

  fermerDialogue() {
    this.dialogRef.close();
  }
}
