import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddAnalyseDialogComponent } from './add-analyse-dialog.component';

describe('AddAnalyseDialogComponent', () => {
  let component: AddAnalyseDialogComponent;
  let fixture: ComponentFixture<AddAnalyseDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AddAnalyseDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddAnalyseDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
