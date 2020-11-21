import { TestBed } from '@angular/core/testing';

import { AdminLiveEventsService } from './admin-live-events.service';

describe('AdminLiveEventsService', () => {
  let service: AdminLiveEventsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminLiveEventsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
