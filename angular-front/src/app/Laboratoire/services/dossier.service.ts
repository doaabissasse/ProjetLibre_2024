import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Dossier} from '../Entite_labo/dossier.model';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DossierService {
  private apiUrl  = 'http://localhost:8085/api/dossiers'; // Remplacez par votre URL backend

  constructor(private http: HttpClient) {}

  getDossiersByUserId(userId: number): Observable<Dossier[]> {
    return this.http.get<Dossier[]>(`${this.apiUrl}/by-utilisateur/${userId}`);
  }


  getDossierById(dossierId: number): Observable<Dossier> {
    return this.http.get<Dossier>(`${this.apiUrl}/${dossierId}`);
  }
}
