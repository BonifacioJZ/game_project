package com.bonifacio.game_project.dtos.video_game;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Getter
public class VideoGameInDto {
    @Size(max = 150)
    private String name;
    @Size(max = 500)
    private String description;
    @NotNull
    private LocalDate realiseDate;
    private String image;
}
