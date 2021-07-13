import { Component, OnInit } from '@angular/core';
import {CustomerService} from '../customer.service';
import {Customer} from '../model/customer.model';
import {FormControl, FormGroup} from '@angular/forms';
import {Router} from '@angular/router';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {
  myForm: FormGroup;
  customers: Customer[] = [];
  customer =  new Customer();
  constructor( private customerService: CustomerService, private router: Router) { }

  ngOnInit() {
    this.createForm();
    this.search();
  }
  /**
   * create form validators
   */
  createForm() {
    this.myForm = new FormGroup({
      name: new FormControl(),
      email: new FormControl(),
    });
  }

  search() {
    this.customerService.list(this.customer).subscribe(results => {
      this.customers = results;
    });
  }
  create() {
    this.router.navigateByUrl('/customer/create');
  }
  update(id) {
    this.router.navigateByUrl('/customer/update/' + id);
  }
  delete(id) {
    this.customerService.delete(id).subscribe(() => {
      this.search();
    });
  }
}
