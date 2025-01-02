import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuUSERComponent } from './menu-user.component';

describe('MenuUSERComponent', () => {
  let component: MenuUSERComponent;
  let fixture: ComponentFixture<MenuUSERComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MenuUSERComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MenuUSERComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
