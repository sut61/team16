import { Component, OnInit } from '@angular/core';
import { FoodOrderService } from '../shared/food-order.service';
import { HttpClient } from '@angular/common/http';
import { DataSource } from '@angular/cdk/collections';
import { Observable } from 'rxjs/Observable';
import { ActivatedRoute, Router } from '@angular/router';

export interface UpdateFoodStatusComponent {
  totalPriceFoodId: number;
  newRoomEntity: {
    roomNumber: String;
  }
  newFoodPaymentEntity: {
    foodPaymentStatus: String;
  }
}
@Component({
  selector: 'app-update-food-status',
  templateUrl: './update-food-status.component.html',
  styleUrls: ['./update-food-status.component.css']
})
export class UpdateFoodStatusComponent implements OnInit {
  totalPriceFood: {
    newRoomEntity: {
      roomNumber: String
    }
  }
  select: any = {
    totalPriceFoodOutput: '',
    roomSelect: '',
    memberUserName: ''

  }

  displayedColumns: string[] = ['id', 'room', 'price', 'status'];
  dataSource = new OrderDataSource(this.foodOrderService);
  constructor(private route: ActivatedRoute, private router: Router, private foodOrderService: FoodOrderService, private httpClient: HttpClient) {
      this.select.memberUserName = this.route.snapshot.paramMap.get('inputUserName');
   }

  ngOnInit() {
    this.foodOrderService.getTotalPriceFoods().subscribe(data => {
      this.totalPriceFood = data;
      console.log(this.totalPriceFood)
    });
  }
  Promotion(){
    this.router.navigate(['/promotion', this.select.memberUserName]);
  }
  refresh() {
    this.foodOrderService.getTotalPriceFood().subscribe((res) => {
      this.dataSource = new OrderDataSource(this.foodOrderService);
    });
  }
  addRoom() {
    this.router.navigate(['/addroom', this.select.memberUserName]);
  }
  UpdateRoomStatus() {
    this.router.navigate(['/roomstatus', this.select.memberUserName]);
  }
  ttPrice() {

    console.log(this.select.roomSelect)
    this.foodOrderService.getTotalprice(this.select.roomSelect).subscribe(data => {
      this.select.totalPriceFoodOutput = data;
    })
  }

  pay() {
    if (this.select.roomSelect == '') {
      alert('Please Select Room');
    }
    else {
      this.httpClient.get('http://localhost:8080/updatefoodstatus/' + this.select.roomSelect, this.select)
        .subscribe(
          data => {
            console.log(data);
            if (data) {
              console.log('PUT Request is successful', data);
              alert('-Success-');
              this.refresh();
              location.reload();
            }
            else
              alert('Room number ' + this.select.inputRoomNumber + ' in hotel ' + this.select.hotelNameSelect + ' have alrady exist')
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
  connect(): Observable<UpdateFoodStatusComponent[]> {
    return this.foodOrderService.getTotalPriceFood();
  }
  disconnect() { }
}
