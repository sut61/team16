import { Component, OnInit, Inject } from '@angular/core';
import { MeetingRoomService } from '../shared/meeting-room.service';
import { HttpClient } from '@angular/common/http';
import { DataSource } from '@angular/cdk/collections';
import { Observable } from 'rxjs/Observable';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
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
export interface DialogData {
  roomStatus : Array<any>;
  meetingEventRoomTypeTimeManyToManyId : number;
  newRoomStatusEntity: {
    roomStatusName: String;
  }
}
@Component({
  selector: 'app-add-meeting-event-room',
  templateUrl: './add-meeting-event-room.component.html',
  styleUrls: ['./add-meeting-event-room.component.css']
})
export class AddMeetingEventRoomComponent implements OnInit {
  static meetingEventRoomStatus: Array<any>;
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
    hotelName: ''
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
      console.log(res);
      this.dataSource = new MeetingRoomDataSource(this.meetingEventRoomService);
    });
  }
  add() {
    console.log(this.select.selectMeetingEventRoomType);
    console.log(this.select.selectMeetingRoomStatusAfternoon);
    console.log(this.select.selectMeetingRoomStatusEvening);
    console.log(this.select.inputMeetingRoomName);
    console.log(this.select.selectMeetingRoomStatusMorning);
    console.log(this.select.inputMeetingRoomNumber);
    console.log(this.select.inputMeetingRoomPrice);
    if (this.select.selectMeetingRoomStatusMorning === '' || this.select.selectMeetingRoomStatusAfternoon === '' || this.select.selectMeetingRoomStatusEvening === '' || this.select.selectMeetingEventRoomType === ''|| this.select.inputMeetingRoomPrice === ''|| this.select.inputMeetingRoomName === ''|| this.select.inputMeetingRoomNumber === '') {
      alert('Please Enter all Data');
    }
    else if (!Number.isInteger(Number(this.select.inputMeetingRoomPrice))) {
      alert('Please check room price');
    }
    else {
      this.httpClient.get('http://localhost:8080/meetingeventroom/' + this.select.inputMeetingRoomName + '/' + this.select.selectMeetingEventRoomType + '/' + this.select.selectMeetingRoomStatusMorning + '/' + this.select.selectMeetingRoomStatusAfternoon + '/' + this.select.selectMeetingRoomStatusEvening + '/' + this.select.inputMeetingRoomNumber + '/' + this.select.inputMeetingRoomPrice + '/' + this.select.memberUserName, this.select)
        .subscribe(
          data => {
            console.log(data);
            if (data) {
              console.log('PUT Request is successful', data);
              alert('Add MeetingEvent Room Success');
              this.refresh();
            }
            else
              alert('Meeting Room number ' + this.select.inputMeetingRoomNumber + ' in this hotel have alrady exist')
          },
          error => {
            alert('Error cannot add meeting room')
            console.log('Error', error);
          }
        )
    }
    this.refresh();
  }
  UpdateRoomStatus() {
    this.router.navigate(['/roomstatus', this.select.memberUserName]);
  }
  next(){
    this.router.navigate(['/meetingroomstatus', this.select.memberUserName]);
  }
  addRoom() {
    this.router.navigate(['/addroom', this.select.memberUserName]);
  }
  selectRow(row) {
    this.select.selectRowMeetingRoomId = row.meetingEventRoomId;
    console.log(this.select.selectRowMeetingRoomId);
    this.meetingEventRoomService.getMeetingRoomStatusWhenClick(this.select.selectRowMeetingRoomId).subscribe(data => {
      AddMeetingEventRoomComponent.meetingEventRoomStatus = data;
    this.openDialog();
    });
    
  }
  Promotion(){
    this.router.navigate(['/promotion', this.select.memberUserName]);
  }
  UpdateMeetingRoomStatus() {
    this.router.navigate(['/meetingroomstatus', this.select.memberUserName]);
  }
  Cash() {
    this.router.navigate(['/updatefoodstatus', this.select.memberUserName]);
  }
  openDialog(): void {
    const dialogRef = this.dialog.open(DialogOverviewExampleDialog, {
      width: '350px',
      height: '300px',
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
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
@Component({
  selector: 'add-meeting-event-room-dialog',
  templateUrl: 'add-meeting-event-room-dialog.component.html',
})
export class DialogOverviewExampleDialog {
  meetingEventRoomStatus: Array<any>;
  select: any = {
    selectRoomStatusNameMorning: '',
    selectRoomStatusNameAfternoon: '',
    selectRoomStatusNameEvening: ''
  }
  constructor(
    public dialogRef: MatDialogRef<DialogOverviewExampleDialog>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) { }

  onNoClick(): void {
    this.dialogRef.close();
  }
  ngOnInit() {
    
    this.meetingEventRoomStatus = AddMeetingEventRoomComponent.meetingEventRoomStatus;
    console.log("TEST");
    this.select.selectRoomStatusNameMorning = this.meetingEventRoomStatus[0];
    this.select.selectRoomStatusNameAfternoon = this.meetingEventRoomStatus[1];
    this.select.selectRoomStatusNameEvening = this.meetingEventRoomStatus[2];
  }
}
