import { Component, OnInit } from '@angular/core';
import { ProductDetailService } from './product-detail.service';
import { Product } from '../products/products.component';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';

export interface ProductDetail extends Product {
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
  constructor(
    private productDetailService: ProductDetailService,
    private activatedRoute: ActivatedRoute
  ) {}
  productDetail: ProductDetail = {
    productId: 0,
    name: '',
    retailPrice: 0,
    discountedPrice: 0,
    description: '',
    inventory: 0,
    reviews: [],
  };
  productId: string = '1';
  ngOnInit(): void {
    this.setProductIdFromUrlSeg();
    this.getProductDetail(this.productId);
  }

  setProductIdFromUrlSeg() {
    let that = this;
    this.activatedRoute.url.subscribe({
      next(value) {
        // console.log('Activated route path: ', value[0].path);
        let { path } = value[0];
        that.productId = path;
      },
    });
  }

  getProductDetail(productId: string) {
    let that = this;
    this.productDetailService.getProductDetail(productId).subscribe({
      next(value) {
        console.log(value);
        that.productDetail = value;
      },
    });
  }
}
