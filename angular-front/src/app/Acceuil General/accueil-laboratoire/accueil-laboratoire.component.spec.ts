import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccueilLaboratoireComponent } from './accueil-laboratoire.component';

describe('AccueilLaboratoireComponent', () => {
  let component: AccueilLaboratoireComponent;
  let fixture: ComponentFixture<AccueilLaboratoireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AccueilLaboratoireComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AccueilLaboratoireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
