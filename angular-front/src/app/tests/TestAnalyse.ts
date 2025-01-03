export interface TestAnalyse {
    id?: number;                    // L'ID unique du test
    idAnalyse: number;             // L'ID de l'analyse associée
    nomTest: string;               // Nom du test
    sousEpreuve: string;           // Sous-épreuve du test
    intervalMinDeReference: number; // Intervalle min de référence
    intervalMaxDeReference: number; // Intervalle max de référence
    uniteDeReference: string;      // Unité de référence
    details: string;               // Détails supplémentaires
  }
