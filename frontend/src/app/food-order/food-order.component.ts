import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DataSource } from '@angular/cdk/collections';
import { Observable } from 'rxjs/Observable';
import { ActivatedRoute, Router } from '@angular/router';
import { FoodOrderService } from '../shared/food-order.service';
export interface FoodOrderComponent {
  foodOrderId: number;
  totalProiceOrder: number;
  newRoomEntity: {
    roomNumber: String;
  }
  newListEntity: {
    listName: String;
    priceFood: number;
    listPrice: number;
  }
}
@Component({
  selector: 'app-food-order',
  templateUrl: './food-order.component.html',
  styleUrls: ['./food-order.component.css']
})
export class FoodOrderComponent implements OnInit {
  hotel: Array<any>;
  room: Array<any>;
  foodType: Array<any>;
  list: Array<any>;
  select: any = {
    hotelSelect: '',
    roomSelect: '',
    foodTypeSelect: '',
    listSelect: '',
    foodPriceOutput: '',
    customer: '',
    amountInput: ''

  }
  displayedColumns: string[] = ['id', 'room', 'list', 'price'];
  dataSource = new OrderDataSource(this.foodOrderService);
  constructor(private route: ActivatedRoute, private router: Router , private foodOrderService: FoodOrderService, private httpClient: HttpClient) {
    this.select.customer = this.route.snapshot.paramMap.get('inputUserName');
  }

  ngOnInit() {
    this.refresh();
    this.foodOrderService.getHotel().subscribe(data => {
      this.hotel = data;
      console.log(data)
    });

    // this.foodOrderService.getRoom().subscribe(data => {
    //   this.room = data;
    //   console.log(data)
    // });

    this.foodOrderService.getFoodType().subscribe(data => {
      this.foodType = data;
      console.log(data)
    });

    // this.foodOrderService.getList().subscribe(data => {
    //   this.list = data;
    //   console.log(data)
    // });

  }

  // close ngOnInit
  refresh() {
    this.foodOrderService.getFoodOrder().subscribe((res) => {
      this.room = res;
      this.dataSource = new OrderDataSource(this.foodOrderService);
    });
  }
  findRoom() {
    this.foodOrderService.getRoom(this.select.hotelSelect).subscribe(data => {
      this.room = data;
      this.select.roomSelect = '';
    })
  }

  findList() {
    this.foodOrderService.getList(this.select.foodTypeSelect).subscribe(data => {
      this.list = data;
      this.select.listSelect = '';
      this.select.foodPriceOutput = '';
    })
  }

  findPrice() {
    this.foodOrderService.getPrice(this.select.listSelect).subscribe(data => {
      this.select.foodPriceOutput = data;
      console.log(data)
    })
  }
  bookingcar(){
    this.router.navigate(['/bookingcar', this.select.customer]);
  }
  reservation(){
    this.router.navigate(['/reservationroom', this.select.customer]);
  }
  review() {
    this.router.navigate(['/view/review', this.select.customer]);
  }

  add() {
    console.log(this.select.amountInput);
    if (this.select.hotelSelect === '' || this.select.roomSelect === '' || this.select.foodTypeSelect === '' || this.select.listSelect === ''|| this.select.amountInput === '') {
      alert('Please Enter all Data');
      location.reload();
    }
    else {
      this.httpClient.get('http://localhost:8080/foodorder/' + this.select.hotelSelect + '/' + this.select.roomSelect + '/' + this.select.listSelect + '/' + this.select.customer +'/'+ this.select.amountInput, this.select)
        .subscribe(
          data => {
            console.log(data);
            if (data) {
              console.log('PUT Request is successful');
              alert('-Success-');
              this.refresh();
              location.reload();
            }
            else
              alert(this.select.listSelect + ':  Not enough! or Out of stock!');
              location.reload();
          },
          error => {
            console.log('Error', error);
            
          }
        )
    }
    this.refresh();
  }
}

export class OrderDataSource extends DataSource<any> {
  constructor(private foodOrderService: FoodOrderService) {
    super();
  }
  connect(): Observable<FoodOrderComponent[]> {
    return this.foodOrderService.getFoodOrder();
  }
  disconnect() { }
}
