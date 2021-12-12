CREATE TABLE IF NOT EXISTS users
(
    id BIGINT PRIMARY KEY ,
    username  VARCHAR(32) UNIQUE not null,
    password  VARCHAR(60) not null,
    authority VARCHAR(32) not null
);

CREATE SEQUENCE users_id_seq
    START 1
    INCREMENT 1;