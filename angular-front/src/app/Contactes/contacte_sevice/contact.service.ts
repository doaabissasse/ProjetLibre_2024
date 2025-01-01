import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Contacte } from '../Contacte';
import { Adresse } from '../../Adresses/Adresse'; 

@Injectable({
  providedIn: 'root'
})
export class ContactService {
  private apiUrl = 'http://localhost:8084/contactes';

  constructor(private http: HttpClient) {}

  ajouterContact(contact: Contacte): Observable<Contacte> {
    console.log('Envoi de l\'analyse :', contact);
    return this.http.post<Contacte>(`${this.apiUrl}`, contact);
  }

 
  getAdresseContact(contactId: number) {
    return this.http.get<Adresse>(`http://localhost:8084/contactes/${contactId}/adresse`);
  }
  
  supprimerContact(contactId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${contactId}`);
  }

}

