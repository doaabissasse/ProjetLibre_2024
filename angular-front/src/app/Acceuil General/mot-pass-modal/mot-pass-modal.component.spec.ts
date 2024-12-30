import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MotPassModalComponent } from './mot-pass-modal.component';

describe('MotPassModalComponent', () => {
  let component: MotPassModalComponent;
  let fixture: ComponentFixture<MotPassModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MotPassModalComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MotPassModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
