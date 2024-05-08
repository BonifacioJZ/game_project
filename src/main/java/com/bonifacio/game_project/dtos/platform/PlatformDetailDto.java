package com.bonifacio.game_project.dtos.platform;

import com.bonifacio.game_project.dtos.video_game.VideoGameOutDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;
import java.util.List;
@AllArgsConstructor
@Getter
@Builder
public class PlatformDetailDto {
    private final UUID id;
    private final String name;
    private final String description;
    private final String guardName;
    private final LocalDate realiseDate;
    private final List<VideoGameOutDto> videoGames;
    private final Instant createAt;
    private final Instant updateAt;
}
