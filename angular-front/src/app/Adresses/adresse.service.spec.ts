import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { AdresseService } from './adresse.service';
import { Adresse } from './Adresse';

describe('AdresseService', () => {
  let service: AdresseService;
  let httpMock: HttpTestingController;

  const mockAdresse: Adresse = {
    id: 1,
    numVoie: '123',
    nomVoie: 'Rue de Test',
    codePostal: '75000',
    ville: 'Paris',
    commune: 'Test Commune',
  };

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [AdresseService],
    });

    service = TestBed.inject(AdresseService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify(); // Ensure no unmatched requests are pending
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  describe('#getAdresses', () => {
    it('should return an Observable<Adresse[]>', () => {
      service.getAdresses().subscribe((adresses) => {
        expect(adresses.length).toBe(1);
        expect(adresses).toEqual([mockAdresse]);
      });

      const req = httpMock.expectOne('http://localhost:8093/adresses');
      expect(req.request.method).toBe('GET');
      req.flush([mockAdresse]);
    });
  });

  describe('#ajouterAdresse', () => {
    it('should add a new Adresse and return it', () => {
      service.ajouterAdresse(mockAdresse).subscribe((adresse) => {
        expect(adresse).toEqual(mockAdresse);
      });

      const req = httpMock.expectOne('http://localhost:8093/adresses');
      expect(req.request.method).toBe('POST');
      expect(req.request.body).toEqual(mockAdresse);
      req.flush(mockAdresse);
    });
  });
});
