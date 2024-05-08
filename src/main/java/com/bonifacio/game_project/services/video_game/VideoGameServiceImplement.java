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
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

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

        return data.stream().map(videoGameMapper::videoGameToVideoGameDto).toList();

    }

    @Override
    public VideoGame save(VideoGameInDto videoGameInDto) {

        var data = videoGameMapper.videoGameInDtoToVideoGame(videoGameInDto);

        if(videoGameInDto == null) return null;
        var classifications = videoGameInDto.getClassification_id().stream().map(classi->{
            return classificationRepository.findById(classi).orElse(null);
        }).toList();
        data.setClassifications(classifications);
        return  videoGameRepository.save(data);
    }

    @Override
    public VideoGameDetails show(UUID id) {
        var data = videoGameRepository.findById(id).orElse(null);
        if(data==null) return null;

        List<ClassificationOutDto> classifications = new ArrayList<>();
        if(!data.getClassifications().isEmpty()){
           classifications =  data.getClassifications().stream().map(classificationMapper::classificationToClassificationOutDto).toList();
        }

        return videoGameMapper.videoGameToVideoGameDetails(data,classifications);

    }

    @Override
    public VideoGameDetails edit(UUID id, VideoGameInDto videoGameInDto) {
        var old = videoGameRepository.findById(id).orElse(null);
        if(old ==null) return null;

        old = videoGameMapper.videoGameUpdate(old,videoGameInDto);
        ArrayList<Classification> classifications = new ArrayList<>();
        if (!videoGameInDto.getClassification_id().isEmpty()){
            videoGameInDto.getClassification_id().forEach(classification->{
                var data = classificationRepository.findById(classification).orElse(null);
                if(data!=null){
                    classifications.add(data);
                }
            });
            old.setClassifications(classifications);
        }
        var data = videoGameRepository.save(old);
        ArrayList<ClassificationOutDto> classificationOutDtos = new ArrayList<>();
        data.getClassifications().forEach(dtos->{
            classificationOutDtos.add(classificationMapper.classificationToClassificationOutDto(dtos));
        });
        return videoGameMapper.videoGameToVideoGameDetails(data,classificationOutDtos);
    }

    @Override
    public void delete(UUID id) {
        videoGameRepository.deleteById(id);
    }
}
