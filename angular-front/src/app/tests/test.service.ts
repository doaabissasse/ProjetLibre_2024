import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TestAnalyse } from './TestAnalyse';
import {Epreuve} from '../epreuves/Epreuve';

@Injectable({
  providedIn: 'root'
})
export class TestService {

  private apiUrl = 'http://localhost:8092/api/test-analyses';

  constructor(private http: HttpClient) {}

  ajouterTestAnalyse(testAnalyse: TestAnalyse): Observable<TestAnalyse> {
    return this.http.post<TestAnalyse>(this.apiUrl, testAnalyse);
  }


   // Nouvelle méthode pour supprimer un test
  supprimerTestAnalyse(id: number): Observable<void> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.delete<void>(url);
  }

  getTest(): Observable<TestAnalyse[]> {
    return this.http.get<TestAnalyse[]>(this.apiUrl);
  }

}
