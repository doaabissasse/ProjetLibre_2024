import {Component, OnInit} from '@angular/core';
import {Dossier} from '../Entite_labo/dossier.model';
import {DossierService} from '../services/dossier.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-dossiers-users-list',
  standalone: false,

  templateUrl: './dossiers-users-list.component.html',
  styleUrl: './dossiers-users-list.component.css'
})
export class DossiersListComponent implements OnInit {
  dossiers: Dossier[] = [];
  userId!: number;

  constructor(
    private route: ActivatedRoute,
    private dossierService: DossierService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.userId = Number(this.route.snapshot.paramMap.get('userId'));
    this.fetchDossiers();
  }

  fetchDossiers(): void {
    this.dossierService.getDossiersByUserId(this.userId).subscribe({
      next: (data) => {
        this.dossiers = data;
      },
      error: (err) => {
        console.error('Erreur lors de la récupération des dossiers', err);
      }
    });
  }

  retour(): void {
    this.router.navigate(['/laboratoires']);
  }

  viewDossier(id: number,idUser: number,idPatient: number): void {
    this.router.navigate(['/dossier-details', id, idUser, idPatient]);
  }


  }
