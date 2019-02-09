import { Component, OnInit } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { CustomerService } from '../shared/customer.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  titlenameTypes: Array<any>;
  sexTypes: Array<any>;
  nationalityNames: Array<any>;
  views: any = {
    titlenameTypeSelect: '',
    nametInput: '',
    idcardInput: '',
    addressInput: '',
    emailInput: '',
    phoneInput: '',
    userInput: '',
    passwordInput: '',
    sexTypeSelect: '',
    nationalityNameSelect: ''
  };

  constructor(private customerService: CustomerService , private httpClient: HttpClient) { }

  ngOnInit() {
    this.customerService.getTitlenameType().subscribe(data => {
      this.titlenameTypes = data;
      console.log(data);
    });

    this.customerService.getSexType().subscribe(data => {
      this.sexTypes = data;
      console.log(data);
    });

    this.customerService.getNationalityName().subscribe(data => {
      this.nationalityNames = data;
      console.log(data);
    });

  }
  save_func(){
    if (this.views.titlenameTypeSelect === '')
      alert('Please Enter Title');
    else if (this.views.nametInput === '')
      alert('Please Enter Name');
    else if (this.views.sexTypeSelect === '')
      alert('Please Enter Sex');
    else if (this.views.idcardInput === '')
      alert('Please Enter Idcrad');
    else if (this.views.addressInput === '')
      alert('Please Enter Address');
    else if (this.views.nationalityNameSelect === '')
      alert('Please Enter Nationality');
    else if (this.views.emailInput === '')
      alert('Please Enter Email');
    else if (this.views.phoneInput === '')
      alert('Please Enter Phone');
    else if (this.views.userInput === '')
      alert('Please Enter Username');
    else if (this.views.passwordInput === '')
      alert('Please Enter Password');
    else{
      this.customerService.checkEmail(this.views.emailInput).subscribe(data => {
        if(data == null){
          this.httpClient.post('http://localhost:8080/customer/' + this.views.titlenameTypeSelect + '/' + this.views.nametInput + '/' + this.views.sexTypeSelect
          + '/' + this.views.idcardInput + '/' + this.views.addressInput + '/' + this.views.nationalityNameSelect + '/' + this.views.emailInput
          + '/' + this.views.phoneInput + '/' + this.views.userInput + '/' + this.views.passwordInput, this.views )
               .subscribe(
                   data => {
                     alert("Register Successfully!!");
                       console.log('POST Request is successful', data);
                   },
                   error => {
                    alert("Please check the data pattern. Again!!");
                        console.log('Rrror', error);
                  }

               );
        }
        else{
        alert("Please enter a new email.!!");
        }
    });
  }
    }
  
}