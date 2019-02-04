import { TestBed, inject } from '@angular/core/testing';

import { MeetingRoomService } from './meeting-room.service';

describe('MeetingRoomService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MeetingRoomService]
    });
  });

  it('should be created', inject([MeetingRoomService], (service: MeetingRoomService) => {
    expect(service).toBeTruthy();
  }));
});
