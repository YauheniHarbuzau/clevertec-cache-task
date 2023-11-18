package ru.clevertec.testdatautil;

import lombok.Builder;
import lombok.Data;
import ru.clevertec.dao.entity.Person;
import ru.clevertec.service.dto.InfoPersonDto;
import ru.clevertec.service.dto.PersonDto;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Data
@Builder(setterPrefix = "with")
public class PersonTestData {

    @Builder.Default
    private Long id = 100L;

    @Builder.Default
    private String firstName = "First Name";

    @Builder.Default
    private String lastName = "Last Name";

    @Builder.Default
    private String email = "email@gmail.com";

    @Builder.Default
    private OffsetDateTime createDate = OffsetDateTime.of(2023, 10, 30, 18, 0, 0, 0, ZoneOffset.ofHours(3));

    public Person buildPerson() {
        return new Person(id, firstName, lastName, email, createDate);
    }

    public PersonDto buildPersonDto() {
        return new PersonDto(id, firstName, lastName, email);
    }

    public InfoPersonDto buildInfoPersonDto() {
        return new InfoPersonDto(id, firstName, lastName, email, createDate);
    }
}
