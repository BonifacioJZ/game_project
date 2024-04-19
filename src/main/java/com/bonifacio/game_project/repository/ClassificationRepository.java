package com.bonifacio.game_project.repository;

import com.bonifacio.game_project.entities.Classification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClassificationRepository extends JpaRepository<Classification, UUID> {
}
