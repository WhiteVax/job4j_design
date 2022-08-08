CREATE TABLE type(
        id SERIAL PRIMARY KEY,
        name VARCHAR(30)
);

CREATE TABLE product(
        id SERIAL PRIMARY KEY,
        name VARCHAR(30),
        type_id INT REFERENCES type(id),
        expired_date TIMESTAMP,
        price INT
);

INSERT INTO type(name)
VALUES  ('Сыр'),
        ('Хлеб'),
        ('Мороженое'),
        ('Молоко');

INSERT INTO product(name, type_id, expired_date, price)
VALUES  ('Белый', 2, '12-08-2022', 30),
        ('Эдам', 3, '6-08-2022', 15),
        ('Граскаас', 4, '8-16-2022', 35),
        ('Гауда', 1, '8-30-2022', 120),
        ('Леердам', 1, '8-30-2022', 140),
        ('Лейден', 1, '8-30-2022', 120),
        ('Маасдам', 1, '8-30-2022', 160),
        ('Паррано', 1, '8-30-2022',120),
        ('Роомано', 1, '8-30-2022', 190),
        ('Власкаас', 1, '8-30-2022', 200),
        ('Буренкаас', 1, '8-30-2022', 350),
        ('Овечий', 1, '8-30-2022', 200),
        ('Маасдам', 1, '8-30-2022', 300),
        ('Мороженое белый медведь', 3, '8-30-2022', 45);

SELECT          *
FROM            product p
INNER JOIN      type t
ON              p.type_id = t.id
WHERE           p.type_id = 1;

SELECT          *
FROM            product p
INNER JOIN type t
ON              p.type_id = t.id
WHERE           p.name LIKE 'Мороженое%';

SELECT          *
FROM            product p
INNER JOIN type t
ON              p.type_id = t.id
WHERE           p.expired_date < '8-8-2022';

SELECT          t.name, p.name, p.price
FROM            product p
INNER JOIN type t
ON              p.type_id = t.id
GROUP BY        p.name, t.name, p.price
HAVING          p.price = (SELECT MAX(price) FROM product);

SELECT          t.name имя_типа, COUNT(p.type_id) количество
FROM            product p
INNER JOIN type t
ON              p.type_id = t.id
GROUP BY        t.name;

SELECT          t.name имя_типа, p.name продукт
FROM            product p
INNER JOIN type t
ON              p.type_id = t.id
GROUP BY        t.name, p.name,  p.type_id
HAVING          p.type_id IN(1, 4);

SELECT          t.name тип_продукта, COUNT(p.type_id)
FROM            product p
INNER JOIN type t
ON              p.type_id = t.id
GROUP BY        t.name
HAVING          COUNT(p.type_id) < 10;

SELECT          p.id, p.name, t.name
FROM            product p
INNER JOIN type t
ON              p.type_id = t.id
GROUP By        p.id, p.name, t.name;