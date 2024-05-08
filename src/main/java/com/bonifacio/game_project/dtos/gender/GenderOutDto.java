package com.bonifacio.game_project.dtos.gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Builder
public class GenderOutDto {
    private final UUID id;
    private final String name;
    private final String description;
}
