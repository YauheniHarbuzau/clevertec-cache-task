package ru.clevertec.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.time.OffsetDateTime;

/**
 * Сущность Пользователь (Person)
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldNameConstants
public class Person {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private OffsetDateTime createDate;
}
