import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AjouterEpreuveDialogComponent } from './ajouter-epreuve-dialog.component';

describe('AjouterEpreuveDialogComponent', () => {
  let component: AjouterEpreuveDialogComponent;
  let fixture: ComponentFixture<AjouterEpreuveDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AjouterEpreuveDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AjouterEpreuveDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
