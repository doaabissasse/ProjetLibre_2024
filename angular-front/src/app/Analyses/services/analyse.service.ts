import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Analyse } from '../analyse.model';

@Injectable({
  providedIn: 'root'
})
export class AnalyseService {

  private apiUrl = 'http://localhost:8087/api/analyses';
 
   constructor(private http: HttpClient) {}
 
   ajouterAnalyse(analyse: Analyse): Observable<Analyse> {
    console.log('Envoi de l\'analyse :', analyse); // Vérifiez les données envoyées
    return this.http.post<Analyse>(`${this.apiUrl}`, analyse);
  }
 
  
}
