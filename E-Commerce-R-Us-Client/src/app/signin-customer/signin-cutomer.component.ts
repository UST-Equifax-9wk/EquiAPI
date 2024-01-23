import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RemoteService } from '../remote.service';
import { CustomerSignIn } from '../dto/customer-sign-in';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-signup-customer',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './signin-customer.component.html',
})
export class SigninCustomerComponent implements OnInit {
  public email: string;
  public password: string;

  constructor(private remote: RemoteService) {
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

  onSubmit(): any {
    let customer: CustomerSignIn = {
      email: this.email,
      password: this.password,
    };

    this.remote.postCustomerLogin(customer).subscribe({
      next: (data) => {
        this.remote.setLocalStorage('customer', data);
        this.remote.changeLoggedIn(true);

        this.remote.redirect(`/user/${data.customerId}`);
      },
      error: (error: HttpErrorResponse) => {
        console.log(error.error);
      },
    });
  }
}
