package com.bonifacio.game_project.services;

import com.bonifacio.game_project.dtos.VideoGameInDto;
import com.bonifacio.game_project.dtos.VideoGameOutDto;
import com.bonifacio.game_project.entities.VideoGame;

import java.util.List;

public interface VideoGameService {
    List<VideoGameOutDto> findAll();
    VideoGame save(VideoGameInDto videoGameInDto);
}
