import { Component, OnInit, Output } from '@angular/core';
import { ProductCardComponent } from '../product-card/product-card.component';
import { CommonModule } from '@angular/common';
import { ProductsService } from './products.service';
import { HttpErrorResponse } from '@angular/common/http';
import { EventEmitter } from 'http-proxy';
import { CartItem } from '../dto/cart-item-dto';
import { CartService } from '../cart/cart.service';

export interface Product {
  productId: number;
  name: string;
  retailPrice: number;
  discountedPrice: number;
  description: string;
}

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [ProductCardComponent, CommonModule],
  templateUrl: './products.component.html',
})
export class ProductsComponent implements OnInit {
  products: Product[];
  pgNo: number;
  pgSize: number;
  moreProducts: boolean;

  constructor(
    private productsService: ProductsService,
    private cartService: CartService
  ) {
    this.products = [];
    this.pgNo = 0;
    this.pgSize = 6;
    this.moreProducts = true;
  }

  ngOnInit(): void {
    this.getAllProducts(this.pgNo, this.pgSize);
  }
  nextPage() {
    this.pgNo = this.pgNo + 1;
    this.getAllProducts(this.pgNo, this.pgSize);
  }

  getAllProducts(pgNo: number, pgSize: number): void {
    this.productsService.getAllProducts(this.pgNo, this.pgSize).subscribe({
      next: (value) => {
        this.products = value;
        if (value.length === 0) {
          this.moreProducts = false;
        }
      },
      error: (error: HttpErrorResponse) => {
        console.log(error);
      },
    });
  }
}
