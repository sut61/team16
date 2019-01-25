import { Component, OnInit } from '@angular/core';
import { RoomService } from '../shared/room.service';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-member-login',
  templateUrl: './member-login.component.html',
  styleUrls: ['./member-login.component.css']
})
export class MemberLoginComponent implements OnInit {
  select: any = {
    inputUserName: '',
    inputPassword: '',
  }
  constructor(private roomService: RoomService, private httpClient: HttpClient, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
  }
  login() {
    console.log(this.select.inputUserName)
    if (this.select.inputPassword === '')
      alert('Please Enter Username');
    else if (this.select.inputPassword === '')
      alert('Please Enter Password');
    else {
      this.httpClient.get('http://localhost:8080/memberhotel/login/' + this.select.inputUserName + '/' + this.select.inputPassword, this.select)
        .subscribe(
          data => {
            if (data) {
              alert('Login successfully');
              this.router.navigate(['/addroom',this.select.inputUserName]);
            }
            else
              alert('Can not login, Please check your username or password')
          },
          error => {
            console.log('Error', error);
          }
        )
    }
  }
}


