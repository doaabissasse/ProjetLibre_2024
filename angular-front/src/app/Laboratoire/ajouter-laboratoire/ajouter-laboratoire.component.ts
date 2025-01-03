import { Component,OnInit } from '@angular/core';
import { Laboratoire } from '../Entite_labo/laboratoire';
import { ServiceLaboratoireService } from '../service_labo/service-laboratoire.service';
import { Router } from '@angular/router';

@Component({
  standalone: false,
  selector: 'app-ajouter-laboratoire',
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
