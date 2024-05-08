package com.bonifacio.game_project.controllers;

import com.bonifacio.game_project.dtos.Response;
import com.bonifacio.game_project.dtos.video_game.VideoGameInDto;
import com.bonifacio.game_project.services.video_game.VideoGameService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
    @GetMapping(value = {"{id}","{id}/"})
    @Transactional
    public ResponseEntity<Response<?>> show(@PathVariable UUID id){
        var data = gameService.show(id);
        if(data==null) return  new ResponseEntity<>(Response
                .builder()
                .status(String.valueOf(HttpStatus.NOT_FOUND))
                .success(true)
                .message("El video Juego con el id ".concat(String.valueOf(id)).concat(" no existe"))
                .build(),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(Response.builder()
                .success(true)
                .status(String.valueOf(HttpStatus.OK))
                .message("video juego ".concat(data.getName()))
                .data(data)
                .build(),HttpStatus.OK);
    }
    @PutMapping(value = {"{id}","{id}/"})
    @Transactional
    public ResponseEntity<Response<?>> edit(@RequestBody @Valid VideoGameInDto videoGameInDto,
                                            BindingResult result,@PathVariable UUID id){
        try{
            if(result.hasErrors())return new ResponseEntity<>(Response.builder()
                    .message(result.getNestedPath())
                    .success(false)
                    .status(String.valueOf(HttpStatus.BAD_REQUEST))
                    .data(result.getAllErrors())
                    .build(),HttpStatus.BAD_REQUEST);
            var data = gameService.edit(id,videoGameInDto);
            if(data==null) return new ResponseEntity<>(Response
                    .builder()
                    .message("error al actualizar")
                    .status(String.valueOf(HttpStatus.BAD_REQUEST))
                    .success(false)
                    .data(videoGameInDto)
                    .build(),HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(Response
                    .builder()
                    .message("Video juego ".concat(data.getName()))
                    .success(true)
                    .status(String.valueOf(HttpStatus.OK))
                    .data(data)
                    .build(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(Response
                    .builder()
                    .message(e.getMessage())
                    .success(false)
                    .status(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR))
                    .data(null)
                    .build(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = {"{id}","{id}/"})
    public ResponseEntity<Response<?>> delete(@PathVariable UUID id){
        gameService.delete(id);
        return new ResponseEntity<>(Response.builder()
                .message("Eliminado")
                .success(true)
                .status(String.valueOf(HttpStatus.OK))
                .data(1)
                .build(),HttpStatus.OK);
    }

}
