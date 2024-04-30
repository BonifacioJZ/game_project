package com.bonifacio.game_project.services.classification_system;

import com.bonifacio.game_project.dtos.classification_system.CSDetails;
import com.bonifacio.game_project.dtos.classification_system.CSInDto;
import com.bonifacio.game_project.dtos.classification_system.CSOutDto;
import com.bonifacio.game_project.entities.ClassificationSystem;

import java.util.List;
import java.util.UUID;

public interface ClassificationSystemService {
    List<CSOutDto> findAll();
    ClassificationSystem save(CSInDto csInDto);
    CSDetails show(UUID id);
}
