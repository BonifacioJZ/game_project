package com.bonifacio.game_project.services.video_game;

import com.bonifacio.game_project.dtos.classification.ClassificationOutDto;
import com.bonifacio.game_project.dtos.video_game.VideoGameDetails;
import com.bonifacio.game_project.dtos.video_game.VideoGameInDto;
import com.bonifacio.game_project.dtos.video_game.VideoGameOutDto;
import com.bonifacio.game_project.dtos.video_game.VideoGameUpdateDto;
import com.bonifacio.game_project.entities.Classification;
import com.bonifacio.game_project.entities.VideoGame;
import com.bonifacio.game_project.mappers.classification.ClassificationMapper;
import com.bonifacio.game_project.mappers.video_game.VideoGameMapper;
import com.bonifacio.game_project.repository.ClassificationRepository;
import com.bonifacio.game_project.repository.VideoGameRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class VideoGameServiceImplement implements VideoGameService {
    @Autowired
    private final VideoGameRepository videoGameRepository;
    @Autowired
    private final VideoGameMapper videoGameMapper;
    @Autowired
    private final ClassificationRepository classificationRepository;
    @Autowired
    private final ClassificationMapper classificationMapper;

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
        videoGameInDto.getClassification_id().forEach(classi->{
            var classification = classificationRepository.findById(classi).orElse(null);
            data.addClassification(classification);
        });
        return  videoGameRepository.save(data);
    }

    @Override
    public VideoGameDetails show(UUID id) {
        var data = videoGameRepository.findById(id).orElse(null);
        if(data==null) return null;

        ArrayList<ClassificationOutDto> classifications = new ArrayList<>();

        if(!data.getClassifications().isEmpty()){
            data.getClassifications().forEach(classification->{
                classifications.add(classificationMapper.classificationToClassificationOutDto(classification));
            });
        }

        return videoGameMapper.videoGameToVideoGameDetails(data,classifications);

    }

    @Override
    public VideoGameOutDto edit(UUID id, VideoGameInDto videoGameInDto) {
        var old = videoGameRepository.findById(id).orElse(null);
        if(old ==null) return null;

        old = videoGameMapper.videoGameUpdate(old,videoGameInDto);

        var data = videoGameRepository.save(old);

        return videoGameMapper.videoGameToVideoGameDto(data);
    }

    @Override
    public void delete(UUID id) {
        videoGameRepository.deleteById(id);
    }
}
