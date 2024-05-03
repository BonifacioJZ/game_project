package com.bonifacio.game_project.mappers.classification;

import com.bonifacio.game_project.dtos.classification.ClassificationDetails;
import com.bonifacio.game_project.dtos.classification.ClassificationInDto;
import com.bonifacio.game_project.dtos.classification.ClassificationOutDto;
import com.bonifacio.game_project.dtos.video_game.VideoGameOutDto;
import com.bonifacio.game_project.entities.Classification;
import org.mapstruct.Mapper;

import java.util.ArrayList;

@Mapper
public interface ClassificationMapper {
    ClassificationOutDto  classificationToClassificationOutDto(Classification classification);
    Classification classificationInDtoToClassification(ClassificationInDto classificationInDto);
    ClassificationDetails clasificationToClassificationDetails(Classification classification, ArrayList<VideoGameOutDto> videoGameOutDtos);
    Classification classificationUpdate(ClassificationInDto classification,Classification previous);
}
