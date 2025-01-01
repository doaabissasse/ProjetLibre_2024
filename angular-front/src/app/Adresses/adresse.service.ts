import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Adresse } from './Adresse';

@Injectable({
  providedIn: 'root'
})
export class AdresseService {
  private baseUrl = 'http://localhost:8093/adresses';

  constructor(private http: HttpClient) {}
  
  // Récupérer toutes les adresses existantes
  getAdresses(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}`);
  }

  ajouterAdresse(adresse: Adresse): Observable<Adresse> {
    return this.http.post<Adresse>(`${this.baseUrl}`, adresse);
  }
}