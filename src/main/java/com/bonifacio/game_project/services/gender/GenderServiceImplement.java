package com.bonifacio.game_project.services.gender;

import com.bonifacio.game_project.dtos.gender.GenderDetails;
import com.bonifacio.game_project.dtos.gender.GenderInDto;
import com.bonifacio.game_project.dtos.gender.GenderOutDto;
import com.bonifacio.game_project.dtos.video_game.VideoGameOutDto;
import com.bonifacio.game_project.mappers.gender.GenderMapper;
import com.bonifacio.game_project.mappers.video_game.VideoGameMapper;
import com.bonifacio.game_project.repository.GenderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Qualifier("genderService")
@AllArgsConstructor
public class GenderServiceImplement implements GenderService{

    @Autowired
    private final GenderRepository genderRepository;
    @Autowired
    private final GenderMapper genderMapper;
    @Autowired
    private  final VideoGameMapper videoGameMapper;

    @Override
    public List<GenderOutDto> findAll() {
        var data = genderRepository.findAll();
        return data.stream().map(genderMapper::genderToGenderOutDto).toList();
    }

    @Override
    public GenderOutDto save(GenderInDto genderInDto) {
        var data = genderMapper.genderInDtoToGender(genderInDto);
        if(data == null) return null;
        return genderMapper.genderToGenderOutDto(genderRepository.save(data));
    }

    @Override
    public GenderDetails show(UUID id) {
        var data = genderRepository.findById(id).orElse(null);
        if(data == null) return null;
        List<VideoGameOutDto> videoGames = new ArrayList<>();
        if(!data.getVideoGames().isEmpty()) {
            videoGames = data.getVideoGames().stream().map(videoGameMapper::videoGameToVideoGameDto)
                .toList();
        }
        return genderMapper.genderToGenderDetails(data,videoGames);
    }

}
