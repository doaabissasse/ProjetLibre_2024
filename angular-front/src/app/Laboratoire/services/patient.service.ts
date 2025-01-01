import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Patient} from '../Entite_labo/Patient.model';

@Injectable({
  providedIn: 'root',
})
export class PatientService {
  private apiUrl = 'http://localhost:8089/api/patients'; // Remplacez par votre URL backend

  constructor(private http: HttpClient) {}

  getPatientById(id: number): Observable<Patient> {
    return this.http.get<Patient>(`${this.apiUrl}/${id}`);
  }



  getPatientCount(): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/count`);
  }
}
