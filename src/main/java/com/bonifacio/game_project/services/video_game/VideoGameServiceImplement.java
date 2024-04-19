package com.bonifacio.game_project.services.video_game;

import com.bonifacio.game_project.dtos.video_game.VideoGameInDto;
import com.bonifacio.game_project.dtos.video_game.VideoGameOutDto;
import com.bonifacio.game_project.entities.VideoGame;
import com.bonifacio.game_project.mappers.video_game.VideoGameMapper;
import com.bonifacio.game_project.repository.VideoGameRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class VideoGameServiceImplement implements VideoGameService {
    @Autowired
    private final VideoGameRepository videoGameRepository;
    @Autowired
    private final VideoGameMapper videoGameMapper;

    @Override
    public List<VideoGameOutDto> findAll() {
        var data = videoGameRepository.findAll();
        ArrayList<VideoGameOutDto> out = new ArrayList<>();
        data.forEach(d->{
            out.add(videoGameMapper.videoGameToVideoGameDto(d));
        });
        return out;
    }

    @Override
    public VideoGame save(VideoGameInDto videoGameInDto) {

        var data = videoGameMapper.videoGameInDtoToVideoGame(videoGameInDto);

        if(videoGameInDto == null) return null;

        return  videoGameRepository.save(data);
    }
}
