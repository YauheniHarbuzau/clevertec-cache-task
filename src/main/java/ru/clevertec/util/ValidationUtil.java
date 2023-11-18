package ru.clevertec.util;

import jakarta.validation.ConstraintViolation;
import lombok.experimental.UtilityClass;
import ru.clevertec.service.dto.PersonDto;

import java.util.Set;

import static ru.clevertec.constant.Constant.VALIDATOR;

/**
 * Утилитарный класс для валидации {@link PersonDto}
 */
@UtilityClass
public class ValidationUtil {

    public boolean isValid(PersonDto personDto) {
        boolean result = true;
        Set<ConstraintViolation<PersonDto>> constrs = VALIDATOR.validate(personDto);

        for (ConstraintViolation<PersonDto> constr : constrs) {
            if (constr.getInvalidValue() != null) {
                result = false;
            }

            StringBuilder stringBuilder = new StringBuilder("property: ");
            stringBuilder.append(constr.getPropertyPath())
                         .append(", value: ")
                         .append(constr.getInvalidValue())
                         .append(", message: ")
                         .append(constr.getMessage());

            System.out.println(stringBuilder);
        }
        return result;
    }
}
