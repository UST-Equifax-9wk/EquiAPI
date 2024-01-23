import { ChangeDetectorRef, Component, DoCheck, OnInit } from '@angular/core';
import { Customer } from '../dto/customer-dto';
import { RemoteService } from '../remote.service';
import { CommonModule } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { ApiComponent } from '../api/api.component';


@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, ApiComponent],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css',
})
export class DashboardComponent implements DoCheck, OnInit {
  user: Customer;
  constructor(private remote: RemoteService, private cdRef: ChangeDetectorRef) {
    this.user = this.remote.getStorageItem('customer');
  }

  ngOnInit(): void {
    if (!this.user) {
      this.remote.getCustomerAuth().subscribe({
        next: (data) => {
          if (data) {
            this.remote.setLocalStorage('customer', data);
            this.user = data;
          }
        },
        error: (error: HttpErrorResponse) => {
          this.remote.redirect('/signin');
        },
      });
    }
    console.log("hello");
  }

  ngDoCheck(): void {
    if (this.user !== this.remote.getStorageItem('customer')) {
      this.user = this.remote.getStorageItem('customer');
      this.cdRef.detectChanges();
    }
  }
}