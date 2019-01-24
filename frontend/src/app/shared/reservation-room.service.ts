import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { MainReservationRoomComponent } from '../main-reservation-room/main-reservation-room.component';
import { Subject} from 'rxjs';
@Injectable()
export class ReservationRoomService {
  public API = '//localhost:8080';
  id :number;
  name: String;
  constructor(private http: HttpClient) { }
  getRoom(roomTypeId):Observable<any>{
    this.id = roomTypeId
    console.log(this.id)
    return this.http.get(this.API + '/getroombyroomtype/' + roomTypeId);
  }
  getRoomType(hotelId):Observable<any>{
    return this.http.get(this.API + '/roomtype/' + hotelId);
  }
  
  getHotel():Observable<any>{
    return this.http.get(this.API + '/hotels');
  }

  getPromotion(hotelId):Observable<any>{
    return this.http.get(this.API + '/promotion/'+ hotelId);
  }

  getReservationRoom(customerName):Observable<MainReservationRoomComponent[]>{
    this.name = customerName
    return this.http.get<MainReservationRoomComponent[]>(this.API + '/reservationroom/' + this.name)
  }


}
