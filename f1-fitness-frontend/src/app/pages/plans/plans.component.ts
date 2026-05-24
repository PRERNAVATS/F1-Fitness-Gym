import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { MembershipPlanService } from '../../services/membership-plan.service';
import { MembershipPlan } from '../../models/gym.model';

@Component({
  selector: 'app-plans',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './plans.component.html',
  styleUrls: ['./plans.component.css']
})
export class PlansComponent implements OnInit {
  plans: MembershipPlan[] = [];
  isLoading = true;

  constructor(private planService: MembershipPlanService) {}

  ngOnInit(): void {
    this.loadPlans();
  }

  loadPlans(): void {
    this.planService.getPlans(true).subscribe({
      next: (res) => {
        if (res.success) {
          this.plans = res.data;
        }
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Failed to load membership plans', err);
        this.isLoading = false;
      }
    });
  }
}
