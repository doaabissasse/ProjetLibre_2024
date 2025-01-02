import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { AnalyseService } from './analyse.service';
describe('AnalyseService', () => {
  let service: AnalyseService;
  let httpMock: HttpTestingController;
  let apiUrl = 'http://localhost:8087/api/analyses';

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [AnalyseService]
    });

    service = TestBed.inject(AnalyseService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  describe('supprimerAnalyse', () => {
    it('should delete an analysis by ID', () => {
      const id = 123;

      service.supprimerAnalyse(id).subscribe(response => {
        expect(response).toBeNull();
      });

      const req = httpMock.expectOne(`${apiUrl}/${id}`);
      expect(req.request.method).toBe('DELETE');
      req.flush(null);
    });
  });

  describe('visualiserTests', () => {
    it('should fetch test analyses by analyse ID', () => {
      const analyseId = 123;
      const testAnalyseMock: { name: string; id: number }[] = [{ id: 1, name: 'Test 1' }];

      service.visualiserTests(analyseId).subscribe(tests => {
        expect(tests).toEqual(testAnalyseMock);
      });

      const req = httpMock.expectOne(`${apiUrl}/${analyseId}/tests`);
      expect(req.request.method).toBe('GET');
      req.flush(testAnalyseMock);
    });
  });

  describe('visualiserEpeuves', () => {
    it('should fetch epreuves by analyse ID', () => {
      const analyseId = 123;
      const epreuvesMock: { name: string; id: number }[] = [{ id: 1, name: 'Epreuve 1' }];

      service.visualiserEpeuves(analyseId).subscribe(epreuves => {
        expect(epreuves).toEqual(epreuvesMock);
      });

      const req = httpMock.expectOne(`${apiUrl}/${analyseId}/epreuves`);
      expect(req.request.method).toBe('GET');
      req.flush(epreuvesMock);
    });
  });


  describe('addAnalyse', () => {
    it('should add a new analyse', () => {
      const newAnalyse = { name: 'New Analyse', description: 'New Description' };
      const addedAnalyse: { name: string; description: string; id: number } = { id: 456, ...newAnalyse };

      service.addAnalyse(newAnalyse).subscribe(analyse => {
        expect(analyse).toEqual(addedAnalyse);
      });

      const req = httpMock.expectOne(apiUrl);
      expect(req.request.method).toBe('POST');
      req.flush(addedAnalyse);
    });
  });
});
