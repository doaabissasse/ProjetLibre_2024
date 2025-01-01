// models/patient.model.ts
export interface Patient {
  id: number;
  nom: string;
  prenom: string;
  dateNaissance: string; // Format: 'YYYY-MM-DD'
  lieuDeNaissance: string;
  sexe: string; // "M" ou "F"
  adresse: string;
  email: string;
  telephone: string;
  typePieceIdentite: string;
  numPieceIdentite: string;
  visiblePour: string;
}
