package com.bonifacio.game_project.mappers;

import com.bonifacio.game_project.dtos.VideoGameInDto;
import com.bonifacio.game_project.dtos.VideoGameOutDto;
import com.bonifacio.game_project.entities.VideoGame;
import org.mapstruct.Mapper;

@Mapper
public interface VideoGameMapper {
    VideoGameOutDto videoGameToVideoGameDto(VideoGame videoGame);
    VideoGame videoGameInDtoToVideoGame(VideoGameInDto videoGameInDto);
}
