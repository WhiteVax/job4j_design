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
    role_user INT REFERENCES role(id),
    rule VARCHAR(15)
);

CREATE TABLE commentars(
    id SERIAL PRIMARY KEY,
    txt VARCHAR(30)
);

CREATE TABLe attachs(
    id_attach SERIAL PRIMARY KEY,
    attach_file VARCHAR
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
    commentar INT REFERENCES commentars(id),
    id_attachs INT REFERENCES attachs(id_attach),
    user_adress VARCHAR(30)
);