package com.example.swtod.domain.semester;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SemesterOfStudiesRepository extends JpaRepository<SemesterOfStudies, Long> {
    Optional<SemesterOfStudies> findSemesterOfStudiesByNameAndNumber(String name, int number);
}
