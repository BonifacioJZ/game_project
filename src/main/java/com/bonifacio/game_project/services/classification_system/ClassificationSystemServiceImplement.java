package com.bonifacio.game_project.services.classification_system;

import com.bonifacio.game_project.dtos.classification.ClassificationOutDto;
import com.bonifacio.game_project.dtos.classification_system.CSDetails;
import com.bonifacio.game_project.dtos.classification_system.CSInDto;
import com.bonifacio.game_project.dtos.classification_system.CSOutDto;
import com.bonifacio.game_project.entities.ClassificationSystem;
import com.bonifacio.game_project.mappers.classification.ClassificationMapper;
import com.bonifacio.game_project.mappers.classification_system.ClassificationSystemMapper;
import com.bonifacio.game_project.repository.ClassificationSystemRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@AllArgsConstructor
@Component
public class ClassificationSystemServiceImplement implements ClassificationSystemService{
    @Autowired
    private final ClassificationSystemRepository csRepository;
    @Autowired
    private final ClassificationSystemMapper csMapper;
    @Autowired
    private final ClassificationMapper classificationMapper;

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

    @Override
    public CSDetails show(UUID id) {
        var data = csRepository.findById(id);
        if(data.isEmpty()) return null;
        ArrayList<ClassificationOutDto> outDtos = new ArrayList<>();
        if(data.get().getClassificationList()!=null){
            data.get().getClassificationList().forEach(classification -> {
                outDtos.add(classificationMapper.classificationToClassificationOutDto(classification));
            });
        }
        return csMapper.classificationSystemToCSDetails(data.get(),outDtos);
    }

    @Override
    public CSOutDto edit(UUID id, CSInDto csInDto) {
        var data = csRepository.findById(id);
        if(data.isEmpty()) return null;
        var cs = csMapper.updateCS(data.get(),csInDto);
        return csMapper.classificationSystemToCSOutDto(csRepository.save(cs));

    }

    @Override
    public void delete(UUID id) {
        csRepository.deleteById(id);
    }

}
