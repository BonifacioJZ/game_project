package com.bonifacio.game_project.services;

import com.bonifacio.game_project.dtos.PlataformInDto;
import com.bonifacio.game_project.dtos.PlataformOutDto;
import com.bonifacio.game_project.dtos.PlatformDetailDto;
import com.bonifacio.game_project.entities.Plataform;
import com.bonifacio.game_project.mappers.PlatformMapper;
import com.bonifacio.game_project.mappers.PlataformMapperImplement;
import com.bonifacio.game_project.repository.PlataformRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PlatformServiceImplementTest {
    @Mock
    PlatformMapper plataformMapper;

    @Mock
    PlataformRepository plataformRepository;
    @InjectMocks
    PlatformServiceImplement platformServiceImplement;

    private Plataform plataform;
    private Plataform updatedPlataform;
    private PlataformOutDto plataformOutDto;
    private UUID id;
    private PlatformDetailDto platformDetailDto;
    private PlatformDetailDto platformDetailDto2;
    @BeforeEach
    void setUp()  {
        id  = UUID.randomUUID();
        plataformMapper = new PlataformMapperImplement();
        plataform = Plataform.builder()
                .id(id)
                .name("Test")
                .description("Este es un Test")
                .guardName("Test")
                .realiseDate(LocalDate.of(1970,4,1))
                .build();
        plataformOutDto = PlataformOutDto.builder()
                .id(plataform.getId())
                .name(plataform.getName())
                .description(plataform.getDescription())
                .realiceDate(plataform.getRealiseDate())
                .guardName(plataform.getGuardName())
                .build();
        platformDetailDto = PlatformDetailDto.builder()
                .id(plataform.getId())
                .name(plataform.getName())
                .description(plataform.getDescription())
                .realiseDate(plataform.getRealiseDate())
                .guardName(plataform.getGuardName())
                .build();
        updatedPlataform = Plataform
                .builder()
                .id(id)
                .name("Test2")
                .description("Test 2 prueba")
                .guardName("test2")
                .realiseDate(LocalDate.of(1930,2,12))
                .build();
        platformDetailDto2 = PlatformDetailDto
                .builder()
                .id(updatedPlataform.getId())
                .name(updatedPlataform.getName())
                .description(updatedPlataform.getDescription())
                .guardName(updatedPlataform.getGuardName())
                .realiseDate(plataform.getRealiseDate())
                .build();
    }

    @Test
    void save() {
        when(platformServiceImplement.save(any(PlataformInDto.class))).thenReturn(plataform);
        when(plataformRepository.save(any(Plataform.class))).thenReturn(plataform);
        var in = new PlataformInDto("test","Este es un Test","Test",LocalDate.of(1970,4,1));
        var result = platformServiceImplement.save(in);
        assertNotNull(result);
        assertEquals(result.getGuardName(),plataform.getGuardName());
        assertEquals(result.getName(),plataform.getName());
    }

    @Test
    void findAll() {
        when(platformServiceImplement.findAll()).thenReturn(Arrays.asList(plataformOutDto));
        when(plataformRepository.findAll()).thenReturn( Arrays.asList(plataform));
        var result = platformServiceImplement.findAll();
        assertNotNull(result);
    }

    @Test
    void findById() {
        when(plataformRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(plataform));
        when(platformServiceImplement.findById(id)).thenReturn(platformDetailDto);
        PlatformDetailDto result  = platformServiceImplement.findById(id);
        assertNotNull(result);
        assertEquals(result.getName(),plataform.getName());
        assertEquals(result.getGuardName(),plataform.getName());
        assertEquals(result.getDescription(),plataform.getDescription());
    }

    @Test
    void update() {
        when(plataformRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(plataform));
        when(platformServiceImplement.findById(id)).thenReturn(platformDetailDto);
        when(plataformRepository.save(any(Plataform.class))).thenReturn(updatedPlataform);
        var platformInDto2 = PlataformInDto.builder()
                .name("Test2")
                .description("Test 2 prueba")
                .guardName("test2")
                .realiceDate(LocalDate.of(1930,2,12))
                .build();
        when(platformServiceImplement.update(id,platformInDto2)).thenReturn(platformDetailDto2);
        var result = platformServiceImplement.update(id,platformInDto2);
        assertNotNull(result);
        assertEquals(platformDetailDto2.getId(),platformDetailDto.getId());
        assertNotEquals(platformDetailDto2.getName(),platformDetailDto.getName());
        assertNotEquals(platformDetailDto2.getGuardName(),platformDetailDto.getGuardName());

    }
}