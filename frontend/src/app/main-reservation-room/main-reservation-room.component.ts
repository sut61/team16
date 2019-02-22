import { Component, OnInit } from '@angular/core';
import { ReservationRoomService } from '../shared/reservation-room.service'
import { HttpClient } from '@angular/common/http';
import { DataSource } from '@angular/cdk/collections';
import { Observable } from 'rxjs/Observable';
import { ActivatedRoute, Router } from '@angular/router';
import { MatTableDataSource, MatSort } from '@angular/material';

export interface MainReservationRoomComponent {
  reservationRoomId: number;
  dateIn: Date;
  dateOut: Date;
  newRoomTypeEntity: {
    roomTypeName: String;
  }
  newHotelEntity: {
    hotelNameEng: String;
  }
  newPromotionEntity: {
    promotionName: String;
  }
newMeetingRoomtypeEntity: {
  meetingRoomtypeName: String;
}



}
@Component({
  selector: 'app-main-reservation-room',
  templateUrl: './main-reservation-room.component.html',
  styleUrls: ['./main-reservation-room.component.css'],

})
export class MainReservationRoomComponent implements OnInit {
  room: Array<any>;
  roomType: Array<any>;
  hotel: Array<any>;
  promotion: Array<any>;
  startDate1 = new Date(1990, 0, 1);
  startDate2 = new Date(1990, 0, 1);

  select: any = {
    roomSelect: '',
    roomTypeSelect: '',
    hotelSelect: '',
    promotionSelect: '',
    inputStartDate: '',
    inputEndDate: '',
    cus: '',
    totalPriceRoom: '',
    commentInput: '',
    

  }

  displayedColumns: string[] = ['id', 'roomType', 'hotel', 'promotion', 'datein', 'dateout'];
  dataSource = new ReservationRoomDataSource(this.reservationRoomService, this.select.cus);
  constructor(private router: Router, private route: ActivatedRoute, private reservationRoomService: ReservationRoomService, private httpClient: HttpClient) {
    this.select.cus = this.route.snapshot.paramMap.get('inputUserName');
  }

  ngOnInit() {
    this.refresh(this.select.cus);
    console.log(this.select.cus)
    this.reservationRoomService.getHotel().subscribe(data => {
      this.hotel = data;
      console.log(this.hotel);
    });
  }
  selectRoomType() {
    console.log(this.select.roomTypeSelect)
    this.reservationRoomService.getRoom(this.select.roomTypeSelect).subscribe(data => {
      this.room = data;
      console.log(data);
    });
  }
  selectHotel() {
    this.reservationRoomService.getPromotion(this.select.hotelSelect).subscribe(data => {
      this.promotion = data;
      console.log(data.promotionId);
      console.log(this.promotion);
    });
    this.reservationRoomService.getRoomType(this.select.hotelSelect).subscribe(data => {
      this.roomType = data;
      console.log(data);
      console.log(this.roomType); 
    });

  }
  refresh(customerName) {
    this.reservationRoomService.getReservationRoom(customerName).subscribe((res) => {
      this.dataSource = new ReservationRoomDataSource(this.reservationRoomService, customerName);

    });
  }
  sent(){
    this.router.navigate(['/sent-your-lost', this.select.cus]);
  }
  bookingcar() {
    this.router.navigate(['/bookingcar', this.select.cus]);
  }
  payment(){
    this.router.navigate(['/payment', this.select.cus]);
  }
  reservationmeeting(){
    this.router.navigate(['/reservationmeeting', this.select.cus])
  }
  review() {
    this.router.navigate(['/view/review', this.select.cus]);
  }
  foodOrder() {
    this.router.navigate(['/foodorder', this.select.cus]);
  }
  comfirm() {
    console.log(this.select.hotelSelect);
    console.log(this.select.promotionSelect);
    console.log(this.select.roomTypeSelect);
    console.log(this.select.inputStartDate);
    console.log(this.select.inputEndDate);

    if (this.select.hotelSelect === '' || this.select.roomTypeSelect === '' || this.select.inputStartDate === '' || this.select.inputEndDate === '' || this.select.commentInput === '') {
      alert('Please Enter all Data');
      location.reload();

    }
    else {
      //+ this.select.totalPriceRoom + '/' 
      this.httpClient.get('http://localhost:8080/reservationroom/' + this.select.inputStartDate + '/' + this.select.inputEndDate + '/' + this.select.roomTypeSelect + '/' + this.select.cus + '/' + this.select.promotionSelect + '/' + this.select.hotelSelect + '/' + this.select.roomSelect + '/' + this.select.commentInput , this.select)
        .subscribe(
          data => {
            console.log(data);
            if (data) {
              console.log('PUT Request is successful', data);
              alert('Success');
              this.refresh(this.select.cus);
            }
            else
              alert('Room number ' + this.select.inputRoomNumber + ' in hotel ' + this.select.hotelNameSelect + ' have alrady exist')
          },
          error => {
            alert('cannot reservationroom');
            console.log('Error', error);
          }
        )
    }
    this.refresh(this.select.cus);
  }
}
export class ReservationRoomDataSource extends DataSource<any> {
  name: String;
  constructor(private reservationRoomService: ReservationRoomService, private customerName: String) {
    super();
    this.name = this.customerName;
  }
  connect(): Observable<MainReservationRoomComponent[]> {
    return this.reservationRoomService.getReservationRoom(this.customerName);
  }
  disconnect() { }
}
