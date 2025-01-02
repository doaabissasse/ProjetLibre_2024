import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestService } from './test.service';
import { TestAnalyse } from './TestAnalyse';

describe('TestService', () => {
  let service: TestService;
  let httpMock: HttpTestingController;
  const apiUrl = 'http://localhost:8092/api/test-analyses';

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [TestService]
    });

    service = TestBed.inject(TestService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify(); // Verify no pending requests remain
  });

  it('should created', () => {
    expect(service).toBeTruthy();
  });

  describe('ajouterTestAnalyse', () => {
    it('should send a POST request w correct data', () => {
      const testAnalyse: TestAnalyse = {
        id: 1,
        idAnalyse: 123,
        nomTest: 'Test1',
        sousEpreuve: 'SousEpreuve1',
        intervalMinDeReference: 10,
        intervalMaxDeReference: 20,
        uniteDeReference: 'mg/dL',
        details: 'Détails du test'
      };

      service.ajouterTestAnalyse(testAnalyse).subscribe((response) => {
        expect(response).toEqual(testAnalyse);
      });

      const req = httpMock.expectOne(`${apiUrl}`);
      expect(req.request.method).toBe('POST');
      expect(req.request.body).toEqual(testAnalyse);
      req.flush(testAnalyse); // Mock the response
    });
  });

  describe('supprimertestanalyse', () => {
    it('should send a DELETE request with the correct ID', () => {
      const id = 1;

      service.supprimerTestAnalyse(id).subscribe((response) => {
        expect(response).toBeUndefined();
      });

      const req = httpMock.expectOne(`${apiUrl}/${id}`);
      expect(req.request.method).toBe('DELETE');
      req.flush(null); // Mock the response
    });
  });
});
