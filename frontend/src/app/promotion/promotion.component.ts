import { Component, OnInit } from '@angular/core';
import { PromotionService } from '../shared/promotion.service'
import { Router, ActivatedRoute } from "@angular/router";
import { HttpClient } from '@angular/common/http';


export interface HotelEntity {
  value: string;
  viewValue: string;
}
export interface PromotionTypeEntity {
  value: string;
  viewValue: string;
}
export interface RoomTypeEntity {
  value: string;
  viewValue: string;
}
@Component({
  selector: 'app-promotion',
  templateUrl: './promotion.component.html',
  styleUrls: ['./promotion.component.css']
})
export class PromotionComponent implements OnInit {
  data: any = {}
  promotion: Array<any> = [];
  hotel: Array<any>;
  roomType: Array<any> = [];
  views: any = {
    memberUserName: '',
    dateStartInput: null,
    dateEndInput: null,
    detail: null,
    hotelselecter: null,
    roomTypeselecter: null,
    promotiondelecter: null,
    promotionname: null,

  };
  constructor(private route: ActivatedRoute,private app: PromotionService, private router: Router, private httpClient: HttpClient) { 
      this.views.memberUserName = this.route.snapshot.paramMap.get('inputUserName');
  }

  ngOnInit() {
    console.log(this.views.memberUserName)
    this.app.getPromotionType().subscribe(promotion => {
      this.promotion = promotion;
      console.log(promotion)
    })
    this.app.getRoomType().subscribe(roomType => {
      this.roomType = roomType;
      console.log(roomType)
    })
    this.app.getHotel().subscribe(hotel => {
      this.hotel = hotel;
      console.log(hotel)
    })
  }
  addRoom() {
    this.router.navigate(['/addroom', this.views.memberUserName]);
  }
  UpdateRoomStatus() {
    this.router.navigate(['/roomstatus', this.views.memberUserName]);
  }
  login(){
    this.router.navigate(['/memberhotel/login']);
  }
  Cash(){
    this.router.navigate(['/updatefoodstatus', this.views.memberUserName]);
  }
  save_func() {
    console.log(this.views.dateStartInput);
    console.log(this.views.dateEndInput);
    console.log(this.views.detail);
    console.log(this.views.hotelselecter);
    console.log(this.views.roomTypeselecter);
    console.log(this.views.promotiondelecter);
    console.log(this.views.promotionname);
    //promotionEntity/{dateStartInput}/{dateEndInput}/{detail}/{hotelselecter}/{roomTypeselecter}/{promotiondelecter}/{promotionname}
    this.httpClient.get('http://localhost:8080/promotionEntity/' + this.views.dateStartInput + '/' + this.views.dateEndInput + '/' + this.views.detail
      + '/' + this.views.hotelselecter + '/' + this.views.roomTypeselecter + '/' + this.views.promotiondelecter + '/' + this.views.promotionname, this.views)
      .subscribe(
        data => {
          console.log('POST Request is successful', data);
          alert('POST Request is successful');
        },
        error => {
          console.log('Rrror', error);
        }
      );
  }
}
