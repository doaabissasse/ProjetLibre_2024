import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';  // Assurez-vous que FormsModule est importé ici
import { Router } from '@angular/router';
import { AuthenticationService } from '../../Acceuil General/services/authentication.service';
import { RegistrationRequest } from '../../Acceuil General/models/RegistrationRequest ';

@Component({
  standalone: false,
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css'],
  })
export class AddUserComponent {
  newUser: RegistrationRequest = {
    id: 0,
    nomComplet: '',
    email: '',
    numTel: '',
    profession: '',
    signature: '',
    fkIdLaboratoire: 0,
    password: '',
  };

  constructor(private AuthenticationService: AuthenticationService, private router: Router) {}

  onSubmit(): void {
    this.AuthenticationService.register(this.newUser).subscribe({
      next: () => {
        alert('Utilisateur enregistré avec succès.');
        this.router.navigate(['/MesUtilisateurs']);
      },
      error: (err: any) => {
        console.error('Erreur:', err);
        alert('Une erreur est survenue lors de l\'enregistrement.');
      },
    });
  }

  onCancel(): void {
    this.router.navigate(['/MesUtilisateurs']);
  }
}
