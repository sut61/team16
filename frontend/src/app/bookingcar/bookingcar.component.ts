import { Component, OnInit, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DataSource } from '@angular/cdk/collections';
import { Observable } from 'rxjs/Observable';
import { Router, ActivatedRoute } from '@angular/router';
import { BookingcarService } from '../shared/bookingcar.service';

export interface BooklingCarComponent {
  carName: String;
  bookingCar: {
    bookingcarId: number;
    dateStart: Date;
    dateEnd: Date;
  }
}

@Component({
  selector: 'app-bookingcar',
  templateUrl: './bookingcar.component.html',
  styleUrls: ['./bookingcar.component.css']
})
export class BookingcarComponent implements OnInit {
  carNames: Array<any>;
  Hotels: Array<any>;
  HotelShow: String;
  bookingcars: Array<any>;
  select: any = {
    inputStartDate: '',
    inputEndDate: '',
    inputUserName: '',
    driverIdInput: '',
    carNameSelect: '',
    hotelSelect: '',
    bookingcarId: ''
  }

  displayedColumns: string[] = ['id', 'car', 'price', 'start', 'end'];
  constructor(private bookingcarService: BookingcarService, private httpClient: HttpClient, private route: ActivatedRoute, private router: Router) {
    this.select.inputUserName = this.route.snapshot.paramMap.get('inputUserName')
  }

  ngOnInit() {
    this.bookingcarService.getCarName().subscribe(data => {
      this.carNames = data;
      console.log(data);
    });
    this.bookingcarService.getBookingCar().subscribe(data => {
      this.bookingcars = data;
      console.log(data);
    });
    this.bookingcarService.getHotel().subscribe(data => {
      this.Hotels = data;
      console.log(data);
    });
    this.bookingcarService.getHotelShow(this.select.bookingcarId).subscribe(data => {
      this.HotelShow = data;
      console.log(data);
    });
  }

  save_func() {
    if (this.select.carNameSelect === '')
      alert('Please Enter CarName');
    else if (this.select.hotelSelect === '')
      alert('Please Enter Hotel');
    else if (this.select.inputStartDate === '')
      alert('Please Enter StartDate');
    else if (this.select.inputEndDate === '')
      alert('Please Enter EndDate');
    else if (this.select.driverIdInput === '')
      alert('Please Enter DriverId');
    else {
      this.httpClient.post('http://localhost:8080/bookingcar/' + this.select.hotelSelect + '/' + this.select.carNameSelect + '/' + this.select.inputStartDate + '/' + this.select.inputEndDate
        + '/' + this.select.driverIdInput + '/' + this.select.inputUserName, this.select)
        .subscribe(
          data => {
            alert("Booking Successfully!!");
            console.log('POST Request is successful', data);
            this.bookingcarService.getBookingCar().subscribe(data => {
              this.bookingcars = data;
              console.log(data);
            });
          },
          error => {
            alert("Please check the data pattern. Again!!");
            console.log('Rrror', error);
          }

        );
    }
  }
  review() {
    this.router.navigate(['/view/review', this.select.inputUserName]);
  }
  foodOrder() {
    this.router.navigate(['/foodorder', this.select.inputUserName]);
  }
  reservationroom() {
    this.router.navigate(['/reservationroom', this.select.inputUserName]);
  }
  bookingcar() {
    this.router.navigate(['/bookingcar', this.select.inputUserName]);
  }

}
export class BookingcarDataSource extends DataSource<any> {
  constructor(private bookingcarService: BookingcarService) {
    super();
  }
  connect(): Observable<BookingcarService[]> {
    return this.bookingcarService.getBookingCar();
  }
  disconnect() { }
}

