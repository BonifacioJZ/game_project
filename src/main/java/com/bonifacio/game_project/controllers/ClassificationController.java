package com.bonifacio.game_project.controllers;

import com.bonifacio.game_project.dtos.Response;
import com.bonifacio.game_project.dtos.classification.ClassificationInDto;
import com.bonifacio.game_project.services.classification.ClassificationService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping(value = "api/v1/classification")
public class ClassificationController {
    @Autowired
    private final ClassificationService classificationService;

    @GetMapping(value = {"","/"})
    public ResponseEntity<Response<?>> index(){
        var data = classificationService.findAll();
        return new ResponseEntity<>(Response
                .builder()
                .success(true)
                .message("clasificaciones")
                .status(String.valueOf(HttpStatus.OK))
                .data(data)
                .build(),HttpStatus.OK);
    }

    @PostMapping(value = {"","/"})
    @Transactional
    public ResponseEntity<Response<?>> store(@Valid@RequestBody ClassificationInDto classificationInDto,
                                             BindingResult result){
        try {
            if(result.hasErrors()) return new ResponseEntity<>(Response.builder()
                    .success(false)
                    .status(String.valueOf(HttpStatus.BAD_REQUEST))
                    .message("Error al Validar")
                    .data(result.getAllErrors())
                    .build(),HttpStatus.BAD_REQUEST);
            var data = classificationService.save(classificationInDto);
            if(data==null) return  new ResponseEntity<>(Response
                    .builder()
                    .success(false)
                    .message("Error al guardar la informacion")
                    .status(String.valueOf(HttpStatus.BAD_REQUEST))
                    .data(null)
                    .build(),HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(Response
                    .builder()
                    .success(true)
                    .message("Creado")
                    .status(String.valueOf(HttpStatus.CREATED))
                    .data(data)
                    .build(),HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(Response
                    .builder()
                    .status(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR))
                    .success(false)
                    .message("Error")
                    .data(e.getMessage())
                    .build(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(method = RequestMethod.GET,value = {"{id}","{id}/"})
    public ResponseEntity<Response<?>> show(@PathVariable UUID id){
        var data = classificationService.show(id);
        if(data == null) return new ResponseEntity<>(Response
                .builder()
                .success(false)
                .status(String.valueOf(HttpStatus.OK))
                .message("el id ".concat(String.valueOf(id)).concat(" no existe"))
                .data(null)
                .build(),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(Response
                .builder()
                .message("Clasificacion")
                .success(true)
                .status(String.valueOf(HttpStatus.OK))
                .data(data)
                .build(),HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.PUT,value = {"{id}","{id}/"})
    @Transactional
    public ResponseEntity<Response<?>> edit(@PathVariable UUID id, @Valid @RequestBody ClassificationInDto classification,
                                            BindingResult result){
        try{
            if(result.hasErrors()) return  new ResponseEntity<>(Response
                    .builder()
                    .success(false)
                    .status(String.valueOf(HttpStatus.BAD_REQUEST))
                    .message("Error al validar")
                    .data(null)
                    .build(),HttpStatus.BAD_REQUEST);
            var data = classificationService.edit(id,classification);
            if(data == null) return new ResponseEntity<>(Response
                    .builder()
                    .success(false)
                    .status(String.valueOf(HttpStatus.BAD_REQUEST))
                    .message("Error")
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
            return new ResponseEntity<>(Response
                    .builder()
                    .success(false)
                    .status(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR))
                    .message(e.getMessage())
                    .data(e.getMessage())
                    .build(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(method = RequestMethod.DELETE,value = {"{id}","{id}/"})
    public ResponseEntity<Response<?>> delete(@PathVariable UUID id){
        classificationService.delete(id);
        return new ResponseEntity<>(Response
                .builder()
                .success(true)
                .status(String.valueOf(HttpStatus.OK))
                .message("Eliminado")
                .data(1)
                .build(),HttpStatus.OK);
    }
}
