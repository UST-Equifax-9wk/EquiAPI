import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RemoteService } from '../remote.service';
import { SignUpSeller } from '../dto/signup-seller-dto';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-signup-seller',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './signup-seller.component.html',
})
export class SignupSellerComponent {
  public firstName: string;
  public lastName: string;
  public email: string;
  public password: string;

  constructor(private remote: RemoteService) {
    this.firstName = '';
    this.lastName = '';
    this.email = '';
    this.password = '';
  }

  onSubmit() {
    let seller: SignUpSeller = {
      firstName: this.firstName,
      lastName: this.lastName,
      email: this.email,
      password: this.password,
    };

    this.remote.postSeller(seller).subscribe({
      next: (data) => {
        alert(' Hello ' + data.firstName);
      },
      error: (error: HttpErrorResponse) => {
        console.log(error.error);
      },
    });
  }
}
