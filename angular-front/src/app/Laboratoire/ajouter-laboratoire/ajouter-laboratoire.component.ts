import { Component,OnInit } from '@angular/core';
import { Laboratoire } from '../Entite_labo/laboratoire';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ServiceLaboratoireService } from '../service_labo/service-laboratoire.service';
import { error } from 'console';
import { Router } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-ajouter-laboratoire',
  standalone: true,
  imports: [FormsModule , CommonModule,HttpClientModule,RouterModule],
  templateUrl: './ajouter-laboratoire.component.html',
  styleUrls: ['./ajouter-laboratoire.component.css']
})
export class AjouterLaboratoireComponent implements OnInit{

  laboratoire: Laboratoire = new Laboratoire();
  constructor(private laboratoireService : ServiceLaboratoireService ,
    private router: Router){}
  

  ngOnInit(): void {
    
  }

  ajouterLaboratoire(){
    this.laboratoireService.ajouterLaboratoire(this.laboratoire).subscribe(data =>{
      console.log(data);
      this.allerversListeLabo();
    }),
      (    error: any) => console.log(error);

  }

  allerversListeLabo(){
    this.router.navigate(['/laboratoires']);

  }
 onSubmit(){
  console.log(this.laboratoire);
  this.ajouterLaboratoire();
 }
}
