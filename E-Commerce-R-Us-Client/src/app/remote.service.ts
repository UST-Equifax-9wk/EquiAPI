import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, retry } from 'rxjs';
import { CustomerSignUp } from './dto/customer-sign-up';
import { Customer } from './dto/customer-dto';
import { CustomerSignIn } from './dto/customer-sign-in';

@Injectable({
  providedIn: 'root',
})
export class RemoteService {
  constructor(private http: HttpClient, private router: Router) {}

  // Sign up
  postCustomerSignUp(customer: CustomerSignUp): Observable<Customer> {
    return this.http
      .post<Customer>('/api' + '/auth/seller/sign-up', customer, {
        withCredentials: true,
      })
      .pipe(retry(1));
  }

  // Sign in Customer
  postCustomerLogin(customer: CustomerSignIn): Observable<Customer> {
    return this.http
      .post<Customer>('/api' + '/auth/seller/sign-in', customer, {
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
}
