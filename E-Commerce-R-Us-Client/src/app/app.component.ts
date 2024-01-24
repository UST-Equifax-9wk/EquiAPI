import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from './navbar/navbar.component';
import { ProductsComponent } from './products/products.component';
import { RemoteService } from './remote.service';
import { HttpErrorResponse } from '@angular/common/http';
import { ApiComponent } from './api/api.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    RouterOutlet,
    NavbarComponent,
    ProductsComponent,
    ApiComponent,
  ],
  templateUrl: './app.component.html',
})
export class AppComponent implements OnInit {
  title = 'EquiAPI';

  constructor(private remote: RemoteService) {}

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
  }
}
