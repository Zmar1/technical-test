import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Customer} from './model/customer.model';
import {Observable} from 'rxjs';
import {environment} from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http: HttpClient) { }

  list(customer: Customer): Observable<Customer[]> {
    return this.http.post<Customer[]>(`${environment.backendUrl}customer/list`, customer);
  }

  create(customer: Customer): Observable<Customer> {
    return this.http.post<Customer>(`${environment.backendUrl}customer/`, customer);
  }

  update(id: number, customer: Customer): Observable<Customer> {
    return this.http.put<Customer>(`${environment.backendUrl}customer/${id}`, customer);
  }

  delete(id: number) {
    return this.http.delete(`${environment.backendUrl}customer/${id}`);
  }

  get(id: number): Observable<Customer> {
    return this.http.get<Customer>(`${environment.backendUrl}customer/${id}`);
  }
}
