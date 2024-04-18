package com.bonifacio.game_project.repository;

import com.bonifacio.game_project.entities.ClassificationSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClassificationSystemRepository  extends JpaRepository<ClassificationSystem, UUID> {
}
