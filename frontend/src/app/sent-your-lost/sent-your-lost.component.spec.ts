import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SentYourLostComponent } from './sent-your-lost.component';

describe('SentYourLostComponent', () => {
  let component: SentYourLostComponent;
  let fixture: ComponentFixture<SentYourLostComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SentYourLostComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SentYourLostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
