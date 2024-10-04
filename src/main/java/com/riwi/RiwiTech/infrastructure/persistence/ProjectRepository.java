package com.riwi.RiwiTech.infrastructure.persistence;

import com.riwi.RiwiTech.domain.entities.Project;
import com.riwi.RiwiTech.domain.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByName(String name);
}
