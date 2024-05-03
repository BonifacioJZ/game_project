package com.bonifacio.game_project.dtos.classification;

import com.bonifacio.game_project.dtos.video_game.VideoGameOutDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;
import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class ClassificationDetails {
    private final UUID id;
    private final String name;
    private final String description;
    private final String classification;
    private final UUID system_id;
    private final String system_name;
    private final String system_description;
    private final List<VideoGameOutDto> videoGames;
    private final Instant createAt;
    private final Instant updateAt;
}
