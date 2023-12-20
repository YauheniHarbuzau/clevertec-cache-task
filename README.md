# CLEVERTEC TASK

(CACHE, PDF, PATTERNS, SERVLETS)

**Автор Евгений Гарбузов**

***

### Параметры приложения, библиотеки и зависимости

- Java 17
- Gradle 8.0
- PostgreSQL 15.2
- PostgreSQL JDBC Driver 42.5.4
- Liquibase 4.25.0
- Java Servlet API 4.0.1
- Lombok Plugin 6.5.1
- MapStruct 1.5.5.Final
- AspectJ 1.9.20.1
- AspectJ Plugin 8.3
- Hibernate Validator 8.0.1.Final
- SnakeYAML 2.0
- Gson 2.10.1
- iText 7.2.5
- JDOM 2.0.6.1
- JUnit 5.9.2
- Mockito 5.6.0
- AssertJ 3.24.2

Для работы с приложением локально требуется Docker и Apache Tomcat.

Перед началом работы необходимо выполнить команду ```docker compose up```
для создания контейнера с заполненной базой данных.

Реализованы алгоритмы [LRU](src/main/java/ru/clevertec/dao/cache/impl/LRUCache.java "LRUCache.java")
и [LFU](src/main/java/ru/clevertec/dao/cache/impl/LFUCache.java "LFUCache.java")
для кеширования данных.

Реализована валидация данных на [контролере](src/main/java/ru/clevertec/controller/PersonController.java "PersonController.java")
при выполнении POST-метода (сохранение Пользователя).

Предусмотрена генерация pdf-файлов при получении Пользователя по ID
([см. пример](src/main/resources/pdfexample/person_get_by_id_example.pdf "person_get_by_id_example.pdf")) и
получении всех Пользователей ([см. пример](src/main/resources/pdfexample/person_get_all_example.pdf "person_get_all_example.pdf")).

***

### Примеры [HTTP-запросов](src/main/resources/http/person.http "person.http") для работы с приложением

#### - GET

Получение Пользователя по ID:</br>
http://localhost:8080/clevertec-cache-task/persons?id=1

Получение всех Пользователей с пагинацией по умолчанию:</br>
http://localhost:8080/clevertec-cache-task/persons

Получение всех Пользователей с задаваемой пагинацией:</br>
http://localhost:8080/clevertec-cache-task/persons?pageSize=10&pageNumber=1

#### - POST

Сохранение (создание или обновление) Пользователя:</br>
http://localhost:8080/clevertec-cache-task/persons?id=100&firstName=Ivan&lastName=Ivanov&email=ivan@gmail.com

#### - DELETE

Удаление Пользователя по ID:</br>
http://localhost:8080/clevertec-cache-task/persons?id=100

***