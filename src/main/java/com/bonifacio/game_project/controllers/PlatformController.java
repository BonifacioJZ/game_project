package com.bonifacio.game_project.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.bonifacio.game_project.dtos.platform.PlataformInDto;
import com.bonifacio.game_project.dtos.Response;
import com.bonifacio.game_project.services.platform.PlataformService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.UUID;

@RequestMapping(value = {"api/v1/platform",})
@RestController
@AllArgsConstructor
public class PlatformController {

    @Autowired
    private final PlataformService plataformService;

    @GetMapping(value = {"","/"})
    @Transactional
    public ResponseEntity<Response<?>> index(){
        var data = plataformService.findAll();
        return new ResponseEntity<>(Response.builder()
        .status(String.valueOf(HttpStatus.OK))
        .message("Todas Las Plataformas")
        .success(true)
        .data(data)
        .build(),HttpStatus.OK);
    }
    @PostMapping(value = {"","/"})
    public ResponseEntity<Response<?>> store(@Valid @RequestBody PlataformInDto plataformInDto,BindingResult result){
        try {
            if(result.hasErrors()) return new ResponseEntity<>(Response
            .builder()
            .message("Error")
            .status(String.valueOf(HttpStatus.BAD_REQUEST))
            .success(false)
            .data(result.getAllErrors())
            .build(),HttpStatus.BAD_REQUEST);

            var data = plataformService.save(plataformInDto);
            if(data == null) return  new ResponseEntity<>(Response.builder()
                    .message("error")
                    .success(false)
                    .status(String.valueOf(HttpStatus.BAD_REQUEST))
                    .data(data)
                    .build(),HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(Response.builder()
                    .success(true)
                    .message("Creado")
                    .status(String.valueOf(HttpStatus.CREATED))
                    .data(data)
                    .build(),HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Response
            .builder()
            .message(e.getMessage())
            .status(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR))
            .success(false)
            .data(e.getCause())
            .build(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(method = RequestMethod.GET,value = {"{id}","{id}/"})
    public ResponseEntity<Response<?>>show(@PathVariable UUID id){
        var data = plataformService.findById(id);
        if (data == null) return new ResponseEntity<>(Response.builder()
                .status(String.valueOf(HttpStatus.NOT_FOUND))
                .success(false)
                .message("Not Found")
                .data(null)
                .build(),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(Response.builder()
                .status(String.valueOf(HttpStatus.OK))
                .success(true)
                .message("Plataforma con el id ".concat(String.valueOf(id)))
                .data(data)
                .build(),HttpStatus.OK);
    }
    @PutMapping(value = {"{id}/","{id}"})
    public ResponseEntity<Response<?>> update(@PathVariable UUID id,@Valid @RequestBody PlataformInDto plataformInDto,
                                              BindingResult result){
        if(result.hasErrors())return  new ResponseEntity<>(Response
                .builder()
                .message("Error al Actualizar")
                .status(String.valueOf(HttpStatus.BAD_REQUEST))
                .success(false)
                .data(result.getAllErrors())
                .build(),HttpStatus.BAD_REQUEST);
        var data = plataformService.update(id,plataformInDto);
        return  new ResponseEntity<>(Response
                .builder()
                .message("Actualizado")
                .status(String.valueOf(HttpStatus.OK))
                .success(true)
                .data(data)
                .build(),HttpStatus.OK);
    }
    @DeleteMapping(value = {"{id}","{id}/"})
    public ResponseEntity<Response<?>> delete(@PathVariable UUID id ){
        try {
            plataformService.delete(id);
            return new ResponseEntity<>(Response.builder()
                    .message("Eliminado")
                    .status(String.valueOf(HttpStatus.OK))
                    .success(true)
                    .data(null)
                    .build(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(Response.builder()
                    .message("error")
                    .status(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR))
                    .success(false)
                    .data(e.getMessage())
                    .build(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
