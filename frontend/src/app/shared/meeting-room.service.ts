import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { AddMeetingEventRoomComponent } from '../add-meeting-event-room/add-meeting-event-room.component'
import { Subject, from } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class MeetingRoomService {

  public API = '//localhost:8080';
  constructor(private http: HttpClient) { }
  getHotelNameEng(memberUserName):Observable<any>{
    return this.http.get(this.API + '/gethotelnameeng/' + memberUserName);
  }
  getMeetingEventRoomType():Observable<any>{
    return this.http.get(this.API + '/meetingeventroomtypes');
  }
  getRoomStatus():Observable<any>{
    return this.http.get(this.API + '/roomstatusesformeetingroom');
  }
  getAllByMeetingRoomId(id):Observable<any>{
    return this.http.get(this.API + '/meetingeventmtmidandrstid/' + id);
  }
  getMeetingEventRoom():Observable<AddMeetingEventRoomComponent[]>{
    return this.http.get<AddMeetingEventRoomComponent[]>(this.API + '/meetingeventrooms');
  }
  getMeetingRoomStatusWhenClick(selectRowMeetingRoomId):Observable<any>{
    return this.http.get(this.API + '/meetingeventroomstatus/' + selectRowMeetingRoomId);
  }
}
