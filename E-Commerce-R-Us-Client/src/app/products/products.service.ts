import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from './products.component';

@Injectable({
  providedIn: 'root',
})
export class ProductsService {
  constructor(private http: HttpClient) {}

  getAllProducts(pgNo: number, pgSize: number): Observable<Product[]> {
    let url: string = `http://localhost:8080/products?pg_no=${pgNo}&pg_size=${pgSize}`;
    let options: object = {};
    return this.http.get<Product[]>(url, options);
  }
}
