import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnalysesDialogComponent } from './analyses-dialog.component';

describe('AnalysesDialogComponent', () => {
  let component: AnalysesDialogComponent;
  let fixture: ComponentFixture<AnalysesDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AnalysesDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AnalysesDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
