import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AccueilLaboratoireComponent } from './accueil-laboratoire.component';
import { LoginModalComponent } from "../login-modal/login-modal.component";
import { RouterTestingModule } from '@angular/router/testing';

describe('AccueilLaboratoireComponent', () => {
  let component: AccueilLaboratoireComponent;
  let fixture: ComponentFixture<AccueilLaboratoireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AccueilLaboratoireComponent, LoginModalComponent],
      imports: [RouterTestingModule],
    }).compileComponents();

    fixture = TestBed.createComponent(AccueilLaboratoireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should toggle login modal visibility', () => {
    expect(component.isLoginModalVisible).toBe(false);

    // Open the modal
    component.openLoginModal();
    expect(component.isLoginModalVisible).toBe(true);

    // Close the modal
    component.closeLoginModal();
    expect(component.isLoginModalVisible).toBe(false);
  });
});
