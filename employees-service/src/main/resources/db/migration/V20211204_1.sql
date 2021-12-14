INSERT INTO users (id, username, password, authority)
VALUES
    (nextval('users_id_seq'), 'user', '$2a$12$JAW2YyKlL2B04lmdxb8lue6QAnhPOd66HiVaW7D2Sb4zO7bREARwW', 'user'),
    (nextval('users_id_seq'), 'admin', '$2a$12$ZIwP50WnVEIWBERD8mhzzOEX0ZJpz1NeuHRYDdsPJoFCL.n1jvTsq', 'admin')