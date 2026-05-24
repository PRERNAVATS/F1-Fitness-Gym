import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiResponse, ContactEnquiry } from '../models/gym.model';

@Injectable({
  providedIn: 'root'
})
export class ContactService {
  private baseUrl = 'http://localhost:8089/api/enquiries';

  constructor(private http: HttpClient) { }

  submitEnquiry(enquiry: ContactEnquiry): Observable<ApiResponse<ContactEnquiry>> {
    return this.http.post<ApiResponse<ContactEnquiry>>(this.baseUrl, enquiry);
  }

  getEnquiries(): Observable<ApiResponse<ContactEnquiry[]>> {
    return this.http.get<ApiResponse<ContactEnquiry[]>>(this.baseUrl);
  }

  deleteEnquiry(id: number): Observable<ApiResponse<void>> {
    return this.http.delete<ApiResponse<void>>(`${this.baseUrl}/${id}`);
  }
}
