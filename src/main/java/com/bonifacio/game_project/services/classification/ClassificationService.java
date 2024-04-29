package com.bonifacio.game_project.services.classification;

import com.bonifacio.game_project.dtos.classification.ClassificationDetails;
import com.bonifacio.game_project.dtos.classification.ClassificationInDto;
import com.bonifacio.game_project.dtos.classification.ClassificationOutDto;
import com.bonifacio.game_project.entities.Classification;

import java.util.List;
import java.util.UUID;

public interface ClassificationService {
    List<ClassificationOutDto> findAll();
    Classification save(ClassificationInDto classificationInDto);
    ClassificationDetails show(UUID id);
    ClassificationDetails edit(UUID id, ClassificationInDto classification);
    void delete(UUID id);
}
