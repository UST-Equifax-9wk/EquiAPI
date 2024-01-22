import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, retry } from 'rxjs';
import { CartItem } from '../dto/cart-item-dto';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  constructor(private http: HttpClient) {}

  getCartItems(): Observable<CartItem[]> {
    return this.http
      .get<CartItem[]>('/api' + '/customers/cart/view-cart', {
        withCredentials: true,
      })
      .pipe(retry(1));
  }

  deleteCartItem(productId: number): Observable<CartItem[]> {
    return this.http
      .delete<CartItem[]>('/api' + `/customers/cart/remove-item/${productId}`, {
        withCredentials: true,
      })
      .pipe(retry(1));
  }

  addToCart(productId: number, price: number): Observable<CartItem[]> {
    return this.http
      .post<CartItem[]>(
        '/api' + '/customers/cart/add-to-cart',
        {
          productId: productId,
          price: price,
        },
        {
          withCredentials: true,
        }
      )
      .pipe(retry(1));
  }

  // get cart array

  // calculate Total
  total(items: CartItem[]): number {
    let total = 0;

    for (let item of items) {
      total += item.price;
    }

    return total;
  }
}
