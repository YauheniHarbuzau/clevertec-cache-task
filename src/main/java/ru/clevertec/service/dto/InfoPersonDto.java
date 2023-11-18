package ru.clevertec.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import ru.clevertec.dao.entity.Person;

import java.time.OffsetDateTime;

/**
 * DTO для Пользователя {@link Person}
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldNameConstants
public class InfoPersonDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private OffsetDateTime createDate;
}
