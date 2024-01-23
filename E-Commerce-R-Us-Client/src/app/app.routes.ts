import { Routes } from '@angular/router';
import { ProductsComponent } from './products/products.component';
import { SignupCustomerComponent } from './signup-customer/signup-cutomer.component';
import { SigninCustomerComponent } from './signin-customer/signin-cutomer.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ProductDetailComponent } from './product-detail/product-detail.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { CheckoutComponent } from './checkout/checkout.component';
<<<<<<< Updated upstream
=======
import { OrdersComponent } from './orders/orders.component';
>>>>>>> Stashed changes

export const routes: Routes = [
  {
    path: '',
    component: LandingPageComponent,
  },
  {
    path: 'products',
    component: ProductsComponent,
  },
  {
    path: 'signup',
    component: SignupCustomerComponent,
  },
  {
    path: 'signin',
    component: SigninCustomerComponent,
  },
  {
<<<<<<< Updated upstream
<<<<<<< HEAD
    path: 'user/:id',
=======
=======
>>>>>>> Stashed changes
    path: 'checkout',
    component: CheckoutComponent,
  },
  {
<<<<<<< Updated upstream
=======
    path: 'orders',
    component: OrdersComponent,
  },
  {
>>>>>>> Stashed changes
    path: 'user/:email',
>>>>>>> dfad17ef07deac1aa1c1a75e4326053bee2c5f5a
    component: DashboardComponent,
  },
  {
    path: 'products/:id',
    component: ProductDetailComponent,
  },
];
