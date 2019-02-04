import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { MeetingRoomService } from '../shared/meeting-room.service';
import { HttpClient } from '@angular/common/http';
import { DataSource } from '@angular/cdk/collections';
import { Observable } from 'rxjs/Observable';
export interface AddMeetingEventRoomComponent {
  meetingEventRoomId: number;
  meetingEventRoomNumber: String;
  meetingEventRoomName: String;
  meetingEventRoomPrice: number;
  newHotelEntity: {
    hotelNameEng: String;
  }
  newMeetingEventRoomTypeEntity: {
    meetingEventRoomTypeName: String;
  }
}
@Component({
  selector: 'app-meeting-room-status',
  templateUrl: './meeting-room-status.component.html',
  styleUrls: ['./meeting-room-status.component.css']
})
export class MeetingRoomStatusComponent implements OnInit {
  meetingEventRoomStatus: Array<any>;
  temp: Array<any>;
  meetingEventRoom: Array<any>;
  meetingEventRoomType: Array<any>;
  roomStatus: Array<any>;
  select: any = {
    selectMeetingRoomStatusMorning: '',
    selectMeetingRoomStatusAfternoon: '',
    selectMeetingRoomStatusEvening: '',
    selectMeetingEventRoomType: '',
    inputMeetingRoomPrice: '',
    inputMeetingRoomName: '',
    inputMeetingRoomNumber: '',
    selectRowMeetingRoomId: '',
    memberUserName: '',
    hotelName: '',

    selectedMeetingRoomStatusMorning: '',
    selectedMeetingRoomStatusAfternoon: '',
    selectedMeetingRoomStatusEvening: '',
    selectedMeetingEventRoomType: '',
    inputedMeetingRoomPrice: '',
    inputedMeetingRoomName: '',
    inputedMeetingRoomNumber: '',

    selectManyToManyId1: '',
    selectManyToManyId2: '',
    selectManyToManyId3: ''
  }
  displayedColumns: string[] = ['position', 'roomname', 'roomprice', 'roomnumber', 'roomtype'];
  dataSource = new MeetingRoomDataSource(this.meetingEventRoomService);
  constructor(public dialog: MatDialog, private meetingEventRoomService: MeetingRoomService, private httpClient: HttpClient, private route: ActivatedRoute, private router: Router) {
    this.select.memberUserName = this.route.snapshot.paramMap.get('inputUserName');
  }

  ngOnInit() {
    this.refresh();
    this.meetingEventRoomService.getHotelNameEng(this.select.memberUserName).subscribe(data => {
      this.select.hotelName = data.hotelNameEng;
    });
    this.meetingEventRoomService.getMeetingEventRoomType().subscribe(data => {
      this.meetingEventRoomType = data;
      console.log(data[0].meetingEventRoomTypeName);
      console.log(this.meetingEventRoomType);

    });
    this.meetingEventRoomService.getRoomStatus().subscribe(data => {
      this.roomStatus = data;
      console.log(this.roomStatus);
    });

  }
  refresh() {
    this.meetingEventRoomService.getMeetingEventRoom().subscribe((res) => {
      this.meetingEventRoom = res;
      this.dataSource = new MeetingRoomDataSource(this.meetingEventRoomService);
    });
  }
  UpdateRoomStatus() {
    this.router.navigate(['/roomstatus', this.select.memberUserName]);
  }
  addMeetingEventRoom() {
    this.router.navigate(['/addmeetingroom', this.select.memberUserName]);
  }
  Promotion(){
    this.router.navigate(['/promotion', this.select.memberUserName]);
  }
  Cash() {
    this.router.navigate(['/updatefoodstatus', this.select.memberUserName]);
  }
  addRoom() {
    this.router.navigate(['/addroom', this.select.memberUserName]);
  }
  addMeetingRoom() {
    this.router.navigate(['/addmeetingroom', this.select.memberUserName]);
  }
  getRoomStatusWhenClick(id) {
    this.meetingEventRoomService.getAllByMeetingRoomId(id).subscribe(data => {
      this.meetingEventRoomStatus = data;
      console.log(this.meetingEventRoomStatus);
      this.select.selectedMeetingRoomStatusMorning = this.meetingEventRoomStatus[0][1];
      this.select.selectedMeetingRoomStatusAfternoon = this.meetingEventRoomStatus[1][1];
      this.select.selectedMeetingRoomStatusEvening = this.meetingEventRoomStatus[2][1];
      this.select.selectManyToManyId1 = this.meetingEventRoomStatus[0][0];
      this.select.selectManyToManyId2 = this.meetingEventRoomStatus[1][0];
      this.select.selectManyToManyId3 = this.meetingEventRoomStatus[2][0];
    });

  }
  selectRow(row) {
    this.select.selectedMeetingEventRoomType = row.newMeetingEventRoomTypeEntity.meetingEventRoomTypeName;
    this.select.inputedMeetingRoomPrice = row.meetingEventRoomPrice;
    this.select.inputedMeetingRoomName = row.meetingEventRoomName;
    this.select.inputedMeetingRoomNumber = row.meetingEventRoomNumber;
    this.select.selectRowMeetingRoomId = row.meetingEventRoomId;
    console.log(row.meetingEventRoomId)
    console.log(this.select.selectRowMeetingRoomId)
    this.getRoomStatusWhenClick(this.select.selectRowMeetingRoomId);
  }
  edit() {
    if (!this.select.selectMeetingEventRoomType)
      this.select.selectMeetingEventRoomType = this.select.selectedMeetingEventRoomType;
    if (!this.select.inputMeetingRoomPrice)
      this.select.inputMeetingRoomPrice = this.select.inputedMeetingRoomPrice;
    if (!this.select.inputMeetingRoomName)
      this.select.inputMeetingRoomName = this.select.inputedMeetingRoomName;
    if (!this.select.inputMeetingRoomNumber)
      this.select.inputMeetingRoomNumber = this.select.inputedMeetingRoomNumber;
    if (!this.select.selectMeetingRoomStatusMorning)
      this.select.selectMeetingRoomStatusMorning = this.select.selectedMeetingRoomStatusMorning;
    if (!this.select.selectMeetingRoomStatusAfternoon)
      this.select.selectMeetingRoomStatusAfternoon = this.select.selectedMeetingRoomStatusAfternoon;
    if (!this.select.selectMeetingRoomStatusEvening)
      this.select.selectMeetingRoomStatusEvening = this.select.selectedMeetingRoomStatusEvening;
    else if (!Number.isInteger(Number(this.select.inputMeetingRoomPrice))) {
      alert('Please check room price');
    }
    else {
      this.httpClient.get('http://localhost:8080/updatemeetingeventroomstatus/' + this.select.selectRowMeetingRoomId + '/' + this.select.inputMeetingRoomName + '/' + this.select.selectMeetingEventRoomType + '/' + this.select.selectMeetingRoomStatusMorning + '/' + this.select.selectMeetingRoomStatusAfternoon + '/' + this.select.selectMeetingRoomStatusEvening + '/' + this.select.inputMeetingRoomNumber + '/' + this.select.inputMeetingRoomPrice + '/' + this.select.memberUserName
        + '/' + this.select.selectManyToManyId1 + '/' + this.select.selectManyToManyId2 + '/' + this.select.selectManyToManyId3, this.select)
      .subscribe(
        data => {
          if (data) {
            this.refresh();
            console.log('Success');
            alert('Update Meeting Room Success');
          }
          else {
            this.refresh();
            alert('Cannot update meeting room status, meeting room number ' + this.select.inputMeetingRoomNumber + ' maybe already exit!');
          }

        },
        error => {
          console.log('Uncomplete', error);
        }
      )
      this.select.selectedMeetingEventRoomType = '';
      this.select.selectedMeetingRoomStatusAfternoon = '';
      this.select.selectedMeetingRoomStatusMorning = '';
      this.select.selectedMeetingRoomStatusEvening = '';
      this.select.inputedMeetingRoomName = '';
      this.select.inputedMeetingRoomPrice = '';
      this.select.inputedMeetingRoomNumber = '';
    }
  }
}
export class MeetingRoomDataSource extends DataSource<any> {
  constructor(private meetingEventRoomService: MeetingRoomService) {
    super();
  }
  connect(): Observable<AddMeetingEventRoomComponent[]> {
    return this.meetingEventRoomService.getMeetingEventRoom();
  }
  disconnect() { }
}