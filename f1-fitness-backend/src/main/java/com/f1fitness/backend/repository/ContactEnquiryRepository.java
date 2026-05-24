package com.f1fitness.backend.repository;

import com.f1fitness.backend.entity.ContactEnquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactEnquiryRepository extends JpaRepository<ContactEnquiry, Long> {
}
