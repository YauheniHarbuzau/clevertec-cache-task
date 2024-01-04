package ru.clevertec.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import ru.clevertec.dao.entity.Person;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO для Пользователя {@link Person}
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldNameConstants
public class PersonDto {

    @NotNull(message = "ID cannot be null")
    private Long id;

    @Size(min = 1, max = 50, message = "Invalid first name")
    private String firstName;

    @Size(min = 1, max = 50, message = "Invalid last name")
    private String lastName;

    @Email(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "Invalid email")
    private String email;
}
