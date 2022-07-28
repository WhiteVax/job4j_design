-- many-to-one
CREATE TABLE course(
    id_student SERIAL PRIMARY KEY, 
    course_id INT REFERENCES student(id_person)
);

CREATE TABLE student(
    id_person SERIAL PRIMARY KEY,
    first_name VARCHAR(15),
    last_name VARCHAR(15)
);

INSERT INTO student(first_name, last_name)
VALUES ('Kiril', 'Syrikov');

INSERT INTO course(course_id)
VALUES (1);

-- one-to-one
CREATE TABLE taxpayer(
    id SERIAL PRIMARY KEY,
    reg_address VARCHAR(30)
);

CREATE TABLE passport(
    ti_person INT REFERENCES taxpayer(id) UNIQUE,
    first_name VARCHAR(15),
    last_name VARCHAR(15)
);

-- many-to-many
CREATE TABLE worker(
    id_person SERIAL PRIMARY KEY,
    first_name VARCHAR(15),
    last_name VARCHAR(15),
    education VARCHAR(15)                                                     
);

CREATE TABLE dobby_is_free(
    vacation INT    
);

CREATE TABLE department(
    first_name VARCHAR(15) REFERENCES worker(first_name),
    id_person INT REFERENCES worker(vacation),
    vacation INT REFERENCES dobby_is_free(vacation),
    profession VARCHAR,
    experience INT
);




