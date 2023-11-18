package ru.clevertec.constant;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * Класс для хранения констант
 */
public class Constant {

    public static final String PERSON_FIND_BY_ID_SQL = "SELECT * FROM persons WHERE id=?";
    public static final String PERSON_FIND_ALL_SQL = "SELECT * FROM persons";
    public static final String PERSON_CREATE_SQL = "INSERT INTO persons (id, first_name, last_name, email, create_date) VALUES (?, ?, ?, ?, NOW())";
    public static final String PERSON_UPDATE_SQL = "UPDATE persons SET first_name=?, last_name=?, email=? WHERE id=?";
    public static final String PERSON_DELETE_BY_ID_SQL = "DELETE FROM persons WHERE id=?";

    public final static DateTimeFormatter OFFSET_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSXXXXX");
    public final static ZoneOffset ZONE_OFFSET = ZoneOffset.ofHours(3);

    public static final Validator VALIDATOR = Validation.byDefaultProvider()
            .configure()
            .messageInterpolator(new ParameterMessageInterpolator())
            .buildValidatorFactory()
            .getValidator();

    public static final String XML_PERSONS_FILE_PATH = "";
    public static final String XML_PERSONS_FILE_NAME = "persons-response-info.xml";
}
