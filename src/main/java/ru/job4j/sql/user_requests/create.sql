CREATE TABLE role(
    id SERIAL PRIMARY KEY,
    role_user VARCHAR(15),
    UNIQUE(role_user)
);

CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    role INT REFERENCES role(id),
    nickname VARCHAR(15) NOT NULL
);

CREATE TABLE rulers(
    id SERIAL PRIMARY KEY,
    rule VARCHAR(15)
);

CREATE TABLE role_rulers(
    id SERIAL PRIMARY KEY,
    id_role INT REFERENCES role(id),
    id_rule INT REFERENCES rulers(id)
);

CREATE TABLE category(
    id SERIAL PRIMARY KEY,
    category_user VARCHAR(15)
);

CREATE TABLE state_item(
    id SERIAL PRIMARY KEY,
    txt VARCHAR(15)
);

CREATE TABLE item(
    id SERIAL PRIMARY KEY,
    id_user INT REFERENCES users(id),
    category INT REFERENCES category(id),
    id_state INT REFERENCES state_item(id),
    user_adress VARCHAR(30)
);

CREATE TABLE commentars(
    id SERIAL PRIMARY KEY,
    id_item INT REFERENCES item(id),
    txt VARCHAR(30)
);

CREATE TABLe attachs(
    id SERIAL PRIMARY KEY,
    id_item INT REFERENCES item(id),
    attach_file VARCHAR
);