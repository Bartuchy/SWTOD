package com.example.swtod.domain.plan.year;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanYearRepository extends JpaRepository<PlanYear, Long> {
    Optional<PlanYear> findPlanYearByName(String name);
}
