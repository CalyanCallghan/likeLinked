import { TestBed } from '@angular/core/testing';

import { AdminGroupsService } from './admin-groups.service';

describe('AdminGroupsService', () => {
  let service: AdminGroupsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminGroupsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
