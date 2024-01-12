import { Component, OnInit } from '@angular/core';
import { ProductCardComponent } from '../product-card/product-card.component';
import { CommonModule } from '@angular/common';
import { ProductsService } from './products.service';

export interface Product {
  productId: number;
  name: string;
  retailPrice: number;
  discountedPrice: number;
  seller: Seller;
}

export interface Seller {
  sellerId: number;
  firstName: string;
  lastName: string;
}

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [ProductCardComponent, CommonModule],
  templateUrl: './products.component.html',
})
export class ProductsComponent implements OnInit {
  constructor(private productsService: ProductsService) {}
  products: Product[] = [];
  pgNo = 0;
  pgSize = 6;
  moreProducts = true;
  ngOnInit(): void {
    this.getAllProducts(this.pgNo, this.pgSize);
  }
  nextPage() {
    this.pgNo = this.pgNo + 1;
    this.getAllProducts(this.pgNo, this.pgSize);
  }
  getAllProducts(pgNo: number, pgSize: number): void {
    let that = this;
    this.productsService.getAllProducts(this.pgNo, this.pgSize).subscribe({
      next(value) {
        that.products = that.products.concat(value);
        if (value.length === 0) {
          that.moreProducts = false;
        }
      },
      error(error) {
        console.log(error);
      },
    });
  }
}
