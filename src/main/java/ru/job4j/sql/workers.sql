CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(15),
    last_name VARCHAR(15),
    salary INT
);

INSERT INTO users(first_name, last_name, salary)
        VALUES('Alex', 'Koval', 5000),
              ('Dmitry', 'Urich', 4500);
              
UPDATE users SET salary = 5100;               

DELETE FROM users;
