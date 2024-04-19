package com.bonifacio.game_project.mappers.video_game;

import com.bonifacio.game_project.dtos.video_game.VideoGameInDto;
import com.bonifacio.game_project.dtos.video_game.VideoGameOutDto;
import com.bonifacio.game_project.entities.VideoGame;
import org.mapstruct.Mapper;

@Mapper
public interface VideoGameMapper {
    VideoGameOutDto videoGameToVideoGameDto(VideoGame videoGame);
    VideoGame videoGameInDtoToVideoGame(VideoGameInDto videoGameInDto);
}
