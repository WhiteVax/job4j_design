INSERT INTO company (id, name)
VALUES (1, 'samsung'),
       (2, 'huawei'),
       (3, 'nokia'),
       (4, 'xiaomi'),
       (5, 'moto');

INSERT INTO person (id, name, company_id)
VALUES (1, 'Alex', 1),
       (2, 'Petr', 1),
       (3, 'Dmitry', 1),
       (4, 'Vlad', 2),
       (5, 'Alex', 2),
       (6, 'Alexandr', 5),
       (7, 'Victoria', 2),
       (8, 'Daria', 3),
       (9, 'Natalia', 3),
       (10, 'Victor', 3),
       (11, 'Magomed', 4),
       (12, 'Ismail', 4),
       (13, 'Alex', 5);

-- 1. В одном запросе получить
-- - имена всех person, которые не состоят в компании с id = 5;
-- - название компании для каждого человека.

SELECT p.id, c.name, p.name
FROM company c
LEFT JOIN person p ON c.id = p.company_id
WHERE p.company_id != 5;

-- 2. Необходимо выбрать название компании с максимальным
-- количеством человек + количество человек в этой компании
-- нужно учесть, что таких компаний может быть несколько,
-- и вывести надо информацию о каждой компании.

SELECT c.name name, COUNT(p.company_id)
FROM company c
LEFT JOIN person p on c.id = p.company_id
group by c.name
HAVING COUNT(p.company_id) = (SELECT COUNT(p.company_id) size
                              FROM person p
                              GROUP BY p.company_id
                              ORDER BY size DESC
                              LIMIT 1);
