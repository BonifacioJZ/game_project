package com.bonifacio.game_project.dtos.video_game;

import com.bonifacio.game_project.dtos.classification.ClassificationOutDto;
import com.bonifacio.game_project.dtos.gender.GenderOutDto;
import com.bonifacio.game_project.dtos.platform.PlataformOutDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Builder
public class VideoGameDetails {
    private final UUID id;
    private final String name;
    private final String description;
    private final String image;
    private final LocalDate realiseDate;
    private final Instant createAt;
    private final Instant updateAt;
    private final List<ClassificationOutDto> classifications;
    private final List<GenderOutDto> genders;
    private final List<PlataformOutDto> platforms;
}
