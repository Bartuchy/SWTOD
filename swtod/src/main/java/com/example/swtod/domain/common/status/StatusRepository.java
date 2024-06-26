package com.example.swtod.domain.common.status;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

    @Query("select s from status s where s.name=:statusName")
    Status findStatusByName(String statusName);
}
