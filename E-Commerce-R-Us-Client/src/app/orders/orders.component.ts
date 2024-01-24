import { Component, OnInit } from '@angular/core';
import { OrderService } from './order.service';
import { OrderDtos } from '../dto/order-dtos';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-orders',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './orders.component.html',
  styleUrl: './orders.component.css',
})
export class OrdersComponent implements OnInit {
  constructor(private orderService: OrderService) {}

  orderDtos: any[] = [];
  itemDescription: any = [];

  ngOnInit(): void {
    let that = this;
    this.orderService.getOrders().subscribe({
      next(value) {
        let itemDescription = value.map((x) => x.itemDescription);
        console.log(itemDescription);
        let arr = itemDescription.map((x) => {
          return x.map((str) => str.split(', \n,'));
        });
        console.log(arr);
        that.itemDescription = arr;
        that.itemDescription = arr.map((x) => {
          // console.log(x);
          return x.map((str: any) =>
            str.map((i: any) => {
              return i.split(':');
            })
          );
        });
        console.log(that.itemDescription);
        that.orderDtos = value;
      },
    });
  }
}
