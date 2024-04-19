package com.bonifacio.game_project.controllers;

import com.bonifacio.game_project.dtos.Response;
import com.bonifacio.game_project.services.classification.ClassificationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
