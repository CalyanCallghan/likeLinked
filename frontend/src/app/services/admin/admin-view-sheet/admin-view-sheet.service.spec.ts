import { TestBed } from '@angular/core/testing';

import { AdminViewSheetService } from './admin-view-sheet.service';

describe('AdminViewSheetService', () => {
  let service: AdminViewSheetService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminViewSheetService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
