CREATE TABLE IF NOT EXISTS employees(
  id BIGINT PRIMARY KEY ,
  name VARCHAR(16),
  lastname VARCHAR(32)
  );

CREATE SEQUENCE employees_id_seq
START 1
INCREMENT 1;