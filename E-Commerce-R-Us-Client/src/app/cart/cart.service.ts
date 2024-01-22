import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, retry } from 'rxjs';
import { CartItem } from '../dto/cart-item-dto';
import { RemoteService } from '../remote.service';
import { Product } from '../products/products.component';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  constructor(private http: HttpClient, private remoteService: RemoteService) {}

  getCartItems(): Observable<CartItem[]> {
    return this.http
      .get<CartItem[]>('/api' + '/customers/cart/view-cart', {
        withCredentials: true,
      })
      .pipe(retry(1));
  }

  deleteCartItem(productId: number): Observable<CartItem[]> {
    return this.http
      .delete<CartItem[]>(
        '/api' + `/customers/cart/remove-item/${productId}`,

        { withCredentials: true }
      )
      .pipe(retry(1));
  }

  addToCart(product: Product): Observable<CartItem> {
    let cart = this.remoteService.getStorageItem('cart');
    cart.push(product);
    this.remoteService.setLocalStorage('cart', cart);
    return this.http.post<CartItem>(
      `http://localhost:8080/customers/cart/add-to-cart`,
      {
        productId: product.productId,
        price: product.retailPrice,
      },
      {
        withCredentials: true,
      }
    );
  }

  // calculate Total
  total(items: CartItem[]): number {
    let total = 0;

    for (let item of items) {
      total += item.price;
    }

    return total;
  }
}
