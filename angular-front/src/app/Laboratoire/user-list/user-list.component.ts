import {Component, OnInit} from '@angular/core';
import {User} from '../../Acceuil General/models/user';
import {ActivatedRoute, Router} from '@angular/router';
import {UserService} from '../../Acceuil General/services/user.service';

@Component({
  selector: 'app-user-list',
  standalone: false,
  templateUrl: './user-list.component.html',
  styleUrl: './user-list.component.css'
})
export class UserListComponent implements OnInit{
  users: User[] = [];
  laboratoireId!: number;

  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private router : Router,

  ) {}

  ngOnInit(): void {
    this.laboratoireId = Number(this.route.snapshot.paramMap.get('laboratoireId'));
    this.fetchUsers();
  }

  private fetchUsers(): void {
    this.userService.getUsersByLaboratoire(this.laboratoireId).subscribe({
      next: (data) => {
        this.users = data;
      },
      error: (err) => {
        console.error('Erreur lors de la récupération des utilisateurs', err);
      }
    });
  }


  AfficherDossierParUser(UserId: number): void {
    this.router.navigate(['/dossiers', UserId]);
  }
}
