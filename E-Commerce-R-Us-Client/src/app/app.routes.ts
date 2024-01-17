import { Routes } from '@angular/router';
import { ProductsComponent } from './products/products.component';
import { CustomerSignUp } from './dto/customer-sign-up';
import { CustomerSignIn } from './dto/customer-sign-in';
import { SignupCustomerComponent } from './signup-customer/signup-cutomer.component';
import { SigninCustomerComponent } from './signin-customer/signin-cutomer.component';

export const routes: Routes = [
  {
    path: '',
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
];
