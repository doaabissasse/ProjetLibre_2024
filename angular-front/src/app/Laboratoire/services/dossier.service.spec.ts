import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { DossierService } from './dossier.service';
import { Dossier } from '../Entite_labo/dossier.model';

describe('DossierService', () => {
  let service: DossierService;
  let httpMock: HttpTestingController;
  const baseUrl = 'http://localhost:8085/api/dossiers';

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [DossierService],
    });

    service = TestBed.inject(DossierService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  describe('#getDossiersByUserId', () => {
    it('should return an Observable<Dossier[]>', () => {
      const userId = 1;
      const mockDossiers: ({ titre: string; description: string; utilisateurId: number; id: number } | {
        titre: string;
        description: string;
        utilisateurId: number;
        id: number
      })[] = [
        { id: 1, titre: 'Dossier1', description: 'Description1', utilisateurId: userId },
        { id: 2, titre: 'Dossier2', description: 'Description2', utilisateurId: userId }
      ];

      service.getDossiersByUserId(userId).subscribe(dossiers => {
        expect(dossiers.length).toBe(2);
        expect(dossiers).toEqual(mockDossiers);
      });

      const req = httpMock.expectOne(`${baseUrl}/by-utilisateur/${userId}`);
      expect(req.request.method).toBe('GET');
      req.flush(mockDossiers);
    });
  });


});
