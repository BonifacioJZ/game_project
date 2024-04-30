package com.bonifacio.game_project.dtos.classification_system;

import com.bonifacio.game_project.dtos.classification.ClassificationOutDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Builder
public class CSDetails {
    private final UUID id;
    private final String name;
    private final String description;
    private final String  places;
    private final List<ClassificationOutDto> classification;
    private final Instant createAt;
    private final Instant updateAt;
}
