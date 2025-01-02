import { TestBed, ComponentFixture } from '@angular/core/testing';
import { AnalyseComponent } from './analyse.component';
import { ServiceLaboratoireService } from '../../Laboratoire/service_labo/service-laboratoire.service';
import { AnalyseService } from '../../Analyses/services/analyse.service';
import { ActivatedRoute, Router } from '@angular/router';
import { of, throwError } from 'rxjs';

describe('AnalyseComponent', () => {
  let component: AnalyseComponent;
  let fixture: ComponentFixture<AnalyseComponent>;
  let laboratoireServiceMock: any;
  let routerMock: any;
  let routeMock: any;

  beforeEach(async () => {
    laboratoireServiceMock = {
      getAnalysesByLaboratoire: jest.fn(),
    };

    routerMock = {
      navigate: jest.fn(),
    };

    routeMock = {
      paramMap: of({
        get: jest.fn().mockReturnValue('1'), // Simulate route parameter
      }),
    };

    await TestBed.configureTestingModule({
      declarations: [AnalyseComponent],
      providers: [
        { provide: ServiceLaboratoireService, useValue: laboratoireServiceMock },
        { provide: AnalyseService, useValue: {} }, // Mock AnalyseService if necessary
        { provide: ActivatedRoute, useValue: routeMock },
        { provide: Router, useValue: routerMock },
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(AnalyseComponent);
    component = fixture.componentInstance;
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });




  it('should handle errors during data retrieval', () => {
    laboratoireServiceMock.getAnalysesByLaboratoire.mockReturnValue(throwError(() => new Error('Erreur')));


    expect(component.analyses).toEqual([]);

  });

  it('should navigate to test-epreuve route with correct analyseId', () => {
    component.visualiserTests(123);
    expect(routerMock.navigate).toHaveBeenCalledWith(['/test-epreuve', 123]);
  });
});
