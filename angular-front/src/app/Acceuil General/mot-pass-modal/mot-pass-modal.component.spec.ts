import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MotPassModalComponent } from './mot-pass-modal.component';
import { FormsModule } from '@angular/forms';
import { By } from '@angular/platform-browser';

describe('MotPassModalComponent', () => {
  let component: MotPassModalComponent;
  let fixture: ComponentFixture<MotPassModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MotPassModalComponent],
      imports: [FormsModule],
    }).compileComponents();

    fixture = TestBed.createComponent(MotPassModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });



  it('should validate password confirmation match', () => {
    const passwordInput = fixture.debugElement.query(By.css('#password'))
      .nativeElement;
    const confirmPasswordInput = fixture.debugElement.query(
      By.css('#confirmPassword')
    ).nativeElement;

    passwordInput.value = 'password123';
    confirmPasswordInput.value = 'password123';
    passwordInput.dispatchEvent(new Event('input'));
    confirmPasswordInput.dispatchEvent(new Event('input'));

    expect(passwordInput.value).toBe(confirmPasswordInput.value);
  });

});
