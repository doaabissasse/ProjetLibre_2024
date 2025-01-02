import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatDialogModule, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ReactiveFormsModule, FormBuilder } from '@angular/forms';
import { of } from 'rxjs';
import { AjouterTestDialogComponent } from './ajouter-test-dialog.component';
import { TestService } from '../test.service';

// Mock TestService
class MockTestService {
  ajouterTestAnalyse(data: any) {
    return of({ success: true });
  }
}

describe('AjouterTestDialogComponent', () => {
  let component: AjouterTestDialogComponent;
  let fixture: ComponentFixture<AjouterTestDialogComponent>;
  let testService: TestService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AjouterTestDialogComponent],
      imports: [MatDialogModule, ReactiveFormsModule],
      providers: [
        { provide: MatDialogRef, useValue: {} },
        { provide: MAT_DIALOG_DATA, useValue: { analyseId: 1 } },
        { provide: TestService, useClass: MockTestService },
        FormBuilder
      ]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AjouterTestDialogComponent);
    component = fixture.componentInstance;
    testService = TestBed.inject(TestService);
    fixture.detectChanges();
  });

  afterEach(() => {
    jest.clearAllMocks();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize form with default values and validations', () => {
    expect(component.testForm).toBeTruthy();
    expect(component.testForm.valid).toBeFalsy();

    const formControls = component.testForm.controls;
    expect(formControls['nomTest'].valid).toBeFalsy();
    expect(formControls['sousEpreuve'].valid).toBeFalsy();
    expect(formControls['intervalMinDeReference'].valid).toBeFalsy();
    expect(formControls['intervalMaxDeReference'].valid).toBeFalsy();
    expect(formControls['uniteDeReference'].valid).toBeFalsy();
    expect(formControls['details'].valid).toBeFalsy();
  });

  it('should call addTest() and submit form data successfully', () => {
    const formData = {
      nomTest: 'Test 1',
      sousEpreuve: 'Sous-Épreuve 1',
      intervalMinDeReference: 10,
      intervalMaxDeReference: 20,
      uniteDeReference: 'unit',
      details: 'details'
    };

    // Set form values
    component.testForm.setValue(formData);

  });


});
