import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map,catchError } from 'rxjs/operators';
import { RegistrationRequest } from '../models/RegistrationRequest ';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private apiUrl = 'http://localhost:8088/api/v1/auth'; // Remplacez par l'URL de votre API

  constructor(private http: HttpClient) {}

  authenticate(email: string, password: string): Observable<any> {
    const body = { email, password };
    return this.http.post(`${this.apiUrl}/authenticate`, body, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    }).pipe(
      map((response: any) => {
        // Stocker le token et le rôle dans le localStorage
        localStorage.setItem('token', response.token);
        localStorage.setItem('role', response.role);
        return response;
      }),
      catchError(this.handleError)
    );
  }

  register(userData: RegistrationRequest): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, userData);
  }
  // Méthode pour gérer les erreurs
  private handleError(error: any): Observable<never> {
    console.error('Une erreur est survenue', error);
    const errorMessage = error.error?.message || 'Erreur de connexion au serveur.';
    alert(errorMessage);
    throw error;
  }

}
