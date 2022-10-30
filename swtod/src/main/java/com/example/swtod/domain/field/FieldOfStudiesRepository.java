package com.example.swtod.domain.field;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FieldOfStudiesRepository extends JpaRepository<FieldOfStudies, Long> {
    Optional<FieldOfStudies> findFieldOfStudiesByNameAndDegree(String name, int degree);
}
