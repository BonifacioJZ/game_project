package com.bonifacio.game_project.dtos.video_game;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Getter
public class VideoGameUpdateDto {
    @Size(max = 150)
    private final String name;
    @Size(max = 500)
    private final String description;
    @NotNull
    private final LocalDate realiseDate;
    @Lob
    private final String image;
}
