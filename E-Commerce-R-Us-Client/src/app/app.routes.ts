import { Routes } from '@angular/router';
import { SignupSellerComponent } from './signup-seller/signup-seller.component';
import { ProductDetailComponent } from './product-detail/product-detail.component';
import { ProductsComponent } from './products/products.component';

export const routes: Routes = [
  {
    path: 'seller/sign-up',
    component: SignupSellerComponent,
  },
  {
    path: 'products',
    children: [
      {
        path: '',
        component: ProductsComponent,
      },
      {
        path: ':productId',
        component: ProductDetailComponent,
      },
    ],
  },
];
