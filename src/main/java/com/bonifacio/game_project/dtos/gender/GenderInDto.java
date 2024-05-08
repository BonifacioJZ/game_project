package com.bonifacio.game_project.dtos.gender;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class GenderInDto {
    @NotBlank
    @NotEmpty
    @Size(max = 150)
    private final String name;
    @Size(max = 500)
    private final String description;
}
