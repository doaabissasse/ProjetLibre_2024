import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { UserService } from './user.service';
import { User } from './user';

describe('UserService', () => {
  let service: UserService;
  let httpMock: HttpTestingController;

  const apiUrl = 'http://localhost:8088/api/v1';
  const laboratoireApiUrl = 'http://localhost:8083';

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [UserService],
    });

    service = TestBed.inject(UserService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify(); // Ensure no unmatched HTTP requests
  });


  it('should fetch users with role "User"', () => {
    const dummyUsers = [{ id: 1, name: 'Jane Doe' }, { id: 2, name: 'Alice' }];

    service.getUsersWithRole().subscribe(users => {
      expect(users).toEqual(dummyUsers);
    });

    const req = httpMock.expectOne(`${apiUrl}/utilisateurs/role/User`);
    expect(req.request.method).toBe('GET');
    req.flush(dummyUsers);
  });

  it('should fetch all users', () => {
    const dummyUsers = [{ id: 1, name: 'John Doe' }, { id: 2, name: 'Jane Doe' }];

    service.getAllUsers().subscribe(users => {
      expect(users).toEqual(dummyUsers);
    });

    const req = httpMock.expectOne(`${apiUrl}/utilisateurs`);
    expect(req.request.method).toBe('GET');
    req.flush(dummyUsers);
  });

  it('should fetch the total user count', () => {
    const dummyCount = 5;

    service.getUsersCount().subscribe(count => {
      expect(count).toBe(dummyCount);
    });

    const req = httpMock.expectOne(`${apiUrl}/utilisateurs/count`);
    expect(req.request.method).toBe('GET');
    req.flush(dummyCount);
  });

  it('should fetch the total laboratorie count', () => {
    const dummyCount = 10;

    service.getLaboratoiresCount().subscribe(count => {
      expect(count).toBe(dummyCount);
    });

    const req = httpMock.expectOne(`${laboratoireApiUrl}/laboratoires/count`);
    expect(req.request.method).toBe('GET');
    req.flush(dummyCount);
  });

  it('should delete a user by ID', () => {
    const userId = 1;

    service.deleteUser(userId).subscribe(response => {
      expect(response).toBeUndefined(); // No content expected
    });

    const req = httpMock.expectOne(`${apiUrl}/utilisateurs/${userId}`);
    expect(req.request.method).toBe('DELETE');
    req.flush(null);
  });


  it('should fetch users by laboratoire ID', () => {
    const dummyUsers = [{ id: 1, name: 'User in Lab' }];

    service.getUsersByLaboratoire(101).subscribe(users => {
      expect(users).toEqual(dummyUsers);
    });

    const req = httpMock.expectOne(`${apiUrl}/utilisateurs/laboratoire/101`);
    expect(req.request.method).toBe('GET');
    req.flush(dummyUsers);
  });
});
