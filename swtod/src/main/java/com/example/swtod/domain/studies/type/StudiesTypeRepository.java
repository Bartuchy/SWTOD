package com.example.swtod.domain.studies.type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudiesTypeRepository extends JpaRepository<StudiesType, Long> {
    Optional<StudiesType> findStudiesTypeByName(String name);
}
