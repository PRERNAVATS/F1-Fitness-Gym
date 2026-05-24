import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiResponse, GalleryImage } from '../models/gym.model';

@Injectable({
  providedIn: 'root'
})
export class GalleryService {
  private baseUrl = 'http://localhost:8089/api/gallery';

  constructor(private http: HttpClient) { }

  getImages(): Observable<ApiResponse<GalleryImage[]>> {
    return this.http.get<ApiResponse<GalleryImage[]>>(this.baseUrl);
  }

  getImageById(id: number): Observable<ApiResponse<GalleryImage>> {
    return this.http.get<ApiResponse<GalleryImage>>(`${this.baseUrl}/${id}`);
  }

  addImage(image: GalleryImage): Observable<ApiResponse<GalleryImage>> {
    return this.http.post<ApiResponse<GalleryImage>>(this.baseUrl, image);
  }

  updateImage(id: number, image: GalleryImage): Observable<ApiResponse<GalleryImage>> {
    return this.http.put<ApiResponse<GalleryImage>>(`${this.baseUrl}/${id}`, image);
  }

  deleteImage(id: number): Observable<ApiResponse<void>> {
    return this.http.delete<ApiResponse<void>>(`${this.baseUrl}/${id}`);
  }
}
