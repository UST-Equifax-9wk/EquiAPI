import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, retry } from 'rxjs';
import { OrderDtos } from '../dto/order-dtos';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http:HttpClient) {}

  getOrders(): Observable <OrderDtos[]>{
    return this.http
    .get<OrderDtos[]>('/customers/view-orders',{
      withCredentials: true,
    } )
    .pipe(retry(1));
  }
}
