import { Component } from '@angular/core';
import { UserService } from '../../Acceuil General/services/user.service';

@Component({
  selector: 'app-admin-dashboard',
  standalone: false,
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.css'
})
export class AdminDashboardComponent {
  usersCount: number = 0;
  laboratoiresCount: number = 0;
  users: any[] = []; // Liste des utilisateurs avec le rôle "User"

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    // Récupérer les statistiques
    this.userService.getUsersCount().subscribe((count) => (this.usersCount = count));
    this.userService.getLaboratoiresCount().subscribe(
      (count) => (this.laboratoiresCount = count)
    );

    // Récupérer les utilisateurs ayant le rôle "User"
    this.userService.getUsersWithRole().subscribe((users) => {
      this.users = users;
    });
  }
}
