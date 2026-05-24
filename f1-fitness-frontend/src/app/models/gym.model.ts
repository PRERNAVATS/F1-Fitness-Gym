export interface ApiResponse<T> {
  success: boolean;
  message: string;
  data: T;
}

export interface MembershipPlan {
  id?: number;
  planName: string;
  price: number;
  duration: string;
  features: string[];
  active: boolean;
}

export interface Trainer {
  id?: number;
  name: string;
  specialization: string;
  experience: string;
  imageUrl: string;
  bio: string;
}

export interface GalleryImage {
  id?: number;
  title: string;
  imageUrl: string;
  description: string;
}

export interface ContactEnquiry {
  id?: number;
  name: string;
  phone: string;
  email: string;
  message: string;
  createdAt?: string;
}
