package com.bonifacio.game_project.mappers.gender;

import com.bonifacio.game_project.dtos.gender.GenderDetails;
import com.bonifacio.game_project.dtos.gender.GenderInDto;
import com.bonifacio.game_project.dtos.gender.GenderOutDto;
import com.bonifacio.game_project.dtos.video_game.VideoGameOutDto;
import com.bonifacio.game_project.entities.Gender;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class GenderMapperImplement implements  GenderMapper{
    @Override
    public GenderOutDto genderToGenderOutDto(Gender gender) {
        if(gender==null) return null;

        return GenderOutDto.builder()
                .id(gender.getId())
                .name(gender.getName())
                .description(gender.getDescription())
                .build();
    }

    @Override
    public Gender genderInDtoToGender(GenderInDto genderInDto) {
        if(genderInDto==null) return null;

        return Gender.builder()
                .name(genderInDto.getName())
                .description(genderInDto.getDescription())
                .build();
    }

    @Override
    public GenderDetails genderToGenderDetails(Gender gender, List<VideoGameOutDto> videoGames) {
        if(gender==null) return null;

        return GenderDetails
                .builder()
                .id(gender.getId())
                .description(gender.getDescription())
                .name(gender.getName())
                .videoGames(videoGames)
                .build();
    }
}
