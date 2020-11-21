import { TestBed } from '@angular/core/testing';

import { AdminQueuEventsService } from './admin-queu-events.service';

describe('AdminQueuEventsService', () => {
  let service: AdminQueuEventsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminQueuEventsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
