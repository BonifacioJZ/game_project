package com.bonifacio.game_project.mappers;

import com.bonifacio.game_project.dtos.CSInDto;
import com.bonifacio.game_project.dtos.CSOutDto;
import com.bonifacio.game_project.entities.ClassificationSystem;
import org.mapstruct.Mapper;

@Mapper
public interface ClassificationSystemMapper {
    CSOutDto classificationSystemToCSOutDto(ClassificationSystem classificationSystem);
    ClassificationSystem csInDtoToClassificationSystem(CSInDto classificationSystem);
}
