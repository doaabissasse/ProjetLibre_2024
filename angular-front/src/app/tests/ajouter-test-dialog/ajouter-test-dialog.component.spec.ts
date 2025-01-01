import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AjouterTestDialogComponent } from './ajouter-test-dialog.component';

describe('AjouterTestDialogComponent', () => {
  let component: AjouterTestDialogComponent;
  let fixture: ComponentFixture<AjouterTestDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AjouterTestDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AjouterTestDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
