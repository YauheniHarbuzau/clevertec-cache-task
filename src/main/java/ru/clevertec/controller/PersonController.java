package ru.clevertec.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.controller.adapter.OffsetDateTimeAdapter;
import ru.clevertec.service.PersonService;
import ru.clevertec.service.dto.PersonDto;
import ru.clevertec.util.ValidationUtil;

import java.time.OffsetDateTime;

import static ru.clevertec.constant.Constant.STATUS_BAD_REQUEST;
import static ru.clevertec.constant.Constant.STATUS_DELETE_OK;
import static ru.clevertec.constant.Constant.STATUS_GET_OK;
import static ru.clevertec.constant.Constant.STATUS_NOT_FOUND;
import static ru.clevertec.constant.Constant.STATUS_SAVE_OK;

/**
 * Контроллер для работы с Пользователями (Person)
 */
@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService service;
    private final Gson gson;

    @Autowired
    public PersonController(@Qualifier("personServiceProxy") PersonService service) {
        this.service = service;
        this.gson = new GsonBuilder()
                .serializeNulls()
                .registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeAdapter())
                .setPrettyPrinting()
                .create();
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getById(@PathVariable("id") Long id) {
        try {
            var person = service.getById(id);
            return new ResponseEntity<>(gson.toJson(person), STATUS_GET_OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(STATUS_NOT_FOUND);
        }
    }

    @GetMapping("")
    public ResponseEntity<String> getAll() {
        try {
            var persons = service.getAll();
            return new ResponseEntity<>(gson.toJson(persons), STATUS_GET_OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(STATUS_NOT_FOUND);
        }
    }

    @GetMapping("/{pageSize}/{pageNumber}")
    public ResponseEntity<String> getAll(@PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber) {
        try {
            var persons = service.getAll(pageSize, pageNumber);
            return new ResponseEntity<>(gson.toJson(persons), STATUS_GET_OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(STATUS_NOT_FOUND);
        }
    }

    @PostMapping("/{id}/{firstName}/{lastName}/{email}")
    public ResponseEntity<String> save(@PathVariable("id") Long id, @PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName, @PathVariable("email") String email) {
        var personToSave = PersonDto.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .build();

        if (ValidationUtil.isValid(personToSave)) {
            try {
                service.save(personToSave);
                return new ResponseEntity<>(gson.toJson(personToSave), STATUS_SAVE_OK);
            } catch (Exception ex) {
                return new ResponseEntity<>(STATUS_BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(STATUS_BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        try {
            service.deleteById(id);
            return new ResponseEntity<>(STATUS_DELETE_OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(STATUS_BAD_REQUEST);
        }
    }
}
