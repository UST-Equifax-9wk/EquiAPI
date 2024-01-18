import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from './navbar/navbar.component';
import { ProductsComponent } from './products/products.component';
import { RemoteService } from './remote.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, NavbarComponent, ProductsComponent],
  templateUrl: './app.component.html',
})
export class AppComponent {
  title = 'E-Commerce-R-Us-Client';

  constructor(private remote: RemoteService) {}
}
