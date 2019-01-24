import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../shared/customer.service';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  select: any = {
    inputUserName: '',
    inputPassword: '',
  }
  constructor(private customerService: CustomerService, private httpClient: HttpClient, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
  }
  login() {
    console.log(this.select.inputUserName)
    if (this.select.inputPassword === '')
      alert('Please Enter Username');
    else if (this.select.inputPassword === '')
      alert('Please Enter Password');
    else {
      this.httpClient.get('http://localhost:8080/customer/' + this.select.inputUserName + '/' + this.select.inputPassword, this.select)
        .subscribe(
          data => {
            if (data) {
              alert('Login successfully');
              this.router.navigate(['/reservationroom',this.select.inputUserName]);
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
