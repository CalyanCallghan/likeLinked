import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GroupMygroupComponent } from './group-mygroup.component';

describe('GroupMygroupComponent', () => {
  let component: GroupMygroupComponent;
  let fixture: ComponentFixture<GroupMygroupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GroupMygroupComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GroupMygroupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
