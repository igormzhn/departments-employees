CREATE TABLE IF NOT EXISTS departments(
    id BIGINT PRIMARY KEY,
    title VARCHAR(64)
);

CREATE SEQUENCE department_id
START 1
INCREMENT 1;