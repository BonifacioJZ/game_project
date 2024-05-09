package com.bonifacio.game_project.services.gender;

import com.bonifacio.game_project.dtos.gender.GenderDetails;
import com.bonifacio.game_project.dtos.gender.GenderInDto;
import com.bonifacio.game_project.dtos.gender.GenderOutDto;
import com.bonifacio.game_project.dtos.video_game.VideoGameOutDto;
import com.bonifacio.game_project.mappers.gender.GenderMapper;
import com.bonifacio.game_project.mappers.video_game.VideoGameMapper;
import com.bonifacio.game_project.repository.GenderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Qualifier("genderService")
@AllArgsConstructor
public class GenderServiceImplement implements GenderService{

    @Autowired
    private final GenderRepository genderRepository;
    @Autowired
    private final GenderMapper genderMapper;
    @Autowired
    private  final VideoGameMapper videoGameMapper;

    /**
     * Recupera todos los géneros disponibles.
     *
     * @return Una lista de objetos GenderOutDto que representan los géneros recuperados.
     */
    @Override
    public List<GenderOutDto> findAll() {
        // Recupera todos los géneros del repositorio y los mapea a GenderOutDto
        var data = genderRepository.findAll();
        return data.stream().map(genderMapper::genderToGenderOutDto).toList();
    }


    /**
     * Guarda un nuevo género en la base de datos.
     *
     * @param genderInDto El objeto GenderInDto que contiene la información del género a guardar.
     * @return El objeto GenderOutDto que representa el género guardado en la base de datos, o null si genderInDto es nulo.
     */
    @Override
    public GenderOutDto save(GenderInDto genderInDto) {
        // Convierte el objeto GenderInDto en un objeto Gender utilizando el mapeador
        var data = genderMapper.genderInDtoToGender(genderInDto);
        // Verifica si genderInDto es nulo y devuelve null si es así
        if (data == null) return null;
        // Guarda el género en el repositorio y devuelve el objeto GenderOutDto que representa el género guardado
        return genderMapper.genderToGenderOutDto(genderRepository.save(data));
    }


    /**
     * Recupera los detalles de un género por su ID.
     *
     * @param id El ID del género que se desea recuperar.
     * @return Los detalles del género, incluyendo los videojuegos asociados, o null si el género no existe.
     */
    @Override
    public GenderDetails show(UUID id) {
        // Busca el género en el repositorio por su ID
        var data = genderRepository.findById(id).orElse(null);
        // Si el género no existe, retorna null
        if (data == null) return null;

        // Inicializa una lista para almacenar los videojuegos asociados al género
        List<VideoGameOutDto> videoGames = new ArrayList<>();

        // Si el género tiene videojuegos asociados, los mapea a objetos VideoGameOutDto
        if (!data.getVideoGames().isEmpty()) {
            videoGames = data.getVideoGames().stream().map(videoGameMapper::videoGameToVideoGameDto).toList();
        }

        // Utiliza el mapeador para obtener los detalles del género y los videojuegos asociados, y los retorna
        return genderMapper.genderToGenderDetails(data, videoGames);
    }

    /**
     * Edita un género existente en la base de datos.
     *
     * @param id     El ID del género que se desea editar.
     * @param gender El objeto GenderInDto que contiene la nueva información del género.
     * @return El objeto GenderOutDto que representa el género editado, o null si el género no existe.
     */
    @Override
    public GenderOutDto edit(UUID id, GenderInDto gender) {
        // Busca el género en el repositorio por su ID
        var oldGender = genderRepository.findById(id).orElse(null);
        // Si el género no existe, retorna null
        if (oldGender == null) return null;

        // Actualiza el género con la nueva información utilizando el método updateGender del mapeador
        var data = genderMapper.updateGender(oldGender, gender);

        // Guarda el género actualizado en el repositorio y devuelve el objeto GenderOutDto que representa el género editado
        return genderMapper.genderToGenderOutDto(genderRepository.save(data));
    }


    /**
     * Elimina un género de la base de datos por su ID.
     *
     * @param id El ID del género que se desea eliminar.
     */
    @Override
    public void delete(UUID id) {
        // Elimina el género del repositorio por su ID
        genderRepository.deleteById(id);
    }



}
