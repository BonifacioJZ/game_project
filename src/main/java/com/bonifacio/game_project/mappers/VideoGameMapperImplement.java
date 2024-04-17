package com.bonifacio.game_project.mappers;

import com.bonifacio.game_project.dtos.VideoGameInDto;
import com.bonifacio.game_project.dtos.VideoGameOutDto;
import com.bonifacio.game_project.entities.VideoGame;
import org.springframework.stereotype.Component;

@Component
public class VideoGameMapperImplement implements VideoGameMapper{
    @Override
    public VideoGameOutDto videoGameToVideoGameDto(VideoGame videoGame) {
        if(videoGame==null) return null;

        return VideoGameOutDto
                .builder()
                .id(videoGame.getId())
                .name(videoGame.getName())
                .description(videoGame.getDescription())
                .image(videoGame.getImage())
                .realiseDate(videoGame.getRealiseDate())
                .createAt(videoGame.getCreateAt())
                .updateAt(videoGame.getUpdateAt())
                .build();
    }

    @Override
    public VideoGame videoGameInDtoToVideoGame(VideoGameInDto videoGameInDto) {
        if (videoGameInDto==null) return null;
        return VideoGame.builder()
                .name(videoGameInDto.getName())
                .description(videoGameInDto.getDescription())
                .realiseDate(videoGameInDto.getRealiseDate())
                .image(videoGameInDto.getImage())
                .build();
    }
}
