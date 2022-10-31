package com.example.swtod.domain.didactic.plan;

import org.hibernate.annotations.SQLDeleteAll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PlanYearSubjectRepository extends JpaRepository<PlanYearSubject, Long> {
    @Modifying
    @Query("delete from plan_year_subject where 1=1")
    void deleteAll();
}
