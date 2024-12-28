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
 

  supprimerAnalyse(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
 
  // Method to add a new analyse
  addAnalyse(analyse: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, analyse);
  }
}
