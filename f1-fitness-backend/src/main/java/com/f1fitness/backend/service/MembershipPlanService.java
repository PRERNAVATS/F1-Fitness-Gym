package com.f1fitness.backend.service;

import com.f1fitness.backend.dto.MembershipPlanDto;

import java.util.List;

public interface MembershipPlanService {
    MembershipPlanDto addPlan(MembershipPlanDto planDto);
    List<MembershipPlanDto> getAllPlans();
    List<MembershipPlanDto> getActivePlans();
    MembershipPlanDto getPlanById(Long id);
    MembershipPlanDto updatePlan(Long id, MembershipPlanDto planDto);
    void deletePlan(Long id);
}
