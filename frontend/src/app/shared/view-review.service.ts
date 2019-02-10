import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { AddRoomComponent } from '../add-room/add-room.component'
import { Subject, from } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ViewReviewService {
  public API = '//localhost:8080';
  name : String;
  constructor(private http: HttpClient) { }

  getReservation(memberUserName):Observable<any>{
    this.name = memberUserName;
    console.log(this.name);
    return this.http.get(this.API + '/viewreview/' + this.name);
  }
}
