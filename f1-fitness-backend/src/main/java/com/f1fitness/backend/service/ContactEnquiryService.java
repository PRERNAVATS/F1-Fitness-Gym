package com.f1fitness.backend.service;

import com.f1fitness.backend.dto.ContactEnquiryDto;

import java.util.List;

public interface ContactEnquiryService {
    ContactEnquiryDto submitEnquiry(ContactEnquiryDto enquiryDto);
    List<ContactEnquiryDto> getAllEnquiries();
    ContactEnquiryDto getEnquiryById(Long id);
    void deleteEnquiry(Long id);
}
