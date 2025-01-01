import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TestAnalyse } from '../../tests/TestAnalyse';
import { Analyse } from '../../Analyses/analyse.model';
import { Epreuve } from '../../epreuves/Epreuve';


@Injectable({
  providedIn: 'root'
})
export class AnalyseService {

  private apiUrl = 'http://localhost:8087/api/analyses';
 
  constructor(private http: HttpClient) {}
 

  supprimerAnalyse(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  
  // Modifier la méthode visualiserTests pour utiliser la nouvelle URL
  visualiserTests(analyseId: number): Observable<TestAnalyse[]> {
    return this.http.get<TestAnalyse[]>(`${this.apiUrl}/${analyseId}/tests`);
  }

   // Modifier la méthode visualiserTests pour utiliser la nouvelle URL
  visualiserEpeuves(analyseId: number): Observable<Epreuve[]> {
    return this.http.get<Epreuve[]>(`${this.apiUrl}/${analyseId}/epreuves`);
  }
 
 

  // Modifier la méthode visualiserTests pour utiliser la nouvelle URL
  getanalyseById(id: number): Observable<Analyse> {
    return this.http.get<Analyse>(`${this.apiUrl}/${id}`);
  }

  // Method to add a new analyse
  addAnalyse(analyse: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, analyse);
  }
}
