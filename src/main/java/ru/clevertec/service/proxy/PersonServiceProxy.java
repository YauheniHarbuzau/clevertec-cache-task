package ru.clevertec.service.proxy;

import ru.clevertec.service.PersonService;
import ru.clevertec.service.dto.InfoPersonDto;
import ru.clevertec.service.dto.PersonDto;
import ru.clevertec.service.impl.PersonServiceImpl;
import ru.clevertec.util.PdfUtil;

import java.util.List;

import static ru.clevertec.constant.Constant.PDF_PERSON_GET_ALL;
import static ru.clevertec.constant.Constant.PDF_PERSON_GET_BY_ID;

/**
 * Proxy для {@link PersonService} и {@link PersonServiceImpl}
 */
public class PersonServiceProxy implements PersonService {

    private final PersonService service;

    public PersonServiceProxy(PersonService service) {
        this.service = service;
    }

    @Override
    public InfoPersonDto getById(Long id) {
        var person = service.getById(id);
        PdfUtil.createPdf(person, PDF_PERSON_GET_BY_ID, true);
        return person;
    }

    @Override
    public List<InfoPersonDto> getAll() {
        var persons = service.getAll();
        PdfUtil.createPdf(persons, PDF_PERSON_GET_ALL, true);
        return persons;
    }

    @Override
    public void save(PersonDto personDto) {
        service.save(personDto);
    }

    @Override
    public void deleteById(Long id) {
        service.deleteById(id);
    }
}
