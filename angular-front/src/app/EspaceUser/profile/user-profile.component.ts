import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-user-profile',
    templateUrl: './user-profile.component.html',
    standalone: false,
    styleUrls: ['./user-profile.component.css']
})
export class ProfileComponent implements OnInit {
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
