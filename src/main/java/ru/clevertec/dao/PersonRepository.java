package ru.clevertec.dao;

import ru.clevertec.dao.entity.Person;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для работы с Пользователями (Person)
 */
public interface PersonRepository {

    Optional<Person> findById(Long id);

    List<Person> findAll();

    Person create(Person person);

    Person update(Person person);

    void deleteById(Long id);
}
