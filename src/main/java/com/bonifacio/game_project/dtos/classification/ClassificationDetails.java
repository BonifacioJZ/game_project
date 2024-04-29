package com.bonifacio.game_project.dtos.classification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;
@AllArgsConstructor
@Getter
@Builder
public class ClassificationDetails {
    private final UUID id;
    private final String name;
    private final String description;
    private final UUID system_id;
    private final String system_name;
    private final String system_description;
}
