CREATE TABLE car_bodies(
    id SERIAL PRIMARY KEY,
    name VARCHAR
);

CREATE TABLE car_engines(
    id SERIAL PRIMARY KEY,
    name VARCHAR
);

CREATE TABLE car_transmissions(
    id SERIAL PRIMARY KEY,
    name VARCHAR
);

CREATE TABLE cars(
    id SERIAL PRIMARY KEY,
    name VARCHAR,
    body_id INT REFERENCES car_bodies(id),
    engine_id INT REFERENCES car_engines(id),
    transmission_id INT REFERENCES car_bodies(id)
);

INSERT INTO car_bodies(name)
VALUES ('coupe'),
        ('sedan'),
        ('universal'),
        ('hatchback'),
        ('minivan');

INSERT INTO car_engines(name)
VALUES ('Toyota 3S-FE'),
        ('Mitsubishi 4G63'),
        ('Honda D-series'),
        ('Opel 20ne'),
        ('Toyota 1JZ-GE и 2JZ-GE');

INSERT INTO car_transmissions(name)
VALUES ('mechanical'),
        ('machine'),
        ('automatic variator (variator)');

INSERT INTO cars(name, body_id, engine_id, transmission_id)
VALUES ('Toyota', 1, 1, 1),
        ('Mitsubishi', 2, 2, 1),
        ('Honda', 3, 3, 2),
        ('Opel', 4, 4, 2),
        ('BMV', null, null, 2); 

SELECT c.id, c.name car_name, cb.name  body_name, ce.name engine_name, ct.name transmission_name
FROM cars c
LEFT JOIN car_bodies cb ON c.body_id = cb.id
LEFT JOIN car_engines ce ON c.engine_id = ce.id
LEFT JOIN car_transmissions ct ON c.transmission_id = ct.id;

SELECT c.name car_name, cb.name body_name
FROM car_bodies cb
LEFT JOIN cars c ON c.body_id = cb.id
WHERE c.body_id IS NULL;

SELECT c.name car_name, ce.name engine_name
FROM car_engines ce
LEFT JOIN cars c ON c.engine_id = ce.id
WHERE c.engine_id IS NULL;

SELECT c.name car_name, ct.name transmission_name
FROM car_transmissions ct
LEFT JOIN cars c ON c.transmission_id = ct.id
WHERE c.transmission_id IS NULL;