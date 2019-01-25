import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { AddRoomComponent } from '../add-room/add-room.component'
import { Subject, from } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class RoomService {
  name: String;
  memberUserName : String;
  public API = '//localhost:8080';
  constructor(private http: HttpClient) { }
  getRoomType(memberUserName):Observable<any>{
    return this.http.get(this.API + '/roomtype/bymemberusername/' + memberUserName);
  }
  getRoomStatus():Observable<any>{
    return this.http.get(this.API + '/roomstatusesforroom');
  }
  getRoom(memberUserName):Observable<AddRoomComponent[]>{
    console.log(memberUserName);
    this.name = memberUserName;
    console.log(this.name)
    return this.http.get<AddRoomComponent[]>(this.API + '/rooms/'+ this.name);
  }
  getHotelNameEng(memberUserName):Observable<any>{
    this.memberUserName = memberUserName;
    console.log(this.memberUserName);
    return this.http.get(this.API + '/gethotelnameeng/' + this.memberUserName);
  }
}
