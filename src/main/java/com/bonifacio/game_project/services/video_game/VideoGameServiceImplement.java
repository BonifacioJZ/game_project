package com.bonifacio.game_project.services.video_game;

import com.bonifacio.game_project.dtos.classification.ClassificationOutDto;
import com.bonifacio.game_project.dtos.gender.GenderOutDto;
import com.bonifacio.game_project.dtos.platform.PlataformOutDto;
import com.bonifacio.game_project.dtos.video_game.VideoGameDetails;
import com.bonifacio.game_project.dtos.video_game.VideoGameInDto;
import com.bonifacio.game_project.dtos.video_game.VideoGameOutDto;
import com.bonifacio.game_project.dtos.video_game.VideoGameUpdateDto;
import com.bonifacio.game_project.entities.Classification;
import com.bonifacio.game_project.entities.Gender;
import com.bonifacio.game_project.entities.Plataform;
import com.bonifacio.game_project.entities.VideoGame;
import com.bonifacio.game_project.mappers.classification.ClassificationMapper;
import com.bonifacio.game_project.mappers.gender.GenderMapper;
import com.bonifacio.game_project.mappers.platform.PlatformMapper;
import com.bonifacio.game_project.mappers.video_game.VideoGameMapper;
import com.bonifacio.game_project.repository.ClassificationRepository;
import com.bonifacio.game_project.repository.GenderRepository;
import com.bonifacio.game_project.repository.PlatformRepository;
import com.bonifacio.game_project.repository.VideoGameRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
@AllArgsConstructor
public class VideoGameServiceImplement implements VideoGameService {
    @Autowired
    private final VideoGameRepository videoGameRepository;
    @Autowired
    private final VideoGameMapper videoGameMapper;
    @Autowired
    private final ClassificationRepository classificationRepository;
    @Autowired
    private final ClassificationMapper classificationMapper;
    @Autowired
    private final GenderRepository genderRepository;
    @Autowired
    private final GenderMapper genderMapper;
    @Autowired
    private final PlatformRepository platformRepository;
    @Autowired
    private final PlatformMapper platformMapper;


    /**
     * Recupera todos los videojuegos disponibles.
     *
     * @return Una lista de objetos VideoGameOutDto que representan los videojuegos recuperados.
     */
    @Override
    public List<VideoGameOutDto> findAll() {
        // Recupera todos los videojuegos del repositorio
        var data = videoGameRepository.findAll();
        // Mapea cada objeto VideoGame a su correspondiente VideoGameOutDto y devuelve la lista resultante
        return data.stream().map(videoGameMapper::videoGameToVideoGameDto).toList();
    }


    /**
     * Guarda un nuevo videojuego en la base de datos.
     *
     * @param videoGameInDto El objeto VideoGameInDto que contiene la información del videojuego a guardar.
     * @return El objeto VideoGame que ha sido guardado en la base de datos, o null si videoGameInDto es nulo.
     */
    @Override
    public VideoGame save(VideoGameInDto videoGameInDto) {
        // Convierte el objeto VideoGameInDto en un objeto VideoGame utilizando el mapeador
        var data = videoGameMapper.videoGameInDtoToVideoGame(videoGameInDto);

        // Verifica si videoGameInDto es nulo y devuelve null si es así
        if (videoGameInDto == null) return null;

        // Recupera las clasificaciones, géneros y plataformas por sus IDs y asigna al videojuego
        var classifications = classificationRepository.findAllById(videoGameInDto.getClassifications());
        var gender = genderRepository.findAllById(videoGameInDto.getGenders());
        var platforms = platformRepository.findAllById(videoGameInDto.getPlatforms());
        data.setClassifications(classifications);
        data.setGenders(gender);
        data.setPlatforms(platforms);

        // Guarda el objeto VideoGame en el repositorio y devuelve el objeto guardado
        return videoGameRepository.save(data);
    }


    /**
     * Recupera los detalles de un videojuego por su ID.
     *
     * @param id El ID del videojuego que se desea recuperar.
     * @return Los detalles del videojuego, incluyendo clasificaciones, géneros y plataformas, o null si el videojuego no existe.
     */
    @Override
    public VideoGameDetails show(UUID id) {
        // Busca el videojuego en el repositorio por su ID
        var data = videoGameRepository.findById(id).orElse(null);
        // Si el videojuego no existe, retorna null
        if (data == null) return null;

        // Inicializa listas para almacenar clasificaciones, géneros y plataformas
        List<ClassificationOutDto> classifications = new ArrayList<>();
        List<GenderOutDto> genders = new ArrayList<>();
        List<PlataformOutDto> platforms = new ArrayList<>();

        // Si el videojuego tiene clasificaciones, mapea cada una a ClassificationOutDto y las agrega a la lista
        if (!data.getClassifications().isEmpty()) {
            classifications = data.getClassifications().stream().map(classificationMapper::classificationToClassificationOutDto).toList();
        }

        // Si el videojuego tiene géneros, mapea cada uno a GenderOutDto y los agrega a la lista
        if (!data.getGenders().isEmpty()) {
            genders = data.getGenders().stream().map(genderMapper::genderToGenderOutDto).toList();
        }

        // Si el videojuego tiene plataformas, mapea cada una a PlataformOutDto y las agrega a la lista
        if (!data.getPlatforms().isEmpty()) {
            platforms = data.getPlatforms().stream().map(platformMapper::plataformToPlataformOutDto).toList();
        }

        // Utiliza el mapeador para obtener los detalles del videojuego y retorna el objeto VideoGameDetails resultante
        return videoGameMapper.videoGameToVideoGameDetails(data, classifications, genders, platforms);
    }


    /**
     * Edita un videojuego existente en la base de datos.
     *
     * @param id             El ID del videojuego que se desea editar.
     * @param videoGameInDto El objeto VideoGameInDto que contiene la nueva información del videojuego.
     * @return Los detalles actualizados del videojuego, incluyendo clasificaciones, géneros y plataformas, o null si el videojuego no existe.
     */
    @Override
    public VideoGameDetails edit(UUID id, VideoGameInDto videoGameInDto) {
        // Busca el videojuego en el repositorio por su ID
        var old = videoGameRepository.findById(id).orElse(null);
        // Si el videojuego no existe, retorna null
        if (old == null) return null;

        // Actualiza el videojuego con la nueva información del VideoGameInDto utilizando el mapeador
        old = videoGameMapper.videoGameUpdate(old, videoGameInDto);

        // Inicializa listas para almacenar las nuevas clasificaciones, géneros y plataformas
        List<Classification> classifications = new ArrayList<>();
        List<Gender> genders = new ArrayList<>();
        List<Plataform> platforms = new ArrayList<>();

        // Si el VideoGameInDto contiene nuevas clasificaciones, las recupera del repositorio y las asigna al videojuego
        if (!videoGameInDto.getClassifications().isEmpty()) {
            classifications = classificationRepository.findAllById(videoGameInDto.getClassifications());
            old.setClassifications(classifications);
        }

        // Si el VideoGameInDto contiene nuevos géneros, los recupera del repositorio y los asigna al videojuego
        if (!videoGameInDto.getGenders().isEmpty()) {
            genders = genderRepository.findAllById(videoGameInDto.getGenders());
            old.setGenders(genders);
        }

        // Si el VideoGameInDto contiene nuevas plataformas, las recupera del repositorio y las asigna al videojuego
        if (!videoGameInDto.getClassifications().isEmpty()) {
            platforms = platformRepository.findAllById(videoGameInDto.getPlatforms());
            old.setPlatforms(platforms);
        }

        // Guarda el videojuego actualizado en el repositorio
        var data = videoGameRepository.save(old);

        // Mapea las clasificaciones, géneros y plataformas del videojuego actualizado a sus respectivos DTOs
        var classificationOutDtos = data.getClassifications().stream().map(classificationMapper::classificationToClassificationOutDto).toList();
        var gendersDto = data.getGenders().stream().map(genderMapper::genderToGenderOutDto).toList();
        var platform = data.getPlatforms().stream().map(platformMapper::plataformToPlataformOutDto).toList();

        // Utiliza el mapeador para obtener los detalles actualizados del videojuego y los retorna
        return videoGameMapper.videoGameToVideoGameDetails(data, classificationOutDtos, gendersDto, platform);
    }


    /**
     * Elimina un videojuego de la base de datos por su ID.
     *
     * @param id El ID del videojuego que se desea eliminar.
     */
    @Override
    public void delete(UUID id) {
        // Elimina el videojuego del repositorio por su ID
        videoGameRepository.deleteById(id);
    }

}
