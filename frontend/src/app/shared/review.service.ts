import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Reservation } from '../view-review/view-review.component';
@Injectable({
  providedIn: 'root'
})
export class ReviewService {
  public API = '//localhost:8080';
  private serviceReservation = '//localhost:8080/ReservationPaid';
  constructor(private http: HttpClient) { }
  getHotelNameEng():Observable<any>{
    return this.http.get(this.API + '/hotels');
  }
  getProvinceName(getHotelNameEng):Observable<any>{
    return this.http.get(this.API+'/hoetls/'+ getHotelNameEng);
  }
  getUserName():Observable<any>{
    return this.http.get(this.API+'/users');
  }
  getReservation(inputEmail):Observable<Reservation[]>{
    return this.http.get<Reservation[]>(this.serviceReservation + '/' + inputEmail);
  }
}
