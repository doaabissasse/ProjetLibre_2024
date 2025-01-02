import {Component, OnInit} from '@angular/core';
import {UserService} from "../../Acceuil General/services/user.service";
import {ServiceLaboratoireService} from "../../Laboratoire/service_labo/service-laboratoire.service";

@Component({
  selector: 'app-user-dashboard',
  standalone: false,
  templateUrl: './user-dashboard.component.html',
  styleUrl: './user-dashboard.component.css'
})
export class UserDashboardComponent implements OnInit {
  user: any;
  laboratoire: any;

  constructor(private laboratoireService: ServiceLaboratoireService) {}

  ngOnInit(): void {
    const userData = localStorage.getItem('user');
    console.log('Détails du analyse récupérés:', userData);
    if (userData) {
      this.user = JSON.parse(userData);
      console.log('Détails du analyse récupérés:', this.user);
      if (this.user.fkIdLaboratoire) {
        console.log('Détails du analyse récupérés:', this.user.fkIdLaboratoire);
        this.laboratoireService.getLaboratoireById(this.user.fkIdLaboratoire).subscribe({
          next: (data) => (this.laboratoire = data),
          error: (err) => console.error('Erreur lors de la récupération du laboratoire:', err),
        });
      }
    }
  }

}