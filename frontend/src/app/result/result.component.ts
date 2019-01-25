import { Component, OnInit } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { CustomerService } from '../shared/customer.service';

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.css']
})
export class ResultComponent implements OnInit {
   records: any={
    name: '',
    card: '',
    phone: '',
    mail: '',
    user: '',
    pass: ''
  }

  constructor(private customerService: CustomerService , private httpClient: HttpClient) { }

  ngOnInit() {
    this.customerService.getRecordcustomer().subscribe(data => {
      this.records.name = data.customerName;
      console.log(data.customerName);
    });

    this.customerService.getRecordcustomer().subscribe(data => {
      this.records.card = data.customerIdcrad;
      console.log(data.customerIdcrad);
    });

    this.customerService.getRecordcustomer().subscribe(data => {
      this.records.phone = data.customerPhone;
      console.log(data.customerPhone);
    });

    this.customerService.getRecordcustomer().subscribe(data => {
      this.records.mail = data.customerEmail;
      console.log(data.customerEmail);
    });

    this.customerService.getRecordcustomer().subscribe(data => {
      this.records.user = data.customerUsername;
      console.log(data.customerUsername);
    });

    this.customerService.getRecordcustomer().subscribe(data => {
      this.records.pass = data.customerPassword;
      console.log(data.customerPassword);
    });
  }

}
