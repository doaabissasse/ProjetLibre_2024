import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AdminDashboardComponent } from './admin-dashboard.component';
import { UserService } from '../../Acceuil General/services/user.service';
import { PatientService } from '../../Laboratoire/services/patient.service';
import { of } from 'rxjs';
import { Chart } from 'chart.js';

jest.mock('chart.js');

describe('AdminDashboardComponent', () => {
  let component: AdminDashboardComponent;
  let fixture: ComponentFixture<AdminDashboardComponent>;
  let userService: jest.Mocked<UserService>;
  let patientService: jest.Mocked<PatientService>;

  beforeEach(async () => {
    userService = {
      getUsersCount: jest.fn().mockReturnValue(of(100)),
      getLaboratoiresCount: jest.fn().mockReturnValue(of(50)),
      getUsersWithRole: jest.fn().mockReturnValue(of([])),
    } as any;

    patientService = {
      getPatientCount: jest.fn().mockReturnValue(of(200)),
    } as any;

    await TestBed.configureTestingModule({
      declarations: [AdminDashboardComponent],
      providers: [
        { provide: UserService, useValue: userService },
        { provide: PatientService, useValue: patientService }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(AdminDashboardComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });


});
