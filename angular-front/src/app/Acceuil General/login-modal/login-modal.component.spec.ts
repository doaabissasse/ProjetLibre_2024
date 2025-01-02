import { ComponentFixture, TestBed } from '@angular/core/testing';
import { LoginModalComponent } from './login-modal.component';
import { AuthenticationService } from '../services/authentication.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { of, throwError } from 'rxjs';

describe('LoginModalComponent', () => {
  let component: LoginModalComponent;
  let fixture: ComponentFixture<LoginModalComponent>;
  let authServiceMock: any;
  let routerMock: any;

  beforeEach(async () => {
    authServiceMock = {
      authenticate: jest.fn(),
    };
    routerMock = {
      navigate: jest.fn(),
    };

    await TestBed.configureTestingModule({
      declarations: [LoginModalComponent],
      imports: [FormsModule],
      providers: [
        { provide: AuthenticationService, useValue: authServiceMock },
        { provide: Router, useValue: routerMock },
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(LoginModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize with empty email and password', () => {
    expect(component.email).toBe('');
    expect(component.password).toBe('');
  });


  it('should not call authenticate when email or password is empty', () => {
    component.email = '';
    component.password = '';
    component.onSubmit();
    expect(authServiceMock.authenticate).not.toHaveBeenCalled();
  });

  it('should call authenticate with email and password', () => {
    const mockResponse = {
      token: 'test-token',
      role: 'ADMIN',
      user: { id: 1, name: 'John Doe' },
    };

    authServiceMock.authenticate.mockReturnValue(of(mockResponse));

    component.email = 'test@example.com';
    component.password = 'password';
    component.onSubmit();

    expect(authServiceMock.authenticate).toHaveBeenCalledWith(
      'test@example.com',
      'password'
    );
    expect(localStorage.getItem('token')).toBe('test-token');
    expect(localStorage.getItem('role')).toBe('ADMIN');
    expect(localStorage.getItem('user')).toBe(JSON.stringify(mockResponse.user));
    expect(routerMock.navigate).toHaveBeenCalledWith(['/admin-dashboard']);
  });


});
