import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminUpdSheetComponent } from './admin-upd-sheet.component';

describe('AdminUpdSheetComponent', () => {
  let component: AdminUpdSheetComponent;
  let fixture: ComponentFixture<AdminUpdSheetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminUpdSheetComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminUpdSheetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
