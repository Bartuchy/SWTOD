package com.example.swtod.domain.teaching.staff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PYSURepository extends JpaRepository<PlanYearSubjectUser, Long> {
}
