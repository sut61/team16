import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { FoodOrderComponent } from '../food-order/food-order.component'
import { Subject, from } from 'rxjs';
import { UpdateFoodStatusComponent } from '../update-food-status/update-food-status.component';
@Injectable({
  providedIn: 'root'
})
export class FoodOrderService {
  public API = '//localhost:8080';
  constructor(private http: HttpClient) { }
  getHotel():Observable<any>{
    return this.http.get(this.API + '/hotels');
  }
  getTotalPriceFoods():Observable<any>{
    return this.http.get(this.API + '/totalpricefoods');
  }
  getRoom(hotel):Observable<any>{
    return this.http.get(this.API + '/room/' + hotel);
  }
  getFoodType():Observable<any>{
    return this.http.get(this.API + '/foodtypes');
  }
  getList(foodType):Observable<any>{
    return this.http.get(this.API + '/list/' + foodType);
  }
  getTotalprice(totalPriceId):Observable<any>{
    return this.http.get(this.API + '/totalprice/' + totalPriceId);
  }
  
  getPrice(list):Observable<any>{
    return this.http.get(this.API + '/listprice/' + list);
  }

  getTotalPriceFood():Observable<UpdateFoodStatusComponent[]>{
    return this.http.get<UpdateFoodStatusComponent[]>(this.API + '/totalpricefoods');
  }
  getFoodOrder():Observable<FoodOrderComponent[]>{
    return this.http.get<FoodOrderComponent[]>(this.API + '/foodorders');
  }
  
}
