package com.bonifacio.game_project.services.classification_system;

import com.bonifacio.game_project.dtos.classification_system.CSInDto;
import com.bonifacio.game_project.dtos.classification_system.CSOutDto;
import com.bonifacio.game_project.entities.ClassificationSystem;
import com.bonifacio.game_project.mappers.classification_system.ClassificationSystemMapper;
import com.bonifacio.game_project.repository.ClassificationSystemRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class ClassificationSystemServiceImplement implements ClassificationSystemService{
    @Autowired
    private final ClassificationSystemRepository csRepository;
    @Autowired
    private final ClassificationSystemMapper csMapper;
    @Override
    public List<CSOutDto> findAll() {
        var data = csRepository.findAll();
        ArrayList<CSOutDto> outData = new ArrayList<>();
        data.forEach(d->{
            outData.add(csMapper.classificationSystemToCSOutDto(d));
        });
        return outData;
    }

    @Override
    public ClassificationSystem save(CSInDto csInDto) {
        var data = csMapper.csInDtoToClassificationSystem(csInDto);
        if(data == null) return null;
        return  csRepository.save(data);
    }

}
