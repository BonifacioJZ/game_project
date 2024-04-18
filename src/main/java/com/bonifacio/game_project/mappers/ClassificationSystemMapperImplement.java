package com.bonifacio.game_project.mappers;

import com.bonifacio.game_project.dtos.CSInDto;
import com.bonifacio.game_project.dtos.CSOutDto;
import com.bonifacio.game_project.entities.ClassificationSystem;
import org.springframework.stereotype.Component;

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
}
