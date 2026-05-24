import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TrainerService } from '../../services/trainer.service';
import { Trainer } from '../../models/gym.model';

@Component({
  selector: 'app-trainers',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './trainers.component.html',
  styleUrls: ['./trainers.component.css']
})
export class TrainersComponent implements OnInit {
  trainers: Trainer[] = [];
  isLoading = true;

  constructor(private trainerService: TrainerService) {}

  ngOnInit(): void {
    this.loadTrainers();
  }

  loadTrainers(): void {
    this.trainerService.getTrainers().subscribe({
      next: (res) => {
        if (res.success) {
          this.trainers = res.data;
        }
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Failed to load trainers', err);
        this.isLoading = false;
      }
    });
  }
}
