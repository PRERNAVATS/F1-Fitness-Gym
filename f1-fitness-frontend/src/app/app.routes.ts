import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { AboutComponent } from './pages/about/about.component';
import { PlansComponent } from './pages/plans/plans.component';
import { TrainersComponent } from './pages/trainers/trainers.component';
import { GalleryComponent } from './pages/gallery/gallery.component';
import { ContactComponent } from './pages/contact/contact.component';

export const routes: Routes = [
  { path: '', component: HomeComponent, title: 'F1 Fitness | Premium Gym & Fitness Center' },
  { path: 'about', component: AboutComponent, title: 'F1 Fitness | About Us' },
  { path: 'plans', component: PlansComponent, title: 'F1 Fitness | Membership Plans' },
  { path: 'trainers', component: TrainersComponent, title: 'F1 Fitness | Certified Trainers' },
  { path: 'gallery', component: GalleryComponent, title: 'F1 Fitness | Gym Gallery' },
  { path: 'contact', component: ContactComponent, title: 'F1 Fitness | Contact & Enquiry' },
  { path: '**', redirectTo: '' }
];
