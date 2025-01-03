import { Component } from '@angular/core';
import { LoginModalComponent } from "../login-modal/login-modal.component";
import {RouterLink, RouterLinkActive} from "@angular/router";

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

  sendMessage(){

  }
  }
