package com.bonifacio.game_project.services.platform;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

import com.bonifacio.game_project.dtos.platform.PlatformDetailDto;
import com.bonifacio.game_project.dtos.video_game.VideoGameOutDto;
import com.bonifacio.game_project.mappers.video_game.VideoGameMapper;
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
    private final VideoGameMapper videoGameMapper;

    @Autowired
    public PlatformServiceImplement(PlatformMapper plataformMapper, PlatformRepository plataformRepository,VideoGameMapper videoGameMapper) {
        this.plataformMapper = plataformMapper;
        this.plataformRepository = plataformRepository;
        this.videoGameMapper =videoGameMapper;
    }

    /**
     * Guarda una nueva plataforma en la base de datos.
     *
     * @param plataformInDto El objeto PlataformInDto que contiene la información de la plataforma a guardar.
     * @return La plataforma que ha sido guardada en la base de datos, o null si plataformInDto es nulo.
     */
    @Override
    public Plataform save(PlataformInDto plataformInDto) {
        // Convierte el objeto PlataformInDto en un objeto Plataform utilizando el mapeador
        var plataform = plataformMapper.plataformInDtoToPlataform(plataformInDto);
        // Verifica si plataformInDto es nulo y devuelve null si es así
        if (plataform == null) {
            return null;
        }
        // Guarda la plataforma en el repositorio y devuelve la plataforma guardada
        return plataformRepository.save(plataform);
    }


    /**
     * Recupera todas las plataformas disponibles.
     *
     * @return Una lista de objetos PlataformOutDto que representan las plataformas recuperadas.
     */
    @Override
    public List<PlataformOutDto> findAll() {
        // Recupera todas las plataformas del repositorio y las mapea a PlataformOutDto
        var plataforms = plataformRepository.findAll();
        return plataforms.stream().map(plataformMapper::plataformToPlataformOutDto).toList();
    }


    /**
     * Recupera los detalles de una plataforma por su ID.
     *
     * @param id El ID de la plataforma que se desea recuperar.
     * @return Los detalles de la plataforma, incluyendo los videojuegos asociados, o null si la plataforma no existe.
     */
    @Override
    public PlatformDetailDto findById(UUID id) {
        // Busca la plataforma en el repositorio por su ID
        var platform = plataformRepository.findById(id).orElse(null);
        // Si la plataforma no existe, retorna null
        if (platform == null) return null;

        // Inicializa una lista para almacenar los videojuegos asociados a la plataforma
        List<VideoGameOutDto> videoGames = new ArrayList<>();

        // Si la plataforma tiene videojuegos asociados, los mapea a objetos VideoGameOutDto
        if (!platform.getVideoGames().isEmpty()) {
            videoGames = platform.getVideoGames().stream().map(videoGameMapper::videoGameToVideoGameDto).toList();
        }

        // Utiliza el mapeador para obtener los detalles de la plataforma y los videojuegos asociados, y los retorna
        return plataformMapper.platformToPlatFormDetailDto(platform, videoGames);
    }


    /**
     * Actualiza los detalles de una plataforma existente en la base de datos.
     *
     * @param id              El ID de la plataforma que se desea actualizar.
     * @param plataformInDto  El objeto PlataformInDto que contiene la nueva información de la plataforma.
     * @return Los detalles actualizados de la plataforma, incluyendo los videojuegos asociados, o null si la plataforma no existe.
     */
    @Override
    public PlatformDetailDto update(UUID id, PlataformInDto plataformInDto) {
        // Busca la plataforma en el repositorio por su ID
        var platform = plataformRepository.findById(id);
        // Si la plataforma no existe, retorna null
        if (platform.isEmpty()) return null;

        // Actualiza los campos de la plataforma con la nueva información del PlataformInDto, si está presente
        platform.get().setName((plataformInDto.getName() == null) ? platform.get().getName() : plataformInDto.getName());
        platform.get().setDescription((plataformInDto.getDescription() == null) ? platform.get().getDescription() : plataformInDto.getDescription());
        platform.get().setGuardName((plataformInDto.getGuardName() == null) ? platform.get().getGuardName() : plataformInDto.getGuardName());
        platform.get().setRealiseDate((plataformInDto.getRealiceDate() == null) ? platform.get().getRealiseDate() : plataformInDto.getRealiceDate());

        // Guarda la plataforma actualizada en el repositorio
        var updatedPlatform = plataformRepository.save(platform.get());

        // Inicializa una lista para almacenar los videojuegos asociados a la plataforma
        List<VideoGameOutDto> videoGames = new ArrayList<>();
        // Si la plataforma actualizada tiene videojuegos asociados, los mapea a objetos VideoGameOutDto
        if (!updatedPlatform.getVideoGames().isEmpty()) {
            videoGames = updatedPlatform.getVideoGames().stream().map(videoGameMapper::videoGameToVideoGameDto).toList();
        }

        // Utiliza el mapeador para obtener los detalles actualizados de la plataforma y los videojuegos asociados, y los retorna
        return plataformMapper.platformToPlatFormDetailDto(updatedPlatform, videoGames);
    }


    /**
     * Elimina una plataforma de la base de datos por su ID.
     *
     * @param id El ID de la plataforma que se desea eliminar.
     */
    @Override
    public void delete(UUID id) {
        // Elimina la plataforma del repositorio por su ID
        plataformRepository.deleteById(id);
    }


}
