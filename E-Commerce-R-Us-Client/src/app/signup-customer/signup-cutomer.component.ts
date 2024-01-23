import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RemoteService } from '../remote.service';
import { CustomerSignUp } from '../dto/customer-sign-up';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-signup-seller',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './signup-customer.component.html',
})
export class SignupCustomerComponent implements OnInit {
  public firstName: string;
  public lastName: string;
  public email: string;
  public password: string;

  constructor(private remote: RemoteService) {
    this.firstName = '';
    this.lastName = '';
    this.email = '';
    this.password = '';
  }

  loggedIn!: boolean;

  ngOnInit(): void {
    this.remote.currentLoggedIn.subscribe(
      (loggedIn) => (this.loggedIn = loggedIn)
    );

    this.remote.getCookieExist().subscribe({
      next: (data) => {
        this.remote.changeLoggedIn(data);
      },
    });

    if (this.loggedIn) {
      this.remote.redirect('/products');
    }
  }

  onSubmit() {
    let customer: CustomerSignUp = {
      firstName: this.firstName,
      lastName: this.lastName,
      email: this.email,
      password: this.password,
    };

    this.remote.postCustomerSignUp(customer).subscribe({
      next: (data) => {
        this.remote.setLocalStorage('customer', data);
        this.remote.redirect(`/user/${data.customerId}`);
      },
      error: (error: HttpErrorResponse) => {
        console.log(error.error);
      },
    });
  }
}
