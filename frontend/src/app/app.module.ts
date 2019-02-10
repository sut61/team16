import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { MatButtonModule, MatCardModule, MatInputModule, MatListModule, MatToolbarModule, MatTableModule,
MatSidenavModule, MatCheckboxModule, MatDialogModule } from '@angular/material';
import {MatIconModule} from '@angular/material/icon';
import {MatSelectModule} from '@angular/material/select';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatGridListModule} from '@angular/material/grid-list';
import { MatNativeDateModule} from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import {MatRadioModule } from '@angular/material/radio';
import { AddRoomComponent } from './add-room/add-room.component';
import { RoomStatusComponent } from './room-status/room-status.component';
import { FoodOrderComponent } from './food-order/food-order.component';
import { RoomService } from './shared/room.service';
import { from } from 'rxjs';
import { FoodOrderService } from './shared/food-order.service';
import { MemberLoginComponent } from './member-login/member-login.component';
import { RegisterComponent } from './register/register.component';
import { ResultComponent } from './result/result.component';
import { LoginComponent } from './login/login.component';
import { MainReservationRoomComponent } from './main-reservation-room/main-reservation-room.component';
import { ReservationRoomService } from './shared/reservation-room.service';
import { PromotionComponent } from './promotion/promotion.component';
import { PromotionService } from './shared/promotion.service';
import { CustomerService } from './shared/customer.service';
import { ReviewComponent } from './review/review.component';
import { ViewReviewComponent } from './view-review/view-review.component';
import { ReviewService } from './shared/review.service';
import { ViewReviewService } from './shared/view-review.service';
import { UpdateFoodStatusComponent } from './update-food-status/update-food-status.component';
import { HomeComponent } from './home/home.component';
import { AddMeetingEventRoomComponent, DialogOverviewExampleDialog } from './add-meeting-event-room/add-meeting-event-room.component';
import { MeetingRoomStatusComponent } from './meeting-room-status/meeting-room-status.component';
import { MeetingRoomService } from './shared/meeting-room.service';
import { BookingcarComponent } from './bookingcar/bookingcar.component';
import { BookingcarService } from './shared/bookingcar.service';
import { EmployeeLoginComponent } from './employee-login/employee-login.component';
import { FoodStockComponent } from './food-stock/food-stock.component';
import { FoodStockService } from './shared/food-stock.service';
import { MainReservationMeetingRoomComponent } from './main-reservation-meeting-room/main-reservation-meeting-room.component';
import { ReservationMeetingRoomService } from './shared/reservation-meeting-room.service';
import { SentYourLostComponent } from './sent-your-lost/sent-your-lost.component';
import { SentService } from './shared/sent.service';
import { PaymentComponent } from './payment/payment.component';
import { PaymentService } from './shared/payment.service';


const appRoutes: Routes = [
  {path:'' , component:HomeComponent},
  {path: 'addroom/:inputUserName', component: AddRoomComponent},
  {path: 'addmeetingroom/:inputUserName', component: AddMeetingEventRoomComponent},
  {path: 'roomstatus/:inputUserName', component: RoomStatusComponent},
  {path: 'meetingroomstatus/:inputUserName', component: MeetingRoomStatusComponent},
  {path: 'foodorder/:inputUserName',component: FoodOrderComponent},
  {path: 'memberhotel/login',component: MemberLoginComponent},
  {path: 'result', component: ResultComponent},
  {path: 'login', component: LoginComponent},
  {path: 'employeelogin', component: EmployeeLoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'reservationroom/:inputUserName', component: MainReservationRoomComponent},
  {path: 'promotion/:inputUserName', component: PromotionComponent},
  {path: 'review', component: ReviewComponent},
  {path: 'view/review/:inputUserName', component: ViewReviewComponent},
  {path: 'review/:inputMemberUserName/:selectHotelNameEng/:selectRoomType/:selectReservationId', component: ReviewComponent},
  {path: 'updatefoodstatus/:inputUserName',component: UpdateFoodStatusComponent},
  {path: 'bookingcar/:inputUserName', component: BookingcarComponent},
  {path: 'foodstock/:inputUserName', component: FoodStockComponent},
  {path: 'payment/:inputUserName',component: PaymentComponent},
  {path: 'sent-your-lost/:inputUserName',component: SentYourLostComponent},
  {path: 'reservationmeeting/:inputUserName',component: MainReservationMeetingRoomComponent}


];

@NgModule({
  declarations: [
    AppComponent,
    AddRoomComponent,
    RoomStatusComponent,
    FoodOrderComponent,
    MemberLoginComponent,
    RegisterComponent,
    ResultComponent,
    LoginComponent,
    MainReservationRoomComponent,
    PromotionComponent,
    ReviewComponent,
    ViewReviewComponent,
    UpdateFoodStatusComponent,
    HomeComponent,
    AddMeetingEventRoomComponent,
    MeetingRoomStatusComponent,
    BookingcarComponent,
    DialogOverviewExampleDialog,
    EmployeeLoginComponent,
    FoodStockComponent,
    MainReservationMeetingRoomComponent,
    SentYourLostComponent,
    PaymentComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatListModule,
    MatToolbarModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    FormsModule,
    MatTableModule,
    MatCheckboxModule,
    MatSidenavModule,
    MatIconModule,
    MatGridListModule,
    MatRadioModule,
    MatDialogModule,
    RouterModule.forRoot(appRoutes)
  ],
  entryComponents: [DialogOverviewExampleDialog],
  providers: [RoomService,FoodOrderService,SentService,ReservationMeetingRoomService,FoodStockService,ReservationRoomService,PromotionService,CustomerService,ReviewService,
    ViewReviewService,MeetingRoomService,BookingcarComponent,BookingcarService,PaymentService],
  bootstrap: [AppComponent]
})
export class AppModule { }
