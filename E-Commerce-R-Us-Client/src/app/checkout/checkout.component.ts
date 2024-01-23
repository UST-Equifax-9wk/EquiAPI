<<<<<<< HEAD
import { Component } from '@angular/core';
import { CartService } from '../cart/cart.service';
import { OnInit } from '@angular/core';
import { CartItem } from '../dto/cart-item-dto';
import { CommonModule } from '@angular/common';
import { RemoteService } from '../remote.service';
import { Router } from '@angular/router';
=======
import { Component, OnInit } from '@angular/core';
import { CartService } from '../cart/cart.service';
import { CartItem } from '../dto/cart-item-dto';
import { CommonModule } from '@angular/common';
>>>>>>> 9ccdd862d768b35da08ba75669ee7e1e1b8892ae

@Component({
  selector: 'app-checkout',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './checkout.component.html',
<<<<<<< HEAD
  styleUrl: './checkout.component.css'
})
export class CheckoutComponent implements OnInit{


  constructor(private cartService: CartService, private remoteService: RemoteService, private router:Router){}

  deleteCartItem(arg0: number) {
    this.cartService.deleteCartItem(arg0).subscribe()
    }

  cartItems : CartItem [] = []

  ngOnInit(): void {
    let that = this;
      this.cartService.getCartItems().subscribe(
        {
          next(value){
            that.cartItems=value;
          }
        }
      )
  }


  submitCartItems(){
    this.remoteService.postCheckOut().subscribe(
      {
        next(value){console.log(value)},
        error(e){console.log(e);},
      }
    )
    this.router.navigate(["orders"])
  }

=======
  styleUrl: './checkout.component.css',
})
export class CheckoutComponent implements OnInit {
  constructor(private cartService: CartService) {}

  cartItems: CartItem[] = [];

  ngOnInit() {
    let that = this;
    console.log('Checkout', that);
    this.cartService.getCartItems().subscribe({
      next(value) {
        that.cartItems = value;
        console.log('inside', this);
        console.log(that.cartItems);
      },
    });
  }
>>>>>>> 9ccdd862d768b35da08ba75669ee7e1e1b8892ae
}
