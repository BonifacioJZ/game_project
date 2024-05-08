package com.bonifacio.game_project.services.gender;


import com.bonifacio.game_project.dtos.gender.GenderDetails;
import com.bonifacio.game_project.dtos.gender.GenderInDto;
import com.bonifacio.game_project.dtos.gender.GenderOutDto;
import java.util.List;
import java.util.UUID;

public interface GenderService {
    List<GenderOutDto> findAll();
    GenderOutDto save(GenderInDto genderInDto);
    GenderDetails show(UUID id);
    GenderOutDto edit(UUID id,GenderInDto gender);
    void delete(UUID id);
}
