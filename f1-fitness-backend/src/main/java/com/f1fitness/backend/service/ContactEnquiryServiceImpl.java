package com.f1fitness.backend.service;

import com.f1fitness.backend.dto.ContactEnquiryDto;
import com.f1fitness.backend.entity.ContactEnquiry;
import com.f1fitness.backend.exception.ResourceNotFoundException;
import com.f1fitness.backend.repository.ContactEnquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactEnquiryServiceImpl implements ContactEnquiryService {

    @Autowired
    private ContactEnquiryRepository enquiryRepository;

    @Override
    public ContactEnquiryDto submitEnquiry(ContactEnquiryDto enquiryDto) {
        ContactEnquiry enquiry = mapToEntity(enquiryDto);
        ContactEnquiry savedEnquiry = enquiryRepository.save(enquiry);
        return mapToDto(savedEnquiry);
    }

    @Override
    public List<ContactEnquiryDto> getAllEnquiries() {
        return enquiryRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ContactEnquiryDto getEnquiryById(Long id) {
        ContactEnquiry enquiry = enquiryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact Enquiry not found with id: " + id));
        return mapToDto(enquiry);
    }

    @Override
    public void deleteEnquiry(Long id) {
        ContactEnquiry enquiry = enquiryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact Enquiry not found with id: " + id));
        enquiryRepository.delete(enquiry);
    }

    private ContactEnquiry mapToEntity(ContactEnquiryDto dto) {
        return ContactEnquiry.builder()
                .id(dto.getId())
                .name(dto.getName())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .message(dto.getMessage())
                .createdAt(dto.getCreatedAt())
                .build();
    }

    private ContactEnquiryDto mapToDto(ContactEnquiry entity) {
        return ContactEnquiryDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .message(entity.getMessage())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
