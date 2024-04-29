package com.bonifacio.game_project.mappers.classification;

import com.bonifacio.game_project.dtos.classification.ClassificationDetails;
import com.bonifacio.game_project.dtos.classification.ClassificationInDto;
import com.bonifacio.game_project.dtos.classification.ClassificationOutDto;
import com.bonifacio.game_project.entities.Classification;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ClassificationMapperImplement implements ClassificationMapper{
    @Override
    public ClassificationOutDto classificationToClassificationOutDto(Classification classification) {
        if(classification ==null) return null;

        return ClassificationOutDto.builder()
                .id(classification.getId())
                .name(classification.getName())
                .classification(classification.getClassification())
                .description(classification.getDescription())
                .createAt(classification.getCreateAt())
                .updateAt(classification.getUpdateAt())
                .build();
    }

    @Override
    public Classification classificationInDtoToClassification(ClassificationInDto classificationInDto) {
        if(classificationInDto == null) return null;
        return Classification.builder()
                .name(classificationInDto.getName())
                .classification(classificationInDto.getClassification())
                .description(classificationInDto.getDescription())
                .build();
    }

    @Override
    public ClassificationDetails clasificationToClassificationDetails(Classification classification) {
        if(classification==null) return null;

        return ClassificationDetails
                .builder()
                .name(classification.getName())
                .description(classification.getDescription())
                .id(classification.getId())
                .system_id(classification.getClassificationSystem().getId())
                .system_name(classification.getClassificationSystem().getName())
                .system_description(classification.getClassificationSystem().getDescriptions())
                .build();
    }

    @Override
    public Classification classificationUpdate(ClassificationInDto classification, Classification previous) {
        if(previous == null || classification== null) return null;
        previous.setClassification((StringUtils.isEmpty(classification.getClassification()))?
                previous.getClassification():classification.getClassification());
        previous.setDescription(StringUtils.isEmpty(classification.getDescription())?
                previous.getDescription():classification.getDescription());
        previous.setName((StringUtils.isEmpty(classification.getName()))?
                previous.getName():classification.getName());
        return previous;
    }
}
