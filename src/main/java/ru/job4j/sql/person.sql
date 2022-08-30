CREATE TABLE person(
    id INTEGER NOT NULL,
    name CHARACTER VARYING,
    company_id INTEGER REFERENCES company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);