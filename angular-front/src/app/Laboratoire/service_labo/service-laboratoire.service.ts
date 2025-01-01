import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Laboratoire } from '../Entite_labo/laboratoire'; // Assurez-vous que le chemin est correct

@Injectable({
  providedIn: 'root' // Assurez-vous que le service est fourni ici
})
export class ServiceLaboratoireService {
  private apiUrl = 'http://localhost:8083/laboratoires'; // Remplacez par l'URL de votre API

  constructor(private httpClient: HttpClient) {}

  getLaboratoiresList(): Observable<Laboratoire[]> {
    return this.httpClient.get<Laboratoire[]>(this.apiUrl);
  }


  ajouterLaboratoire(laboratoire : Laboratoire): Observable<Object>{
    return this.httpClient.post(this.apiUrl,laboratoire);
  }

  supprimerLaboratoire(id: number): Observable<void> {
    const url = `${this.apiUrl}/${id}`;
    return this.httpClient.delete<void>(url);
  }
}
