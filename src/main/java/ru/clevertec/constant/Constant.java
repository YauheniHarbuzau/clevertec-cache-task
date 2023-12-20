package ru.clevertec.constant;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import javax.servlet.http.HttpServletResponse;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * Класс для хранения констант
 */
public class Constant {

    public static final String PERSON_FIND_BY_ID_SQL = "SELECT * FROM persons WHERE id=?";
    public static final String PERSON_FIND_ALL_SQL = "SELECT * FROM persons";
    public static final String PERSON_FIND_ALL_SQL_WITH_PAGINATION = "SELECT * FROM persons LIMIT ? OFFSET ?";
    public static final String PERSON_CREATE_SQL = "INSERT INTO persons (id, first_name, last_name, email, create_date) VALUES (?, ?, ?, ?, NOW())";
    public static final String PERSON_UPDATE_SQL = "UPDATE persons SET first_name=?, last_name=?, email=? WHERE id=?";
    public static final String PERSON_DELETE_BY_ID_SQL = "DELETE FROM persons WHERE id=?";

    public static final int STATUS_GET_OK = HttpServletResponse.SC_OK;
    public static final int STATUS_SAVE_OK = HttpServletResponse.SC_CREATED;
    public static final int STATUS_DELETE_OK = HttpServletResponse.SC_NO_CONTENT;
    public static final int STATUS_NOT_FOUND = HttpServletResponse.SC_NOT_FOUND;
    public static final int STATUS_BAD_REQUEST = HttpServletResponse.SC_BAD_REQUEST;

    public final static DateTimeFormatter OFFSET_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSXXXXX");
    public final static ZoneOffset ZONE_OFFSET = ZoneOffset.ofHours(3);

    public static final Validator VALIDATOR = Validation.byDefaultProvider()
            .configure()
            .messageInterpolator(new ParameterMessageInterpolator())
            .buildValidatorFactory()
            .getValidator();

    public static final String ID = "ID";
    public static final String FIRST_NAME = "FIRST NAME";
    public static final String LAST_NAME = "LAST NAME";
    public static final String EMAIL = "EMAIL";
    public static final String CREATE_DATE = "CREATE DATE";

    public static final String PDF_EXTENSION = ".pdf";
    public static final String XML_EXTENSION = ".xml";

    public static final String PDF_TEMPLATE_PATH = "src/main/resources/pdftemplate/";
    public static final String PDF_TEMPLATE_NAME = "Clevertec_Template.pdf";
    public static final String PDF_TEMPLATE = PDF_TEMPLATE_PATH + PDF_TEMPLATE_NAME;

    public static final String PDF_PERSON_GET_BY_ID_PATH = "";
    public static final String PDF_PERSON_GET_BY_ID_NAME = "person_get_by_id" + PDF_EXTENSION;
    public static final String PDF_PERSON_GET_BY_ID = PDF_PERSON_GET_BY_ID_PATH + PDF_PERSON_GET_BY_ID_NAME;
    public static final String PDF_PERSON_GET_BY_ID_HEAD = "\n\n\n\n\nPERSON - GET BY " + ID;

    public static final String PDF_PERSON_GET_ALL_PATH = "";
    public static final String PDF_PERSON_GET_ALL_NAME = "person_get_all" + PDF_EXTENSION;
    public static final String PDF_PERSON_GET_ALL = PDF_PERSON_GET_ALL_PATH + PDF_PERSON_GET_ALL_NAME;
    public static final String PDF_PERSON_GET_ALL_HEAD = "\n\n\n\n\nPERSONS - GET ALL";

    public static final String PDF_FILE_NUMBER_PATTERN = "0000000000";
    public static final Long PDF_FILE_NUMBER_MAX = 9999999999L;
    public static final String PDF_TEXT_SEPARATOR = "**********************************************************************\n";

    public static final String XML_PERSONS_FILE_PATH = "";
    public static final String XML_PERSONS_FILE_NAME = "persons_response_info" + XML_EXTENSION;
    public static final String XML_PERSONS_FILE = XML_PERSONS_FILE_PATH + XML_PERSONS_FILE_NAME;
}
