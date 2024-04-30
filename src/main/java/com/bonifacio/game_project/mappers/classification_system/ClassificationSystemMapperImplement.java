package com.bonifacio.game_project.mappers.classification_system;

import com.bonifacio.game_project.dtos.classification.ClassificationOutDto;
import com.bonifacio.game_project.dtos.classification_system.CSDetails;
import com.bonifacio.game_project.dtos.classification_system.CSInDto;
import com.bonifacio.game_project.dtos.classification_system.CSOutDto;
import com.bonifacio.game_project.entities.ClassificationSystem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class ClassificationSystemMapperImplement implements ClassificationSystemMapper{
    @Override
    public CSOutDto classificationSystemToCSOutDto(ClassificationSystem classificationSystem) {
        if(classificationSystem==null) return  null;
        return CSOutDto.builder()
                .id(classificationSystem.getId())
                .name(classificationSystem.getName())
                .description(classificationSystem.getDescriptions())
                .places(classificationSystem.getPlaces())
                .createAt(classificationSystem.getCreateAt())
                .updateAt(classificationSystem.getUpdateAt())
                .build();
    }

    @Override
    public ClassificationSystem csInDtoToClassificationSystem(CSInDto classificationSystem) {
        if(classificationSystem==null) return null;

        return ClassificationSystem.builder()
                .name(classificationSystem.getName())
                .descriptions(classificationSystem.getDescriptions())
                .places(classificationSystem.getPlaces())
                .build();
    }

    @Override
    public CSDetails classificationSystemToCSDetails(ClassificationSystem classificationSystem, List<ClassificationOutDto> classifications) {
        if(classificationSystem == null) return null;

        return CSDetails.builder()
                .id(classificationSystem.getId())
                .name(classificationSystem.getName())
                .description(classificationSystem.getDescriptions())
                .places(classificationSystem.getPlaces())
                .createAt(classificationSystem.getCreateAt())
                .updateAt(classificationSystem.getUpdateAt())
                .classification(classifications)
                .build();
    }
}
