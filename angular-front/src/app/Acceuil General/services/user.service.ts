// user.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RegistrationRequest } from '../models/RegistrationRequest ';
import {User} from "../models/user";

@Injectable({
  providedIn: 'root',
})
export class UserService {
  register(newUser: RegistrationRequest) {
    throw new Error('Method not implemented.');
  }
  // Base URLs
  private userApiUrl = 'http://localhost:8088/api/v1';
  private laboratoireApiUrl = 'http://localhost:8083';

  constructor(private http: HttpClient) {}

  getUserById(id: number): Observable<User> {
    return this.http.get<User>(`${this.userApiUrl}/utilisateurs/${id}`);
  }
  // Récupérer les utilisateurs avec le rôle "User"
  getUsersWithRole(): Observable<any[]> {
    return this.http.get<any[]>(`${this.userApiUrl}/utilisateurs/role/User`);
  }

  getAllUsers(): Observable<any[]> {
    return this.http.get<any[]>(`${this.userApiUrl}/utilisateurs`);
  }
  // Récupérer le nombre total d'utilisateurs
  getUsersCount(): Observable<number> {
    return this.http.get<number>(`${this.userApiUrl}/utilisateurs/count`);
  }

  // Récupérer le nombre total de laboratoires
  getLaboratoiresCount(): Observable<number> {
    return this.http.get<number>(`${this.laboratoireApiUrl}/laboratoires/count`);
  }

  deleteUser(userId: number): Observable<void> {
    return this.http.delete<void>(`${this.userApiUrl}/utilisateurs/${userId}`);
  }

  // Mettre à jour un utilisateur
  updateUser(id: number, user: User): Observable<User> {
    return this.http.put<User>(`${this.userApiUrl}/utilisateurs/${id}`, user);
  }


  getUsersByLaboratoire(fkIdLaboratoire: number): Observable<User[]> {
    return this.http.get<User[]>(`${this.userApiUrl}/utilisateurs/laboratoire/${fkIdLaboratoire}`);
  }

}
