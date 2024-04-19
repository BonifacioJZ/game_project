package com.bonifacio.game_project.dtos.platform;

import java.util.UUID;
import java.time.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class PlataformOutDto {
    private final UUID id;
    private final String name;
    private final String description;
    private final String guardName;
    private final LocalDate realiceDate;
    private final Instant createAt;
    private final Instant updateAt;
}
