import { Routes } from '@angular/router';
import { ProductsComponent } from './products/products.component';
import { SignupCustomerComponent } from './signup-customer/signup-cutomer.component';
import { SigninCustomerComponent } from './signin-customer/signin-cutomer.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ProductDetailComponent } from './product-detail/product-detail.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { CheckoutComponent } from './checkout/checkout.component';

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
    path: 'checkout',
    component: CheckoutComponent,
  },
  {
    path: 'user/:email',
    component: DashboardComponent,
  },
  {
    path: 'products/:id',
    component: ProductDetailComponent,
  },
];
