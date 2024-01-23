import {
  ChangeDetectorRef,
  Component,
  Input,
  OnChanges,
  OnInit,
  NgZone,
  SimpleChange,
  SimpleChanges,
  OnDestroy,
} from '@angular/core';
import { CartItem } from '../dto/cart-item-dto';
import { CommonModule } from '@angular/common';
import { CartService } from './cart.service';
import { HttpErrorResponse } from '@angular/common/http';
import { TruncatePipe } from '../truncate.pipe';
import { RemoteService } from '../remote.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [CommonModule, TruncatePipe],
  templateUrl: './cart.component.html',
})
export class CartComponent implements OnInit, OnChanges {
  open: Boolean;

  cartItems!: CartItem[];
  total!: number;

  constructor(
    private cartService: CartService,
    private remote: RemoteService,
    private ngZone: NgZone,
    private router:Router
  ) {
    this.open = false;
  }

  onOpen(): void {
    this.open = !this.open;
  }

  ngOnInit(): void {
    this.cartService.currentCart.subscribe((cart) => (this.cartItems = cart));
    this.cartService.currentTotal.subscribe((total) => (this.total = total));
    this.getCartItems();
  }

  onItemDelete(productId: number): void {
    this.cartService.deleteCartItem(productId).subscribe({
      next: (data) => {
        this.cartService.changeCart(data);
        console.log(data);
      },
      error: (error: HttpErrorResponse) => {
        console.log(error);
      },
    });

    this.getCartItems();
  }

  getCartItems(): void {
    this.cartService.getCartItems().subscribe({
      next: (data) => {
        this.cartService.changeCart(data);
        this.remote.setLocalStorage('cart', data);
      },
      error: (error: HttpErrorResponse) => {
        console.log(error);
      },
    });
  }

  ngOnChanges() {
    this.cartService.currentCart;
  }

  goToCheckoutPage(){
    this.router.navigate(['checkout'])
  }
}
