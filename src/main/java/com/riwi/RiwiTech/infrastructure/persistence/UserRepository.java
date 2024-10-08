package com.riwi.RiwiTech.infrastructure.persistence;

import com.riwi.RiwiTech.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
