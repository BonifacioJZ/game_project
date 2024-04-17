package com.bonifacio.game_project.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bonifacio.game_project.entities.Plataform;

public interface PlataformRepository extends JpaRepository<Plataform,UUID> {

}
