package ru.clevertec.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import ru.clevertec.dao.entity.Person;
import ru.clevertec.service.dto.InfoPersonDto;
import ru.clevertec.service.dto.PersonDto;

/**
 * Конвертер для {@link Person}, {@link PersonDto} и {@link InfoPersonDto}
 */
@Component
@Mapper
public interface PersonMapper {

    @Mapping(target = "createDate", ignore = true)
    Person toPerson(PersonDto personDto);

    InfoPersonDto toInfoPersonDto(Person person);
}
