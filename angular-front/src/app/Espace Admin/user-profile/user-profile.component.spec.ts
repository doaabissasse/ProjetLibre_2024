import { ComponentFixture, TestBed } from '@angular/core/testing';
import { UserProfileComponent } from './user-profile.component';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';
import {render} from '@testing-library/angular';

describe('UserProfileComponent', () => {
  let component: UserProfileComponent;
  let fixture: ComponentFixture<UserProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserProfileComponent ],
      imports: [RouterTestingModule]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserProfileComponent);
    component = fixture.componentInstance;

    // Mock the localStorage to return user data
    const mockUserData = {
      nomComplet: 'John Doe',
      email: 'john.doe@example.com',
      roles: [{ name: 'Admin' }],
      numTel: '123456789',
      profession: 'Developer',
      signature: 'Signature'
    };
    localStorage.setItem('user', JSON.stringify(mockUserData));

    fixture.detectChanges(); // Trigger ngOnInit
  });

  it('should retrieve user data from localStorage', () => {
    expect(component.user).toBeTruthy();
    expect(component.user.nomComplet).toEqual('John Doe');
    expect(component.user.email).toEqual('john.doe@example.com');
    expect(component.user.roles[0].name).toEqual('Admin');
    expect(component.user.numTel).toEqual('123456789');
    expect(component.user.profession).toEqual('Developer');
    expect(component.user.signature).toEqual('Signature');
  });


});
