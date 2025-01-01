import { Component } from '@angular/core';
import {ActivatedRoute, Router, RouterLink} from '@angular/router';
import {User} from "../../Acceuil General/models/user";
import {UserService} from "../../Acceuil General/services/user.service";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-modifier-utilisateur',
  standalone: false,

  templateUrl: './modifier-utilisateur.component.html',
  styleUrl: './modifier-utilisateur.component.css'
})
export class ModifierUtilisateurComponent {
  user: User = {
    id: 0,
    email: '',
    nomComplet: '',
    profession: '',
    numTel: '',
    signature: '',
    fkIdLaboratoire: 0,
    password: '',
    accountLocked: false,
    enabled: true,

  };

  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.userService.getUserById(Number(id)).subscribe(
        (user: User) => {
          this.user = user;
        },
        (error) => {
          console.error('Erreur de chargement de l\'utilisateur', error);
        }
      );
    }
  }

  onSubmit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.userService.updateUser(Number(id), this.user).subscribe(
        () => {
          this.router.navigate(['/utilisateurs', id]);
        },
        (error) => {
          console.error('Erreur de mise à jour de l\'utilisateur', error);
        }
      );
    }
  }
}
