package com.bonifacio.game_project.services.video_game;

import com.bonifacio.game_project.dtos.classification.ClassificationOutDto;
import com.bonifacio.game_project.dtos.gender.GenderOutDto;
import com.bonifacio.game_project.dtos.platform.PlataformOutDto;
import com.bonifacio.game_project.dtos.video_game.VideoGameDetails;
import com.bonifacio.game_project.dtos.video_game.VideoGameInDto;
import com.bonifacio.game_project.dtos.video_game.VideoGameOutDto;
import com.bonifacio.game_project.dtos.video_game.VideoGameUpdateDto;
import com.bonifacio.game_project.entities.Classification;
import com.bonifacio.game_project.entities.Gender;
import com.bonifacio.game_project.entities.Plataform;
import com.bonifacio.game_project.entities.VideoGame;
import com.bonifacio.game_project.mappers.classification.ClassificationMapper;
import com.bonifacio.game_project.mappers.gender.GenderMapper;
import com.bonifacio.game_project.mappers.platform.PlatformMapper;
import com.bonifacio.game_project.mappers.video_game.VideoGameMapper;
import com.bonifacio.game_project.repository.ClassificationRepository;
import com.bonifacio.game_project.repository.GenderRepository;
import com.bonifacio.game_project.repository.PlatformRepository;
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
    @Autowired
    private final GenderRepository genderRepository;
    @Autowired
    private final GenderMapper genderMapper;
    @Autowired
    private final PlatformRepository platformRepository;
    @Autowired
    private final PlatformMapper platformMapper;
    @Override
    public List<VideoGameOutDto> findAll() {
        var data = videoGameRepository.findAll();

        return data.stream().map(videoGameMapper::videoGameToVideoGameDto).toList();

    }

    @Override
    public VideoGame save(VideoGameInDto videoGameInDto) {

        var data = videoGameMapper.videoGameInDtoToVideoGame(videoGameInDto);

        if(videoGameInDto == null) return null;
        var classifications = classificationRepository.findAllById(videoGameInDto.getClassifications());
        var gender = genderRepository.findAllById(videoGameInDto.getGenders());
        var platforms = platformRepository.findAllById(videoGameInDto.getPlatforms());
        data.setClassifications(classifications);
        data.setGenders(gender);
        data.setPlatforms(platforms);
        return  videoGameRepository.save(data);
    }

    @Override
    public VideoGameDetails show(UUID id) {
        var data = videoGameRepository.findById(id).orElse(null);
        if(data==null) return null;

        List<ClassificationOutDto> classifications = new ArrayList<>();
        List<GenderOutDto> genders = new ArrayList<>();
        List<PlataformOutDto> platforms = new ArrayList<>();
        if(!data.getClassifications().isEmpty()){
           classifications =  data.getClassifications().stream().map(classificationMapper::classificationToClassificationOutDto).toList();
        }
        if(!data.getGenders().isEmpty()){
            genders = data.getGenders().stream().map(genderMapper::genderToGenderOutDto).toList();
        }
        if(!data.getPlatforms().isEmpty()){
            platforms = data.getPlatforms().stream().map(platformMapper::plataformToPlataformOutDto).toList();
        }
        return videoGameMapper.videoGameToVideoGameDetails(data,classifications,genders,platforms);

    }

    @Override
    public VideoGameDetails edit(UUID id, VideoGameInDto videoGameInDto) {
        var old = videoGameRepository.findById(id).orElse(null);
        if(old ==null) return null;

        old = videoGameMapper.videoGameUpdate(old,videoGameInDto);
        List<Classification> classifications = new ArrayList<>();
        List<Gender> genders = new ArrayList<>();
        List<Plataform> platforms = new ArrayList<>();
        if (!videoGameInDto.getClassifications().isEmpty()){
            classifications = classificationRepository.findAllById(videoGameInDto.getClassifications());
            old.setClassifications(classifications);
        }
        if(!videoGameInDto.getGenders().isEmpty()){
            genders = genderRepository.findAllById(videoGameInDto.getGenders());
            old.setGenders(genders);
        }
        if(!videoGameInDto.getClassifications().isEmpty()){
            platforms = platformRepository.findAllById(videoGameInDto.getPlatforms());
        }
        var data = videoGameRepository.save(old);
        var classificationOutDtos = data.getClassifications().stream().map(classificationMapper::classificationToClassificationOutDto).toList();
        var gendersDto = data.getGenders().stream().map(genderMapper::genderToGenderOutDto).toList();
        var platform =  data.getPlatforms().stream().map(platformMapper::plataformToPlataformOutDto).toList();
        return videoGameMapper.videoGameToVideoGameDetails(data,classificationOutDtos,gendersDto,platform);
    }

    @Override
    public void delete(UUID id) {
        videoGameRepository.deleteById(id);
    }
}
