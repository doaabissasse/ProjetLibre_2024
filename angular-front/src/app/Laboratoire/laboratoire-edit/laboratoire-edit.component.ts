import { Component, OnInit, Inject } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ServiceLaboratoireService } from '../service_labo/service-laboratoire.service';
import { Laboratoire } from '../Entite_labo/laboratoire';

@Component({
  selector: 'app-laboratoire-edit',
  templateUrl: './laboratoire-edit.component.html',
  styleUrls: ['./laboratoire-edit.component.css']
})
export class LaboratoireEditComponent  {
  laboratoire: Laboratoire = { id: 0, nom: '', nrc: '', logo: '', active: false, dateActivation: ''};

  constructor(
    private laboratoireService: ServiceLaboratoireService,
    private snackBar: MatSnackBar,
    private router: Router,
    private route: ActivatedRoute,
    private dialogRef: MatDialogRef<LaboratoireEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { id: number}
  ) {}

  ngOnInit(): void {
    if (this.data.id) {
      this.getLaboratoireById(this.data.id);
    }
  }

  getLaboratoireById(id: number): void {
    this.laboratoireService.getLaboratoireById(id).subscribe({
      next: (data) => {
        this.laboratoire = data;
      },
      error: (error) => {
        console.error('Erreur lors de la récupération du laboratoire:', error);
      }
    });
  }

  onSave(): void {
    this.laboratoireService.updateLaboratoire(this.laboratoire).subscribe({
      next: () => {
        this.snackBar.open('Laboratoire modifié avec succès', 'Succès', { duration: 3000 });
        this.dialogRef.close(true);
        this.router.navigate(['/laboratoire-list']); // Rediriger vers la liste
      },
      error: (error) => {
        console.error('Erreur lors de la modification:', error);
        this.snackBar.open('Erreur lors de la modification.', 'Erreur', { duration: 3000 });
      }
    });
  }

  onCancel(): void {
    this.dialogRef.close();
  }
}
