import {Component, EventEmitter, Output} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

@Component({
  selector: 'app-mot-pass-modal',
  standalone: false,

  templateUrl: './mot-pass-modal.component.html',
  styleUrl: './mot-pass-modal.component.css'
})
export class MotPassModalComponent {
  @Output() closeModal = new EventEmitter<void>();

  // Émet un événement pour fermer la modale
  onClose(): void {
    this.closeModal.emit();
  }
}
