CREATE TABLE departments(
    id SERIAL PRIMARY KEy,
    name VARCHAR
);

CREATE TABLE employess(
    id SERIAL PRIMARY key,
    department_id INT REFERENCES departments(id),
    name VARCHAR
);

INSERT INTO departments(name)
VALUES ('Охрана труда'),
       ('Экономисты'),
       ('Охрана безопасности'),
       ('Електрики'),
       ('Механики'),
       ('Тестировщики');

INSERT INTO employess(department_id, name)
VALUES  (1, 'Алекс'),
        (2, 'Максим'),
        (3, 'Сергей'),
        (4, 'Игорь'),
        (5, 'Петр'),
        (null, 'Дмитрий');

SELECT          *
FROM            employess e
LEFT JOIN       departments d
ON              e.department_id = d.id;

SELECT          *
FROM            employess e
RIGHT JOIN      departments d
ON              e.department_id = d.id;

SELECT          *
FROM            employess e
CROSS JOIN      departments d;

SELECT          *
FROM            employess e
FULL JOIN       departments d
ON              e.department_id = d.id;

-- Используя left join найти департаменты, у которых нет работников
SELECT          *
FROM            departments d
LEFT JOIN       employess e
ON              e.department_id = d.id
WHERE           e.department_id IS NULL;

-- Используя left и right join написать запросы, которые давали бы одинаковый результат 
-- (порядок вывода колонок в эти запросах также должен быть идентичный). 
SELECT          *
FROM            departments d
LEFT JOIN       employess e
ON              e.department_id = d.id
WHERE           e IS NOT NULL;

SELECT          *
FROM            departments d
RIGHT JOIN      employess e
ON              e.department_id = d.id
WHERE           e IS NOT NULL;

CREATE TABLE teens(
    name VARCHAR(15) PRIMARY KEY,
    gender VARCHAR(15)
);

INSERT INTO teens(name, gender)
VALUES ('Sofia', 'male'),
        ('Boris', 'female'),
        ('Alex', 'male'),
        ('Dmitry', 'male'),
        ('Vasya', 'undetermined'),
        ('Victoria', 'female'),
        ('Natalia', 'female');

SELECT      *
FROM        teens t_1
CROSS JOIN  teens t_2
WHERE       t_1.gender != t_2.gender;