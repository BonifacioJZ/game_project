package com.bonifacio.game_project.repository;

import com.bonifacio.game_project.entities.VideoGame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VideoGameRepository extends JpaRepository<VideoGame, UUID> {
}
