package com.example.swtod.repository;

import com.example.swtod.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
