import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { EpreuveService } from './epreuve.service';
import { Epreuve } from './Epreuve';
import { of } from 'rxjs';

describe('EpreuveService', () => {
  let service: EpreuveService;
  let httpMock: HttpTestingController;
  const baseUrl = 'http://localhost:8094/api/epreuves';

  const mockEpreuves: Epreuve[] = [
    { id: 1, idAnalyse: 101, nom: 'Epreuve 1' },
    { id: 2, idAnalyse: 102, nom: 'Epreuve 2' }
  ];

  const newEpreuve: Epreuve = { id: 3, idAnalyse: 103, nom: 'Epreuve 3' };

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [EpreuveService]
    });
    service = TestBed.inject(EpreuveService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should retrieve all epreuves via GET', () => {
    service.getEpreuves().subscribe(epreuves => {
      expect(epreuves.length).toBe(2);
      expect(epreuves).toEqual(mockEpreuves);
    });

    const req = httpMock.expectOne(baseUrl);
    expect(req.request.method).toBe('GET');
    req.flush(mockEpreuves);
  });

  it('should add a new epreuve via POST', () => {
    service.addEpreuve(newEpreuve).subscribe(epreuve => {
      expect(epreuve).toEqual(newEpreuve);
    });

    const req = httpMock.expectOne(baseUrl);
    expect(req.request.method).toBe('POST');
    req.flush(newEpreuve);
  });

  it('should delete an epreuve via DELETE', () => {
    const epreuveId = 1;

    service.deleteEpreuve(epreuveId).subscribe(() => {
      expect(true).toBe(true); // Just a placeholder
    });

    const req = httpMock.expectOne(`${baseUrl}/${epreuveId}`);
    expect(req.request.method).toBe('DELETE');
    req.flush(null);
  });
});
