import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminQueuEventsComponent } from './admin-queu-events.component';

describe('AdminQueuEventsComponent', () => {
  let component: AdminQueuEventsComponent;
  let fixture: ComponentFixture<AdminQueuEventsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminQueuEventsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminQueuEventsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
