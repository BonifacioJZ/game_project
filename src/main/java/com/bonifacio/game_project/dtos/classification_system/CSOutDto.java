package com.bonifacio.game_project.dtos.classification_system;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class CSOutDto {
    private final UUID id;
    private final String name;
    private final String description;
    private final String  places;
    private final Instant createAt;
    private final Instant updateAt;
}
