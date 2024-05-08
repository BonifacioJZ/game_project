package com.bonifacio.game_project.dtos.gender;

import com.bonifacio.game_project.dtos.video_game.VideoGameOutDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;
import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class GenderDetails {
    private final UUID id;
    private final String name;
    private final String description;
    private final List<VideoGameOutDto> videoGames;
}
