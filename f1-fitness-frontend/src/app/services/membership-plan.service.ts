import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiResponse, MembershipPlan } from '../models/gym.model';

@Injectable({
  providedIn: 'root'
})
export class MembershipPlanService {
  private baseUrl = 'https://f1-fitness-gym.onrender.com/api/plans';

  constructor(private http: HttpClient) { }

  getPlans(activeOnly?: boolean): Observable<ApiResponse<MembershipPlan[]>> {
    const url = activeOnly ? `${this.baseUrl}?activeOnly=true` : this.baseUrl;
    return this.http.get<ApiResponse<MembershipPlan[]>>(url);
  }

  getPlanById(id: number): Observable<ApiResponse<MembershipPlan>> {
    return this.http.get<ApiResponse<MembershipPlan>>(`${this.baseUrl}/${id}`);
  }

  addPlan(plan: MembershipPlan): Observable<ApiResponse<MembershipPlan>> {
    return this.http.post<ApiResponse<MembershipPlan>>(this.baseUrl, plan);
  }

  updatePlan(id: number, plan: MembershipPlan): Observable<ApiResponse<MembershipPlan>> {
    return this.http.put<ApiResponse<MembershipPlan>>(`${this.baseUrl}/${id}`, plan);
  }

  deletePlan(id: number): Observable<ApiResponse<void>> {
    return this.http.delete<ApiResponse<void>>(`${this.baseUrl}/${id}`);
  }
}
