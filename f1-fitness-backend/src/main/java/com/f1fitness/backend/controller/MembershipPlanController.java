package com.f1fitness.backend.controller;

import com.f1fitness.backend.dto.ApiResponse;
import com.f1fitness.backend.dto.MembershipPlanDto;
import com.f1fitness.backend.service.MembershipPlanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plans")
public class MembershipPlanController {

    @Autowired
    private MembershipPlanService planService;

    @PostMapping
    public ResponseEntity<ApiResponse<MembershipPlanDto>> addPlan(@Valid @RequestBody MembershipPlanDto planDto) {
        MembershipPlanDto createdPlan = planService.addPlan(planDto);
        ApiResponse<MembershipPlanDto> response = new ApiResponse<>(true, "Membership plan added successfully", createdPlan);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<MembershipPlanDto>>> getAllPlans(@RequestParam(required = false) Boolean activeOnly) {
        List<MembershipPlanDto> plans;
        if (activeOnly != null && activeOnly) {
            plans = planService.getActivePlans();
        } else {
            plans = planService.getAllPlans();
        }
        ApiResponse<List<MembershipPlanDto>> response = new ApiResponse<>(true, "Membership plans retrieved successfully", plans);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MembershipPlanDto>> getPlanById(@PathVariable Long id) {
        MembershipPlanDto plan = planService.getPlanById(id);
        ApiResponse<MembershipPlanDto> response = new ApiResponse<>(true, "Membership plan retrieved successfully", plan);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MembershipPlanDto>> updatePlan(
            @PathVariable Long id, 
            @Valid @RequestBody MembershipPlanDto planDto) {
        MembershipPlanDto updatedPlan = planService.updatePlan(id, planDto);
        ApiResponse<MembershipPlanDto> response = new ApiResponse<>(true, "Membership plan updated successfully", updatedPlan);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePlan(@PathVariable Long id) {
        planService.deletePlan(id);
        ApiResponse<Void> response = new ApiResponse<>(true, "Membership plan deleted successfully");
        return ResponseEntity.ok(response);
    }
}
