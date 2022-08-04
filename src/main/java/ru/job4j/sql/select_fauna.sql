CREATE TABLE fauna(
    id             serial primary key,
    name           text,
    avg_age        int,
    discovery_date date
);

INSERT INTO fauna(name, avg_age, discovery_date)
VALUES ('fish_carassius', 10, '1952/03/20'),
       ('fish_pike', 25, '1806/06/15'),
       ('fish_perch', 10, '1874/01/01'),
       ('fish_yellow_perch', 1, '1814/01/01'),
       ('animal_hares', 12, '1758/01/01'),
       ('fish_yellow_perch', 1, '1814/01/01'),
       ('animal_hares', 12, '1758/01/01'),
       ('animal_wolf', 12, NULL),
       ('three_sequoiadendron', 12000, NULL);

SELECT *
FROM fauna
WHERE name LIKE '%fish%';

SELECT *
FROM fauna
WHERE avg_age BETWEEN 10000 AND 21000;

SELECT *
FROM fauna
WHERE discovery_date IS NULL;

SELECT *
FROM fauna
WHERE discovery_date < '1950-01-01';