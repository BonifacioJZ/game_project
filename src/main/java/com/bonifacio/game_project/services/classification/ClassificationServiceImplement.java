package com.bonifacio.game_project.services.classification;

import com.bonifacio.game_project.dtos.classification.ClassificationDetails;
import com.bonifacio.game_project.dtos.classification.ClassificationInDto;
import com.bonifacio.game_project.dtos.classification.ClassificationOutDto;
import com.bonifacio.game_project.dtos.video_game.VideoGameOutDto;
import com.bonifacio.game_project.entities.Classification;
import com.bonifacio.game_project.mappers.classification.ClassificationMapper;
import com.bonifacio.game_project.mappers.video_game.VideoGameMapper;
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
    @Autowired
    private final VideoGameMapper videoGameMapper;

    /**
     * Recupera todas las clasificaciones disponibles.
     *
     * @return Una lista de objetos ClassificationOutDto que representan las clasificaciones recuperadas.
     */
    @Override
    public List<ClassificationOutDto> findAll() {
        // Recupera todas las clasificaciones del repositorio y las mapea a ClassificationOutDto
        var data = classificationRepository.findAll();
        return data.stream().map(classificationMapper::classificationToClassificationOutDto).toList();
    }


    /**
     * Guarda una nueva clasificación en la base de datos.
     *
     * @param classificationInDto El objeto ClassificationInDto que contiene la información de la clasificación a guardar.
     * @return El objeto Classification que representa la clasificación guardada en la base de datos, o null si classificationInDto es nulo o si no se encuentra el sistema de clasificación asociado.
     */
    @Override
    public Classification save(ClassificationInDto classificationInDto) {
        // Convierte el objeto ClassificationInDto en un objeto Classification utilizando el mapeador
        var data = classificationMapper.classificationInDtoToClassification(classificationInDto);
        // Verifica si classificationInDto es nulo y devuelve null si es así
        if (data == null) return null;

        // Busca el sistema de clasificación asociado por su ID
        var cs = csRepository.findById(classificationInDto.getClassification_id());
        // Si no se encuentra el sistema de clasificación asociado, retorna null
        if (cs.isEmpty()) return null;

        // Establece el sistema de clasificación asociado en la clasificación
        data.setClassificationSystem(cs.get());

        // Guarda la clasificación en el repositorio y devuelve el objeto Classification que representa la clasificación guardada
        return classificationRepository.save(data);
    }


    /**
     * Recupera los detalles de una clasificación por su ID.
     *
     * @param id El ID de la clasificación que se desea recuperar.
     * @return Los detalles de la clasificación, incluyendo los videojuegos asociados, o null si la clasificación no existe.
     */
    @Override
    public ClassificationDetails show(UUID id) {
        // Busca la clasificación en el repositorio por su ID
        var data = classificationRepository.findById(id).orElse(null);
        // Si la clasificación no existe, retorna null
        if (data == null) return null;

        // Inicializa una lista para almacenar los videojuegos asociados a la clasificación
        ArrayList<VideoGameOutDto> videoGameOutDtos = new ArrayList<>();

        // Si la clasificación tiene videojuegos asociados, los mapea a objetos VideoGameOutDto
        if (data.getVideoGames() != null) {
            data.getVideoGames().forEach(videoGame -> {
                videoGameOutDtos.add(videoGameMapper.videoGameToVideoGameDto(videoGame));
            });
        }

        // Utiliza el mapeador para obtener los detalles de la clasificación y los videojuegos asociados, y los retorna
        return classificationMapper.clasificationToClassificationDetails(data, videoGameOutDtos);
    }


    /**
     * Edita una clasificación existente en la base de datos.
     *
     * @param id             El ID de la clasificación que se desea editar.
     * @param classification El objeto ClassificationInDto que contiene la nueva información de la clasificación.
     * @return Los detalles de la clasificación editada, incluyendo los videojuegos asociados, o null si la clasificación no existe o si el sistema de clasificación asociado no se encuentra.
     */
    @Override
    public ClassificationDetails edit(UUID id, ClassificationInDto classification) {
        // Busca la clasificación en el repositorio por su ID
        var data = classificationRepository.findById(id);
        // Si la clasificación no existe, retorna null
        if (data.isEmpty()) return null;

        // Actualiza la clasificación con la nueva información utilizando el método classificationUpdate del mapeador
        var map = classificationMapper.classificationUpdate(classification, data.get());

        // Verifica si el ID del sistema de clasificación ha cambiado y actualiza el sistema de clasificación asociado si es necesario
        if (map.getClassificationSystem().getId() != classification.getClassification_id()) {
            var cs = csRepository.findById(classification.getClassification_id());
            // Si el sistema de clasificación asociado no se encuentra, retorna null
            if (cs.isEmpty()) return null;
            map.setClassificationSystem(cs.get());
        }

        // Guarda la clasificación actualizada en el repositorio
        var out = classificationRepository.save(map);

        // Inicializa una lista para almacenar los videojuegos asociados a la clasificación
        ArrayList<VideoGameOutDto> videoGameOutDtos = new ArrayList<>();
        // Si la clasificación tiene videojuegos asociados, los mapea a objetos VideoGameOutDto
        if (out.getVideoGames() != null) {
            out.getVideoGames().forEach(videoGame -> {
                videoGameOutDtos.add(videoGameMapper.videoGameToVideoGameDto(videoGame));
            });
        }

        // Utiliza el mapeador para obtener los detalles de la clasificación editada y los videojuegos asociados, y los retorna
        return classificationMapper.clasificationToClassificationDetails(out, videoGameOutDtos);
    }


    /**
     * Elimina una clasificación de la base de datos por su ID.
     *
     * @param id El ID de la clasificación que se desea eliminar.
     */
    @Override
    public void delete(UUID id) {
        // Elimina la clasificación del repositorio por su ID
        classificationRepository.deleteById(id);
    }

}
