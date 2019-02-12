import { TestBed, inject } from '@angular/core/testing';

import { ReservationMeetingRoomService } from './reservation-meeting-room.service';

describe('ReservationMeetingRoomService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ReservationMeetingRoomService]
    });
  });

  it('should be created', inject([ReservationMeetingRoomService], (service: ReservationMeetingRoomService) => {
    expect(service).toBeTruthy();
  }));
});
