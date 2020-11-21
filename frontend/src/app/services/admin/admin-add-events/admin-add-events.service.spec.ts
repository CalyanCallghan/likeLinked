import { TestBed } from '@angular/core/testing';

import { AdminAddEventsService } from './admin-add-events.service';

describe('AdminAddEventsService', () => {
  let service: AdminAddEventsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminAddEventsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
