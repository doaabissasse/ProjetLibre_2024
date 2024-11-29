import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-confirm-dialog',
  template: `
    <h2 mat-dialog-title>Confirmer la suppression</h2>
    <mat-dialog-content>
      <p>Voulez-vous vraiment supprimer le laboratoire "{{ data.nom }}" ?</p>
    </mat-dialog-content>
    <mat-dialog-actions>
      <button mat-button (click)="onNoClick()">Annuler</button>
      <button mat-button (click)="onConfirm()">Confirmer</button>
    </mat-dialog-actions>
  `,
})
export class ConfirmDialogComponent {
  constructor(
    public dialogRef: MatDialogRef<ConfirmDialogComponent>, // Ajoutez MatDialogRef ici
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}

  onNoClick(): void {
    this.dialogRef.close(false); // Ferme le dialog avec une valeur false
  }

  onConfirm(): void {
    this.dialogRef.close(true); // Ferme le dialog avec une valeur true
  }
}
