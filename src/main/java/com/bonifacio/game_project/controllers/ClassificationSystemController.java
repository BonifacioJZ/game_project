package com.bonifacio.game_project.controllers;

import com.bonifacio.game_project.dtos.classification_system.CSInDto;
import com.bonifacio.game_project.dtos.Response;
import com.bonifacio.game_project.services.classification_system.ClassificationSystemService;
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
@RequestMapping(value = {"api/v1/classification_system"})
@AllArgsConstructor
public class ClassificationSystemController {
    @Autowired
    private final ClassificationSystemService csService;

    @GetMapping(value = {"","/"})
    public ResponseEntity<Response<?>> index(){
        var data = csService.findAll();

        return new ResponseEntity<>(Response.builder()
                .success(true)
                .message("Sistemas de clasificacion")
                .status(String.valueOf(HttpStatus.OK))
                .data(data)
                .build(),HttpStatus.OK);
    }
    @PostMapping(value = {"","/"})
    public ResponseEntity<Response<?>> store(@Valid @RequestBody CSInDto csInDto, BindingResult result){
        try{
            if(result.hasErrors()) return new ResponseEntity<>(Response
                    .builder()
                    .status(String.valueOf(HttpStatus.BAD_REQUEST))
                    .message("Error to Validacion")
                    .success(false)
                    .data(result.getAllErrors())
                    .build(),HttpStatus.BAD_REQUEST);
            var data = csService.save(csInDto);
            if(result.hasErrors()) return new ResponseEntity<>(Response
                    .builder()
                    .status(String.valueOf(HttpStatus.BAD_REQUEST))
                    .message("Error al Guardar")
                    .success(false)
                    .data(null)
                    .build(),HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(Response
                    .builder()
                    .success(false)
                    .status(String.valueOf(HttpStatus.CREATED))
                    .success(true)
                    .data(data)
                    .build(),HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(Response.builder()
                    .message("Server Error")
                    .status(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR))
                    .success(false)
                    .data(e.getMessage())
                    .build(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(method = RequestMethod.GET,value = {"{id}","{id}/"})
    public ResponseEntity<Response<?>> show(@PathVariable UUID id){
        var data = csService.show(id);
        if (data==null){
            return new ResponseEntity<>(Response.builder()
                    .success(false)
                    .message("el id ".concat(String.valueOf(id)).concat(" no existe"))
                    .status(String.valueOf(HttpStatus.NOT_FOUND))
                    .data(null)
                    .build(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Response.builder()
                .success(true)
                .status(String.valueOf(HttpStatus.OK))
                .message("Sistema de clasficacion")
                .data(data)
                .build(),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT,value = {"{id}","{id}/"})
    @Transactional
    public ResponseEntity<Response<?>> edit(@Valid @RequestBody CSInDto csInDto,
                                            BindingResult result,@PathVariable UUID id){
        try{
            if(result.hasErrors()) return new ResponseEntity<>(Response
                    .builder()
                    .success(false)
                    .message("Erro en la validacion")
                    .status(String.valueOf(HttpStatus.BAD_REQUEST))
                    .data(null)
                    .build(),HttpStatus.BAD_REQUEST);
            var data = csService.edit(id,csInDto);
            if(data==null)return new ResponseEntity<>(Response.builder()
                    .success(false)
                    .message("Error al Guardar")
                    .status(String.valueOf(HttpStatus.BAD_REQUEST))
                    .data(null)
                    .build(),HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(Response
                    .builder()
                    .success(true)
                    .message("Actualizado")
                    .status(String.valueOf(HttpStatus.OK))
                    .data(data)
                    .build(),HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(Response
                    .builder()
                    .success(true)
                    .message(e.getMessage())
                    .status(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR))
                    .data(e.getMessage())
                    .build(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(method = RequestMethod.DELETE,value = {"{id}","{id}/"})
    public ResponseEntity<Response<?>> delete(@PathVariable UUID id){
        csService.delete(id);
        return new ResponseEntity<>(Response
                .builder()
                .success(true)
                .message("Eliminado")
                .status(String.valueOf(HttpStatus.OK))
                .data(1)
                .build(),HttpStatus.OK);
    }

}
