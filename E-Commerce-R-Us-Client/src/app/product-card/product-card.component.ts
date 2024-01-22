import { Component, Input } from '@angular/core';
import { Product } from '../products/products.component';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { TruncatePipe } from '../truncate.pipe';
import { CartService } from '../cart/cart.service';

@Component({
  selector: 'app-product-card',
  standalone: true,
  imports: [CommonModule, RouterLink, TruncatePipe],
  templateUrl: './product-card.component.html',
})
export class ProductCardComponent {
  constructor(private router: Router, private cartService: CartService) {}

  @Input() product: any;

  goToProductDetail(productId: string): void {
    this.router.navigate(['/products', productId]);
  }

  addToCart(product: Product): void {
    console.log(product);
    this.cartService.addToCart(this.product).subscribe({
      next(value) {
        console.log(value);
      },
    });
  }
}
