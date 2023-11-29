package ru.clevertec.service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import ru.clevertec.dao.entity.Person;

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
