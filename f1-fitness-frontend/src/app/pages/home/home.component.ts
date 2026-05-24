import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { MembershipPlanService } from '../../services/membership-plan.service';
import { TrainerService } from '../../services/trainer.service';
import { GalleryService } from '../../services/gallery.service';
import { MembershipPlan, Trainer, GalleryImage } from '../../models/gym.model';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  plans: MembershipPlan[] = [];
  trainers: Trainer[] = [];
  galleryImages: GalleryImage[] = [];
  isLoadingPlans = true;
  isLoadingTrainers = true;
  isLoadingGallery = true;

  constructor(
    private planService: MembershipPlanService,
    private trainerService: TrainerService,
    private galleryService: GalleryService
  ) {}

  ngOnInit(): void {
    this.loadData();
  }

  loadData(): void {
    // Load plans (active only)
    this.planService.getPlans(true).subscribe({
      next: (res) => {
        if (res.success) {
          this.plans = res.data.slice(0, 3);
        }
        this.isLoadingPlans = false;
      },
      error: (err) => {
        console.error('Failed to load plans', err);
        this.isLoadingPlans = false;
      }
    });

    // Load trainers
    this.trainerService.getTrainers().subscribe({
      next: (res) => {
        if (res.success) {
          this.trainers = res.data.slice(0, 3);
        }
        this.isLoadingTrainers = false;
      },
      error: (err) => {
        console.error('Failed to load trainers', err);
        this.isLoadingTrainers = false;
      }
    });

    // Load gallery images
    this.galleryService.getImages().subscribe({
      next: (res) => {
        if (res.success) {
          this.galleryImages = res.data.slice(0, 3);
        }
        this.isLoadingGallery = false;
      },
      error: (err) => {
        console.error('Failed to load gallery images', err);
        this.isLoadingGallery = false;
      }
    });
  }
}
