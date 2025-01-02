export class Examen {
    id: number;
    idDossier: number;
    idEpreuve: number;
    idTestAnalyse: number;
    resultat: string;
  
    constructor(id: number, idDossier: number, idEpreuve: number, idTestAnalyse: number, resultat: string) {
      this.id = id;
      this.idDossier = idDossier;
      this.idEpreuve = idEpreuve;
      this.idTestAnalyse = idTestAnalyse;
      this.resultat = resultat;
    }
  }
  