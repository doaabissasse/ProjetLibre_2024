import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-menu-user',
  standalone: false,

  templateUrl: './menu-user.component.html',
  styleUrl: './menu-user.component.css'
})
export class MenuUSERComponent implements OnInit {
  user: any; // Définir la variable `user`

  ngOnInit(): void {
    // Récupérer les données de l'utilisateur depuis le localStorage ou un service
    const userData = localStorage.getItem('user');
    if (userData) {
      this.user = JSON.parse(userData);
    } else {
      console.error('Aucun utilisateur trouvé dans le localStorage.');
    }
  }
}
