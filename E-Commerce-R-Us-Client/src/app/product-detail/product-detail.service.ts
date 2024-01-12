import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../products/products.component';

@Injectable({
  providedIn: 'root',
})
export class ProductDetailService {
  constructor(private http: HttpClient) {}

  getProductDetail(productId: number): Observable<Product> {
    let url: string = `http://localhost:8080/products/${productId}`;
    let options: object = {};
    return this.http.get<Product>(url, options);
  }
}
