import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ContactService } from '../../services/contact.service';
import { ContactEnquiry } from '../../models/gym.model';

@Component({
  selector: 'app-contact',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent {
  enquiry: ContactEnquiry = {
    name: '',
    phone: '',
    email: '',
    message: ''
  };

  isSubmitting = false;
  submitSuccess = false;
  submitError = '';

  constructor(private contactService: ContactService) {}

  onSubmit(form: any): void {
    if (form.invalid) return;

    this.isSubmitting = true;
    this.submitSuccess = false;
    this.submitError = '';

    this.contactService.submitEnquiry(this.enquiry).subscribe({
      next: (res) => {
        if (res.success) {
          this.submitSuccess = true;
          this.enquiry = { name: '', phone: '', email: '', message: '' };
          form.resetForm();
        } else {
          this.submitError = res.message || 'Submission failed. Please try again.';
        }
        this.isSubmitting = false;
      },
      error: (err) => {
        console.error('Failed to submit enquiry', err);
        if (err.error && err.error.data) {
          // If Spring Boot returns validation errors
          if (typeof err.error.data === 'object') {
            const validationErrors = Object.entries(err.error.data)
              .map(([field, msg]) => `${field}: ${msg}`)
              .join(' | ');
            this.submitError = `Validation failed: ${validationErrors}`;
          } else {
            this.submitError = err.error.message || 'An error occurred during submission.';
          }
        } else {
          this.submitError = 'Failed to connect to the backend server. Please verify it is running on port 8089.';
        }
        this.isSubmitting = false;
      }
    });
  }
}
