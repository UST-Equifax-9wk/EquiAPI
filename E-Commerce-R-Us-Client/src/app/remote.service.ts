import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, retry } from 'rxjs';
import { SignUpSeller } from './dto/signup-seller-dto';
import { Seller } from './dto/seller-dto';

@Injectable({
  providedIn: 'root',
})
export class RemoteService {
  constructor(private http: HttpClient, private router: Router) {}

  // Sign up Seller
  postSeller(seller: SignUpSeller): Observable<Seller> {
    return this.http
      .post<Seller>('/api' + '/auth/seller/sign-up', seller, {
        withCredentials: true,
      })
      .pipe(retry(1));
  }

  // Sign up Customer

  // Sign in Seller

  // Sign in Customer

  // Util functions
  // redirect function

  // Storage Functions
  // set Local storage
}
