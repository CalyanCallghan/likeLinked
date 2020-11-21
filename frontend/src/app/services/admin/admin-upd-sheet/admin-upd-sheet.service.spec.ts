import { TestBed } from '@angular/core/testing';

import { AdminUpdSheetService } from './admin-upd-sheet.service';

describe('AdminUpdSheetService', () => {
  let service: AdminUpdSheetService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminUpdSheetService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
