import { TestBed } from '@angular/core/testing';

import { AdminArchEventsService } from './admin-arch-events.service';

describe('AdminArchEventsService', () => {
  let service: AdminArchEventsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminArchEventsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
