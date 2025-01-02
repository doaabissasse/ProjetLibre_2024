import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ContactService } from './contact.service';
import { Contacte } from '../Contacte';
import { Adresse } from '../../Adresses/Adresse';

describe('ContactService', () => {
  let service: ContactService;
  let httpMock: HttpTestingController;
  const apiUrl = 'http://localhost:8084/contactes';

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ContactService]
    });

    service = TestBed.inject(ContactService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should call ajouterContact and return a Contacte', () => {
    const mockContact: Contacte = {
      id: 1,
      idLaboratoire: 1,
      idAdresse: 1,
      tel: '123456789',
      fax: '987654321',
      email: 'test@example.com'
    };

    service.ajouterContact(mockContact).subscribe(contact => {
      expect(contact).toEqual(mockContact);
    });

    const req = httpMock.expectOne(`${apiUrl}`);
    expect(req.request.method).toBe('POST');
    req.flush(mockContact);
  });

  it('should call getAdresseContact and return an Adresse', () => {
    const mockAdresse: Adresse = {
      id: 1,
      numVoie: '12',
      nomVoie: 'Main Street',
      codePostal: '12345',
      ville: 'Testville',
      commune: 'Testtown'
    };

    service.getAdresseContact(1).subscribe(adresse => {
      expect(adresse).toEqual(mockAdresse);
    });

    const req = httpMock.expectOne(`${apiUrl}/1/adresse`);
    expect(req.request.method).toBe('GET');
    req.flush(mockAdresse);
  });

  it('should call supprimerContact and return void', () => {
    service.supprimerContact(1).subscribe();

    const req = httpMock.expectOne(`${apiUrl}/1`);
    expect(req.request.method).toBe('DELETE');
    req.flush({});
  });
});
