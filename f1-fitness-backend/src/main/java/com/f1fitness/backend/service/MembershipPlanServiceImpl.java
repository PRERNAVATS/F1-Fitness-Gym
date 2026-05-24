package com.f1fitness.backend.service;

import com.f1fitness.backend.dto.MembershipPlanDto;
import com.f1fitness.backend.entity.MembershipPlan;
import com.f1fitness.backend.exception.ResourceNotFoundException;
import com.f1fitness.backend.repository.MembershipPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MembershipPlanServiceImpl implements MembershipPlanService {

    @Autowired
    private MembershipPlanRepository planRepository;

    @Override
    public MembershipPlanDto addPlan(MembershipPlanDto planDto) {
        MembershipPlan plan = mapToEntity(planDto);
        MembershipPlan savedPlan = planRepository.save(plan);
        return mapToDto(savedPlan);
    }

    @Override
    public List<MembershipPlanDto> getAllPlans() {
        return planRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MembershipPlanDto> getActivePlans() {
        return planRepository.findByActive(true).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public MembershipPlanDto getPlanById(Long id) {
        MembershipPlan plan = planRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Membership Plan not found with id: " + id));
        return mapToDto(plan);
    }

    @Override
    public MembershipPlanDto updatePlan(Long id, MembershipPlanDto planDto) {
        MembershipPlan plan = planRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Membership Plan not found with id: " + id));
        
        plan.setPlanName(planDto.getPlanName());
        plan.setPrice(planDto.getPrice());
        plan.setDuration(planDto.getDuration());
        plan.setFeatures(planDto.getFeatures());
        plan.setActive(planDto.isActive());

        MembershipPlan updatedPlan = planRepository.save(plan);
        return mapToDto(updatedPlan);
    }

    @Override
    public void deletePlan(Long id) {
        MembershipPlan plan = planRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Membership Plan not found with id: " + id));
        planRepository.delete(plan);
    }

    private MembershipPlan mapToEntity(MembershipPlanDto dto) {
        return MembershipPlan.builder()
                .id(dto.getId())
                .planName(dto.getPlanName())
                .price(dto.getPrice())
                .duration(dto.getDuration())
                .features(dto.getFeatures())
                .active(dto.isActive())
                .build();
    }

    private MembershipPlanDto mapToDto(MembershipPlan entity) {
        return MembershipPlanDto.builder()
                .id(entity.getId())
                .planName(entity.getPlanName())
                .price(entity.getPrice())
                .duration(entity.getDuration())
                .features(entity.getFeatures())
                .active(entity.isActive())
                .build();
    }
}
