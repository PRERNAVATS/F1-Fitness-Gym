package com.f1fitness.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MembershipPlanDto {

    private Long id;

    @NotBlank(message = "Plan name is required")
    private String planName;

    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price must be greater than or equal to 0")
    private Double price;

    @NotBlank(message = "Duration is required")
    private String duration;

    @NotEmpty(message = "At least one plan feature is required")
    private List<String> features;

    @Builder.Default
    private boolean active = true;
}
