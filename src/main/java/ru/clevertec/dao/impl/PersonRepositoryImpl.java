package ru.clevertec.dao.impl;

import ru.clevertec.dao.PersonRepository;
import ru.clevertec.dao.entity.Person;
import ru.clevertec.util.ConnectionUtil;
import ru.clevertec.util.DateUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ru.clevertec.constant.Constant.PERSON_CREATE_SQL;
import static ru.clevertec.constant.Constant.PERSON_DELETE_BY_ID_SQL;
import static ru.clevertec.constant.Constant.PERSON_FIND_ALL_SQL;
import static ru.clevertec.constant.Constant.PERSON_FIND_BY_ID_SQL;
import static ru.clevertec.constant.Constant.PERSON_UPDATE_SQL;

/**
 * Имплементация репозитория для работы с Пользователями (Person)
 */
public class PersonRepositoryImpl implements PersonRepository {

    private final Connection connection;

    public PersonRepositoryImpl() {
        this.connection = ConnectionUtil.open();
    }

    /**
     * Получение Пользователя по ID
     *
     * @param id идентификационный номер Пользователя
     * @return Пользователь
     */
    @Override
    public Optional<Person> findById(Long id) {
        try (var preparedStatement = connection.prepareStatement(PERSON_FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);

            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.ofNullable(Person.builder()
                        .id(resultSet.getLong("id"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .email(resultSet.getString("email"))
                        .createDate(DateUtil.getOffsetDateTime(resultSet.getTimestamp("create_date")))
                        .build());
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return Optional.empty();
    }

    /**
     * Получение всех Пользователей
     *
     * @return список Пользователей
     */
    @Override
    public List<Person> findAll() {
        try (var statement = connection.createStatement()) {
            List<Person> persons = new ArrayList<>(0);

            var resultSet = statement.executeQuery(PERSON_FIND_ALL_SQL);
            while (resultSet.next()) {
                persons.add(Person.builder()
                        .id(resultSet.getLong("id"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .email(resultSet.getString("email"))
                        .createDate(DateUtil.getOffsetDateTime(resultSet.getTimestamp("create_date")))
                        .build());
            }
            return persons;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Создание нового Пользователя
     *
     * @param person Пользователь
     * @return Пользователь
     */
    @Override
    public Person create(Person person) {
        try (var preparedStatement = connection.prepareStatement(PERSON_CREATE_SQL)) {
            preparedStatement.setLong(1, person.getId());
            preparedStatement.setString(2, person.getFirstName());
            preparedStatement.setString(3, person.getLastName());
            preparedStatement.setString(4, person.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return person;
    }

    /**
     * Обновление существующего Пользователя
     *
     * @param person Пользователь
     * @return Пользователь
     */
    @Override
    public Person update(Person person) {
        try (var preparedStatement = connection.prepareStatement(PERSON_UPDATE_SQL)) {
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.setLong(4, person.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return person;
    }

    /**
     * Удаление Пользователя по ID
     *
     * @param id идентификационный номер Пользователя
     */
    @Override
    public void deleteById(Long id) {
        try (var preparedStatement = connection.prepareStatement(PERSON_DELETE_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
