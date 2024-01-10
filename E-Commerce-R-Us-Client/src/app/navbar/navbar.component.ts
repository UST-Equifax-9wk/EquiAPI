import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { SignupSellerComponent } from '../signup-seller/signup-seller.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterOutlet, RouterLink, CommonModule],
  templateUrl: './navbar.component.html',
})
export class NavbarComponent {}
