package com.bonifacio.game_project.services;

import com.bonifacio.game_project.dtos.PlataformInDto;
import com.bonifacio.game_project.dtos.PlataformOutDto;
import com.bonifacio.game_project.dtos.PlatformDetailDto;
import com.bonifacio.game_project.entities.Plataform;
import java.util.List;
import java.util.UUID;

public interface PlataformService {
    Plataform save(PlataformInDto plataformInDto);
    List<PlataformOutDto> findAll();
    PlatformDetailDto findById(UUID id);
    PlatformDetailDto update(UUID id, PlataformInDto plataformInDto);
    void delete(UUID id);


}
