package com.bonifacio.game_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface Classification extends JpaRepository<Classification, UUID> {
}
