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

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [CommonModule, TruncatePipe],
  templateUrl: './cart.component.html',
})
export class CartComponent implements OnInit, OnChanges {
  open: Boolean;

  cartItems!: CartItem[];
  @Input() total!: number;

  constructor(
    private cartService: CartService,
    private remote: RemoteService,
    private ngZone: NgZone
  ) {
    this.open = true;
  }

  onOpen(): void {
    this.open = !this.open;
  }

  ngOnInit(): void {
    this.cartService.currentCart.subscribe((cart) => (this.cartItems = cart));
    this.getCartItems();
    this.total = this.cartService.total(this.cartItems);
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
    this.total = this.cartService.total(this.cartItems);
  }

  ngOnChanges() {
    this.cartService.currentCart;
  }
}
