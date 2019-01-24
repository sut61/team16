import { Component, OnInit } from '@angular/core';
import { DataSource } from '@angular/cdk/collections';
import { ReviewService } from '../shared/review.service';
import { Observable } from 'rxjs/Observable';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { ViewReviewService } from '../shared/view-review.service';
export interface Reservation {
  reservationRoomId: number;
  newHoteltype: {
    hotelNameEng: String;
  }
  newStatusPaymentEntity: {
    statusPaymentTypeName: String;
  }
  newRoomTypeEntity:{
    roomTypeName: String;
  }
}

@Component({
  selector: 'app-view-review',
  templateUrl: './view-review.component.html',
  styleUrls: ['./view-review.component.css']
})
export class ViewReviewComponent implements OnInit {
  select: any = {
    selectHotelNameEng: '',
    selectRoomType: '',
    selectReservationId: ''
  }
  inputUserName: String;
  
  constructor(private router: Router, private route: ActivatedRoute, private viewReviewService: ViewReviewService, private httpClient: HttpClient) { 
    this.inputUserName = this.route.snapshot.paramMap.get('inputUserName');
  }
  displayedColumns: string[] = ['reservationId', 'HotelName', 'status','roomType'];
  dataSource = new ReservationDataSource(this.route,this.viewReviewService);
  ngOnInit() {
      console.log(this.inputUserName);
  }
  review(){
    this.router.navigate(['/review',this.inputUserName, this.select.selectHotelNameEng,this.select.selectRoomType,this.select.selectReservationId]);
  }
  selectRow(row){
      this.select.selectHotelNameEng  = row.newHotelEntity.hotelNameEng;
      this.select.selectRoomType  = row.newRoomTypeEntity.roomTypeName;
      this.select.selectReservationId  = row.reservationRoomId;
      console.log(this.select.selectHotelNameEng);
      console.log(this.select.selectRoomType);
      console.log(this.select.selectReservationId);
  }

}
export class ReservationDataSource extends DataSource<any> {
  constructor(private route: ActivatedRoute,private viewReviewService: ViewReviewService) {
    super();
  }
  inputUserName = this.route.snapshot.paramMap.get('inputUserName');;
  
  connect(): Observable<Reservation[]> {
    console.log(this.viewReviewService.getReservation(this.inputUserName));
    return this.viewReviewService.getReservation(this.inputUserName);
  }
  disconnect() { }
}