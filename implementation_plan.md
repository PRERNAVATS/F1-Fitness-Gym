# Implementation Plan - F1 Fitness Full-Stack Website

Create a premium, modern gym website for **F1 Fitness** with a dark, high-contrast red/black theme. The project is split into two separate directories: `f1-fitness-backend` (Spring Boot running on port `8089`) and `f1-fitness-frontend` (Angular 17 running on port `4200`).

---

## User Review Required

> [!IMPORTANT]
> **PostgreSQL Configuration**
> By default, the application will attempt to connect to PostgreSQL at `localhost:5432` with username `postgres` and password `postgres`.
> We will implement an automatic database creator that connects to the default `postgres` database first, checks if `f1_fitness_db` exists, and creates it if missing. Please let us know if your local PostgreSQL requires a different username or password.

> [!NOTE]
> **Design Assets**
> We will generate high-quality images for the gallery and trainers using the `generate_image` tool and place them inside the frontend's assets folder (`src/assets/images`). They will be referenced by the seed database script so that the website displays professional assets upon launch.

---

## Open Questions

- **PostgreSQL Credentials**: Are `postgres` / `postgres` the correct username/password for your local database? If they are different, please provide them so we can adjust the default `application.properties` before execution.
- **Java Version**: Your system has Java 21 installed. We plan to configure Maven to target Java 17 compatibility as requested, which compiles and runs seamlessly on Java 21. Let us know if you prefer to target Java 21 directly.

---

## Proposed Changes

### Component 1: `f1-fitness-backend`

A Maven-based Spring Boot application with a layered architecture, validation, exception handling, and auto-seeding.

#### [NEW] [pom.xml](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-backend/pom.xml)
Maven configuration containing dependencies: `spring-boot-starter-web`, `spring-boot-starter-data-jpa`, `postgresql`, `lombok`, and `spring-boot-starter-validation`.

#### [NEW] [application.properties](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-backend/src/main/resources/application.properties)
Configuration for server port `8089`, JPA hibernate DDL auto (`update`), and PostgreSQL connection parameters.

#### [NEW] [F1FitnessBackendApplication.java](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-backend/src/main/java/com/f1fitness/backend/F1FitnessBackendApplication.java)
Main entry point. We will add a pre-startup routine to connect to `postgres` default database and create `f1_fitness_db` if it doesn't already exist.

#### [NEW] Entities
- [MembershipPlan.java](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-backend/src/main/java/com/f1fitness/backend/entity/MembershipPlan.java) (id, planName, price, duration, features, active)
- [Trainer.java](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-backend/src/main/java/com/f1fitness/backend/entity/Trainer.java) (id, name, specialization, experience, imageUrl, bio)
- [GalleryImage.java](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-backend/src/main/java/com/f1fitness/backend/entity/GalleryImage.java) (id, title, imageUrl, description)
- [ContactEnquiry.java](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-backend/src/main/java/com/f1fitness/backend/entity/ContactEnquiry.java) (id, name, phone, email, message, createdAt)

#### [NEW] DTOs
- [MembershipPlanDto.java](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-backend/src/main/java/com/f1fitness/backend/dto/MembershipPlanDto.java) (validation annotations)
- [TrainerDto.java](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-backend/src/main/java/com/f1fitness/backend/dto/TrainerDto.java)
- [GalleryImageDto.java](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-backend/src/main/java/com/f1fitness/backend/dto/GalleryImageDto.java)
- [ContactEnquiryDto.java](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-backend/src/main/java/com/f1fitness/backend/dto/ContactEnquiryDto.java)
- [ApiResponse.java](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-backend/src/main/java/com/f1fitness/backend/dto/ApiResponse.java) (clean response envelope)

#### [NEW] Repositories
- [MembershipPlanRepository.java](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-backend/src/main/java/com/f1fitness/backend/repository/MembershipPlanRepository.java)
- [TrainerRepository.java](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-backend/src/main/java/com/f1fitness/backend/repository/TrainerRepository.java)
- [GalleryImageRepository.java](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-backend/src/main/java/com/f1fitness/backend/repository/GalleryImageRepository.java)
- [ContactEnquiryRepository.java](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-backend/src/main/java/com/f1fitness/backend/repository/ContactEnquiryRepository.java)

#### [NEW] Services (Interfaces & Implementations)
- [MembershipPlanService.java](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-backend/src/main/java/com/f1fitness/backend/service/MembershipPlanService.java)
- [TrainerService.java](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-backend/src/main/java/com/f1fitness/backend/service/TrainerService.java)
- [GalleryImageService.java](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-backend/src/main/java/com/f1fitness/backend/service/GalleryImageService.java)
- [ContactEnquiryService.java](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-backend/src/main/java/com/f1fitness/backend/service/ContactEnquiryService.java)

#### [NEW] Controllers (with CORS allowed for http://localhost:4200)
- [MembershipPlanController.java](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-backend/src/main/java/com/f1fitness/backend/controller/MembershipPlanController.java)
- [TrainerController.java](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-backend/src/main/java/com/f1fitness/backend/controller/TrainerController.java)
- [GalleryImageController.java](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-backend/src/main/java/com/f1fitness/backend/controller/GalleryImageController.java)
- [ContactEnquiryController.java](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-backend/src/main/java/com/f1fitness/backend/controller/ContactEnquiryController.java)

#### [NEW] Exceptions & Handlers
- [ResourceNotFoundException.java](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-backend/src/main/java/com/f1fitness/backend/exception/ResourceNotFoundException.java)
- [GlobalExceptionHandler.java](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-backend/src/main/java/com/f1fitness/backend/exception/GlobalExceptionHandler.java) (handles resource not found, validation errors, and general exceptions, returning Dto errors)

#### [NEW] Seed Data Configuration
- [DatabaseInitializer.java](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-backend/src/main/java/com/f1fitness/backend/config/DatabaseInitializer.java)
  Checks tables on startup. If empty, inserts initial Membership Plans (Basic, Pro, Elite), Trainers (John, Sarah, Mike), and Gallery Images.

---

### Component 2: `f1-fitness-frontend`

Angular 17 frontend featuring responsive CSS layouts, standalone components, a sport/fitness-oriented theme (Dark gray, intense red, white), and state-managed API interactions.

#### [NEW] Services
- [membership-plan.service.ts](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-frontend/src/app/services/membership-plan.service.ts)
- [trainer.service.ts](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-frontend/src/app/services/trainer.service.ts)
- [gallery.service.ts](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-frontend/src/app/services/gallery.service.ts)
- [contact.service.ts](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-frontend/src/app/services/contact.service.ts)

#### [NEW] Components (Standalone)
- **Navbar / Footer Layout**: Built directly in the main [app.component.html](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-frontend/src/app/app.component.html) and [app.component.css](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-frontend/src/app/app.component.css).
- **Home**: [home.component.ts](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-frontend/src/app/pages/home/home.component.ts), [home.component.html](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-frontend/src/app/pages/home/home.component.html), [home.component.css](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-frontend/src/app/pages/home/home.component.css).
- **About**: [about.component.ts](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-frontend/src/app/pages/about/about.component.ts), [about.component.html](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-frontend/src/app/pages/about/about.component.html).
- **Plans**: [plans.component.ts](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-frontend/src/app/pages/plans/plans.component.ts), [plans.component.html](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-frontend/src/app/pages/plans/plans.component.html).
- **Trainers**: [trainers.component.ts](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-frontend/src/app/pages/trainers/trainers.component.ts), [trainers.component.html](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-frontend/src/app/pages/trainers/trainers.component.html).
- **Gallery**: [gallery.component.ts](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-frontend/src/app/pages/gallery/gallery.component.ts), [gallery.component.html](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-frontend/src/app/pages/gallery/gallery.component.html).
- **Contact**: [contact.component.ts](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-frontend/src/app/pages/contact/contact.component.ts), [contact.component.html](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-frontend/src/app/pages/contact/contact.component.html). Includes contact form validation and submissions to the backend database.

#### [MODIFY] [index.html](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-frontend/src/index.html)
Added Outfit / Orbitron sport typography from Google Fonts, FontAwesome icons, and viewport configurations.

#### [MODIFY] [styles.css](file:///c:/Users/vatsp/Desktop/F1%20Gym/f1-fitness-frontend/src/styles.css)
Global styling for dark background, premium red gradients, custom scrollbars, and styling resets.

---

## Verification Plan

### Automated Tests
- Run `mvn clean compile` to verify Java and dependency builds.
- Run `ng build --configuration=production` to check for compilation issues in Angular.

### Manual Verification
- We will boot the backend at http://localhost:8089 and check API endpoints (e.g. GET `/api/plans`, GET `/api/trainers`).
- We will boot the frontend at http://localhost:4200 using `npm run dev` or `ng serve --port 4200`.
- We will visually verify the landing page responsive layout and navigation.
- We will fill out the contact form and verify in the database (or via console logging/GET endpoint) that the contact enquiry is correctly captured.
