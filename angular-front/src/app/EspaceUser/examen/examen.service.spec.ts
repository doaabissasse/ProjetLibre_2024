import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ExamenService } from './examen.service';
import { Examen } from './examen.model';

describe('ExamenService', () => {
  let service: ExamenService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ExamenService],
    });
    service = TestBed.inject(ExamenService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify(); // Verify that there are no outstanding requests
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should create an examen', () => {
    const examenData: Examen = new Examen(1, 1, 1, 1, 'test result');

    service.createExamen(examenData).subscribe((res) => {
      expect(res).toEqual(examenData);
    });

    const req = httpMock.expectOne(`${service.apiUrl}`);
    expect(req.request.method).toBe('POST');
    req.flush(examenData);
  });

  it('should get examen by id', () => {
    const examenData: Examen = new Examen(1, 1, 1, 1, 'test result');

    service.getExamenById(1).subscribe((res) => {
      expect(res).toEqual(examenData);
    });

    const req = httpMock.expectOne(`${service.apiUrl}/1`);
    expect(req.request.method).toBe('GET');
    req.flush(examenData);
  });

  it('should get all examens', () => {
    const examenData: Examen[] = [new Examen(1, 1, 1, 1, 'test result')];

    service.getAllExamens().subscribe((res) => {
      expect(res).toEqual(examenData);
    });

    const req = httpMock.expectOne(`${service.apiUrl}`);
    expect(req.request.method).toBe('GET');
    req.flush(examenData);
  });

  it('should update an examen', () => {
    const examenData: Examen = new Examen(1, 1, 1, 1, 'updated result');

    service.updateExamen(1, examenData).subscribe((res) => {
      expect(res).toEqual(examenData);
    });

    const req = httpMock.expectOne(`${service.apiUrl}/1`);
    expect(req.request.method).toBe('PUT');
    req.flush(examenData);
  });

  it('should delete an examen', () => {
    service.deleteExamen(1).subscribe((res) => {
      expect(res).toEqual({});
    });

    const req = httpMock.expectOne(`${service.apiUrl}/1`);
    expect(req.request.method).toBe('DELETE');
    req.flush({});
  });

  it('should get examens by idTestAnalyse', () => {
    const examenData: Examen[] = [new Examen(1, 1, 1, 1, 'test result')];

    service.getExamensByIdTestAnalyse(1).subscribe((res) => {
      expect(res).toEqual(examenData);
    });

    const req = httpMock.expectOne(`${service.apiUrl}/by-test-analyse/1`);
    expect(req.request.method).toBe('GET');
    req.flush(examenData);
  });

  it('should get examens by idDossier', () => {
    const examenData: Examen[] = [new Examen(1, 1, 1, 1, 'test result')];

    service.getExamensByIdDossier(1).subscribe((res) => {
      expect(res).toEqual(examenData);
    });

    const req = httpMock.expectOne(`${service.apiUrl}/by-dossier/1`);
    expect(req.request.method).toBe('GET');
    req.flush(examenData);
  });

  it('should get examens by idEpreuve', () => {
    const examenData: Examen[] = [new Examen(1, 1, 1, 1, 'test result')];

    service.getExamensByIdEpreuve(1).subscribe((res) => {
      expect(res).toEqual(examenData);
    });

    const req = httpMock.expectOne(`${service.apiUrl}/by-epreuve/1`);
    expect(req.request.method).toBe('GET');
    req.flush(examenData);
  });
});
