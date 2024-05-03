package com.bonifacio.game_project.repository;

import com.bonifacio.game_project.entities.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GenderRepository extends JpaRepository<Gender, UUID> {
}
