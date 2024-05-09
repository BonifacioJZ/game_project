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

    /**
     * Recupera todos los sistemas de clasificación disponibles.
     *
     * @return Una lista de objetos CSOutDto que representan los sistemas de clasificación recuperados.
     */
    @Override
    public List<CSOutDto> findAll() {
        // Recupera todos los sistemas de clasificación del repositorio y los mapea a CSOutDto
        var data = csRepository.findAll();
        return data.stream().map(csMapper::classificationSystemToCSOutDto).toList();
    }

    /**
     * Guarda un nuevo sistema de clasificación en la base de datos.
     *
     * @param csInDto El objeto CSInDto que contiene la información del sistema de clasificación a guardar.
     * @return El objeto ClassificationSystem que representa el sistema de clasificación guardado en la base de datos, o null si csInDto es nulo.
     */
    @Override
    public ClassificationSystem save(CSInDto csInDto) {
        // Convierte el objeto CSInDto en un objeto ClassificationSystem utilizando el mapeador
        var data = csMapper.csInDtoToClassificationSystem(csInDto);
        // Verifica si csInDto es nulo y devuelve null si es así
        if (data == null) return null;
        // Guarda el sistema de clasificación en el repositorio y devuelve el objeto ClassificationSystem que representa el sistema guardado
        return csRepository.save(data);
    }

    /**
     * Recupera los detalles de un sistema de clasificación por su ID.
     *
     * @param id El ID del sistema de clasificación que se desea recuperar.
     * @return Los detalles del sistema de clasificación, incluyendo las clasificaciones asociadas, o null si el sistema de clasificación no existe.
     */
    @Override
    public CSDetails show(UUID id) {
        // Busca el sistema de clasificación en el repositorio por su ID
        var data = csRepository.findById(id);
        // Si el sistema de clasificación no existe, retorna null
        if (data.isEmpty()) return null;

        // Inicializa una lista para almacenar las clasificaciones asociadas al sistema de clasificación
        ArrayList<ClassificationOutDto> outDtos = new ArrayList<>();

        // Si el sistema de clasificación tiene clasificaciones asociadas, las mapea a objetos ClassificationOutDto
        if (data.get().getClassificationList() != null) {
            data.get().getClassificationList().forEach(classification -> {
                outDtos.add(classificationMapper.classificationToClassificationOutDto(classification));
            });
        }

        // Utiliza el mapeador para obtener los detalles del sistema de clasificación y las clasificaciones asociadas, y los retorna
        return csMapper.classificationSystemToCSDetails(data.get(), outDtos);
    }


    /**
     * Edita un sistema de clasificación existente en la base de datos.
     *
     * @param id      El ID del sistema de clasificación que se desea editar.
     * @param csInDto El objeto CSInDto que contiene la nueva información del sistema de clasificación.
     * @return El objeto CSOutDto que representa el sistema de clasificación editado, o null si el sistema de clasificación no existe.
     */
    @Override
    public CSOutDto edit(UUID id, CSInDto csInDto) {
        // Busca el sistema de clasificación en el repositorio por su ID
        var data = csRepository.findById(id);
        // Si el sistema de clasificación no existe, retorna null
        if (data.isEmpty()) return null;

        // Actualiza el sistema de clasificación con la nueva información utilizando el método updateCS del mapeador
        var cs = csMapper.updateCS(data.get(), csInDto);

        // Guarda el sistema de clasificación actualizado en el repositorio y devuelve el objeto CSOutDto que representa el sistema editado
        return csMapper.classificationSystemToCSOutDto(csRepository.save(cs));
    }

    /**
     * Elimina un sistema de clasificación de la base de datos por su ID.
     *
     * @param id El ID del sistema de clasificación que se desea eliminar.
     */
    @Override
    public void delete(UUID id) {
        // Elimina el sistema de clasificación del repositorio por su ID
        csRepository.deleteById(id);
    }


}
