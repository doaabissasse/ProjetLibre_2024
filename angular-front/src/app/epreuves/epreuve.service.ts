import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Epreuve } from './Epreuve';

@Injectable({
  providedIn: 'root'
})
export class EpreuveService {
  private apiUrl = 'http://localhost:8088/api/epreuves'; // Remplacez par votre URL backend

  constructor(private http: HttpClient) {}

  // Récupérer toutes les épreuves
  getEpreuves(): Observable<Epreuve[]> {
    return this.http.get<Epreuve[]>(this.apiUrl);
  }

  // Ajouter une nouvelle épreuve
  addEpreuve(epreuve: Epreuve): Observable<Epreuve> {
    console.log('Données envoyées à l\'API:', epreuve);
    return this.http.post<Epreuve>(this.apiUrl, epreuve);
  }

  // Supprimer une épreuve
  deleteEpreuve(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
