package com.bonifacio.game_project.mappers.video_game;

import com.bonifacio.game_project.dtos.classification.ClassificationOutDto;
import com.bonifacio.game_project.dtos.video_game.VideoGameDetails;
import com.bonifacio.game_project.dtos.video_game.VideoGameInDto;
import com.bonifacio.game_project.dtos.video_game.VideoGameOutDto;
import com.bonifacio.game_project.entities.VideoGame;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VideoGameMapperImplement implements VideoGameMapper{
    @Override
    public VideoGameOutDto videoGameToVideoGameDto(VideoGame videoGame) {
        if(videoGame==null) return null;

        return VideoGameOutDto
                .builder()
                .id(videoGame.getId())
                .name(videoGame.getName())
                .description(videoGame.getDescription())
                .image(videoGame.getImage())
                .realiseDate(videoGame.getRealiseDate())
                .createAt(videoGame.getCreateAt())
                .updateAt(videoGame.getUpdateAt())
                .build();
    }

    @Override
    public VideoGame videoGameInDtoToVideoGame(VideoGameInDto videoGameInDto) {
        if (videoGameInDto==null) return null;
        return VideoGame.builder()
                .name(videoGameInDto.getName())
                .description(videoGameInDto.getDescription())
                .realiseDate(videoGameInDto.getRealiseDate())
                .image(videoGameInDto.getImage())
                .build();
    }

    @Override
    public VideoGameDetails videoGameToVideoGameDetails(VideoGame videoGames, List<ClassificationOutDto> classification) {
        if(videoGames==null) return null;

        return VideoGameDetails.builder()
                .id(videoGames.getId())
                .name(videoGames.getName())
                .description(videoGames.getDescription())
                .realiseDate(videoGames.getRealiseDate())
                .image(videoGames.getImage())
                .classifications(classification)
                .updateAt(videoGames.getUpdateAt())
                .createAt(videoGames.getCreateAt())
                .build();
    }

    @Override
    public VideoGame videoGameUpdate(VideoGame videoGame, VideoGameInDto videoGameInDto) {
        if(videoGame==null) return null;
        videoGame.setName((StringUtils.isEmpty(videoGameInDto.getName()))?
                videoGame.getName(): videoGameInDto.getName());
        videoGame.setDescription((StringUtils.isEmpty(videoGameInDto.getDescription()))?
                videoGame.getDescription():videoGameInDto.getDescription());
        videoGame.setImage((StringUtils.isEmpty(videoGameInDto.getImage()))?
                videoGame.getImage():videoGameInDto.getImage());
        videoGame.setRealiseDate((videoGameInDto.getRealiseDate()==null)?
                videoGame.getRealiseDate():videoGameInDto.getRealiseDate());

        return videoGame;
    }
}
