package com.bonifacio.game_project.services.classification;

import com.bonifacio.game_project.dtos.classification.ClassificationInDto;
import com.bonifacio.game_project.dtos.classification.ClassificationOutDto;
import com.bonifacio.game_project.entities.Classification;

import java.util.List;

public interface ClassificationService {
    List<ClassificationOutDto> findAll();
    Classification save(ClassificationInDto classificationInDto);
}
