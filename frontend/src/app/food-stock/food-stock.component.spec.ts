import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FoodStockComponent } from './food-stock.component';

describe('FoodStockComponent', () => {
  let component: FoodStockComponent;
  let fixture: ComponentFixture<FoodStockComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FoodStockComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FoodStockComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
