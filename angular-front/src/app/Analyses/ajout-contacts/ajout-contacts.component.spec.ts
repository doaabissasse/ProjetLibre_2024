import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AjoutContactsComponent } from './ajout-contacts.component';

describe('AjoutContactsComponent', () => {
  let component: AjoutContactsComponent;
  let fixture: ComponentFixture<AjoutContactsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AjoutContactsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AjoutContactsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
