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

  ngOnInit(): void {
    this.cartService.currentCart.subscribe((cart) => (this.cartItems = cart));
    this.getCartItems();
  }

  goToProductDetail(productId: string): void {
    this.router.navigate(['/products', productId]);
  }

  addToCart(productId: number, price: number) {
    this.cartService.addToCart(productId, price).subscribe({
      next: (data) => {
        console.log(data);
        this.cartService.changeCart(data);
      },
      error: (error: HttpErrorResponse) => {
        console.log(error.message);
      },
    });
    this.getCartItems();
  }

  getCartItems(): void {
    this.cartService.getCartItems().subscribe({
      next: (data) => {
        this.cartService.changeCart(data);
        console.log(data);
      },
      error: (error: HttpErrorResponse) => {
        console.log(error);
      },
    });
  }
}
