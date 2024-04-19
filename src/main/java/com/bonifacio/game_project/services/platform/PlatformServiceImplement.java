package com.bonifacio.game_project.services.platform;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

import com.bonifacio.game_project.dtos.platform.PlatformDetailDto;
import org.springframework.beans.factory.annotation.Autowired;


import com.bonifacio.game_project.dtos.platform.PlataformInDto;
import com.bonifacio.game_project.dtos.platform.PlataformOutDto;
import com.bonifacio.game_project.entities.Plataform;
import com.bonifacio.game_project.mappers.platform.PlatformMapper;
import com.bonifacio.game_project.repository.PlatformRepository;
import org.springframework.stereotype.Service;

@Service
public class PlatformServiceImplement implements PlataformService {
    

    private final PlatformMapper plataformMapper;
    private final PlatformRepository plataformRepository;

    @Autowired
    public PlatformServiceImplement(PlatformMapper plataformMapper, PlatformRepository plataformRepository) {
        this.plataformMapper = plataformMapper;
        this.plataformRepository = plataformRepository;
    }

    @Override
    public Plataform save(PlataformInDto plataformInDto) {
        var plataform = plataformMapper.plataformInDtoToPlataform(plataformInDto);
        if(plataform == null){
            return null;
        }
        return plataformRepository.save(plataform);
    }

    @Override
    public List<PlataformOutDto> findAll() {
        var plataforms = plataformRepository.findAll();
        ArrayList<PlataformOutDto> out = new ArrayList<>();
        plataforms.forEach(p->{
            out.add(plataformMapper.plataformToPlataformOutDto(p));
        });
        return out;
    }

    @Override
    public PlatformDetailDto findById(UUID id) {
        if(id== null)return null;

        var platform = plataformRepository.findById(id);
        return platform.map(plataformMapper::platformToPlatFormDetailDto).orElse(null);
    }

    @Override
    public PlatformDetailDto update(UUID id, PlataformInDto plataformInDto) {
        var platform = plataformRepository.findById(id);
        if(platform.isEmpty()) return null;
        platform.get()
                .setName(((plataformInDto.getName()==null))
                        ?platform.get().getName():plataformInDto.getName());

        platform.get()
                .setDescription((plataformInDto.getDescription()==null)
                        ?platform.get().getDescription():plataformInDto.getDescription());

        platform.get().setGuardName((plataformInDto.getGuardName()==null)
        ?platform.get().getGuardName():plataformInDto.getGuardName());

        platform.get()
                .setRealiseDate((plataformInDto.getRealiceDate()==null)
                        ?platform.get().getRealiseDate():plataformInDto.getRealiceDate());

        var out = plataformRepository.save(platform.get());
        return plataformMapper.platformToPlatFormDetailDto(out);
    }

    @Override
    public void delete(UUID id) {
        plataformRepository.deleteById(id);
    }

}
