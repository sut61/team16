import { Component, OnInit } from '@angular/core';
import { ReservationMeetingRoomService } from '../shared/reservation-meeting-room.service'
import { HttpClient } from '@angular/common/http';
import { DataSource } from '@angular/cdk/collections';
import { Observable } from 'rxjs/Observable';
import { ActivatedRoute, Router } from '@angular/router';
import { MatTableDataSource, MatSort } from '@angular/material';


export interface MainReservationMeetingRoomComponent {
  reservationRoomId: number;
  dateIn: Date;
  dateOut: Date;
  newMeetingRoomTypeEntity: {
    meetingEventRoomTypeName: String;
  }
  newHotelEntity: {
    hotelNameEng: String;
  }
  newPromotionEntity: {
    promotionName: String;
  }
  newFunctionEntity: {
    functonName: String
  }
}
@Component({
  selector: 'app-main-reservation-meeting-room',
  templateUrl: './main-reservation-meeting-room.component.html',
  styleUrls: ['./main-reservation-meeting-room.component.css']
})
export class MainReservationMeetingRoomComponent implements OnInit {
  meetingRoom: Array<any>;
  meetingRoomType: Array<any>;
  hotel: Array<any>;
  startDate1 = new Date(1990, 0, 1);
  startDate2 = new Date(1990, 0, 1);
  typetime: Array<any>;
  roomStatus: Array<any>;
  function: Array<any>;
  detail: Array<any>;



  select: any = {
    meetingRoomSelect: '',
    meetingRoomTypeSelect: '',
    hotelSelect: '',
    promotionSelect: '',
    inputStartDate: '',
    inputEndDate: '',
    cus: '',
    typeTimeSelect: '',
    roomPriceInput: '',
    functionSelect: '',
    commentInput: '',
    cheese: ''

  }
  displayedColumns: string[] = ['id', 'roomType', 'hotel', 'datein', 'dateout'];
  dataSource = new ReservationMeetingRoomDataSource(this.reservationMeetingRoomService, this.select.cus);
  constructor(private router: Router, private route: ActivatedRoute, private reservationMeetingRoomService: ReservationMeetingRoomService, private httpClient: HttpClient) {
    this.select.cus = this.route.snapshot.paramMap.get('inputUserName');
  }

  ngOnInit() {
    this.refresh(this.select.cus);
    console.log(this.select.cus)
    this.reservationMeetingRoomService.getHotel().subscribe(data => {
      this.hotel = data;
      console.log(this.hotel);
    });

    this.reservationMeetingRoomService.getTypeTime().subscribe(data => {
      this.typetime = data;
      console.log(this.typetime);
    });

    this.reservationMeetingRoomService.getFunction().subscribe(data => {
      this.function = data;
      console.log(this.function);
    });



  }
  selectMeetingRoomType() {
    console.log(this.select.meetingRoomTypeSelect)
    this.reservationMeetingRoomService.getMeetingRoom(this.select.meetingRoomTypeSelect).subscribe(data => {
      this.meetingRoom = data;
      console.log(data.meetingEventRoomId);
      console.log(this.meetingRoom);
    });
  }
  selectHotel() {
    this.reservationMeetingRoomService.getMeetingEventRoomType(this.select.hotelSelect).subscribe(data => {
      this.meetingRoomType = data;
      console.log(data.meetingEventRoomTypeId);
      console.log(this.meetingRoomType);
    });
  }
  refresh(customerName) {
    this.reservationMeetingRoomService.getReservationMeetingRoom(customerName).subscribe((res) => {
      this.dataSource = new ReservationMeetingRoomDataSource(this.reservationMeetingRoomService, customerName);

    });
  }
  payment() {
    this.router.navigate(['/payment', this.select.cus]);
  }
  bookingcar() {
    this.router.navigate(['/bookingcar', this.select.cus]);
  }
  sent() {
    this.router.navigate(['/sent-your-lost', this.select.cus]);
  }
  reservation() {
    this.router.navigate(['/reservationroom', this.select.cus])
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

    if (this.select.hotelSelect === '' || this.select.meetingRoomTypeSelect === '' || this.select.cheese === null || this.select.meetingRoomSelect === null || this.select.inputStartDate === null || this.select.inputEndDate === null || this.select.functionSelect === null || this.select.commentInput === null) {
      alert('Please Enter all Data');
      location.reload();
    }
    else {
      this.httpClient.get('http://localhost:8080/reservationmeetingeventroom/' + this.select.inputStartDate +
        '/' + this.select.inputEndDate + '/' + this.select.meetingRoomSelect + '/' + this.select.meetingRoomTypeSelect + '/' +
        this.select.cheese + '/' + this.select.cus + '/' + this.select.hotelSelect + '/' + this.select.commentInput + '/' + this.select.functionSelect, this.select)
        .subscribe(
          data => {
            console.log(data);
            if (data) {
              console.log('PUT Request is successful', data);
              alert('Success' + this.select.typeTimeSelect);
              this.refresh(this.select.cus);
            }
            else
              alert('Room number ' + this.select.meetingRoomTypeSelect + ' in hotel ' + this.select.cheese + ' have alrady exist')

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
export class ReservationMeetingRoomDataSource extends DataSource<any> {
  name: String;
  constructor(private ReservationMeetingRoomService: ReservationMeetingRoomService, private customerName: String) {
    super();
    this.name = this.customerName;
  }
  connect(): Observable<MainReservationMeetingRoomComponent[]> {
    return this.ReservationMeetingRoomService.getReservationMeetingRoom(this.customerName);
  }
  disconnect() { }
}

