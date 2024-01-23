import { Component, Input, OnInit, Output } from '@angular/core';
import { Product } from '../products/products.component';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { TruncatePipe } from '../truncate.pipe';
import { CartService } from '../cart/cart.service';
<<<<<<< HEAD
import { NotExpr } from '@angular/compiler';
import { RemoteService } from '../remote.service';
import { HttpErrorResponse } from '@angular/common/http';
import { CartItem } from '../dto/cart-item-dto';
=======
>>>>>>> dfad17ef07deac1aa1c1a75e4326053bee2c5f5a

@Component({
  selector: 'app-product-card',
  standalone: true,
  imports: [CommonModule, RouterLink, TruncatePipe],
  templateUrl: './product-card.component.html',
})
<<<<<<< HEAD
export class ProductCardComponent implements OnInit {
  cartItems!: CartItem[];
  constructor(
    private router: Router,
    private cartService: CartService,
    private remote: RemoteService
  ) {}
=======
export class ProductCardComponent {
  constructor(private router: Router, private cartService: CartService) {}
>>>>>>> dfad17ef07deac1aa1c1a75e4326053bee2c5f5a

  @Input() product: any;

  ngOnInit(): void {
    this.cartService.currentCart.subscribe((cart) => (this.cartItems = cart));
    this.getCartItems();
  }

  goToProductDetail(productId: string): void {
    this.router.navigate(['/products', productId]);
  }

<<<<<<< HEAD
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
=======
  addToCart(product: Product): void {
    console.log(product);
    this.cartService.addToCart(this.product).subscribe({
      next(value) {
        console.log(value);
>>>>>>> dfad17ef07deac1aa1c1a75e4326053bee2c5f5a
      },
    });
  }
}
