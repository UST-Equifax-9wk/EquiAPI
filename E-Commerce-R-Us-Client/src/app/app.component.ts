import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from './navbar/navbar.component';
import { ProductsComponent } from './products/products.component';
import { RemoteService } from './remote.service';
<<<<<<< HEAD
import { HttpErrorResponse } from '@angular/common/http';
=======
import { ApiComponent } from './api/api.component';
>>>>>>> dfad17ef07deac1aa1c1a75e4326053bee2c5f5a

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, NavbarComponent, ProductsComponent, ApiComponent],
  templateUrl: './app.component.html',
})
export class AppComponent {
  title = 'EquiAPI';

  constructor(private remote: RemoteService) {}

  // ngOnInit(): void {
  //   let user = this.remote.getStorageItem('user');
  //   if (!user) {
  //     this.remote.getCustomerAuth().subscribe({
  //       next: (data) => {
  //         if (data.valueOf()) {
  //           this.remote.setLocalStorage('customer', data);
  //           this.remote.redirect(`/dashboard/${data.customerId}`);
  //         } else {
  //           this.remote.redirect('');
  //         }
  //       },
  //       error: (error: HttpErrorResponse) => {
  //         console.log(error.message);
  //       },
  //     });
  //   }
  // }
}
