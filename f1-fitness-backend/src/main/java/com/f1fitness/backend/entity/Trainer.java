package com.f1fitness.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "trainers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String specialization;

    @Column(nullable = false)
    private String experience; // e.g. "5 Years", "8+ Years"

    @Column(name = "image_url")
    private String imageUrl;

    @Column(length = 1000)
    private String bio;
}
