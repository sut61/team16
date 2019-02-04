import { Component, OnInit } from '@angular/core';
import { RoomService } from '../shared/room.service';
import { HttpClient } from '@angular/common/http';
import { DataSource } from '@angular/cdk/collections';
import { Observable } from 'rxjs/Observable';
import { Router, ActivatedRoute } from '@angular/router';

export interface AddRoomComponent {
  roomId: number;
  roomNumber: String;
  roomPrice: number;
  newHotelEntity: {
    hotelNameEng: String;
  }
  newRoomTypeEntity: {
    roomTypeName: String;
  }
  newRoomStatusEntity: {
    roomStatusName: String;
  }
}
@Component({
  selector: 'app-add-room',
  templateUrl: './add-room.component.html',
  styleUrls: ['./add-room.component.css'],

})
export class AddRoomComponent implements OnInit {
  room: Array<any>;
  roomType: Array<any>;
  roomStatus: Array<any>;
  select: any = {
    roomTypeSelect: '',
    roomNumberInput: '',
    roomPriceInput: '',
    roomStatusSelect: '',
    memberUserName: String,
    hotelName: ''
  }
  constructor(private roomService: RoomService, private httpClient: HttpClient, private route: ActivatedRoute, private router: Router) {
    this.select.memberUserName = this.route.snapshot.paramMap.get('inputUserName');
  }
  displayedColumns: string[] = ['position', 'room_type', 'room_no', 'room_price', 'room_status'];
  dataSource = new RoomDataSource(this.roomService, this.select.memberUserName);
  ngOnInit() {
    console.log(this.select.memberUserName);
    this.refresh(this.select.memberUserName);
    console.log('test')
    this.roomService.getRoomStatus().subscribe(data => {
      console.log('In get roomstatus')
      this.roomStatus = data;
      console.log(this.roomStatus);
    });
    this.roomService.getHotelNameEng(this.select.memberUserName).subscribe(data => {
      this.select.hotelName = data.hotelNameEng;
    });

    this.roomService.getRoomType(this.select.memberUserName).subscribe(data => {
      this.roomType = data;
      console.log(this.roomType);
    });
  }

  refresh(memberUserName) {
    this.roomService.getRoom(memberUserName).subscribe((res) => {
      this.room = res;
      this.dataSource = new RoomDataSource(this.roomService, memberUserName);
    });
  }
  add() {
    console.log(this.select.memberUserName);
    if (this.select.roomStatusSelect === '' || this.select.roomTypeSelect === '' || this.select.roomNumberSelect === '' || this.select.roomPriceInput === '') {
      alert('Please enter all Data');
      location.reload();

    }
    else if (!Number.isInteger(Number(this.select.roomPriceInput))) {
      alert('Please check room price');
      location.reload();
    }
    else {
      this.httpClient.get('http://localhost:8080/room/' + this.select.roomTypeSelect + '/' + this.select.roomStatusSelect + '/' + this.select.roomNumberInput + '/' + this.select.roomPriceInput + '/' + this.select.memberUserName, this.select)
        .subscribe(
          data => {
            console.log(data);
            if (data) {
              alert('Add Room Success');
              console.log('send' + this.select.memberUserName)
              this.refresh(this.select.memberUserName);
            }
            else {
              alert('Room number ' + this.select.roomNumberInput + ' have alrady exist')
            }
          },
          error => {
            alert('Error cannot add room')
            console.log('Error', error);
          }
        )
    }
    this.refresh(this.select.memberUserName);
  }
  Promotion() {
    this.router.navigate(['/promotion', this.select.memberUserName]);
  }
  UpdateRoomStatus() {
    this.router.navigate(['/roomstatus', this.select.memberUserName]);
  }
  addMeetingEventRoom() {
    this.router.navigate(['/addmeetingroom', this.select.memberUserName]);
  }
  UpdateMeetingRoomStatus() {
    this.router.navigate(['/meetingroomstatus', this.select.memberUserName]);
  }
  Cash() {
    this.router.navigate(['/updatefoodstatus', this.select.memberUserName]);
  }
  login() {
    this.router.navigate(['/memberhotel/login']);
  }
}
export class RoomDataSource extends DataSource<any> {
  name: String;
  constructor(private roomService: RoomService, private memberUserName: String) {
    super();
    this.name = this.memberUserName;
  }
  connect(): Observable<AddRoomComponent[]> {
    return this.roomService.getRoom(this.name);
  }
  disconnect() {
    console.log('disconnect')
  }
}