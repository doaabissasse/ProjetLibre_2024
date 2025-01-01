import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DossiersUsersListComponent } from './dossiers-users-list.component';

describe('DossiersUsersListComponent', () => {
  let component: DossiersUsersListComponent;
  let fixture: ComponentFixture<DossiersUsersListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DossiersUsersListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DossiersUsersListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
