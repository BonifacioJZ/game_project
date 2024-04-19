package com.bonifacio.game_project.services.classification;

import com.bonifacio.game_project.dtos.classification.ClassificationInDto;
import com.bonifacio.game_project.dtos.classification.ClassificationOutDto;
import com.bonifacio.game_project.entities.Classification;
import com.bonifacio.game_project.mappers.classification.ClassificationMapper;
import com.bonifacio.game_project.repository.ClassificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class ClassificationServiceImplement implements ClassificationService{
    @Autowired
    private final ClassificationRepository classificationRepository;
    @Autowired
    private final ClassificationMapper classificationMapper;
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
        return classificationRepository.save(data);
    }
}
