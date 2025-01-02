import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { PatientService } from './patient.service';
import { Patient } from '../Entite_labo/Patient.model';

describe('PatientService', () => {
  let service: PatientService;
  let httpMock: HttpTestingController;
  const baseUrl = 'http://localhost:8089/api/patients';

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [PatientService],
    });

    service = TestBed.inject(PatientService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });


  describe('#getPatientCount', () => {
    it('should return an Observable<number>', () => {
      const mockCount = 10;

      service.getPatientCount().subscribe(count => {
        expect(count).toBe(mockCount);
      });

      const req = httpMock.expectOne(`${baseUrl}/count`);
      expect(req.request.method).toBe('GET');
      req.flush(mockCount);
    });
  });
});
