package com.bonifacio.game_project.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
public class CSInDto {
    @NotBlank
    @NotEmpty
    @Size(max = 150)
    private final String name;
    @Size(max = 500)
    private final String descriptions;
    @NotEmpty
    @NotBlank
    @Size(max = 250)
    private final String places;
}
