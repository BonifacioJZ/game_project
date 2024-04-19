package com.bonifacio.game_project.mappers.platform;

import com.bonifacio.game_project.dtos.platform.PlatformDetailDto;
import org.mapstruct.Mapper;

import com.bonifacio.game_project.dtos.platform.PlataformInDto;
import com.bonifacio.game_project.dtos.platform.PlataformOutDto;
import com.bonifacio.game_project.entities.Plataform;

@Mapper
public interface PlatformMapper {

    Plataform plataformInDtoToPlataform(PlataformInDto plataformInDto);
    PlataformOutDto plataformToPlataformOutDto(Plataform plataform);
    PlatformDetailDto platformToPlatFormDetailDto(Plataform plataform);
}
