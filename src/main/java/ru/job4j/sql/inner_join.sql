CREATE TABLE students(
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(15),
    last_name VARCHAR(15)
);

CREATE TABLE department(
    id SERIAL PRIMARY KEY,
    course_math INT,
    course_psychology INT,
    data_start TIMESTAMP
);

INSERT INTO students(first_name, last_name)
VALUES ('Petr', 'Zelenky'),
        ('Ivan', 'Gorobich'),
        ('Alexandr', 'Velichko');

INSERT INTO department(course_math, course_psychology, data_start)
VALUES (200, 160, '2018-10-10'),
        (200, 100, '2018-9-10'),
        (120, 150, '2018-12-10');

SELECT      first_name, last_name, course_math, data_start
FROM        students s
INNER JOIN  department d
ON          s.id = d.id;

SELECT      first_name  Имя, last_name  Фамилия, data_start  Дата_начала
FROM        students s
INNER JOIN  department
ON          s.id IN(2, 3);

SELECT      *
FROM        students s
INNER JOIN  department d
ON          d.id = s.id;