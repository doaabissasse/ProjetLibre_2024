import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatDialogModule, MatDialog } from '@angular/material/dialog';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';
import { TestsComponent } from './tests.component';
import { TestService } from '../test.service';
import { EpreuveService } from '../../epreuves/epreuve.service';
import { AnalyseService } from '../../Analyses/services/analyse.service';
import { AjouterTestDialogComponent } from '../ajouter-test-dialog/ajouter-test-dialog.component';
import { ConfirmationDialogComponent } from '../../Adresses/confirmation-dialog/confirmation-dialog.component';
import { TestAnalyse } from '../TestAnalyse';
import { Epreuve } from '../../epreuves/Epreuve';

describe('TestsComponent', () => {
  let component: TestsComponent;
  let fixture: ComponentFixture<TestsComponent>;
  let testService: TestService;
  let epreuveService: EpreuveService;
  let analyseService: AnalyseService;
  let dialog: MatDialog;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TestsComponent, AjouterTestDialogComponent, ConfirmationDialogComponent],
      imports: [MatDialogModule, HttpClientTestingModule, RouterTestingModule],
      providers: [TestService, EpreuveService, AnalyseService, MatDialog],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TestsComponent);
    component = fixture.componentInstance;
    testService = TestBed.inject(TestService);
    epreuveService = TestBed.inject(EpreuveService);
    analyseService = TestBed.inject(AnalyseService);
    dialog = TestBed.inject(MatDialog);

    component.analyseId = 1; // Example ID
    fixture.detectChanges();
  });

  afterEach(() => {
    jest.clearAllMocks();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });


  });
