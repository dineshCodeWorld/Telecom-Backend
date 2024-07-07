package com.telecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telecom.entity.Plan;

public interface PlanRepository extends JpaRepository<Plan, Integer> {

}
