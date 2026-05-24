package com.f1fitness.backend.config;

import com.f1fitness.backend.entity.GalleryImage;
import com.f1fitness.backend.entity.MembershipPlan;
import com.f1fitness.backend.entity.Trainer;
import com.f1fitness.backend.repository.GalleryImageRepository;
import com.f1fitness.backend.repository.MembershipPlanRepository;
import com.f1fitness.backend.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private MembershipPlanRepository planRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private GalleryImageRepository galleryRepository;

    @Override
    public void run(String... args) throws Exception {
        seedPlans();
        seedTrainers();
        seedGallery();
    }

    private void seedPlans() {
        if (planRepository.count() == 0) {
            MembershipPlan basic = MembershipPlan.builder()
                    .planName("Starter Strength")
                    .price(1500.00)
                    .duration("Monthly")
                    .features(Arrays.asList(
                            "Standard Gym Floor Access",
                            "Free Lockers & Showers",
                            "1 Cardio Assessment Session",
                            "Modern Strength & Cardio Equipment"
                    ))
                    .active(true)
                    .build();

            MembershipPlan pro = MembershipPlan.builder()
                    .planName("Pro Performance")
                    .price(3500.00)
                    .duration("Quarterly")
                    .features(Arrays.asList(
                            "All Starter Strength Features",
                            "Unlimited HIIT & Group Classes",
                            "2 Free Personal Trainer Sessions",
                            "Custom Workout Plan Assistance",
                            "10% Discount on Protein Bar"
                    ))
                    .active(true)
                    .build();

            MembershipPlan elite = MembershipPlan.builder()
                    .planName("F1 Elite Transformation")
                    .price(12000.00)
                    .duration("Annual")
                    .features(Arrays.asList(
                            "Unlimited 24/7 Access to Gym",
                            "Dedicated Personal Coach (1 Session/week)",
                            "Fully Custom Diet & Nutrition Chart",
                            "Steam Room & Sauna Access",
                            "Unlimited Group & Combat Yoga Classes",
                            "Monthly Body Composition Analysis"
                    ))
                    .active(true)
                    .build();

            planRepository.saveAll(Arrays.asList(basic, pro, elite));
            System.out.println("DatabaseInitializer: Seeded 3 membership plans.");
        }
    }

    private void seedTrainers() {
        if (trainerRepository.count() == 0) {
            Trainer trainer1 = Trainer.builder()
                    .name("Vikram Rathore")
                    .specialization("Strength & Bodybuilding")
                    .experience("8+ Years")
                    .imageUrl("assets/images/trainer-1.jpg")
                    .bio("Certified Gold's Gym Professional. Vikram specializes in high-intensity hypertrophy training and powerlifting mechanics.")
                    .build();

            Trainer trainer2 = Trainer.builder()
                    .name("Elena D'Cruz")
                    .specialization("Cardio, Zumba & HIIT")
                    .experience("6 Years")
                    .imageUrl("assets/images/trainer-2.jpg")
                    .bio("Elena is passionate about explosive functional training, cardiovascular conditioning, and making weight loss routines exciting and engaging.")
                    .build();

            Trainer trainer3 = Trainer.builder()
                    .name("Ranveer Singh")
                    .specialization("CrossFit & Combat Fitness")
                    .experience("5 Years")
                    .imageUrl("assets/images/trainer-3.jpg")
                    .bio("Black belt in Kickboxing and certified CrossFit level-2 coach. Ranveer specializes in explosive speed, agility, and core athletic transformation.")
                    .build();

            trainerRepository.saveAll(Arrays.asList(trainer1, trainer2, trainer3));
            System.out.println("DatabaseInitializer: Seeded 3 trainers.");
        }
    }

    private void seedGallery() {
        if (galleryRepository.count() == 0) {
            GalleryImage img1 = GalleryImage.builder()
                    .title("Premium Strength Section")
                    .imageUrl("assets/images/gallery-1.jpg")
                    .description("Equipped with high-end power racks, dumbbells up to 60kg, and custom selectorized machines.")
                    .build();

            GalleryImage img2 = GalleryImage.builder()
                    .title("Elite Cardio Zone")
                    .imageUrl("assets/images/gallery-2.jpg")
                    .description("Imported high-performance treadmills, cross-trainers, and spin-bikes with visual feedback dashboards.")
                    .build();

            GalleryImage img3 = GalleryImage.builder()
                    .title("HIIT & CrossFit Arena")
                    .imageUrl("assets/images/gallery-3.jpg")
                    .description("Dedicated astro-turf track with sleds, kettlebells, battle ropes, and climbing structures.")
                    .build();

            GalleryImage img4 = GalleryImage.builder()
                    .title("Personal Training Area")
                    .imageUrl("assets/images/gallery-4.jpg")
                    .description("Private transformation assessment spaces for goal consultations and personal coaching sessions.")
                    .build();

            GalleryImage img5 = GalleryImage.builder()
                    .title("Zumba & Yoga Studio")
                    .imageUrl("assets/images/gallery-5.jpg")
                    .description("Spacious sound-insulated studio with premium hard-wood flooring and ambient lighting setups.")
                    .build();

            GalleryImage img6 = GalleryImage.builder()
                    .title("Steam Room & Recovery Zone")
                    .imageUrl("assets/images/gallery-6.jpg")
                    .description("Relaxing post-workout steam chambers, lockers, and showers to accelerate physical recovery.")
                    .build();

            galleryRepository.saveAll(Arrays.asList(img1, img2, img3, img4, img5, img6));
            System.out.println("DatabaseInitializer: Seeded 6 gallery images.");
        }
    }
}
