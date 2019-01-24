import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PromotionService {
  public API = '//localhost:8080';
  check : Boolean
  private serviceUrl = '//localhost:8080/PromotionEntity';
  constructor(private http: HttpClient) { }
  getPromotionType() :  Observable<any>{
    return this.http.get(this.API+'/promotionTypeEntity');
  }
  getHotel() :  Observable<any>{
    return this.http.get(this.API+'/hotelEntity');
  }
  getRoomType() :  Observable<any>{
    return this.http.get(this.API+'/roomTypeEntity');
  }

}
