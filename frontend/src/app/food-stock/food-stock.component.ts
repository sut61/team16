import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DataSource } from '@angular/cdk/collections';
import { Observable } from 'rxjs/Observable';
import { ActivatedRoute, Router } from '@angular/router';
import { FoodStockService } from '../shared/food-stock.service';

export interface FoodStockComponent {
  foodStockId: number;
  stockName: String;
  price: number;
  amount: number;
  newEmployeeEntity: {
    employeeName: String;
  }
  newFoodTypeEntity: {
    foodTypeName: String;
  }
  newHotelEntity: {
    hotelNameEng: String;
  }
}

@Component({
  selector: 'app-food-stock',
  templateUrl: './food-stock.component.html',
  styleUrls: ['./food-stock.component.css']
})
export class FoodStockComponent implements OnInit {

  hotel: Array<any>;
  foodType: Array<any>;
  select: any = {
    hotelSelect: '',
    foodTypeSelect: '',
    priceinput: '',
    employee: '',
    stockName: '',
    amountInput: ''
  }
  status: boolean;
  displayedColumns: string[] = ['hotel', 'type', 'menu', 'price', 'amount', 'employee'];
  dataSource = new OrderDataSource(this.foodStockService);
  constructor(private route: ActivatedRoute, private router: Router , private foodStockService: FoodStockService, private httpClient: HttpClient) {
    this.select.employee = this.route.snapshot.paramMap.get('inputUserName');
    console.log(this.select.employee)
  }
  ngOnInit() {
    this.status = true;

    this.foodStockService.getHotel().subscribe(data => {
      this.hotel = data;
      console.log(data)
    });

    this.foodStockService.getFoodType().subscribe(data => {
      this.foodType = data;
      console.log(data)
    });


  }

  // close ngOnInit
  checkStatus(){
    console.log(this.select.foodTypeSelect)
    this.httpClient.get('http://localhost:8080/foodstock/' + this.select.foodTypeSelect, this.select)
    .subscribe(
      data => {
        if(data){

          console.log(data);
          this.status = true;
        }
        else{
          console.log(data);
          this.status = false;
        }
          
      },
      error => {
        console.log('Error', error);
      }
    )
    
  }

  add() {
    if (this.select.hotelSelect === '' || this.select.foodTypeSelect === '' || this.select.stockName === '' || this.select.priceInput === '' ) {
      alert('Please Enter all Data');
      console.log(this.select.hotelSelect)
      console.log(this.select.foodTypeSelect)
      console.log(this.select.foodNameInput)
      console.log(this.select.priceInput)
      console.log(this.select.amountInput)
    }
    else {
      if(!this.status)
        this.select.amountInput = 1;
      this.httpClient.get('http://localhost:8080/foodstock/' + this.select.hotelSelect + '/' + this.select.foodTypeSelect + '/' + this.select.stockName  + '/' + this.select.priceInput + '/' + this.select.amountInput + '/' + this.select.employee, this.select)
        .subscribe(
          data => {
            console.log(data);
            if (data) {
              console.log('PUT Request is successful', data);
              alert('-Success-');
              location.reload();
            }
            else
              alert('Error Data')
          },
          error => {
            alert('Error: Please Enter Amount! (1-99)')
            console.log('Error', error);
          }
        )
    }
  }
}

export class OrderDataSource extends DataSource<any> {
  constructor(private foodStockService: FoodStockService) {
    super();
  }
  connect(): Observable<FoodStockComponent[]> {
    return this.foodStockService.getFoodStock();
  }
  disconnect() { }
}
