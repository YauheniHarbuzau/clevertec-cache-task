DROP TABLE IF EXISTS persons;

CREATE TABLE IF NOT EXISTS persons
(
    id          SERIAL PRIMARY KEY NOT NULL,
    first_name  VARCHAR(100),
    last_name   VARCHAR(100),
    email       VARCHAR(100),
    create_date TIMESTAMP
);

INSERT INTO persons (id, first_name, last_name, email, create_date)
VALUES (1, 'Person 1', 'Person 1', 'email1@gmail.com', NOW()),
       (2, 'Person 2', 'Person 2', 'email2@gmail.com', NOW()),
       (3, 'Person 3', 'Person 3', 'email3@gmail.com', NOW()),
       (4, 'Person 4', 'Person 4', 'email4@gmail.com', NOW()),
       (5, 'Person 5', 'Person 5', 'email5@gmail.com', NOW()),
       (6, 'Person 6', 'Person 6', 'email6@gmail.com', NOW()),
       (7, 'Person 7', 'Person 7', 'email7@gmail.com', NOW()),
       (8, 'Person 8', 'Person 8', 'email8@gmail.com', NOW()),
       (9, 'Person 9', 'Person 9', 'email9@gmail.com', NOW()),
       (10, 'Person 10', 'Person 10', 'email10@gmail.com', NOW()),
       (11, 'Person 11', 'Person 11', 'email11@gmail.com', NOW()),
       (12, 'Person 12', 'Person 12', 'email12@gmail.com', NOW()),
       (13, 'Person 13', 'Person 13', 'email13@gmail.com', NOW()),
       (14, 'Person 14', 'Person 14', 'email14@gmail.com', NOW()),
       (15, 'Person 15', 'Person 15', 'email15@gmail.com', NOW()),
       (16, 'Person 16', 'Person 16', 'email16@gmail.com', NOW()),
       (17, 'Person 17', 'Person 17', 'email17@gmail.com', NOW()),
       (18, 'Person 18', 'Person 18', 'email18@gmail.com', NOW()),
       (19, 'Person 19', 'Person 19', 'email19@gmail.com', NOW()),
       (20, 'Person 20', 'Person 20', 'email20@gmail.com', NOW());