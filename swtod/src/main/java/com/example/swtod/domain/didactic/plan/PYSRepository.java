package com.example.swtod.domain.didactic.plan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PYSRepository extends JpaRepository<PlanYearSubject, Long> {

    @Query("select p from plan_year_subject p where p.planYear.name=:academicYear")
    List<PlanYearSubject> findPlanYearSubjectByAcademicYear(String academicYear);

    @Query("select p from plan_year_subject p where p.subject.id=:subjectId")
    List<PlanYearSubject> findPlanYearSubjectBySubjectId(Long subjectId);

    @Modifying
    @Query("delete from plan_year_subject where id=:id")
    void deletePlanYearSubjectById(Long id);

    @Modifying
    @Query("delete from plan_year_subject where 1=1")
    void deleteAll();
}
