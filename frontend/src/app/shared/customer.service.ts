import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  public API = '//localhost:8080';

  constructor(private http: HttpClient) { }

  getTitlenameType(): Observable<any> {
  return this.http.get(this.API + '/title');
  }

  getSexType(): Observable<any> {
  return this.http.get(this.API + '/sex');
  }

  getNationalityName(): Observable<any> {
  return this.http.get(this.API + '/national');
  }

  checkEmail(emailInput): Observable<any> {
  return this.http.get(this.API + '/customer/'+ emailInput);
  }

  getRecordcustomer(): Observable<any> {
   return this.http.get(this.API + '/lastRecord');
  }
}
