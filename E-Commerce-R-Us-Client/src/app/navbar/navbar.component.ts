import { ChangeDetectorRef, Component, DoCheck, OnInit } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
import { RemoteService } from '../remote.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Customer } from '../dto/customer-dto';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterOutlet, RouterLink, CommonModule],
  templateUrl: './navbar.component.html',
})
export class NavbarComponent implements OnInit, DoCheck {
  isLoggedIn: boolean;
  user: Customer;

  constructor(private remote: RemoteService, private cdRef: ChangeDetectorRef) {
    this.isLoggedIn = false;
    this.user = this.remote.getStorageItem('customer');
  }

  onLogout() {
    this.remote.postCustomerLogout().subscribe({
      next: () => {
        this.remote.clearStorage();
        this.remote.redirect('/');
      },
      error: (error: HttpErrorResponse) => {
        console.log(error.error);
      },
    });
  }

  ngOnInit(): void {
    this.isLoggedIn = this.remote.getStorageItem('customer') ? true : false;
  }

  ngDoCheck(): void {
    this.isLoggedIn = this.remote.getStorageItem('customer') ? true : false;
    this.cdRef.detectChanges();
  }
}
