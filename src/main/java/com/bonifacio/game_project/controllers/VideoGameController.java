package com.bonifacio.game_project.controllers;

import com.bonifacio.game_project.dtos.Response;
import com.bonifacio.game_project.dtos.VideoGameInDto;
import com.bonifacio.game_project.services.VideoGameService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/video_game")
@AllArgsConstructor
public class VideoGameController {
    @Autowired
    private  final VideoGameService gameService;

    @GetMapping(value = {"","/"})
    public ResponseEntity<Response<?>> index(){
        var data = gameService.findAll();
        return new ResponseEntity<>(Response
                .builder()
                .success(true)
                .status(String.valueOf(HttpStatus.OK))
                .message("video juegos")
                .data(data)
                .build(),HttpStatus.OK);
    }
    @PostMapping(value = {"","/"})
    public ResponseEntity<Response<?>> store(@Valid @RequestBody VideoGameInDto videoGameInDto,
                                             BindingResult result){
        try{
            if(result.hasErrors()) return new ResponseEntity<>(Response
                    .builder()
                    .message("Error al Validar")
                    .success(false)
                    .status(String.valueOf(HttpStatus.BAD_REQUEST))
                    .data(result.getAllErrors())
                    .build(),HttpStatus.BAD_REQUEST);
            var data = gameService.save(videoGameInDto);
            if(data== null) return new ResponseEntity<>(Response
                    .builder()
                    .message("Error")
                    .status(String.valueOf(HttpStatus.BAD_REQUEST))
                    .success(false)
                    .data(null)
                    .build(),HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(Response
                    .builder()
                    .message("Video Juego con id ".concat(String.valueOf(data.getId())))
                    .status(String.valueOf(HttpStatus.CREATED))
                    .success(true)
                    .data(data)
                    .build(),HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(Response
                    .builder()
                    .message("Error")
                    .status(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR))
                    .success(false)
                    .data(e.getMessage())
                    .build(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
