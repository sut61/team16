import { Component, OnInit } from '@angular/core';
import { PaymentService } from '../shared/payment.service'
import { HttpClient } from '@angular/common/http';
import { DataSource } from '@angular/cdk/collections';
import { Observable } from 'rxjs/Observable';
import { ActivatedRoute, Router } from '@angular/router';
import { MatTableDataSource, MatSort } from '@angular/material';

export interface PaymentComponent {
  date : Date;
  cardId: String;


bankEntity: {
    bankName: String;
  }
  reservationRoomEntity : {
    reservationRoomId : number;
  }
  bookingCarEntity :{
    bookingcarId: number;
  }
  reservationMeetingEventRoomEntity : {
    reservationMeetingEventRoomId : number;
  }
}

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {
  bank: Array<any>;
  room: Array<any>;
  roomMeet: Array<any>;
  bookingCar: Array<any>;

  select: any = {
    cardidSelect: '',
    bankSelect: '',
    roomSelect: '',
    roomMeetSelect: '',
    DateSelect: '',
    cus: '',
    bookingCarSelect: '',
    nameSelect: '',
    cvvSelect: '',


  }
  displayedColumns: string[] = ['id', 'cardid', 'bank', 'room', 'roomMeet', 'car', 'date'];
  dataSource = new PaymentDataSource(this.paymentService, this.select.cus);
  constructor(private router: Router, private route: ActivatedRoute, private paymentService: PaymentService, private httpClient: HttpClient) {
    this.select.cus = this.route.snapshot.paramMap.get('inputUserName');
  }

  ngOnInit() {
   this.refresh(this.select.cus);
    console.log(this.select.cus)
  this.paymentService.getBank().subscribe(date => {
      this.bank = date;
      console.log(date)
    })
   this.paymentService.getRoom().subscribe(date => {
      this.room = date;
      console.log(date)
    })
    this.paymentService.getRoomMeet().subscribe(date => {
       this.roomMeet = date;
    console.log(date)
   })

    this.paymentService.getBookingCar().subscribe(date => {
      this.bookingCar = date;
      console.log(date)
    })
 }
refresh(customerName) {
      this.paymentService.getPayment(customerName).subscribe((res) => {
      this.dataSource = new PaymentDataSource(this.paymentService, customerName);

    });
}
   review() {
    this.router.navigate(['/view/review', this.select.cus]);
  }
  foodOrder() {
    this.router.navigate(['/foodorder', this.select.cus]);
  }
   reservation(){
    this.router.navigate(['/reservationroom', this.select.cus]);
  }
  bookingcar() {
    this.router.navigate(['/bookingcar', this.select.cus]);
  }
  reservationMeeetingRoom(){
    this.router.navigate(['/reservationmeeting', this.select.cus]);
  }

  comfirm() {
    console.log(this.select.cardidSelect);
    console.log(this.select.bankSelect);
    console.log(this.select.roomSelect);

    console.log(this.select.bookingCarSelect);
    console.log(this.select.DateSelect);



      if (this.select.cardidSelect === '' || this.select.bankSelect === '' || this.select.roomSelect === '' || this.select.DateSelect === '') {
      alert('Please Enter all Data');
    }
    else {
      this.httpClient.get('http://localhost:8080/payment/' + this.select.bankSelect + '/' + this.select.roomSelect  + '/' + this.select.DateSelect  + '/' + this.select.cardidSelect + '/' + this.select.cus + '/' + this.select.bookingCarSelect + '/' + this.select.nameSelect
      + '/' + this.select.cvvSelect + '/' + this.select.roomMeetSelect, this.select)
        .subscribe(
          data => {
            console.log(data);
            if (data) {
              console.log('PUT Request is successful', data);
              alert('Success');
              this.refresh(this.select.cus);

            }
            else
              alert('have alrady exist')
          },
          error => {
       alert('error')
            console.log('Error', error);
          }
)
}
this.refresh(this.select.cus);
  }
}
export class PaymentDataSource extends DataSource<any> {
  name: String;
  constructor(private paymentService: PaymentService, private customerName: String) {
    super();
    this.name = this.customerName;
  }
  connect(): Observable<PaymentComponent[]> {
    return this.paymentService.getPayment(this.customerName);
  }
  disconnect() { }
}

