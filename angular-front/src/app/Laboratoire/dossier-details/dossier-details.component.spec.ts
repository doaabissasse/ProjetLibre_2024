import { ComponentFixture, TestBed } from '@angular/core/testing';
import { DossierDetailsComponent } from './dossier-details.component';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { MatDialogModule } from '@angular/material/dialog';
import { DossierService } from '../services/dossier.service';
import { UserService } from '../../Acceuil General/services/user.service';
import { PatientService } from '../services/patient.service';

import jsPDF from 'jspdf';
import {HttpClientModule} from '@angular/common/http';
import {ExamenService} from '../../EspaceUser/examen/examen.service';

describe('DossierDetailsComponent', () => {
  let component: DossierDetailsComponent;
  let fixture: ComponentFixture<DossierDetailsComponent>;

  const mockActivatedRoute = {
    snapshot: { paramMap: new Map([['idUser', '1'], ['id', '1'], ['idPatient', '1']]) },
  };

  const mockUserService = {
    getUserById: jest.fn().mockReturnValue(of({ nomComplet: 'John Doe', email: 'john@example.com' })),
  };

  const mockPatientService = {
    getPatientById: jest.fn().mockReturnValue(of({ nom: 'Doe', prenom: 'John', dateNaissance: '1990-01-01', adresse: '123 Main St', telephone: '1234567890', lieuDeNaissance: 'Paris' })),
  };

  const mockDossierService = {
    getDossierById: jest.fn().mockReturnValue(of({ id: 1, date: '2025-01-01' })),
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DossierDetailsComponent],
      providers: [
        { provide: ActivatedRoute, useValue: mockActivatedRoute },
        { provide: UserService, useValue: mockUserService },
        { provide: PatientService, useValue: mockPatientService },
        { provide: DossierService, useValue: mockDossierService },
      ],
      imports: [MatDialogModule], // Import necessary modules
    }).compileComponents();
  });

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientModule], // Import HttpClientModule here
      declarations: [DossierDetailsComponent],
      providers: [ExamenService], // Provide your services here
    }).compileComponents();
  });


  it('should create the DossierDetailsComponent', () => {
    const fixture = TestBed.createComponent(DossierDetailsComponent);
    const component = fixture.componentInstance;
    expect(component).toBeTruthy();
  });

  it('should load user, patient, and dossier details', () => {
    expect(component.user.nomComplet).toBe('John Doe');
    expect(component.user.email).toBe('john@example.com');
    expect(component.patient.nom).toBe('Doe');
    expect(component.patient.prenom).toBe('John');
    expect(component.patient.dateNaissance).toBe('1990-01-01');
    expect(component.patient.adresse).toBe('123 Main St');
    expect(component.dossier.id).toBe(1);
    expect(component.dossier.date).toBe('2025-01-01');
  });

});
