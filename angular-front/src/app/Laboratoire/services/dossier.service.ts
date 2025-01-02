import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Dossier } from '../Entite_labo/dossier.model';

@Injectable({
  providedIn: 'root'
})
export class DossierService {
  private apiUrl = 'http://localhost:8085/api/dossiers'; // Remplacez par votre URL backend

  constructor(private http: HttpClient) {}

  // Récupérer tous les dossiers d'un utilisateur
  getDossiersByUserId(userId: number): Observable<Dossier[]> {
    return this.http.get<Dossier[]>(`${this.apiUrl}/by-utilisateur/${userId}`);
  }

  // Récupérer un dossier par ID
  getDossierById(dossierId: number): Observable<Dossier> {
    return this.http.get<Dossier>(`${this.apiUrl}/${dossierId}`);
  }

  addDossier(dossier: Dossier): Observable<Object> {
    console.log(`[POST] Ajouter un dossier:`, dossier);
    return this.http.post(this.apiUrl, dossier, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
    });
  }
   
  // Rechercher des dossiers par critère
  searchDossiers(query: string): Observable<Dossier[]> {
    return this.http.get<Dossier[]>(`${this.apiUrl}/search?query=${query}`);
  }

  // Supprimer un dossier par ID
  deleteDossier(dossierId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${dossierId}`);
  }
}
