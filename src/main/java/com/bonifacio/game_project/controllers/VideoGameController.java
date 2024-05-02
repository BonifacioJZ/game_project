package com.bonifacio.game_project.controllers;

import com.bonifacio.game_project.dtos.Response;
import com.bonifacio.game_project.dtos.video_game.VideoGameInDto;
import com.bonifacio.game_project.dtos.video_game.VideoGameUpdateDto;
import com.bonifacio.game_project.services.video_game.VideoGameService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
    @RequestMapping(method = RequestMethod.GET,value = {"{id}","{id}/"})
    @Transactional
    public ResponseEntity<Response<?>> show(@PathVariable UUID id){
         var data = gameService.show(id);
         if(data == null) return new ResponseEntity<>(Response.builder()
                 .message("el video juego con id ".concat(String.valueOf(id)).concat(" no existe"))
                 .success(true)
                 .status(String.valueOf(HttpStatus.NOT_FOUND))
                 .success(false)
                 .data(data)
                 .build(),HttpStatus.NOT_FOUND);

         return new ResponseEntity<>(Response.builder()
                 .message("Video Juego ".concat(data.getName()))
                 .success(true)
                 .status(String.valueOf(HttpStatus.OK))
                 .data(data)
                 .build(),HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.PUT,value = {"{id}","{id}/"})
    @Transactional
    public ResponseEntity<Response<?>> edit(@PathVariable UUID id, @Valid @RequestBody VideoGameInDto videoGameInDto,
                                            BindingResult result){
        try{
            if(result.hasErrors()) return  new ResponseEntity<>(Response.builder()
                    .message("Error en la validacion")
                    .success(false)
                    .status(String.valueOf(HttpStatus.BAD_REQUEST))
                    .data(null)
                    .build(),HttpStatus.BAD_REQUEST);
            var data = gameService.edit(id,videoGameInDto);
            if(data==null) return  new ResponseEntity<>(Response.builder()
                    .message("error")
                    .data(null)
                    .success(false)
                    .status(String.valueOf(HttpStatus.BAD_REQUEST))
                    .build(),HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(Response.builder()
                    .message("video game")
                    .status(String.valueOf(HttpStatus.OK))
                    .success(true)
                    .data(data)
                    .build(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(Response.builder()
                    .message(e.getMessage())
                    .success(false)
                    .status(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR))
                    .data(e.getMessage())
                    .build(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE,value = {"{id}","{id}/"})
    public ResponseEntity<Response<?>> delete(@PathVariable UUID id){
        gameService.delete(id);
        return  new ResponseEntity<>(Response
                .builder()
                .message("Eliminado")
                .status(String.valueOf(HttpStatus.OK))
                .success(true)
                .data(1)
                .build(),HttpStatus.OK);
    }

    //Todo(creacion de enpoint para editar la relaciones muchos a muchos)

}
