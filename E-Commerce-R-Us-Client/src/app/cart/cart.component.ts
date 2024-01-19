import {
  ChangeDetectorRef,
  Component,
  DoCheck,
  Input,
  OnInit,
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
export class CartComponent implements OnInit {
  open: Boolean;

  @Input() cartItems!: CartItem[];
  @Input() total!: number;

  constructor(
    private cartService: CartService,
    private remote: RemoteService,
    private cdRef: ChangeDetectorRef
  ) {
    this.open = true;
  }

  onOpen(): void {
    console.log('hit');
    this.open = !this.open;
  }

  onItemDelete(productId: number): void {
    this.cartService
      .deleteCartItem(productId)
      .subscribe((error) => console.log(error));

    this.getCartItems();
  }

  ngOnInit(): void {
    if (this.remote.getStorageItem('cart') === null) {
      this.getCartItems();
    } else {
      this.cartItems = this.remote.getStorageItem('cart');
    }
    this.total = this.cartService.total(this.cartItems);
  }

  getCartItems(): void {
    this.cartService.getCartItems().subscribe({
      next: (data) => {
        this.cartItems = data;
        this.remote.setLocalStorage('cart', data);
      },
      error: (error: HttpErrorResponse) => {
        console.log(error);
      },
    });
    this.total = this.cartService.total(this.cartItems);
  }
}
