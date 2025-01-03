export interface ContactMessage {
  id?: number;
  name: string;
  email: string;
  message: string;
  sentAt?: string; // Optionnel, si vous voulez gérer la date d'envoi
}
