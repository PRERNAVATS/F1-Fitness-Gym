package com.f1fitness.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "membership_plans")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MembershipPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "plan_name")
    private String planName;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String duration;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "membership_plan_features", joinColumns = @JoinColumn(name = "plan_id"))
    @Column(name = "feature")
    @Builder.Default
    private List<String> features = new ArrayList<>();

    @Column(nullable = false)
    @Builder.Default
    private boolean active = true;
}
