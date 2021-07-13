import { Component, OnInit } from '@angular/core';
import {Customer} from '../../model/customer.model';
import {FormControl, FormGroup} from '@angular/forms';
import {CustomerService} from '../../customer.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {
  mode = 'CREATE';
  myForm: FormGroup;
  customer =  new Customer();
  constructor(private customerService: CustomerService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
    this.createForm();
    let id = null;
    this.route.paramMap.subscribe((param) => id = param.get('id'));
    if (id) {
      this.mode = 'UPDATE';
      this.customerService.get(id).subscribe((cstm) => {
        this.customer = cstm;
      });
    } else {
      this.mode = 'CREATE';
    }
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

  create() {
    this.customerService.create(this.customer).subscribe(custom => {
      this.router.navigateByUrl('/customer');
    });
  }

  update() {
    this.customerService.update(this.customer.id, this.customer).subscribe(custom => {
      this.router.navigateByUrl('/customer');
    });
  }
}
