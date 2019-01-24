import { TestBed, inject } from '@angular/core/testing';

import { FoodOrderService } from './food-order.service';

describe('FoodOrderService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [FoodOrderService]
    });
  });

  it('should be created', inject([FoodOrderService], (service: FoodOrderService) => {
    expect(service).toBeTruthy();
  }));
});
