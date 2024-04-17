package com.bonifacio.game_project.mappers;

import com.bonifacio.game_project.dtos.PlatformDetailDto;
import org.mapstruct.Mapper;

import com.bonifacio.game_project.dtos.PlataformInDto;
import com.bonifacio.game_project.dtos.PlataformOutDto;
import com.bonifacio.game_project.entities.Plataform;

@Mapper
public interface PlatformMapper {

    Plataform plataformInDtoToPlataform(PlataformInDto plataformInDto);
    PlataformOutDto plataformToPlataformOutDto(Plataform plataform);
    PlatformDetailDto platformToPlatFormDetailDto(Plataform plataform);
}
