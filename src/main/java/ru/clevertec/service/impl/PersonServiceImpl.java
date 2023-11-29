package ru.clevertec.service.impl;

import ru.clevertec.dao.PersonRepository;
import ru.clevertec.dao.entity.Person;
import ru.clevertec.dao.impl.PersonRepositoryImpl;
import ru.clevertec.exception.EntityNotFoundException;
import ru.clevertec.service.PersonService;
import ru.clevertec.service.dto.InfoPersonDto;
import ru.clevertec.service.dto.PersonDto;
import ru.clevertec.service.mapper.PersonMapper;
import ru.clevertec.service.mapper.PersonMapperImpl;

import java.util.List;

/**
 * Имплементация сервиса для работы с Person
 */
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;
    private final PersonMapper mapper;

    public PersonServiceImpl() {
        this.repository = new PersonRepositoryImpl();
        this.mapper = new PersonMapperImpl();
    }

    /**
     * Получение Пользователя по ID
     *
     * @param id идентификационный номер Пользователя
     * @return DTO Пользователя
     */
    @Override
    public InfoPersonDto getById(Long id) {
        return repository.findById(id)
                .map(mapper::toInfoPersonDto)
                .orElseThrow(() -> new EntityNotFoundException(Person.class, id));
    }

    /**
     * Получение всех Пользователей
     *
     * @return список DTO Пользователей
     */
    @Override
    public List<InfoPersonDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toInfoPersonDto)
                .toList();
    }

    /**
     * Создание нового Пользователя
     *
     * @param personDto DTO Пользователя
     */
    private void create(PersonDto personDto) {
        repository.create(mapper.toPerson(personDto));
    }

    /**
     * Обновление существующего Пользователя
     *
     * @param personDto DTO Пользователя
     */
    private void update(PersonDto personDto) {
        repository.update(mapper.toPerson(personDto));
    }

    /**
     * Сохранение (создание или обновление) Пользователя
     *
     * @param personDto DTO Пользователя
     */
    @Override
    public void save(PersonDto personDto) {
        var ids = getAll().stream().map(InfoPersonDto::getId).toList();

        if (ids.contains(personDto.getId())) {
            update(personDto);
        } else {
            create(personDto);
        }
    }

    /**
     * Удаление Пользователя по ID
     *
     * @param id идентификационный номер Пользователя
     */
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
