ALTER TABLE employees
    RENAME COLUMN title TO department_id;

ALTER TABLE employees
    ALTER COLUMN department_id TYPE INTEGER USING (department_id::integer);
