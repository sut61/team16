import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { MainReservationMeetingRoomComponent } from '../main-reservation-meeting-room/main-reservation-meeting-room.component';
import { Subject} from 'rxjs';
@Injectable()
export class ReservationMeetingRoomService {
  public API = '//localhost:8080';
  id :number;
  name: String;
  constructor(private http: HttpClient) { }
  getMeetingRoom(meetingRoomTypeSelect):Observable<any>{
   // this.id = meetingEventRoomTypeId
   // console.log(this.id)
    return this.http.get(this.API + '/getmeetingeventroom/' + meetingRoomTypeSelect);
  }
  getMeetingEventRoomType(hotelSelect):Observable<any>{
    return this.http.get(this.API + '/getmeetingeventroomtype/'+hotelSelect);
  }
  
  getHotel():Observable<any>{
    return this.http.get(this.API + '/hotels');
  }
  getTypeTime():Observable<any>{
    return this.http.get(this.API + '/typetimes/');
  }
  getRoomStatus():Observable<any>{
    return this.http.get(this.API + '/roomstatusesformeetingroom');
  }

  getFunction():Observable<any>{
    return this.http.get(this.API + '/ff');
  }

  getReservationMeetingRoom(customerName):Observable<MainReservationMeetingRoomComponent[]>{
    this.name = customerName
    return this.http.get<MainReservationMeetingRoomComponent[]>(this.API + '/reservationmeetingeventroom/' + this.name)
  }

  


}
