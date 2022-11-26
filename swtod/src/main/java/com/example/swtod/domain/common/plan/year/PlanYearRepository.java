package com.example.swtod.domain.common.plan.year;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanYearRepository extends JpaRepository<PlanYear, Long> {

    @Query("select p from plan_year p where p.name=:name and p.year=:year")
    Optional<PlanYear> findPlanYearByNameAndYear(String name, int year);
}
