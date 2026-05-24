package com.f1fitness.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainerDto {

    private Long id;

    @NotBlank(message = "Trainer name is required")
    private String name;

    @NotBlank(message = "Specialization is required")
    private String specialization;

    @NotBlank(message = "Experience details are required")
    private String experience;

    @NotBlank(message = "Trainer image URL is required")
    private String imageUrl;

    @NotBlank(message = "Bio / description is required")
    private String bio;
}
