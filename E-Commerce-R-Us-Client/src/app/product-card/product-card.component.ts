import { Component, Input } from '@angular/core';
import { Product } from '../products/products.component';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { TruncatePipe } from '../truncate.pipe';
import { CartService } from '../cart/cart.service';
import { NotExpr } from '@angular/compiler';
import { RemoteService } from '../remote.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-product-card',
  standalone: true,
  imports: [CommonModule, RouterLink, TruncatePipe],
  templateUrl: './product-card.component.html',
})
export class ProductCardComponent {
  constructor(
    private router: Router,
    private cartService: CartService,
    private remote: RemoteService
  ) {}

  @Input() product: any;

  goToProductDetail(productId: string): void {
    this.router.navigate(['/products', productId]);
  }

  addToCart(productId: number, price: number) {
    this.cartService.addToCart(productId, price).subscribe({
      next: (data) => {
        this.cartService.getCartItems().subscribe({
          next: (data) => {
            this.remote.setLocalStorage('cart', data);
          },
          error: (error: HttpErrorResponse) => {
            console.log(error);
          },
        });
      },
      error: (error: HttpErrorResponse) => {
        console.log(error.message);
      },
    });
  }
}
