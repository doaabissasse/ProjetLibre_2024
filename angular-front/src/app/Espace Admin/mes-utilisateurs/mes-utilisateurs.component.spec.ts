import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MesUtilisateursComponent } from './mes-utilisateurs.component';

describe('MesUtilisateursComponent', () => {
  let component: MesUtilisateursComponent;
  let fixture: ComponentFixture<MesUtilisateursComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MesUtilisateursComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MesUtilisateursComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
