import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MainReservationRoomComponent } from './main-reservation-room.component';

describe('MainReservationRoomComponent', () => {
  let component: MainReservationRoomComponent;
  let fixture: ComponentFixture<MainReservationRoomComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MainReservationRoomComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MainReservationRoomComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
