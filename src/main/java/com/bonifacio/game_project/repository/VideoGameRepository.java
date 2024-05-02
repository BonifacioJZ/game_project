package com.bonifacio.game_project.repository;

import com.bonifacio.game_project.entities.Classification;
import com.bonifacio.game_project.entities.VideoGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VideoGameRepository extends JpaRepository<VideoGame, UUID> {
   
}
