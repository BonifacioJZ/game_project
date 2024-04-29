package com.bonifacio.game_project.dtos.classification;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Builder
public class ClassificationInDto {
    @NotBlank
    @NotEmpty
    @Size(max = 150)
    private final String name;
    @NotEmpty
    @NotBlank
    @Size(max = 10,min = 1)
    private final String classification;
    @Size(max = 500)
    private final String description;
    @NotNull
    private final UUID classification_id;
}
