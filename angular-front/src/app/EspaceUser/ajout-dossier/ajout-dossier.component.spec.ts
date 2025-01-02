import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatDialogModule, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { AjoutDossierComponent } from './ajout-dossier.component';
import { DossierService } from '../../Laboratoire/services/dossier.service';

describe('AjoutDossierComponent', () => {
  let component: AjoutDossierComponent;
  let fixture: ComponentFixture<AjoutDossierComponent>;
  let dialogRefMock: jest.Mocked<MatDialogRef<AjoutDossierComponent>>;
  let dossierServiceMock: jest.Mocked<DossierService>;

  beforeEach(async () => {
    dialogRefMock = {
      close: jest.fn()
    } as unknown as jest.Mocked<MatDialogRef<AjoutDossierComponent>>;

    dossierServiceMock = {
      addDossier: jest.fn()
    } as unknown as jest.Mocked<DossierService>;

    await TestBed.configureTestingModule({
      declarations: [AjoutDossierComponent],
      imports: [MatDialogModule, HttpClientTestingModule],
      providers: [
        { provide: MatDialogRef, useValue: dialogRefMock },
        { provide: MAT_DIALOG_DATA, useValue: { userId: 1 } },
        { provide: DossierService, useValue: dossierServiceMock }
      ]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AjoutDossierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize dossier with default values', () => {
    expect(component.dossier.idPatient).toBe(0);
    expect(component.dossier.date).toBe(new Date().toISOString().split('T')[0]);
    expect(component.dossier.idUtilisateur).toBe(1); // Data userId is injected
  });


  it('should call onCancel()', () => {
    component.onCancel();
    expect(dialogRefMock.close).toHaveBeenCalledWith(false);
  });
});
