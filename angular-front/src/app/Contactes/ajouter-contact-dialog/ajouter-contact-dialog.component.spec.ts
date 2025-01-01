import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AjouterContactDialogComponent } from './ajouter-contact-dialog.component';

describe('AjouterContactDialogComponent', () => {
  let component: AjouterContactDialogComponent;
  let fixture: ComponentFixture<AjouterContactDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AjouterContactDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AjouterContactDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
