import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ApplicationRef, ChangeDetectorRef, Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable, map, retry } from 'rxjs';
import { CustomerSignUp } from './dto/customer-sign-up';
import { Customer } from './dto/customer-dto';
import { CustomerSignIn } from './dto/customer-sign-in';
import { CartItem } from './dto/cart-item-dto';

@Injectable({
  providedIn: 'root',
})
export class RemoteService {
  constructor(
    private http: HttpClient,
    private router: Router,
    private appRef: ApplicationRef
  ) {}
  private loggedIn = new BehaviorSubject<boolean>(false);
  currentLoggedIn = this.loggedIn.asObservable();

  changeLoggedIn(item: boolean) {
    this.loggedIn.next(item);
    this.appRef.tick();
  }

  // Sign u
  postCustomerSignUp(customer: CustomerSignUp): Observable<Customer> {
    return this.http
      .post<Customer>('/api' + '/auth/customer/sign-up', customer, {
        withCredentials: true,
      })
      .pipe(retry(1));
  }

  // Sign in Customer
  postCustomerLogin(customer: CustomerSignIn): Observable<Customer> {
    return this.http
      .post<Customer>('/api' + '/auth/customer/sign-in', customer, {
        withCredentials: true,
      })
      .pipe(retry(1));
  }

  // Customer logout
  postCustomerLogout(): Observable<object> {
    return this.http
      .post('/api' + '/auth/customer/logout', null, { withCredentials: true })
      .pipe(retry(1));
  }

  // Check auth
  getCustomerAuth(): Observable<Customer> {
    return this.http
      .get<Customer>('/api' + '/auth/customer/auth', {
        withCredentials: true,
      })
      .pipe(retry(1));
  }

  // Cookie
  getCookieExist(): Observable<boolean> {
    return this.http
      .get<boolean>('/api' + '/auth/cookie', {
        withCredentials: true,
      })
      .pipe(retry(1));
  }

  // Util functions
  // redirect function
  redirect(url: string) {
    this.router.navigate([url]);
  }

  // Storage Functions
  // set Local storage
  setLocalStorage(key: string, value: object) {
    const expiration = new Date().getTime() + 10800;
    const item = {
      value: value,
      expirationDate: expiration,
    };
    localStorage.setItem(key, JSON.stringify(item));
  }

  //Get storage
  getStorageItem(key: string) {
    const itemInStorage = localStorage.getItem(key);
    if (!itemInStorage) {
      return null;
    }
    const item = JSON.parse(itemInStorage);
    const now = new Date();
    if (item.expiration && now.getTime() > item.expiry) {
      localStorage.removeItem(key);
      return null;
    }

    return item.value;
  }

  removeStorageItem(key: string): void {
    localStorage.removeItem(key);
  }

  clearStorage(): void {
    localStorage.clear();
  }

  postCheckOut(): Observable<object> {
    return this.http.post('/api' + '/customers/order/checkout', null, {
      withCredentials: true,
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
    });
  }
}
