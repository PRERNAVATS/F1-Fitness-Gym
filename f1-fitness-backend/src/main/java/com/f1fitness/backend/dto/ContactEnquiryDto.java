package com.f1fitness.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactEnquiryDto {

    private Long id;

    @NotBlank(message = "Your name is required")
    private String name;

    @NotBlank(message = "Your phone number is required")
    @Pattern(regexp = "^\\+?[0-9\\s-]{10,15}$", message = "Please enter a valid phone number (10-15 digits)")
    private String phone;

    @NotBlank(message = "Your email address is required")
    @Email(message = "Please enter a valid email address")
    private String email;

    @NotBlank(message = "Your message is required")
    @Size(max = 2000, message = "Message details must not exceed 2000 characters")
    private String message;

    private LocalDateTime createdAt;
}
