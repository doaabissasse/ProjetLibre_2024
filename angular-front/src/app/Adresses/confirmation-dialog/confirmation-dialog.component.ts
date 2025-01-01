import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';


@Component({
  selector: 'app-confirmation-dialog',
  standalone: false,
  
  templateUrl: './confirmation-dialog.component.html',
  styleUrl: './confirmation-dialog.component.css'
})
export class ConfirmationDialogComponent {
  constructor(
    public dialogRef: MatDialogRef<ConfirmationDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { message: string }
  ) {}

  onCancel(): void {
    this.dialogRef.close(false); // Fermer le dialogue avec un résultat "faux"
  }

  onConfirm(): void {
    this.dialogRef.close(true); // Fermer le dialogue avec un résultat "vrai"
  }
}
