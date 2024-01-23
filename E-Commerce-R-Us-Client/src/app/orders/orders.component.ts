import { Component, OnInit } from '@angular/core';
import { OrderService } from './order.service';
import { OrderDtos } from '../dto/order-dtos';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-orders',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './orders.component.html',
  styleUrl: './orders.component.css'
})
export class OrdersComponent implements OnInit{

  constructor(private orderService:OrderService){}

  orderDtos:OrderDtos [] = []

  ngOnInit(): void {
    let that = this;
    this.orderService.getOrders().subscribe(
      {
        next(value){
          that.orderDtos=value;
        }
      }
    )
      
  }



}
