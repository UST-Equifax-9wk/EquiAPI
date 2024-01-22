import { Component, Input } from '@angular/core';
import { Product } from '../products/products.component';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { TruncatePipe } from '../truncate.pipe';

@Component({
  selector: 'app-product-card',
  standalone: true,
  imports: [CommonModule, RouterLink, TruncatePipe],
  templateUrl: './product-card.component.html',
})
export class ProductCardComponent {
  constructor(private router: Router) {}

  @Input() product: any;

  goToProductDetail(productId: string): void {
    this.router.navigate(['/products', productId]);
  }
}
