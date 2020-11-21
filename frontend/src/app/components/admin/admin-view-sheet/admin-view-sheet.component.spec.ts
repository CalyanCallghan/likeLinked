import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminViewSheetComponent } from './admin-view-sheet.component';

describe('AdminViewSheetComponent', () => {
  let component: AdminViewSheetComponent;
  let fixture: ComponentFixture<AdminViewSheetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminViewSheetComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminViewSheetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
