import { Component, OnInit } from '@angular/core';
import { ProductDetailService } from './product-detail.service';
import { Product } from '../products/products.component';
import { CommonModule } from '@angular/common';

interface ProductDetail extends Product {
  description: string;
  inventory: number;
  reviews: Review[];
}

interface Review {}

@Component({
  selector: 'app-product-detail',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './product-detail.component.html',
})
export class ProductDetailComponent implements OnInit {
  constructor(private productDetailService: ProductDetailService) {}
  productDetail: ProductDetail = {
    productId: 0,
    name: '',
    retailPrice: 0,
    discountedPrice: 0,
    seller: {
      sellerId: 0,
      firstName: '',
      lastName: '',
    },
    description: '',
    inventory: 0,
    reviews: [],
  };
  productId: number = 0;
  ngOnInit(): void {
    let that = this;
    this.productDetailService.getProductDetail(that.productId).subscribe({
      next(value) {
        console.log(value);
      },
    });
  }
}
