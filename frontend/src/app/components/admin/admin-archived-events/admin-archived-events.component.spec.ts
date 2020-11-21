import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminArchivedEventsComponent } from './admin-archived-events.component';

describe('AdminArchivedEventsComponent', () => {
  let component: AdminArchivedEventsComponent;
  let fixture: ComponentFixture<AdminArchivedEventsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminArchivedEventsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminArchivedEventsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
