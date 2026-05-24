import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GalleryService } from '../../services/gallery.service';
import { GalleryImage } from '../../models/gym.model';

@Component({
  selector: 'app-gallery',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './gallery.component.html',
  styleUrls: ['./gallery.component.css']
})
export class GalleryComponent implements OnInit {
  images: GalleryImage[] = [];
  isLoading = true;

  constructor(private galleryService: GalleryService) {}

  ngOnInit(): void {
    this.loadImages();
  }

  loadImages(): void {
    this.galleryService.getImages().subscribe({
      next: (res) => {
        if (res.success) {
          this.images = res.data;
        }
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Failed to load gallery images', err);
        this.isLoading = false;
      }
    });
  }
}
