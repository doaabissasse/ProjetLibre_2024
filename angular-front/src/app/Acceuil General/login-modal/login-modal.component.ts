import { Component, EventEmitter, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {Router, RouterLink, RouterLinkActive} from "@angular/router";
import {AuthenticationService} from "../services/authentication.service";

@Component({
  selector: 'app-login-modal',
  standalone: false,
  templateUrl: './login-modal.component.html',
  styleUrls: ['./login-modal.component.css'],
})
export class LoginModalComponent {
  @Output() closeModal = new EventEmitter<void>();
  email: string = '';
  password: string = '';

  constructor(private authService: AuthenticationService, private router: Router) {
  }

  // Émet un événement pour fermer la modale
  onClose(): void {
    this.closeModal.emit();
  }

  onSubmit(): void {
    if (!this.email || !this.password) {
      alert('Veuillez remplir tous les champs.');
      return;
    }

    this.authService.authenticate(this.email, this.password).subscribe({
      next: (response) => {
        console.log(response); // Vérifiez ici si le backend retourne bien une réponse correcte.
        const role = response.role;
        const user = response.user; // Récupération des informations utilisateur

        localStorage.setItem('token', response.token);
        localStorage.setItem('role', role);
        localStorage.setItem('user', JSON.stringify(user));

        if (role === 'ADMIN') {
          this.router.navigate(['/admin-dashboard']); // Redirection admin
        } else {
          this.router.navigate(['/user-dashboard']); // Redirection utilisateur
        }
      },
      error: (err) => {
        console.error('Erreur d\'authentification', err); // Vérifiez ici les erreurs côté Angular.
        alert('Email ou mot de passe incorrect.');
      },
    });
  }


}
