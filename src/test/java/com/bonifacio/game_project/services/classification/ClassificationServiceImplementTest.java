package com.bonifacio.game_project.services.classification;

import com.bonifacio.game_project.dtos.classification.ClassificationDetails;
import com.bonifacio.game_project.dtos.classification.ClassificationInDto;
import com.bonifacio.game_project.dtos.classification.ClassificationOutDto;
import com.bonifacio.game_project.dtos.video_game.VideoGameOutDto;
import com.bonifacio.game_project.entities.Classification;
import com.bonifacio.game_project.entities.ClassificationSystem;
import com.bonifacio.game_project.mappers.classification.ClassificationMapper;
import com.bonifacio.game_project.repository.ClassificationRepository;
import com.bonifacio.game_project.repository.ClassificationSystemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClassificationServiceImplementTest {
    @Mock
    ClassificationRepository classificationRepository;
    @Mock
    ClassificationSystemRepository csRepository;
    @Mock
    ClassificationMapper classificationMapper;
    @InjectMocks
    ClassificationServiceImplement classificationServiceImplement;

    ClassificationOutDto classificationOutDto;
    Classification classification;
    ClassificationSystem classificationSystem;
    ClassificationDetails classificationDetails;
    ClassificationDetails classificationDetailsUpdate;
    Classification update;
    UUID id;
    UUID csId;
    ArrayList<VideoGameOutDto> videoGameOutDtos = new ArrayList<>();
    @BeforeEach
    void setUp()  {
        id= UUID.randomUUID();
        csId = UUID.randomUUID();
        classificationSystem = ClassificationSystem.builder()
                .id(csId)
                .name("csTest")
                .descriptions("description Test")
                .places("Mexico")
                .build();
        classification= Classification.builder()
                .id(id)
                .name("Classification test")
                .description("class test")
                .classification("E")
                .classificationSystem(classificationSystem)
                .build();
        update = Classification.builder()
                .id(id)
                .name("Classification2 test")
                .description("class2 test")
                .classification("E10")
                .classificationSystem(classificationSystem)
                .build();
        classificationOutDto = ClassificationOutDto.builder()
                .id(id)
                .name("Classification test")
                .description("class test")
                .classification("E")
                .build();
        classificationDetails = ClassificationDetails.builder()
                .id(id)
                .name(classification.getName())
                .description(classification.getDescription())
                .classification(classification.getClassification())
                .system_id(classification.getClassificationSystem().getId())
                .system_name(classification.getClassificationSystem().getName())
                .system_description(classification.getClassificationSystem().getDescriptions())
                .videoGames(videoGameOutDtos)
                .build();
        classificationDetailsUpdate = ClassificationDetails.builder()
                .id(id)
                .name(update.getName())
                .description(update.getDescription())
                .classification(update.getClassification())
                .system_id(update.getClassificationSystem().getId())
                .system_name(update.getClassificationSystem().getName())
                .system_description(update.getClassificationSystem().getDescriptions())
                .build();
    }

    @Test
    void findAll() {
        when(classificationServiceImplement.findAll()).thenReturn(Arrays.asList(classificationOutDto));
        when(classificationRepository.findAll()).thenReturn(Arrays.asList(classification));
        var result = classificationServiceImplement.findAll();
        assertNotNull(result);
    }

    @Test
    void save() {
        when(classificationServiceImplement.save(any(ClassificationInDto.class))).thenReturn(classification);
        when(classificationRepository.save(any(Classification.class))).thenReturn(classification);
        when(csRepository.findById(csId)).thenReturn(Optional.ofNullable(classificationSystem));
        var inClassification = ClassificationInDto.builder()
                .name("Classification test")
                .description("class test")
                .classification("E")
                .classification_id(csId)
                .build();
        var result = classificationServiceImplement.save(inClassification);
        assertNotNull(result);
        assertEquals(result.getId(),classification.getId());
        assertEquals(result.getClassificationSystem().getId(),classificationSystem.getId());
    }

    @Test
    void show() {
        when(classificationRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(classification));
        when(classificationServiceImplement.show(id)).thenReturn(classificationDetails);
        var result = classificationServiceImplement.show(id);
        assertNotNull(result);
        assertEquals(result.getId(),classificationDetails.getId());
        assertEquals(result.getName(),classificationDetails.getName());
        assertEquals(result.getSystem_id(),classificationDetails.getSystem_id());
    }

    @Test
    void edit() {
        when(classificationRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(classification));
        when(classificationRepository.save(any(Classification.class))).thenReturn(update);
        var in = ClassificationInDto.builder()
                .name("Classification2 test")
                .description("class2 test")
                .classification("E10")
                .classification_id(csId)
                .build();

        when(classificationMapper.classificationUpdate(in,classification)).thenReturn(update);
        when(classificationMapper.clasificationToClassificationDetails(update,videoGameOutDtos)).thenReturn(classificationDetailsUpdate);
       // when(classificationServiceImplement.edit(id,in)).thenReturn(classificationDetailsUpdate);
        var result = classificationServiceImplement.edit(id,in);
        assertNotNull(result);
        assertNotEquals(result.getName(),classificationDetails.getName());
        assertNotEquals(result.getDescription(),classificationDetails.getDescription());
    }
}