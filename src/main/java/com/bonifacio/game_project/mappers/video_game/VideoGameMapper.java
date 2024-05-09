package com.bonifacio.game_project.mappers.video_game;

import com.bonifacio.game_project.dtos.classification.ClassificationOutDto;
import com.bonifacio.game_project.dtos.gender.GenderOutDto;
import com.bonifacio.game_project.dtos.platform.PlataformOutDto;
import com.bonifacio.game_project.dtos.video_game.VideoGameDetails;
import com.bonifacio.game_project.dtos.video_game.VideoGameInDto;
import com.bonifacio.game_project.dtos.video_game.VideoGameOutDto;
import com.bonifacio.game_project.entities.VideoGame;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper
public interface VideoGameMapper {
    VideoGameOutDto videoGameToVideoGameDto(VideoGame videoGame);
    VideoGame videoGameInDtoToVideoGame(VideoGameInDto videoGameInDto);
    VideoGameDetails videoGameToVideoGameDetails(VideoGame videoGames, List<ClassificationOutDto> classification, List<GenderOutDto> genders,
                                                 List<PlataformOutDto> platforms);
    VideoGame videoGameUpdate(VideoGame videoGame,VideoGameInDto videoGameInDto);
}
