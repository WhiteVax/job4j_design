-- many-to-one
CREATE TABLE course_math(
    id_student SERIAL PRIMARY KEY, 
    hours INT
);

CREATE TABLE students(
    id_person SERIAL PRIMARY KEY,
    id_course INT REFERENCES course_math(id_student),
    first_name VARCHAR(15),
    last_name VARCHAR(15),
    UNIQUE(first_name, last_name)
);

INSERT INTO course_math(hours)
VALUES(120);

INSERT INTO students(id_course, first_name, last_name)
VALUES (2, 'Kiril', 'Syrikov');

-- one-to-one
CREATE TABLE taxpayer(
    id SERIAL PRIMARY KEY,
    reg_address VARCHAR(30)
);

CREATE TABLE passport(
    ti_person INT REFERENCES taxpayer(id),
    first_name VARCHAR(15),
    last_name VARCHAR(15)
);

INSERT INTO taxpayer(reg_address)
VALUES('RF ...');

INSERT INTO taxpayer(reg_address)
VALUES('UK ...');

INSERT INTO passport(ti_person, first_name, last_name)
VALUES(2, 'Petr', 'Kovalski');

-- many-to-many
CREATE TABLE decans(
    id_decan SERIAL PRIMARY KEY,
    first_name VARCHAR(15),
    last_name VARCHAR(15),
    experiance VARCHAR(30)
);

CREATE TABLE diploma_theme(
    id_theme SERIAL PRIMARY KEY,
    theme VARCHAR(30)
);

CREATE TABLE students_on_diplom(
    id_theme INT REFERENCES diploma_theme(id_theme),
    decan INT REFERENCES decans(id_decan),
    id_person SERIAL PRIMARY KEY,
    first_name VARCHAR(15),
    last_name VARCHAR(15)
);

INSERT INTO decans(first_name, last_name, experiance)
VALUES('Alex', 'Koval', 'Distillation column');

INSERT INTO diploma_theme(theme)
VALUES('reactor calculation');

INSERT INTO students_on_diplom(id_theme, decan, first_name, last_name)
VALUES(1, 1, 'Dmitry', 'Koval')




