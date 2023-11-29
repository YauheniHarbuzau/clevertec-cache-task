package ru.clevertec.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.clevertec.controller.adapter.OffsetDateTimeAdapter;
import ru.clevertec.service.PersonService;
import ru.clevertec.service.dto.PersonDto;
import ru.clevertec.service.impl.PersonServiceImpl;
import ru.clevertec.service.proxy.PersonServiceProxy;
import ru.clevertec.util.ValidationUtil;

import java.time.OffsetDateTime;

/**
 * Контроллер для работы с Пользователями (Person)
 */
public class PersonController {

    private final PersonService service;
    private final Gson gson;

    public PersonController() {
        this.service = new PersonServiceProxy(new PersonServiceImpl());
        this.gson = new GsonBuilder()
                .serializeNulls()
                .registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeAdapter())
                .setPrettyPrinting()
                .create();
    }

    public String getById(Long id) {
        var person = service.getById(id);
        return gson.toJson(person);
    }

    public String getAll() {
        var persons = service.getAll();
        return gson.toJson(persons);
    }

    public String save(String personDtoJson) {
        var personToSave = gson.fromJson(personDtoJson, PersonDto.class);

        if (!ValidationUtil.isValid(personToSave)) {
            return "Invalid Person DTO";
        } else {
            service.save(personToSave);
            return "Saved successfully: " + personToSave.toString();
        }
    }

    public String deleteById(Long id) {
        service.deleteById(id);
        return "Deleted by ID " + id + " successfully";
    }
}
