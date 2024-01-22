import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Api } from './api.component';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private apiUrl = 'http://localhost:8080/customer/view-apis';
  constructor(private http: HttpClient) { }

  getApiKeys(): Observable<Api[]> {
    const options = {
      withCredentials: true,
    };
    return this.http.get<Api[]>(this.apiUrl, options);
  }

  deleteApiKey(apiId: string): Observable<Api>{
    const url = `http://localhost:8080/customer/delete-api`;

    const body = { "apiId": apiId };

    const options = {
      body,
      withCredentials: true,
    };

    return this.http.delete<Api>(url, options);

  }
}
