package com.bonifacio.game_project.services;

import com.bonifacio.game_project.dtos.CSInDto;
import com.bonifacio.game_project.dtos.CSOutDto;
import com.bonifacio.game_project.entities.ClassificationSystem;

import java.util.List;

public interface ClassificationSystemService {
    List<CSOutDto> findAll();
    ClassificationSystem save(CSInDto csInDto);
}
