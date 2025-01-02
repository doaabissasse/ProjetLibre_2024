import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AjoutDossierComponent } from './ajout-dossier.component';

describe('AjoutDossierComponent', () => {
  let component: AjoutDossierComponent;
  let fixture: ComponentFixture<AjoutDossierComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AjoutDossierComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AjoutDossierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
