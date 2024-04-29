package com.bonifacio.game_project.services.classification;

import com.bonifacio.game_project.dtos.classification.ClassificationDetails;
import com.bonifacio.game_project.dtos.classification.ClassificationInDto;
import com.bonifacio.game_project.dtos.classification.ClassificationOutDto;
import com.bonifacio.game_project.entities.Classification;
import com.bonifacio.game_project.mappers.classification.ClassificationMapper;
import com.bonifacio.game_project.repository.ClassificationRepository;
import com.bonifacio.game_project.repository.ClassificationSystemRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class ClassificationServiceImplement implements ClassificationService{
    @Autowired
    private final ClassificationRepository classificationRepository;
    @Autowired
    private final ClassificationMapper classificationMapper;
    @Autowired
    private  final ClassificationSystemRepository csRepository;

    @Override
    public List<ClassificationOutDto> findAll() {
        var data = classificationRepository.findAll();
        ArrayList<ClassificationOutDto> out = new ArrayList<>();
        data.forEach(d->{
            out.add(classificationMapper.classificationToClassificationOutDto(d));
        });
        return out;
    }

    @Override
    public Classification save(ClassificationInDto classificationInDto) {
        var data = classificationMapper.classificationInDtoToClassification(classificationInDto);
        if(data ==null) return null;
        var cs =csRepository.findById(classificationInDto.getClassification_id());
        if(cs.isEmpty()) return null;
        data.setClassificationSystem(cs.get());
        return classificationRepository.save(data);
    }

    @Override
    public ClassificationDetails show(UUID id) {
        var data = classificationRepository.findById(id);
        return data.map(classificationMapper::clasificationToClassificationDetails).orElse(null);
    }

    @Override
    public ClassificationDetails edit(UUID id, ClassificationInDto classification) {
        var data = classificationRepository.findById(id);

        if(data.isEmpty()) return null;

        var map = classificationMapper.classificationUpdate(classification,data.get());
        if(map == null) return null;

        if(map.getClassificationSystem().getId()!= classification.getClassification_id()){
            var cs = csRepository.findById(classification.getClassification_id());
            if(cs.isEmpty()) return null;
            map.setClassificationSystem(cs.get());
        }

        var out = classificationRepository.save(map);
        return  classificationMapper.clasificationToClassificationDetails(out);
    }

    @Override
    public void delete(UUID id) {
        classificationRepository.deleteById(id);
    }
}
