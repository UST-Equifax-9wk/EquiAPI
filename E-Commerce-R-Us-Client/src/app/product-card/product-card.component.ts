import { Component, Input, OnInit, Output } from '@angular/core';
import { Product } from '../products/products.component';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { TruncatePipe } from '../truncate.pipe';
import { CartService } from '../cart/cart.service';
import { NotExpr } from '@angular/compiler';
import { RemoteService } from '../remote.service';
import { HttpErrorResponse } from '@angular/common/http';
import { CartItem } from '../dto/cart-item-dto';

@Component({
  selector: 'app-product-card',
  standalone: true,
  imports: [CommonModule, RouterLink, TruncatePipe],
  templateUrl: './product-card.component.html',
})
export class ProductCardComponent implements OnInit {
  cartItems!: CartItem[];
  constructor(
    private router: Router,
    private cartService: CartService,
    private remote: RemoteService
  ) {}

  @Input() product: any;

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
    this.cartService.currentCart.subscribe((cart) => (this.cartItems = cart));
    if (this.loggedIn) {
      this.getCartItems();
    }
  }

  goToProductDetail(productId: string): void {
    this.router.navigate(['/products', productId]);
  }

  addToCart(productId: number, price: number) {
    if (this.loggedIn) {
      this.cartService.addToCart(productId, price).subscribe({
        next: (data) => {
          this.cartService.changeCart(data);
        },
        error: (error: HttpErrorResponse) => {
          console.log(error.message);
        },
      });
    } else {
      this.remote.redirect('signin');
    }
  }

  getCartItems(): void {
    this.cartService.getCartItems().subscribe({
      next: (data) => {
        this.cartService.changeCart(data);
      },
      error: (error: HttpErrorResponse) => {
        console.log(error);
      },
    });
  }
}
