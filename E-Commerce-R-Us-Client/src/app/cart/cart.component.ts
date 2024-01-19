import { Component } from '@angular/core';
import { RemoteService } from '../remote.service';
import { CartItem } from '../dto/cart-item-dto';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css',
})
export class CartComponent {
  open: Boolean;
  cartItems: number[];

  constructor(private remote: RemoteService) {
    this.open = true;
    this.cartItems = [1, 2, 3];
  }

  onOpen(): void {
    console.log('hit');
    this.open = !this.open;
  }
}
