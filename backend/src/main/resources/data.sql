INSERT INTO department (name, status)
VALUES ('Sistemas', 'A'),
       ('Contabilidad', 'A'),
       ('RRHH', 'I'),
       ('People', 'A');

INSERT INTO employee (first_name, last_name, age, role, salary, start_date, end_date, status, department_id)
VALUES ('Luis', 'Pérez', 22, 'Desarrollador', 500.00, '2021-02-10', NULL, 'A', 1),
       ('María', 'González', 25, 'Desarrollador', NULL, '2020-03-11', NULL, 'A', 1),
       ('Pedro', 'Gómez', 30, 'Analista', NULL, '2020-03-11', '2024-05-20', 'I', 2),
       ('José', 'López', 20, 'Contador', NULL, NULL, NULL, 'A', 2);
