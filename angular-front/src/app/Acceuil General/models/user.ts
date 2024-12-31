// src/app/models/user.model.ts
export interface User {
  id: number; // L'identifiant de l'utilisateur
  email: string;
  nomComplet: string;
  profession: string;
  numTel: string;
  signature?: string; // Optionnel, selon vos besoins
  fkIdLaboratoire: number;
  password: string;
  accountLocked: boolean;
  enabled: boolean;
}
