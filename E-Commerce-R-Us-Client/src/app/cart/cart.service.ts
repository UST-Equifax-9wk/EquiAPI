import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, retry } from 'rxjs';
import { CartItem } from '../dto/cart-item-dto';
import { RemoteService } from '../remote.service';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  private cart = new BehaviorSubject<CartItem[]>([]);
  private total = new BehaviorSubject<number>(0);
  currentCart = this.cart.asObservable();
  currentTotal = this.total.asObservable();

  changeCart(items: CartItem[]) {
    this.cart.next(items);
    this.total.next(this.totalCalc(items));
  }

  constructor(private http: HttpClient, private remote: RemoteService) {}
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

  // calculate Total
  totalCalc(items: CartItem[]): number {
    let total = 0;

    for (let item of items) {
      total += item.price;
    }

    return total;
  }
}
