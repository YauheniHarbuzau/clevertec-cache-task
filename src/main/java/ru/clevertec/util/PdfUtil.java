package ru.clevertec.util;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import ru.clevertec.service.dto.InfoPersonDto;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static ru.clevertec.constant.Constant.CREATE_DATE;
import static ru.clevertec.constant.Constant.EMAIL;
import static ru.clevertec.constant.Constant.FIRST_NAME;
import static ru.clevertec.constant.Constant.ID;
import static ru.clevertec.constant.Constant.LAST_NAME;
import static ru.clevertec.constant.Constant.PDF_EXTENSION;
import static ru.clevertec.constant.Constant.PDF_FILE_NUMBER_MAX;
import static ru.clevertec.constant.Constant.PDF_FILE_NUMBER_PATTERN;
import static ru.clevertec.constant.Constant.PDF_PERSON_GET_ALL_HEAD;
import static ru.clevertec.constant.Constant.PDF_PERSON_GET_BY_ID_HEAD;
import static ru.clevertec.constant.Constant.PDF_TEMPLATE;
import static ru.clevertec.constant.Constant.PDF_TEXT_SEPARATOR;

/**
 * Утилитарный класс для для создания pdf-файлов
 */
@UtilityClass
public class PdfUtil {

    private Long fileNumber = 1L;

    public void createPdf(InfoPersonDto person, String fileName, boolean fileNumber) {
        var document = new Document(getPdfDocument(fileName, fileNumber));

        document.add(putCell(PDF_PERSON_GET_BY_ID_HEAD, TextAlignment.CENTER));
        document.add(putCell(PDF_TEXT_SEPARATOR, TextAlignment.CENTER));
        document.add(personTable(person));
        document.add(putCell(PDF_TEXT_SEPARATOR, TextAlignment.CENTER));
        document.close();
    }

    public void createPdf(List<InfoPersonDto> persons, String fileName, boolean fileNumber) {
        var document = new Document(getPdfDocument(fileName, fileNumber));

        document.add(putCell(PDF_PERSON_GET_ALL_HEAD, TextAlignment.CENTER));
        document.add(putCell(PDF_TEXT_SEPARATOR, TextAlignment.CENTER));
        for (Table table : allPersonsTables(persons)) {
            document.add(table);
            document.add(putCell(PDF_TEXT_SEPARATOR, TextAlignment.CENTER));
        }
        document.close();
    }

    @SneakyThrows
    private PdfReader getPdfReader() {
        return new PdfReader(PDF_TEMPLATE);
    }

    @SneakyThrows
    private PdfWriter getPdfWriter(String fileName, boolean fileNumber) {
        return fileNumber ?
                new PdfWriter(getDocumentNameWithNumber(fileName)) :
                new PdfWriter(fileName);
    }

    private PdfDocument getPdfDocument(String fileName, boolean fileNumber) {
        return new PdfDocument(getPdfReader(), getPdfWriter(fileName, fileNumber));
    }

    private String getDocumentNumber() {
        fileNumber = fileNumber > PDF_FILE_NUMBER_MAX ? 1L : fileNumber;
        String documentNumber = new DecimalFormat(PDF_FILE_NUMBER_PATTERN).format(fileNumber);
        fileNumber++;
        return documentNumber;
    }

    private String getDocumentNameWithNumber(String fileName) {
        return new StringBuilder(fileName)
                .insert(fileName.indexOf(PDF_EXTENSION), "-" + getDocumentNumber())
                .toString();
    }

    private Table personTable(InfoPersonDto person) {
        var table = new Table(new float[]{200F, 200F});
        table.setHorizontalAlignment(HorizontalAlignment.CENTER);

        table.addCell(putCell(ID, TextAlignment.LEFT));
        table.addCell(putCell(String.valueOf(person.getId()), TextAlignment.RIGHT));

        table.addCell(putCell(FIRST_NAME, TextAlignment.LEFT));
        table.addCell(putCell(person.getFirstName(), TextAlignment.RIGHT));

        table.addCell(putCell(LAST_NAME, TextAlignment.LEFT));
        table.addCell(putCell(person.getLastName(), TextAlignment.RIGHT));

        table.addCell(putCell(EMAIL, TextAlignment.LEFT));
        table.addCell(putCell(person.getEmail(), TextAlignment.RIGHT));

        table.addCell(putCell(CREATE_DATE, TextAlignment.LEFT));
        table.addCell(putCell(String.valueOf(person.getCreateDate()), TextAlignment.RIGHT));

        return table;
    }

    private List<Table> allPersonsTables(List<InfoPersonDto> persons) {
        List<Table> tables = new ArrayList<>(0);

        for (InfoPersonDto person : persons) {
            tables.add(personTable(person));
        }
        return tables;
    }

    private Cell putCell(String text, TextAlignment textAlignment) {
        return new Cell()
                .add(new Paragraph(text))
                .setFontSize(16F)
                .setTextAlignment(textAlignment)
                .setBorder(Border.NO_BORDER);
    }
}
