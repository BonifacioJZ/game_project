package com.bonifacio.game_project.dtos.video_game;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
public class VideoGameOutDto {
    private UUID id;
    private String name;
    private String description;
    private String image;
    private LocalDate realiseDate;
    private Instant createAt;
    private Instant updateAt;
}
