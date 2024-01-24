import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor() {}
  authenticate(): boolean {
    let curCustomer = localStorage.getItem('customer');
    return curCustomer === null ? false : true;
  }
}
