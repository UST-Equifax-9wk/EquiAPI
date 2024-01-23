import { ChangeDetectorRef, Component, DoCheck, OnInit } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
import { RemoteService } from '../remote.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Customer } from '../dto/customer-dto';
import { CartComponent } from '../cart/cart.component';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterOutlet, RouterLink, CommonModule, CartComponent],
  templateUrl: './navbar.component.html',
})
export class NavbarComponent implements OnInit {
  user: Customer;

  constructor(private remote: RemoteService, private cdRef: ChangeDetectorRef) {
    this.user = this.remote.getStorageItem('customer');
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

    this.user = this.remote.getStorageItem('customer');
  }

  onLogout() {
    this.remote.postCustomerLogout().subscribe({
      next: () => {
        this.remote.clearStorage();
        this.remote.changeLoggedIn(false);
        this.remote.redirect('/');
      },
      error: (error: HttpErrorResponse) => {
        console.log(error.error);
      },
    });
  }
}
