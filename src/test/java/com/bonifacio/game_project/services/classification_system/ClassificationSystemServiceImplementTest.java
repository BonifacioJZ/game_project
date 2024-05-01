package com.bonifacio.game_project.services.classification_system;

import com.bonifacio.game_project.dtos.classification.ClassificationOutDto;
import com.bonifacio.game_project.dtos.classification_system.CSDetails;
import com.bonifacio.game_project.dtos.classification_system.CSInDto;
import com.bonifacio.game_project.dtos.classification_system.CSOutDto;
import com.bonifacio.game_project.entities.ClassificationSystem;
import com.bonifacio.game_project.mappers.classification.ClassificationMapper;
import com.bonifacio.game_project.mappers.classification_system.ClassificationSystemMapper;
import com.bonifacio.game_project.repository.ClassificationSystemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClassificationSystemServiceImplementTest {
    @Mock
    ClassificationSystemRepository csRepository;
    @Mock
    ClassificationSystemMapper csMapper;
    @Mock
    ClassificationMapper classificationMapper;
    @InjectMocks
    ClassificationSystemServiceImplement csService;

    ClassificationSystem classificationSystem;
    CSOutDto csOutDto;
    CSInDto csInDto;
    CSDetails csDetails;
    ClassificationSystem update;
    CSOutDto csOutDtoUpdate;
    UUID id;
    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        classificationSystem = ClassificationSystem
                .builder()
                .id(id)
                .name("cs Name")
                .descriptions("cs Description")
                .places("mexico")
                .build();
        csOutDto = CSOutDto.builder()
                .id(classificationSystem.getId())
                .name(classificationSystem.getName())
                .description(classificationSystem.getDescriptions())
                .places(classificationSystem.getPlaces())
                .build();
        csDetails = CSDetails.builder()
                .id(classificationSystem.getId())
                .places(classificationSystem.getPlaces())
                .name(classificationSystem.getName())
                .description(classificationSystem.getDescriptions())
                .build();
        update = ClassificationSystem.builder()
                .id(id)
                .name("cs Name2")
                .descriptions("cs Description2")
                .places("mexico")
                .build();
        csOutDtoUpdate = CSOutDto.builder()
                .id(update.getId())
                .name(update.getName())
                .description(update.getDescriptions())
                .places(update.getPlaces())
                .build();
    }

    @Test
    void findAll() {
        when(csService.findAll()).thenReturn(Arrays.asList(csOutDto));
        when(csRepository.findAll()).thenReturn(Arrays.asList(classificationSystem));
        var data = csService.findAll();
        assertNotNull(data);

    }

    @Test
    void save() {
        when(csService.save(any(CSInDto.class))).thenReturn(classificationSystem);
        when(csRepository.save(any(ClassificationSystem.class))).thenReturn(classificationSystem);
        csInDto = CSInDto.builder()
                .name("cs Name")
                .descriptions("cs Description")
                .places("mexico")
                .build();
        var result = csService.save(csInDto);
        assertNotNull(result);
        assertEquals(classificationSystem.getId(),result.getId());
        assertEquals(classificationSystem.getName(),result.getName());
        assertEquals(classificationSystem.getDescriptions(),result.getDescriptions());
    }

    @Test
    void show() {
        when(csRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(classificationSystem));
        when(csService.show(id)).thenReturn(csDetails);
        var result = csService.show(id);
        assertNotNull(result);
        assertEquals(result.getId(),classificationSystem.getId());
        assertEquals(result.getName(),classificationSystem.getName());
    }

    @Test
    void edit() {
        when(csRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(classificationSystem));
        when(csRepository.save(any(ClassificationSystem.class))).thenReturn(update);
        var in = CSInDto.builder()
                .name("cs Name2")
                .descriptions("cs Description2")
                .places("mexico")
                .build();
        when(csMapper.updateCS(classificationSystem,in)).thenReturn(update);
        when(csMapper.classificationSystemToCSOutDto(update)).thenReturn(csOutDtoUpdate);
        var result = csService.edit(id,in);
        assertNotNull(result);
        assertEquals(result.getPlaces(),csOutDto.getPlaces());
        assertNotNull(result.getName(),csOutDto.getName());
        assertNotNull(result.getDescription(),csOutDto.getDescription());
    }
}