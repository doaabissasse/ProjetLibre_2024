import { ComponentFixture, TestBed } from '@angular/core/testing';
import { UserDashboardComponent } from './user-dashboard.component';
import { of } from 'rxjs';

describe('UserDashboardComponent', () => {
  let component: UserDashboardComponent;
  let fixture: ComponentFixture<UserDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserDashboardComponent ]
    })
      .compileComponents();

    fixture = TestBed.createComponent(UserDashboardComponent);
    component = fixture.componentInstance;

    // Mock the user data in localStorage
    const userMockData = {
      nomComplet: 'John Doe',
      email: 'john.doe@example.com',
      numTel: '123456789'
    };
    localStorage.setItem('user', JSON.stringify(userMockData));

    fixture.detectChanges(); // Trigger ngOnInit lifecycle
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });



});
