package com.example.swtod.domain.teaching.staff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PYSURepository extends JpaRepository<PlanYearSubjectUser, Long> {

    @Query("select p from plan_year_subject_user p where p.user.id=:userId and p.planYearSubject.subject.id=:subjectId")
    List<PlanYearSubjectUser> findPlanYearSubjectUsersByUserIdAndSubjectId(Long userId, Long subjectId);
}
