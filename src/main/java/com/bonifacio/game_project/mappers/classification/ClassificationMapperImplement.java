package com.bonifacio.game_project.mappers.classification;

import com.bonifacio.game_project.dtos.classification.ClassificationInDto;
import com.bonifacio.game_project.dtos.classification.ClassificationOutDto;
import com.bonifacio.game_project.entities.Classification;
import org.springframework.stereotype.Component;

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
}
