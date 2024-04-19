package com.bonifacio.game_project.mappers.classification_system;

import com.bonifacio.game_project.dtos.classification_system.CSInDto;
import com.bonifacio.game_project.dtos.classification_system.CSOutDto;
import com.bonifacio.game_project.entities.ClassificationSystem;
import org.mapstruct.Mapper;

@Mapper
public interface ClassificationSystemMapper {
    CSOutDto classificationSystemToCSOutDto(ClassificationSystem classificationSystem);
    ClassificationSystem csInDtoToClassificationSystem(CSInDto classificationSystem);
}
