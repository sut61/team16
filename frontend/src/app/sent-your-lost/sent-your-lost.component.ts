import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DataSource } from '@angular/cdk/collections';
import { Observable } from 'rxjs/Observable';
import { ActivatedRoute, Router } from '@angular/router';
import { SentService } from '../shared/sent.service';
import { MatTableDataSource, MatSort } from '@angular/material';
export interface SentComponent {

  newSentYourLostEntity: {
    address: String;
  }
  newItemEntity: {
    itemName: String;
  }

}

@Component({
  selector: 'app-sent-your-lost',
  templateUrl: './sent-your-lost.component.html',
  styleUrls: ['./sent-your-lost.component.css']
})
export class SentYourLostComponent implements OnInit {
  hotelName: Array<any>;
  item: Array<any>;
  room: Array<any>;
  roomType: Array<any>;
  address: String;
  select: any = {
    roomnumber: '',
    roomTypename: '',
    hotelName: '',
    itemname: '',
    inputMemberUserName: ''
  }
  displayedColumns: string[] = ["Item", 'Address'];
  dataSource = new ItemDataSource(this.sentService);
  constructor(private sentService: SentService, private httpClient: HttpClient, private route: ActivatedRoute, private router: Router) {
    this.select.inputMemberUserName = this.route.snapshot.paramMap.get('inputUserName');
    console.log(this.select.inputMemberUserName)
  }

  ngOnInit() {

    this.sentService.getHotelNameEng().subscribe(data => {
      this.hotelName = data;
    });
    this.sentService.getItem().subscribe(data => {
      this.item = data;
    });
    this.sentService.getRoomType().subscribe(data => {
      this.roomType = data;
    });
    this.sentService.getRoom().subscribe(data => {
      this.room = data;
    });
  }
  bookingcar() {
    this.router.navigate(['/bookingcar', this.select.inputMemberUserName]);
  }
  payment() {
    this.router.navigate(['/payment', this.select.inputMemberUserName]);
  }
  reservationmeeting() {
    this.router.navigate(['/reservationmeeting', this.select.inputMemberUserName])
  }
  reservation() {
    this.router.navigate(['/reservationroom', this.select.inputMemberUserName])
  }
  review() {
    this.router.navigate(['/view/review', this.select.inputMemberUserName]);
  }
  foodOrder() {
    this.router.navigate(['/foodorder', this.select.inputMemberUserName]);
  }
  add() {
    if (this.select.itemname == null || this.select.roomnumber == null || this.select.roomTypename == null || this.select.hotelName == null || this.address == null) {
      alert("Please enter all data!!");
    }
    else {
      this.httpClient.get('http://localhost:8080/sent-your-lost/' + this.select.inputMemberUserName + '/' + this.select.itemname + '/' + this.select.hotelName + '/' + this.select.roomnumber + '/' + this.select.roomTypename + '/' + this.address, this.select)
        .subscribe(
          data => {
            if (data) {
              console.log('POST Request is successful', data);
              alert("Successfully!!");
              location.reload();

            } else {
              console.log('Please enter all Data.');

            }
          },
          error => {
            console.log('Error', error);
          }

        );

    }
  }
}

export class ItemDataSource extends DataSource<any> {
  constructor(private sentService: SentService) {
    super();
  }
  connect(): Observable<SentComponent[]> {
    return this.sentService.getSentItem();
  }
  disconnect() { }
}

