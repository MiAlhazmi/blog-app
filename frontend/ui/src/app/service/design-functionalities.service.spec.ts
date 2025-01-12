import { TestBed } from '@angular/core/testing';

import { DesignFunctionalitiesService } from './design-functionalities.service';

describe('DesignFunctionalitiesService', () => {
  let service: DesignFunctionalitiesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DesignFunctionalitiesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
