package com.bonifacio.game_project.dtos.classification;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Builder
public class ClassificationOutDto {
    private final UUID id;
    private final String name;
    private final String classification;
    private final String description;
    private final Instant createAt;
    private final Instant updateAt;
}
