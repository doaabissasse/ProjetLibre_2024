export interface RegistrationRequest {
  id:number;
  nomComplet: string;
  email: string;
  numTel: string;
  profession: string;
  signature: string;
  fkIdLaboratoire: number;
  password: string;
}
