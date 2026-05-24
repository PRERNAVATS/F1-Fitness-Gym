package com.f1fitness.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GalleryImageDto {

    private Long id;

    @NotBlank(message = "Image title is required")
    private String title;

    @NotBlank(message = "Image URL is required")
    private String imageUrl;

    private String description;
}
