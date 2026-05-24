package com.f1fitness.backend.controller;

import com.f1fitness.backend.dto.ApiResponse;
import com.f1fitness.backend.dto.ContactEnquiryDto;
import com.f1fitness.backend.service.ContactEnquiryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enquiries")
public class ContactEnquiryController {

    @Autowired
    private ContactEnquiryService enquiryService;

    @PostMapping
    public ResponseEntity<ApiResponse<ContactEnquiryDto>> submitEnquiry(@Valid @RequestBody ContactEnquiryDto enquiryDto) {
        ContactEnquiryDto savedEnquiry = enquiryService.submitEnquiry(enquiryDto);
        ApiResponse<ContactEnquiryDto> response = new ApiResponse<>(true, "Enquiry submitted successfully", savedEnquiry);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ContactEnquiryDto>>> getAllEnquiries() {
        List<ContactEnquiryDto> enquiries = enquiryService.getAllEnquiries();
        ApiResponse<List<ContactEnquiryDto>> response = new ApiResponse<>(true, "Contact enquiries retrieved successfully", enquiries);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ContactEnquiryDto>> getEnquiryById(@PathVariable Long id) {
        ContactEnquiryDto enquiry = enquiryService.getEnquiryById(id);
        ApiResponse<ContactEnquiryDto> response = new ApiResponse<>(true, "Contact enquiry retrieved successfully", enquiry);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEnquiry(@PathVariable Long id) {
        enquiryService.deleteEnquiry(id);
        ApiResponse<Void> response = new ApiResponse<>(true, "Contact enquiry deleted successfully");
        return ResponseEntity.ok(response);
    }
}
