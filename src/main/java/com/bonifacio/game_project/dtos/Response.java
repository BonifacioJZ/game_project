package com.bonifacio.game_project.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Response<T> {
    private String status;
    private String message;
    private boolean success;
    private T data;
}
