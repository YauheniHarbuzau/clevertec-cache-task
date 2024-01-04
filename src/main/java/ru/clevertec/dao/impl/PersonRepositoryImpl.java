package ru.clevertec.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.clevertec.config.ConnectionConfig;
import ru.clevertec.dao.PersonRepository;
import ru.clevertec.dao.entity.Person;
import ru.clevertec.util.DateUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ru.clevertec.constant.Constant.PERSON_CREATE_SQL;
import static ru.clevertec.constant.Constant.PERSON_DELETE_BY_ID_SQL;
import static ru.clevertec.constant.Constant.PERSON_FIND_ALL_SQL;
import static ru.clevertec.constant.Constant.PERSON_FIND_ALL_SQL_WITH_PAGINATION;
import static ru.clevertec.constant.Constant.PERSON_FIND_BY_ID_SQL;
import static ru.clevertec.constant.Constant.PERSON_UPDATE_SQL;

/**
 * Имплементация репозитория для работы с Пользователями (Person)
 */
@Component
public class PersonRepositoryImpl implements PersonRepository {

    private final ConnectionConfig connection;

    @Autowired
    public PersonRepositoryImpl(ConnectionConfig connection) {
        this.connection = connection;
    }

    /**
     * Получение Пользователя по ID
     *
     * @param id идентификационный номер Пользователя
     * @return Пользователь
     */
    @Override
    public Optional<Person> findById(Long id) {
        try (var preparedStatement = connection.getConnection().prepareStatement(PERSON_FIND_BY_ID_SQL)) {
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
        try (var statement = connection.getConnection().createStatement()) {
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
     * Получение всех Пользователей с пагинацией
     *
     * @param pageSize   количество элементов на странице
     * @param pageNumber номер страницы
     * @return список Пользователей
     */
    @Override
    public List<Person> findAll(int pageSize, int pageNumber) {
        try (var preparedStatement = connection.getConnection().prepareStatement(PERSON_FIND_ALL_SQL_WITH_PAGINATION)) {
            List<Person> persons = new ArrayList<>(0);

            preparedStatement.setInt(1, pageSize);
            preparedStatement.setInt(2, (pageNumber - 1) * pageSize);

            var resultSet = preparedStatement.executeQuery();
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
        try (var preparedStatement = connection.getConnection().prepareStatement(PERSON_CREATE_SQL)) {
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
        try (var preparedStatement = connection.getConnection().prepareStatement(PERSON_UPDATE_SQL)) {
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
        try (var preparedStatement = connection.getConnection().prepareStatement(PERSON_DELETE_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
