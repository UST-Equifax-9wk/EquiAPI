import { Component, OnInit } from '@angular/core';
import { ApiService } from './api.service';
import { CommonModule } from '@angular/common';

export interface Api {
  apiId: string;
  productId: number;
}

@Component({
  selector: 'app-api',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './api.component.html',
  styleUrl: './api.component.css'
})


export class ApiComponent implements OnInit {
  apiData: Api[] = [];

  constructor(private apiService: ApiService){}

  ngOnInit(): void {
    this.apiService.getApiKeys().subscribe((data)=>{
      this.apiData = data;
    })
  }

  deleteApi(apiId: string): void {
    this.apiService.deleteApiKey(apiId).subscribe(() => {
      this.apiService.getApiKeys().subscribe((data) => {
        this.apiData = data;
      });
    });
  }

}
