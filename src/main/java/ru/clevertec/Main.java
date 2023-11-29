package ru.clevertec;

import ru.clevertec.controller.PersonController;

/**
 * Main-класс - точка входа в приложение
 */
public class Main {

    public static void main(String[] args) {

        PersonController controller = new PersonController();

        System.out.println(controller.getById(1L));

        String json = """
                {
                  "id": 100,
                  "firstName": "Ivan",
                  "lastName": "Ivanov",
                  "email": "ivanov@gmail.com"
                }
                """;

        System.out.println(controller.save(json));

        System.out.println(controller.getAll());
    }
}
