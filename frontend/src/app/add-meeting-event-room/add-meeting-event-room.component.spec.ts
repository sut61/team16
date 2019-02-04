import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddMeetingEventRoomComponent } from './add-meeting-event-room.component';

describe('AddMeetingEventRoomComponent', () => {
  let component: AddMeetingEventRoomComponent;
  let fixture: ComponentFixture<AddMeetingEventRoomComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddMeetingEventRoomComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddMeetingEventRoomComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
