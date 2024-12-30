import {Component, OnInit} from '@angular/core';
import {UserService} from "../../Acceuil General/services/user.service";

@Component({
  selector: 'app-user-dashboard',
  standalone: false,
  templateUrl: './user-dashboard.component.html',
  styleUrl: './user-dashboard.component.css'
})
export class UserDashboardComponent implements OnInit {
  user: any; // Contiendra les informations de l'utilisateur

  constructor() {}

  ngOnInit(): void {
    // Récupérer les informations de l'utilisateur depuis le localStorage
    const userData = localStorage.getItem('user');
    if (userData) {
      this.user = JSON.parse(userData);
    }
  }
}
