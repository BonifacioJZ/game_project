package com.bonifacio.game_project.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bonifacio.game_project.entities.Plataform;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformRepository extends JpaRepository<Plataform,UUID> {

}
