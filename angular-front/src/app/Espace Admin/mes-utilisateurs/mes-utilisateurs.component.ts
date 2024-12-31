import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common'; // Importer CommonModule
import { UserService } from '../../Acceuil General/services/user.service';
import { HttpClient } from '@angular/common/http';
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-mes-utilisateurs',
  standalone: false,
  templateUrl: './mes-utilisateurs.component.html',
  styleUrls: ['./mes-utilisateurs.component.css']
})
export class MesUtilisateursComponent implements OnInit {
  users: any[] = []; // Liste des utilisateurs
  errorMessage: string = ''; // Pour afficher les erreurs éventuelles

  constructor(private userService: UserService, private http: HttpClient) {}

  ngOnInit(): void {
    // Récupérer tous les utilisateurs via le service
    this.userService.getAllUsers().subscribe(
      (users) => {
        this.users = users; // Assignation des utilisateurs récupérés
      },
      (error) => {
        console.error('Erreur lors de la récupération des utilisateurs :', error);
        this.errorMessage = 'Impossible de charger les utilisateurs.';
      }
    );
  }

  // Méthode pour récupérer les informations du laboratoire
  showLabDetails(fkIdLaboratoire: number): void {
    if (!fkIdLaboratoire) {
      alert("Aucun laboratoire associé à cet utilisateur.");
      return;
    }



    this.http.get<any>(`http://localhost:8083/laboratoires/${fkIdLaboratoire}`)
      .subscribe({
        next: (labDetails) => {
          alert(`Informations du laboratoire :
          Logo: ${labDetails.logo}
          Nom: ${labDetails.nom}
          Date Activation: ${labDetails.dateActivation}`);
          console.log('Détails du laboratoire :', labDetails);
        },
        error: (err) => {
          console.error("Erreur lors de la récupération du laboratoire :", err);
          alert("Impossible de récupérer les informations du laboratoire.");
        }
      });
  }

  editUser(user: any): void {
    console.log('Modifier utilisateur :', user);
    // Ajoutez ici la logique pour ouvrir un formulaire d'édition
  }

  deleteUser(user: any): void {
    if (confirm(`Voulez-vous vraiment supprimer l'utilisateur ${user.nomComplet} ?`)) {
      console.log('Supprimer utilisateur :', user);
      this.userService.deleteUser(user.id).subscribe(
        () => {
          // Supprimer l'utilisateur de la liste après la suppression
          this.users = this.users.filter(u => u.id !== user.id);
          alert('Utilisateur supprimé avec succès.');
        },
        (error) => {
          console.error('Erreur lors de la suppression de l\'utilisateur:', error);
          alert('Une erreur est survenue lors de la suppression de l\'utilisateur.');
        }
      );
    }
  }


}
