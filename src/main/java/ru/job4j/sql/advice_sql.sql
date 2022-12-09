CREATE TABLE IF NOT EXISTS authors (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    UNIQUE (first_name, last_name)
);

CREATE TABLE IF NOT EXISTS books(
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    year DATE,
    author_id INT,
    FOREIGN KEY (author_id) REFERENCES authors (id)
);

CREATE TABLE IF NOT EXISTS student (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS orders (
    id SERIAL PRIMARY KEY,
    active BOOLEAN,
    book_id INT REFERENCES books (id),
    student_id INT REFERENCES student(id)
);

INSERT INTO authors(first_name, last_name)
VALUES ('Брайн', 'Гетц'),
       ('Брюс', 'Эккель'),
       ('Майк', 'Макграт'),
       ('Мартин', 'Роберт'),
       ('Сэджвик', 'Роберт');

INSERT INTO books(name, year, author_id)
VALUES ('Java concurrency', '2020-01-01', 1),
       ('Философия Java', '2015-01-01', 2),
       ('Java программирование для начинающих', '2015-01-01', 3),
       ('Идеальная работа', '2022-01-01', 4),
       ('Алгоритмы', '2018-01-01', 5);

INSERT INTO student(first_name, last_name)
VALUES ('Иван', 'Петричко'),
        ('Семён', 'Ковальский'),
        ('Павел', 'Нимиров'),
        ('Дмитрий', 'Соколовский'),
        ('Сергей', 'Старицкий');

INSERT INTO orders(active, book_id, student_id)
VALUES ('false', '3', '1'),
       ('false', '5', '2'),
       ('false', '2', '3'),
       ('true', '4', '4');

CREATE VIEW show_order_and_student_book_with_author
AS
SELECT o.id,
       o.active,
       s.first_name student_name,
       s.last_name  student_surname,
       b.name       book,
       b.year,
       a.first_name author_name,
       a.last_name  author_surname
FROM orders o
         JOIN student s ON o.student_id = s.id
         JOIN books b ON o.book_id = b.id
         JOIN authors a ON o.id = a.id
WHERE b.name LIKE '%Java%'
ORDER BY o.id;

DROP VIEW show_order_and_student_book_with_author;