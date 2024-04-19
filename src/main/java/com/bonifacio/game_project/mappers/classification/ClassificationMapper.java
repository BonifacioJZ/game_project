package com.bonifacio.game_project.mappers.classification;

import com.bonifacio.game_project.dtos.classification.ClassificationInDto;
import com.bonifacio.game_project.dtos.classification.ClassificationOutDto;
import com.bonifacio.game_project.entities.Classification;
import org.mapstruct.Mapper;

@Mapper
public interface ClassificationMapper {
    ClassificationOutDto  classificationToClassificationOutDto(Classification classification);
    Classification classificationInDtoToClassification(ClassificationInDto classificationInDto);
}
