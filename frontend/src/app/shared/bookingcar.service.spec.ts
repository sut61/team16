import { TestBed, inject } from '@angular/core/testing';

import { BookingcarService } from './bookingcar.service';

describe('BookingcarService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [BookingcarService]
    });
  });

  it('should be created', inject([BookingcarService], (service: BookingcarService) => {
    expect(service).toBeTruthy();
  }));
});
