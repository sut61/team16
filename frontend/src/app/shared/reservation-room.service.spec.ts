import { TestBed, inject } from '@angular/core/testing';

import { ReservationRoomService } from './reservation-room.service';

describe('ReservationRoomService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ReservationRoomService]
    });
  });

  it('should be created', inject([ReservationRoomService], (service: ReservationRoomService) => {
    expect(service).toBeTruthy();
  }));
});
