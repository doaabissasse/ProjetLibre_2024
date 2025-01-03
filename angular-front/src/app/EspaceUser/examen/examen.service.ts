import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Examen } from './examen.model';

@Injectable({
  providedIn: 'root'
})
export class ExamenService {

  apiUrl = 'http://localhost:8091/api/examens';  // L'URL de votre API backend

  constructor(private http: HttpClient) { }

  // Créer un examen
  createExamen(examen: Examen): Observable<Examen> {
    return this.http.post<Examen>(`${this.apiUrl}`, examen);
  }

  // Obtenir un examen par ID
  getExamenById(id: number): Observable<Examen> {
    return this.http.get<Examen>(`${this.apiUrl}/${id}`);
  }

  // Obtenir tous les examens
  getAllExamens(): Observable<Examen[]> {
    return this.http.get<Examen[]>(`${this.apiUrl}`);
  }

  // Mettre à jour un examen
  updateExamen(id: number, examen: Examen): Observable<Examen> {
    return this.http.put<Examen>(`${this.apiUrl}/${id}`, examen);
  }

  // Supprimer un examen
  deleteExamen(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  // Obtenir des examens par test analyse
  getExamensByIdTestAnalyse(idTestAnalyse: number): Observable<Examen[]> {
    return this.http.get<Examen[]>(`${this.apiUrl}/by-test-analyse/${idTestAnalyse}`);
  }


  // Obtenir des examens par dossier
  getExamensByIdDossier(idDossier: number): Observable<Examen[]> {
    return this.http.get<Examen[]>(`${this.apiUrl}/dossier/${idDossier}`);
  }
  // Obtenir des examens par épreuve
  getExamensByIdEpreuve(idEpreuve: number): Observable<Examen[]> {
    return this.http.get<Examen[]>(`${this.apiUrl}/by-epreuve/${idEpreuve}`);
  }
}
