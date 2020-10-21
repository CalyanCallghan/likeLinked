import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonPostComponent } from './person-post.component';

describe('PersonPostComponent', () => {
  let component: PersonPostComponent;
  let fixture: ComponentFixture<PersonPostComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PersonPostComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PersonPostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
