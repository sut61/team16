import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MainReservationMeetingRoomComponent } from './main-reservation-meeting-room.component';

describe('MainReservationMeetingRoomComponent', () => {
  let component: MainReservationMeetingRoomComponent;
  let fixture: ComponentFixture<MainReservationMeetingRoomComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MainReservationMeetingRoomComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MainReservationMeetingRoomComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
