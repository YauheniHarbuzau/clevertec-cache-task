package ru.clevertec.service;

import ru.clevertec.service.dto.InfoPersonDto;
import ru.clevertec.service.dto.PersonDto;

import java.util.List;

/**
 * Сервис для работы с Person
 */
public interface PersonService {

    InfoPersonDto getById(Long id);

    List<InfoPersonDto> getAll();

    List<InfoPersonDto> getAll(int pageSize, int pageNumber);

    void save(PersonDto personDto);

    void deleteById(Long id);
}
