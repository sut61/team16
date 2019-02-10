import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BookingcarComponent } from './bookingcar.component';

describe('BookingcarComponent', () => {
  let component: BookingcarComponent;
  let fixture: ComponentFixture<BookingcarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BookingcarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BookingcarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
