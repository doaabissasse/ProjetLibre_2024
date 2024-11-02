import { TestBed } from '@angular/core/testing';

import { ServiceLaboratoireService } from './service-laboratoire.service';

describe('ServiceLaboratoireService', () => {
  let service: ServiceLaboratoireService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceLaboratoireService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
