import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Subject } from 'rxjs';
import { PaymentComponent } from '../payment/payment.component';



@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  public API = '//localhost:8080';
  check : Boolean
  private serviceUrl = '//localhost:8080/PaymentEntity';
  constructor(private http: HttpClient) { }
  getBank() :  Observable<any>{
    return this.http.get(this.API+'/bankEntity');
  }
  getRoom() :  Observable<any>{
    return this.http.get(this.API+'/reservationRoomEntity');
  }
  getRoomMeet() :  Observable<any>{
    return this.http.get(this.API+'/reservationMeetingEventRoomEntity');
  }
  getBookingCar() :  Observable<any>{
    return this.http.get(this.API+'/bookingCarEntity');
  }
  getPayment(customerName):Observable<PaymentComponent[]>{
    return this.http.get<PaymentComponent[]>(this.API + '/payment/' + customerName);
  }
}



