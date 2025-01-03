import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {ContactMessage} from '../models/ContactMessage';
 // Assurez-vous d'avoir un modèle pour ContactMessage

@Injectable({
  providedIn: 'root'
})
export class ContactService {
  private apiUrl = 'http://localhost:8083/contact'; // Remplacez par l'URL de votre backend

  constructor(private http: HttpClient) {}

  sendMessage(message: ContactMessage): Observable<ContactMessage> {
    return this.http.post<ContactMessage>(`${this.apiUrl}/send`, message);
  }

  getAllMessages(): Observable<ContactMessage[]> {
    return this.http.get<ContactMessage[]>(`${this.apiUrl}/messages`);
  }
}
