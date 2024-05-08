package com.bonifacio.game_project.controllers;

import com.bonifacio.game_project.dtos.Response;
import com.bonifacio.game_project.dtos.gender.GenderInDto;
import com.bonifacio.game_project.dtos.gender.GenderOutDto;
import com.bonifacio.game_project.services.gender.GenderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "api/v1/gender")
public class GenderController {

    @Autowired
    @Qualifier("genderService")
    private GenderService genderService;

    @GetMapping(value = {"","/"})
    public ResponseEntity<Response<?>> index(){
        var data = genderService.findAll();
        return new ResponseEntity<>(Response.builder()
                .status(String.valueOf(HttpStatus.OK))
                .success(true)
                .message("generos")
                .data(data)
                .build(),HttpStatus.OK);
    }

    @PostMapping(value = {"","/"})
    public ResponseEntity<Response<?>> store(@RequestBody @Valid GenderInDto gender, BindingResult result){
        try{
            if(result.hasErrors()) return new ResponseEntity<>(Response.builder()
                    .status(String.valueOf(HttpStatus.BAD_REQUEST))
                    .success(false)
                    .message(result.getNestedPath())
                    .data(result.getAllErrors())
                    .build(),HttpStatus.BAD_REQUEST);
            var data = genderService.save(gender);
            if(data==null) return new ResponseEntity<>(Response
                    .builder()
                    .status(String.valueOf(HttpStatus.BAD_REQUEST))
                    .success(false)
                    .data(null)
                    .build(),HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(Response.builder()
                    .status(String.valueOf(HttpStatus.CREATED))
                    .message("Genero ".concat(data.getName()))
                    .success(true)
                    .data(data)
                    .build(),HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(Response.builder()
                    .status(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR))
                    .message(e.getMessage())
                    .success(true)
                    .data(null)
                    .build(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = {"{id}","{id}/"})
    public ResponseEntity<Response<?>> show(@PathVariable UUID id){
        var data = genderService.show(id);
        if(data==null) return new ResponseEntity<>(Response.builder()
                .status(String.valueOf(HttpStatus.NOT_FOUND))
                .success(false)
                .message("El Genero con id ".concat(String.valueOf(id)).concat(" no existe"))
                .data(null)
                .build(),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(Response
                .builder()
                .status(String.valueOf(HttpStatus.OK))
                .success(true)
                .message("Genero ".concat(data.getName()))
                .data(data)
                .build(),HttpStatus.OK);
    }

    @PutMapping(value = {"{id}","{id}/"})
    public ResponseEntity<Response<?>> edit(@PathVariable UUID id,@RequestBody @Valid GenderInDto gender,
                                            BindingResult result){
        try{
            if(result.hasErrors())return new ResponseEntity<>(Response.builder()
                    .status(String.valueOf(HttpStatus.BAD_REQUEST))
                    .message("Error al Validar")
                    .success(true)
                    .data(result.getAllErrors())
                    .build(),HttpStatus.BAD_REQUEST);
            var data = genderService.edit(id,gender);
            if(data==null) return  new ResponseEntity<>(Response.builder()
                    .status(String.valueOf(HttpStatus.NOT_FOUND))
                    .message("el genero con id ".concat(String.valueOf(id)).concat(" no existe"))
                    .success(true)
                    .build(),HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(Response.builder()
                    .status(String.valueOf(HttpStatus.OK))
                    .success(true)
                    .message("genero")
                    .data(data)
                    .build(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(Response.builder()
                    .status(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR))
                    .success(true)
                    .message(e.getMessage())
                    .data(e.getCause())
                    .build(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = {"{id}","{id}/"})
    public ResponseEntity<Response<?>> delete(@PathVariable UUID id){
        genderService.delete(id);
        return  new ResponseEntity<>(Response.builder()
                .status(String.valueOf(HttpStatus.OK))
                .success(true)
                .message("Eliminado")
                .data(1)
                .build(),HttpStatus.OK);
    }
}
