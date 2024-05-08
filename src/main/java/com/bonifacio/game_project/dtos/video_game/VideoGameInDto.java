package com.bonifacio.game_project.dtos.video_game;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class VideoGameInDto {
    @Size(max = 150)
    private  String name;
    @Size(max = 500)
    private String description;
    @NotNull
    private LocalDate realiseDate;
    @Lob
    private String image;
    @NotNull
    private List<UUID> classifications;
    @NotNull
    private List<UUID> genders;
    @NotNull
    private List<UUID> platforms;
}
