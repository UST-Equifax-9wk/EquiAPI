import { Component, OnInit } from '@angular/core';
import { CartService } from '../cart/cart.service';
import { CartItem } from '../dto/cart-item-dto';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-checkout',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './checkout.component.html',
  styleUrl: './checkout.component.css',
})
export class CheckoutComponent implements OnInit {
  constructor(private cartService: CartService) {}

  cartItems: CartItem[] = [];

  ngOnInit() {
    let that = this;
    console.log('Checkout', that);
    this.cartService.getCartItems().subscribe({
      next(value) {
        that.cartItems = value;
        console.log('inside', this);
        console.log(that.cartItems);
      },
    });
  }
}
