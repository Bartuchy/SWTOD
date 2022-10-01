package com.example.swtod.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Modifying
    @Query("UPDATE users SET password = :password where username = :username")
    void changePassword(String password, String username);

    @Modifying
    @Query("UPDATE users SET isActive = :is_active where id = :id")
    void modifyAccountActivationFlag(Long id, Boolean is_active);
}
