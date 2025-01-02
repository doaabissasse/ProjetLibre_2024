import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { AuthenticationService } from './authentication.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { of } from 'rxjs';

// Dummy data for testing
const dummyAuthResponse = {
  token: 'dummy-token',
  role: 'user',
};

const dummyRegistrationRequest = {
  email: 'test@example.com',
  password: 'password123',
  name: 'Test User',
};

describe('AuthenticationService', () => {
  let service: AuthenticationService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [AuthenticationService],
    });

    service = TestBed.inject(AuthenticationService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  describe('authenticate', () => {
    it('should authenticate and store token and role in localStorage', () => {
      service.authenticate('test@example.com', 'password123').subscribe((response) => {
        expect(response).toEqual(dummyAuthResponse);
        expect(localStorage.getItem('token')).toBe('dummy-token');
        expect(localStorage.getItem('role')).toBe('user');
      });

      const req = httpMock.expectOne(`${service.apiUrl}/authenticate`);
      expect(req.request.method).toBe('POST');
      expect(req.request.body).toEqual({ email: 'test@example.com', password: 'password123' });
      req.flush(dummyAuthResponse);
    });

    it('should handle errors from authentication', () => {
      const errorResponse = { status: 401, statusText: 'Unauthorized', error: { message: 'Invalid credentials' } };

      service.authenticate('invalid@example.com', 'wrongpassword').subscribe(
        () => fail('Expected error, not response'),
        (error) => {
          expect(error).toBeTruthy();
          expect(localStorage.getItem('token')).toBeNull();
        }
      );

      const req = httpMock.expectOne(`${service.apiUrl}/authenticate`);
      expect(req.request.method).toBe('POST');
      req.flush({ message: 'Invalid credentials' }, errorResponse);
    });
  });
  });


