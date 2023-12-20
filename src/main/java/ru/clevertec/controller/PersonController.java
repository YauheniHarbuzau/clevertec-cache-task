package ru.clevertec.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.clevertec.controller.adapter.OffsetDateTimeAdapter;
import ru.clevertec.service.PersonService;
import ru.clevertec.service.dto.PersonDto;
import ru.clevertec.service.impl.PersonServiceImpl;
import ru.clevertec.service.proxy.PersonServiceProxy;
import ru.clevertec.util.ValidationUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.OffsetDateTime;

import static ru.clevertec.constant.Constant.STATUS_BAD_REQUEST;
import static ru.clevertec.constant.Constant.STATUS_DELETE_OK;
import static ru.clevertec.constant.Constant.STATUS_GET_OK;
import static ru.clevertec.constant.Constant.STATUS_NOT_FOUND;
import static ru.clevertec.constant.Constant.STATUS_SAVE_OK;

/**
 * Контроллер для работы с Пользователями (Person)
 */
@WebServlet(value = "/persons")
public class PersonController extends HttpServlet {

    private PersonService service;
    private Gson gson;

    @Override
    public void init() {
        this.service = new PersonServiceProxy(new PersonServiceImpl());
        this.gson = new GsonBuilder()
                .serializeNulls()
                .registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeAdapter())
                .setPrettyPrinting()
                .create();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var id = request.getParameter("id");
        var pageSize = request.getParameter("pageSize");
        var pageNumber = request.getParameter("pageNumber");

        if (id != null) {
            getById(response, id);
        } else if (pageSize != null && pageNumber != null) {
            getAll(response, pageSize, pageNumber);
        } else {
            getAll(response);
        }
    }

    private void getById(HttpServletResponse response, String id) throws IOException {
        try {
            var request = gson.toJson(service.getById(Long.parseLong(id)));
            answerFromServer(response, STATUS_GET_OK, request);
        } catch (Exception ex) {
            answerFromServer(response, STATUS_NOT_FOUND, ex.getMessage());
        }
    }

    private void getAll(HttpServletResponse response) throws IOException {
        try {
            var request = gson.toJson(service.getAll());
            answerFromServer(response, STATUS_GET_OK, request);
        } catch (Exception ex) {
            answerFromServer(response, STATUS_NOT_FOUND, ex.getMessage());
        }
    }

    private void getAll(HttpServletResponse response, String pageSize, String pageNumber) throws IOException {
        try {
            var request = gson.toJson(service.getAll(Integer.parseInt(pageSize), Integer.parseInt(pageNumber)));
            answerFromServer(response, STATUS_GET_OK, request);
        } catch (Exception ex) {
            answerFromServer(response, STATUS_NOT_FOUND, ex.getMessage());
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var personToSave = PersonDto.builder()
                .id(Long.parseLong(request.getParameter("id")))
                .firstName(request.getParameter("firstName"))
                .lastName(request.getParameter("lastName"))
                .email(request.getParameter("email"))
                .build();

        if (!ValidationUtil.isValid(personToSave)) {
            answerFromServer(response, STATUS_BAD_REQUEST, "Invalid Person DTO");
        } else {
            save(response, personToSave);
        }
    }

    private void save(HttpServletResponse response, PersonDto personToSave) throws IOException {
        try {
            service.save(personToSave);
            answerFromServer(response, STATUS_SAVE_OK, "Saved Successfully\n" + gson.toJson(personToSave));
        } catch (Exception ex) {
            answerFromServer(response, STATUS_BAD_REQUEST, ex.getMessage());
        }
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var idToDelete = request.getParameter("id");

        try {
            service.deleteById(Long.parseLong(idToDelete));
            answerFromServer(response, STATUS_DELETE_OK, "Deleted By ID " + idToDelete + " Successfully");
        } catch (Exception ex) {
            answerFromServer(response, STATUS_BAD_REQUEST, ex.getMessage());
        }
    }

    private void answerFromServer(HttpServletResponse response, int status, String request) throws IOException {
        response.setStatus(status);
        response.setHeader("Content-Type", "application/json");
        response.getOutputStream().println(request);
    }
}
