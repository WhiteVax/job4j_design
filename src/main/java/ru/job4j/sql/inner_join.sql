CREATE TABLE students(
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(15),
    last_name VARCHAR(15),
    department_id INT REFERENCES department(id)
);

CREATE TABLE department(
    id SERIAL PRIMARY KEY,
    course_math INT,
    course_psychology INT,
    data_start TIMESTAMP
);

INSERT INTO students(id, first_name, last_name, department_id)
VALUES (1, 'Petr', 'Zelenky', 1),
        (2, 'Ivan', 'Gorobich', 2),
        (3, 'Alexandr', 'Velichko', 3);

INSERT INTO department(course_math, course_psychology, data_start)
VALUES (200, 160, '2018-10-10'),
        (200, 100, '2018-9-10'),
        (120, 150, '2018-12-10');

SELECT      first_name, last_name, course_math, data_start
FROM        students s
INNER JOIN  department d
ON          s.id = d.id
WHERE       s.id IN(1, 3);

SELECT      first_name  Имя, last_name  Фамилия, data_start  Дата_начала
FROM        students s
INNER JOIN  department d
ON          s.id = d.id
WHERE       d.data_start = '2018-9-10';

SELECT      *
FROM        students s
INNER JOIN  department d
ON          d.id = s.id
WHERE       d.id IN(1, 2) AND course_math IN(180, 200);