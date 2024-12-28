import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AjoutAnalysesComponent } from './ajout-analyses.component';

describe('AjoutAnalysesComponent', () => {
  let component: AjoutAnalysesComponent;
  let fixture: ComponentFixture<AjoutAnalysesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AjoutAnalysesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AjoutAnalysesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
