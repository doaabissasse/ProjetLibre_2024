import { Component } from '@angular/core';
import { LoginModalComponent } from "../login-modal/login-modal.component";
import {RouterLink, RouterLinkActive} from "@angular/router";
import { ContactMessage } from '../models/ContactMessage';
import {ContactService} from '../services/ContactService.service';

@Component({
  selector: 'app-accueil-laboratoire',
  templateUrl: './accueil-laboratoire.component.html',
  standalone: false,
  styleUrls: ['./accueil-laboratoire.component.css']
})
export class AccueilLaboratoireComponent {
  id: undefined;
  name: string = '';
  email: string = '';
  message: string = '';

  isLoginModalVisible = false; // Contrôle l'affichage de la modale

    openLoginModal() {
    this.isLoginModalVisible = true;
  }

  closeLoginModal() {
    this.isLoginModalVisible = false;
  }
  constructor(private contactService: ContactService) {}
  sendMessage(): void {
    const contactMessage: ContactMessage = {
      name: this.name,
      email: this.email,
      message: this.message
    };
    this.contactService.sendMessage(contactMessage).subscribe(
      response => {
        console.log('Message envoyé avec succès:', response);
        // Réinitialiser le formulaire ou afficher un message de succès
        this.name = '';
        this.email = '';
        this.message = '';
      },
      error => {
        console.error('Erreur lors de l\'envoi du message:', error);
      }
    );
  }
  }
