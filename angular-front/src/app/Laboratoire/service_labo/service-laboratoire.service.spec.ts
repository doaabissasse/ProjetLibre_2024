import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ServiceLaboratoireService } from './service-laboratoire.service';
import { Laboratoire } from '../Entite_labo/laboratoire';

describe('ServiceLaboratoireService', () => {
  let service: ServiceLaboratoireService;
  let httpMock: HttpTestingController;
  const baseUrl = 'http://localhost:8083/laboratoires';

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ServiceLaboratoireService],
    });

    service = TestBed.inject(ServiceLaboratoireService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  describe('#getLaboratoiresList', () => {
    it('should return an Observable<Laboratoire[]>', () => {
      const mockLaboratoires: ({ contact: string; adresse: string; id: number; nom: string } | {
        contact: string;
        adresse: string;
        id: number;
        nom: string
      })[] = [
        { id: 1, nom: 'Lab1', adresse: 'Address1', contact: 'Contact1' },
        { id: 2, nom: 'Lab2', adresse: 'Address2', contact: 'Contact2' }
      ];

      service.getLaboratoiresList().subscribe(laboratoires => {
        expect(laboratoires.length).toBe(2);
        expect(laboratoires).toEqual(mockLaboratoires);
      });

      const req = httpMock.expectOne(`${baseUrl}`);
      expect(req.request.method).toBe('GET');
      req.flush(mockLaboratoires);
    });
  });


  describe('#supprimerLaboratoire', () => {
    it('should delete a laboratoire and return a void observable', () => {
      const id = 1;

      service.supprimerLaboratoire(id).subscribe(response => {
        expect(response).toBeUndefined();
      });

      const req = httpMock.expectOne(`${baseUrl}/${id}`);
      expect(req.request.method).toBe('DELETE');
      req.flush(null);
    });
  });

  describe('#rechercherLaboratoires', () => {
    it('should return a filtered list of laboratoires based on name', () => {
      const nom = 'Lab1';
      const mockLaboratoires: { contact: string; adresse: string; id: number; nom: string }[] = [
        { id: 1, nom: 'Lab1', adresse: 'Address1', contact: 'Contact1' }
      ];

      service.rechercherLaboratoires(nom).subscribe(laboratoires => {
        expect(laboratoires.length).toBe(1);
        expect(laboratoires).toEqual(mockLaboratoires);
      });

      const req = httpMock.expectOne(`${baseUrl}/search?nom=${nom}`);
      expect(req.request.method).toBe('GET');
      req.flush(mockLaboratoires);
    });
  });



  describe('#getAnalysesByLaboratoire', () => {
    it('should return analyses for a given laboratoire ID', () => {
      const idLabo = 1;
      const mockAnalyses = [{ id: 1, nom: 'Analysis1' }, { id: 2, nom: 'Analysis2' }];

      service.getAnalysesByLaboratoire(idLabo).subscribe(analyses => {
        expect(analyses.length).toBe(2);
        expect(analyses).toEqual(mockAnalyses);
      });

      const req = httpMock.expectOne(`${baseUrl}/${idLabo}/analyses`);
      expect(req.request.method).toBe('GET');
      req.flush(mockAnalyses);
    });
  });
});
