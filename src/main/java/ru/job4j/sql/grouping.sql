CREATE TABLE devices(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    price FLOAT
);

CREATE TABLE people(
    id serial primary key,
    name varchar(255)
);

CREATE TABLE devices_people(
    id SERIAL PRIMARY KEY,
    device_id INT REFERENCES devices(id),
    people_id INT REFERENCES people(id)
);

INSERT INTO devices(name, price)
VALUES      ('laptop legion 5 3060', 60000.50),
            ('laptop legion 5 pro 3060', 69000.90),
            ('laptop asus N53SN', 12000.30),
            ('laptop asus ROG 3060', 68000.50),
            ('smartfone nokia 3110', 4000.90);

INSERT INTO people(name)
VALUES      ('Alex'),
            ('Dmitry'),
            ('Ivan'),
            ('Petr'),
            ('Sergo');

INSERT INTO devices_people(device_id, people_id)
VALUES      (1, 2),
            (2, 1),
            (3, 3),
            (4, 4),
            (5, 5);

SELECT AVG(price)
FROM devices;

SELECT p.name, AVG(d.price)
FROM devices d
INNER JOIN people p
ON d.id = p.id
INNER JOIN devices_people dp
ON dp.id = d.id
GROUP BY p.name;

SELECT p.name, AVG(d.price)
FROM devices d
INNER JOIN people p
ON d.id = p.id
INNER JOIN devices_people dp
ON dp.id = d.id
GROUP BY p.name
HAVING AVG(d.price) > 5000;