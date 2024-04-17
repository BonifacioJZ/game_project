package com.bonifacio.game_project.mappers;

import com.bonifacio.game_project.dtos.PlatformDetailDto;
import org.springframework.stereotype.Component;

import com.bonifacio.game_project.dtos.PlataformInDto;
import com.bonifacio.game_project.dtos.PlataformOutDto;
import com.bonifacio.game_project.entities.Plataform;

@Component
public class PlataformMapperImplement implements PlatformMapper {

    @Override
    public Plataform plataformInDtoToPlataform(PlataformInDto plataformInDto) {
        if(plataformInDto==null){
            return null;
        }
        
   
        return Plataform.builder()
        .name(plataformInDto.getName())
        .description(plataformInDto.getDescription())
        .guardName(plataformInDto.getGuardName())
        .realiseDate(plataformInDto.getRealiceDate())
        .build();
    }

    @Override
    public PlataformOutDto plataformToPlataformOutDto(Plataform plataform) {
        if(plataform==null){
            return null;
        }
        return PlataformOutDto
        .builder()
        .id(plataform.getId())
        .name(plataform.getName())
        .description(plataform.getDescription())
        .guardName(plataform.getGuardName())
        .realiceDate(plataform.getRealiseDate())
        .createAt(plataform.getCreateAt())
        .updateAt(plataform.getUpdateAt())
        .build();
    }

    @Override
    public PlatformDetailDto platformToPlatFormDetailDto(Plataform plataform) {
        if(plataform == null) return null;
        return  PlatformDetailDto.builder()
                .id(plataform.getId())
                .name(plataform.getName())
                .description(plataform.getDescription())
                .guardName(plataform.getGuardName())
                .realiseDate(plataform.getRealiseDate())
                .createAt(plataform.getCreateAt())
                .updateAt(plataform.getUpdateAt())
                .build();
    }

}
