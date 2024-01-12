import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../products/products.component';
import { ProductDetail } from './product-detail.component';

@Injectable({
  providedIn: 'root',
})
export class ProductDetailService {
  constructor(private http: HttpClient) {}

  getProductDetail(productId: string): Observable<ProductDetail> {
    let url: string = `http://localhost:8080/products/${productId}`;
    console.log(productId);
    let options: object = {};
    return this.http.get<ProductDetail>(url, options);
  }
}
