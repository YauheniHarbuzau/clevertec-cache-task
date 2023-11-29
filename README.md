# CLEVERTEC TASKS (CACHE, PDF, PATTERNS)

**Автор Евгений Гарбузов**

***

### Параметры приложения, библиотеки и зависимости

- Java 17
- Gradle 8.0
- PostgreSQL 15.2
- PostgreSQL JDBC Driver 42.5.4
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

Для работы с приложением локально требуется Docker.

Перед началом работы необходимо выполнить команду ```docker compose up```
для создания контейнера PostgreSQL с заполненной базой данных.

Точка входа в приложение - [Main-класс](src/main/java/ru/clevertec/Main.java "Main.java")
(содержит примеры работы с приложением).

Реализованы алгоритмы [LRU](src/main/java/ru/clevertec/dao/cache/impl/LRUCache.java "LRUCache.java")
и [LFU](src/main/java/ru/clevertec/dao/cache/impl/LFUCache.java "LFUCache.java")
для кеширования данных.

Реализована валидация данных на [контролере](src/main/java/ru/clevertec/controller/PersonController.java "PersonController.java")
при выполнении метода сохранения.

Предусмотрена генерация pdf-файлов при получении Пользователя по ID
([см. пример](src/main/resources/pdfexample/person_get_by_id_example.pdf "person_get_by_id_example.pdf")) и
получении всех Пользователей ([см. пример](src/main/resources/pdfexample/person_get_all_example.pdf "person_get_all_example.pdf")).

***

### Методы [контролера](src/main/java/ru/clevertec/controller/PersonController.java "PersonController.java") для работы с приложением

#### - Получение Пользователя по ID (метод и пример вывода результата в консоль)

```java
public String getById(Long id){...}
```

```
{
  "id": 1,
  "firstName": "Ivan",
  "lastName": "Ivanov",
  "email": "ivanov@gmail.com"
  "createDate": "2023-11-17T12:34:20.131553000+03:00"
}
```

#### - Получение всех Пользователей (метод и пример вывода результата в консоль)

```java
public String getAll(){...}
```

```
[
  {
    "id": 1,
    "firstName": "Ivan",
    "lastName": "Ivanov",
    "email": "ivanov@gmail.com"
    "createDate": "2023-11-17T12:34:20.131553000+03:00"
  },
  {
    "id": 2,
    "firstName": "Petr",
    "lastName": "Petrov",
    "email": "petrov@gmail.com"
    "createDate": "2023-11-17T12:34:20.131553000+03:00"
  }
]
```

#### - Сохранение (создание или обновление) Пользователя (метод и пример вывода результата в консоль)

```java
public String save(String personDtoJson){...}
```

При валидных данных:

```
Saved successfully: PersonDto(id=100, firstName=Ivan, lastName=Ivanov, email=ivanov@gmail.com)
```

При невалидных данных:

```
property: id, value: null, message: ID cannot be null
property: firstName, value: IvanAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA, message: Invalid first name
property: lastName, value: IvanovAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA, message: Invalid last name
property: email, value: ivanovgmailcom, message: Invalid email
Invalid Person DTO
```

#### - Удаление Пользователя по ID (метод и пример вывода результата в консоль)

```java
public String deleteById(Long id){...}
```

```
Deleted by ID 1 successfully
```

***