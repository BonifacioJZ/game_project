package com.bonifacio.game_project.dtos.classification;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

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
}
