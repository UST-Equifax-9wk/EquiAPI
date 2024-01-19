import { Component, Input, OnInit } from '@angular/core';
import { CartItem } from '../dto/cart-item-dto';
import { CommonModule } from '@angular/common';
import { CartService } from './cart.service';
import { HttpErrorResponse } from '@angular/common/http';
import { TruncatePipe } from '../truncate.pipe';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [CommonModule, TruncatePipe],
  templateUrl: './cart.component.html',
})
export class CartComponent implements OnInit {
  open: Boolean;

  @Input() cartItems!: CartItem[];

  constructor(private cartService: CartService) {
    this.open = true;
  }

  onOpen(): void {
    console.log('hit');
    this.open = !this.open;
  }

  ngOnInit(): void {
    this.cartService.getCartItems().subscribe({
      next: (data) => {
        this.cartItems = data;
        console.log(this.cartItems);
      },
      error: (error: HttpErrorResponse) => {
        console.log(error);
      },
    });
  }
}
