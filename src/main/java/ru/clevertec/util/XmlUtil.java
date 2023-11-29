package ru.clevertec.util;

import lombok.experimental.UtilityClass;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import ru.clevertec.dao.entity.Person;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;

import static ru.clevertec.constant.Constant.XML_PERSONS_FILE;

/**
 * Утилитарный класс для создания xml-файла
 */
@UtilityClass
public class XmlUtil {

    private final Document document = new Document();
    private final Element persons = new Element("persons");

    public void writeXml(Person person, String operation) throws IOException {
        var element = new Element("person");
        element.addContent(new Element("operation").setText(operation));
        element.addContent(new Element("timestamp").setText(String.valueOf(Date.from(Instant.now()))));
        element.addContent(new Element("id").setText(String.valueOf(person.getId())));
        element.addContent(new Element("first_name").setText(String.valueOf(person.getFirstName())));
        element.addContent(new Element("last_name").setText(String.valueOf(person.getLastName())));
        element.addContent(new Element("email").setText(String.valueOf(person.getEmail())));

        persons.addContent(element);
        document.setContent(persons);
        writeFile();
    }

    private void writeFile() throws IOException {
        var fileWriter = new FileWriter(XML_PERSONS_FILE);
        var xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        xmlOutputter.output(document, fileWriter);
    }
}
