import { TestBed, ComponentFixture } from '@angular/core/testing';
import { ContacteComponent } from './contacte.component';
import { ActivatedRoute } from '@angular/router';
import { of, throwError } from 'rxjs';
import { ServiceLaboratoireService } from '../../Laboratoire/service_labo/service-laboratoire.service';
import { ContactService } from '../../Contactes/contacte_sevice/contact.service';

describe('ContacteComponent', () => {
  let component: ContacteComponent;
  let fixture: ComponentFixture<ContacteComponent>;
  let laboratoireServiceMock: any;
  let contactServiceMock: any;
  let routeMock: any;

  beforeEach(async () => {
    laboratoireServiceMock = {
      getContactsByLaboratoireId: jest.fn(),
    };

    contactServiceMock = {
      getAdresseContact: jest.fn(),
    };

    routeMock = {
      paramMap: of({
        get: jest.fn().mockReturnValue('1'), // Simulate route parameter
      }),
    };

    await TestBed.configureTestingModule({
      declarations: [ContacteComponent],
      providers: [
        { provide: ServiceLaboratoireService, useValue: laboratoireServiceMock },
        { provide: ContactService, useValue: contactServiceMock },
        { provide: ActivatedRoute, useValue: routeMock },
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(ContacteComponent);
    component = fixture.componentInstance;
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });


  it('should fetch and populate contacts on successful response', () => {
    const mockContacts = [
      {
        id: 1,
        tel: '123456789',
        fax: '123456',
        email: 'contact1@example.com',
        adresse: null, // Initially no address
      },
      {
        id: 2,
        tel: '987654321',
        fax: '654321',
        email: 'contact2@example.com',
        adresse: null, // Initially no address
      },
    ];
    laboratoireServiceMock.getContactsByLaboratoireId.mockReturnValue(of(mockContacts));

  });

  it('should handle errors during contacts retrieval', () => {
    laboratoireServiceMock.getContactsByLaboratoireId.mockReturnValue(throwError(() => new Error('Erreur')));


    expect(component.contacts).toEqual([]);

  });

  it('should fetch and populate address for a contact when afficherAdresse is called', () => {
    const mockAdresse = {
      numVoie: '10',
      nomVoie: 'Rue de la Paix',
      codePostal: '12345',
      ville: 'Paris',
      commune: 'Commune de Paris',
    };
    contactServiceMock.getAdresseContact.mockReturnValue(of(mockAdresse));

    component.contacts = [
      { id: 1, tel: '123456789', fax: '123456', email: 'contact1@example.com', adresse: null },
    ];
    component.afficherAdresse(1);

    expect(component.contacts[0].adresse).toEqual(mockAdresse);
  });


});
