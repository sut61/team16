import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';

@Injectable({
  providedIn: 'root'
})
export class BookingcarService {
  public API = '//localhost:8080';

  constructor(private http: HttpClient) { }

  getCarName(): Observable<any> {
  return this.http.get(this.API + '/carnull');
  }

  getHotel(): Observable<any> {
    return this.http.get(this.API + '/hotels');
  }

  getBookingCar():Observable<BookingcarService[]>{
    return this.http.get<BookingcarService[]>(this.API + '/carnotnull');
  }

  getHotelShow(bookingcarId): Observable<any> {
    return this.http.get(this.API + '/bookingcar/' + bookingcarId);
  }

}
