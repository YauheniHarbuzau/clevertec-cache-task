package ru.clevertec.util;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import com.itextpdf.kernel.pdf.canvas.parser.listener.LocationTextExtractionStrategy;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.service.dto.InfoPersonDto;
import ru.clevertec.testdatautil.PersonTestData;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Тестовый класс для {@link PdfUtil}
 */
class PdfUtilTest {

    private static final String PDF_FOR_TEST = "src/test/java/ru/clevertec/TestPdfFile.pdf";

    @BeforeEach
    void setUp() {
        deleteTestPdfFile();
    }

    @AfterEach
    void tearDown() {
        deleteTestPdfFile();
    }

    @SneakyThrows
    @Test
    void checkCreatePdfShouldReturnPdfFileWithCorrectText() {
        // given
        InfoPersonDto firstPerson = PersonTestData.builder()
                .withId(50L)
                .withFirstName("Person 50 First Name")
                .withLastName("Person 50 Last Name")
                .withEmail("email50@gmail.com")
                .build().buildInfoPersonDto();
        InfoPersonDto secondPerson = PersonTestData.builder()
                .withId(150L)
                .withFirstName("Person 150 First Name")
                .withLastName("Person 150 Last Name")
                .withEmail("email150@gmail.com")
                .build().buildInfoPersonDto();
        List<InfoPersonDto> persons = List.of(firstPerson, secondPerson);

        // when
        PdfUtil.createPdf(persons, PDF_FOR_TEST, false);

        // than
        PdfDocument pdfDocument = new PdfDocument(new PdfReader(PDF_FOR_TEST));
        String pdfText = getPdfText(pdfDocument);
        pdfDocument.close();

        assertThat(pdfText)
                .contains("50")
                .contains("Person 50 First Name")
                .contains("Person 50 Last Name")
                .contains("email50@gmail.com")
                .contains("150")
                .contains("Person 150 First Name")
                .contains("Person 150 Last Name")
                .contains("email150@gmail.com");
    }

    private String getPdfText(PdfDocument pdfDocument) {
        StringBuilder pdfText = new StringBuilder();
        for (int i = 1; i <= pdfDocument.getNumberOfPages(); i++) {
            PdfPage page = pdfDocument.getPage(i);
            String text = PdfTextExtractor.getTextFromPage(page, new LocationTextExtractionStrategy());
            pdfText.append(text);
        }
        return pdfText.toString();
    }

    @SneakyThrows
    private void deleteTestPdfFile() {
        Files.deleteIfExists(Path.of(PDF_FOR_TEST));
    }
}
