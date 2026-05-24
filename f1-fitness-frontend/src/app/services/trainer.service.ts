import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiResponse, Trainer } from '../models/gym.model';

@Injectable({
  providedIn: 'root'
})
export class TrainerService {
  private baseUrl = 'http://localhost:8089/api/trainers';

  constructor(private http: HttpClient) { }

  getTrainers(): Observable<ApiResponse<Trainer[]>> {
    return this.http.get<ApiResponse<Trainer[]>>(this.baseUrl);
  }

  getTrainerById(id: number): Observable<ApiResponse<Trainer>> {
    return this.http.get<ApiResponse<Trainer>>(`${this.baseUrl}/${id}`);
  }

  addTrainer(trainer: Trainer): Observable<ApiResponse<Trainer>> {
    return this.http.post<ApiResponse<Trainer>>(this.baseUrl, trainer);
  }

  updateTrainer(id: number, trainer: Trainer): Observable<ApiResponse<Trainer>> {
    return this.http.put<ApiResponse<Trainer>>(`${this.baseUrl}/${id}`, trainer);
  }

  deleteTrainer(id: number): Observable<ApiResponse<void>> {
    return this.http.delete<ApiResponse<void>>(`${this.baseUrl}/${id}`);
  }
}
