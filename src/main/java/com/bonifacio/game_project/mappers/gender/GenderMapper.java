package com.bonifacio.game_project.mappers.gender;

import com.bonifacio.game_project.dtos.gender.GenderDetails;
import com.bonifacio.game_project.dtos.gender.GenderInDto;
import com.bonifacio.game_project.dtos.gender.GenderOutDto;
import com.bonifacio.game_project.dtos.video_game.VideoGameOutDto;
import com.bonifacio.game_project.entities.Gender;
import org.mapstruct.Mapper;
import java.util.List;


@Mapper
public interface GenderMapper {
    GenderOutDto genderToGenderOutDto(Gender gender);
    Gender genderInDtoToGender(GenderInDto genderInDto);
    GenderDetails genderToGenderDetails(Gender gender, List<VideoGameOutDto> videoGames);
    Gender updateGender(Gender oldGender, GenderInDto gender);
}
