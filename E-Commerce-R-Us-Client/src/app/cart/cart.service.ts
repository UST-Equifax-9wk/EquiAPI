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
}
