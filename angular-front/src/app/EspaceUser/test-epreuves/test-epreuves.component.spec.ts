import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TestEpreuvesComponent } from './test-epreuves.component';

describe('TestEpreuvesComponent', () => {
  let component: TestEpreuvesComponent;
  let fixture: ComponentFixture<TestEpreuvesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TestEpreuvesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TestEpreuvesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
