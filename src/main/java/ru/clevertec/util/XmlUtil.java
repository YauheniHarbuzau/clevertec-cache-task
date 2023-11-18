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

import static ru.clevertec.constant.Constant.XML_PERSONS_FILE_NAME;
import static ru.clevertec.constant.Constant.XML_PERSONS_FILE_PATH;

/**
 * Утилитарный класс для создания xml-файла
 */
@UtilityClass
public class XmlUtil {

    private final Document document = new Document();
    private final Element persons = new Element("persons");

    public void writeXml(Person person, String operation) throws IOException {
        Element child = new Element("person");
        child.addContent(new Element("operation").setText(operation));
        child.addContent(new Element("timestamp").setText(String.valueOf(Date.from(Instant.now()))));
        child.addContent(new Element("id").setText(String.valueOf(person.getId())));
        child.addContent(new Element("first_name").setText(String.valueOf(person.getFirstName())));
        child.addContent(new Element("last_name").setText(String.valueOf(person.getLastName())));
        child.addContent(new Element("email").setText(String.valueOf(person.getEmail())));

        persons.addContent(child);
        document.setContent(persons);
        writeFile(XML_PERSONS_FILE_PATH + XML_PERSONS_FILE_NAME);
    }

    private void writeFile(String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        XMLOutputter outputter = new XMLOutputter();
        outputter.setFormat(Format.getPrettyFormat());
        outputter.output(document, fileWriter);
    }
}
