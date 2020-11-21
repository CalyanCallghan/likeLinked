import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminLiveEventsComponent } from './admin-live-events.component';

describe('AdminLiveEventsComponent', () => {
  let component: AdminLiveEventsComponent;
  let fixture: ComponentFixture<AdminLiveEventsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminLiveEventsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminLiveEventsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
