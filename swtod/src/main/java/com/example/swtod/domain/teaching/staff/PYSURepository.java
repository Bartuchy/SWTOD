package com.example.swtod.domain.teaching.staff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PYSURepository extends JpaRepository<PlanYearSubjectUser, Long> {

    @Query(value = "select pysu from plan_year_subject_user pysu where pysu.planYearSubject.planYear.name=:academicYear")
    List<PlanYearSubjectUser> findPlanYearSubjectUsersByAcademicYear(String academicYear);

    @Query("select p from plan_year_subject_user p where p.user.id=:userId and p.planYearSubject.subject.id=:subjectId")
    List<PlanYearSubjectUser> findPlanYearSubjectUsersByUserIdAndSubjectId(Long userId, Long subjectId);

    @Query("select p from plan_year_subject_user p where p.user.id=:userId")
    List<PlanYearSubjectUser> findPlanYearSubjectUsersByUserId(Long userId);

    @Modifying
    @Query(value = "delete from plan_year_subject_user pysu where pysu.user_id=:userId and pysu.plan_year_subject_id in " +
            "(select pys.id from plan_year_subject pys where pys.subject_id=:subjectId)", nativeQuery = true)
    void deletePlanYearSubjectUserByUserIdAndSubjectId(Long userId, Long subjectId);
}
