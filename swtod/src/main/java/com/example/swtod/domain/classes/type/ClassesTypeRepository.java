package com.example.swtod.domain.classes.type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassesTypeRepository extends JpaRepository<ClassesType, Long> {
    Optional<ClassesType> findClassesTypeByName(String name);
}
