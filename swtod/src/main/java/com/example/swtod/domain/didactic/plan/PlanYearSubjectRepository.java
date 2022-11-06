package com.example.swtod.domain.didactic.plan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlanYearSubjectRepository extends JpaRepository<PlanYearSubject, Long> {

    @Query("select p from plan_year_subject p where p.subject.id=:subjectId")
    List<PlanYearSubject> findPlanYearSubjectBySubjectId(long subjectId);
    @Modifying
    @Query("delete from plan_year_subject where 1=1")
    void deleteAll();

    @Modifying
    @Query("delete from plan_year_subject where id=:id")
    void deletePlanYearSubjectById(Long id);
}
