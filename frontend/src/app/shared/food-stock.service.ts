import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { FoodOrderComponent } from '../food-order/food-order.component'
import { Subject, from } from 'rxjs';
import { UpdateFoodStatusComponent } from '../update-food-status/update-food-status.component';
import { FoodStockComponent } from '../food-stock/food-stock.component';
@Injectable({
  providedIn: 'root'
})
export class FoodStockService {
  public API = '//localhost:8080';
  constructor(private http: HttpClient) { }
  getHotel():Observable<any>{
    return this.http.get(this.API + '/hotels');
  }

  getFoodType():Observable<any>{
    return this.http.get(this.API + '/foodtypes');
  }
  getList(foodType):Observable<any>{
    return this.http.get(this.API + '/list/' + foodType);
  }
  
  getPrice(list):Observable<any>{
    return this.http.get(this.API + '/listprice/' + list);
  }

  getFoodStock():Observable<FoodStockComponent[]>{
    return this.http.get<FoodStockComponent[]>(this.API + '/foodstocks');
  }
  
}
