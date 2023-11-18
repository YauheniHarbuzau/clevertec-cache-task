package ru.clevertec.service.mapper;

import org.junit.jupiter.api.Test;
import ru.clevertec.dao.entity.Person;
import ru.clevertec.service.dto.InfoPersonDto;
import ru.clevertec.service.dto.PersonDto;
import ru.clevertec.testdatautil.PersonTestData;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.isNull;

/**
 * Тестовый класс для {@link PersonMapper}
 */
class PersonMapperTest {

    private final PersonMapper mapper = new PersonMapperImpl();

    @Test
    void checkToPersonShouldReturnCorrectResult() {
        // given
        PersonDto mappingPersonDto = PersonTestData.builder()
                .build().buildPersonDto();

        // when
        Person actualPerson = mapper.toPerson(mappingPersonDto);

        // than
        assertThat(actualPerson)
                .hasFieldOrPropertyWithValue(Person.Fields.id, mappingPersonDto.getId())
                .hasFieldOrPropertyWithValue(Person.Fields.firstName, mappingPersonDto.getFirstName())
                .hasFieldOrPropertyWithValue(Person.Fields.lastName, mappingPersonDto.getLastName())
                .hasFieldOrPropertyWithValue(Person.Fields.email, mappingPersonDto.getEmail())
                .hasFieldOrPropertyWithValue(Person.Fields.createDate, isNull());
    }

    @Test
    void checkToInfoPersonDtoShouldReturnCorrectResult() {
        // given
        Person mappingPerson = PersonTestData.builder()
                .build().buildPerson();

        // when
        InfoPersonDto actualInfoPersonDto = mapper.toInfoPersonDto(mappingPerson);

        // than
        assertThat(actualInfoPersonDto)
                .hasFieldOrPropertyWithValue(InfoPersonDto.Fields.id, mappingPerson.getId())
                .hasFieldOrPropertyWithValue(InfoPersonDto.Fields.firstName, mappingPerson.getFirstName())
                .hasFieldOrPropertyWithValue(InfoPersonDto.Fields.lastName, mappingPerson.getLastName())
                .hasFieldOrPropertyWithValue(InfoPersonDto.Fields.email, mappingPerson.getEmail())
                .hasFieldOrPropertyWithValue(InfoPersonDto.Fields.createDate, mappingPerson.getCreateDate());
    }
}
