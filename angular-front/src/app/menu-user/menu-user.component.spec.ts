import { TestBed, ComponentFixture } from '@angular/core/testing';
import { MenuUSERComponent } from './menu-user.component';
import { NO_ERRORS_SCHEMA } from '@angular/core';

describe('MenuUSERComponent', () => {
  let component: MenuUSERComponent;
  let fixture: ComponentFixture<MenuUSERComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MenuUSERComponent],
      schemas: [NO_ERRORS_SCHEMA], // Ignore child components like routerLink in the template
    }).compileComponents();

    fixture = TestBed.createComponent(MenuUSERComponent);
    component = fixture.componentInstance;
  });

  afterEach(() => {
    // Clear any mocked localStorage data after each test
    localStorage.clear();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize `user` from localStorage if data exists', () => {
    const mockUserData = { fkIdLaboratoire: 1, name: 'John Doe' };
    localStorage.setItem('user', JSON.stringify(mockUserData));

    component.ngOnInit(); // Trigger the initialization

    expect(component.user).toEqual(mockUserData);
  });

  it('should handle missing user data in localStorage gracefully', () => {

    component.ngOnInit(); // Trigger the initialization

    expect(component.user).toBeUndefined();

  });
});
