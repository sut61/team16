import { TestBed, inject } from '@angular/core/testing';

import { ViewReviewService } from './view-review.service';

describe('ViewReviewService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ViewReviewService]
    });
  });

  it('should be created', inject([ViewReviewService], (service: ViewReviewService) => {
    expect(service).toBeTruthy();
  }));
});
