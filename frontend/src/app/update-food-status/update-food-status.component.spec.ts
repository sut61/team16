import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateFoodStatusComponent } from './update-food-status.component';

describe('UpdateFoodStatusComponent', () => {
  let component: UpdateFoodStatusComponent;
  let fixture: ComponentFixture<UpdateFoodStatusComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateFoodStatusComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateFoodStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
