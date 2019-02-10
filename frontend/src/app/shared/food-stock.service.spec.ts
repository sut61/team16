import { TestBed, inject } from '@angular/core/testing';

import { FoodStockService } from './food-stock.service';

describe('FoodStockService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [FoodStockService]
    });
  });

  it('should be created', inject([FoodStockService], (service: FoodStockService) => {
    expect(service).toBeTruthy();
  }));
});
