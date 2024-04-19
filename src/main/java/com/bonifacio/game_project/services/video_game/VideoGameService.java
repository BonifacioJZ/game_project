package com.bonifacio.game_project.services.video_game;

import com.bonifacio.game_project.dtos.video_game.VideoGameInDto;
import com.bonifacio.game_project.dtos.video_game.VideoGameOutDto;
import com.bonifacio.game_project.entities.VideoGame;

import java.util.List;

public interface VideoGameService {
    List<VideoGameOutDto> findAll();
    VideoGame save(VideoGameInDto videoGameInDto);
}
