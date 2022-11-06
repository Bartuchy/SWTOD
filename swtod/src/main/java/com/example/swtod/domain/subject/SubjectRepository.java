package com.example.swtod.domain.subject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Optional<Subject> findSubjectByName(String name);

    @Modifying
    @Query("delete from subject where 1=1")
    void deleteAll();

    @Modifying
    @Query("delete from subject where id=:id")
    void deleteSubjectById(Long id);
}
