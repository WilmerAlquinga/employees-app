DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS department;

CREATE TABLE department
(
    id     BIGINT PRIMARY KEY AUTO_INCREMENT,
    name   VARCHAR(128) NOT NULL,
    status CHARACTER    NOT NULL
);

CREATE TABLE employee
(
    id            BIGINT PRIMARY KEY AUTO_INCREMENT,
    department_id BIGINT,
    first_name    VARCHAR(128) NOT NULL,
    last_name     VARCHAR(128) NOT NULL,
    age           INT          NOT NULL,
    role          VARCHAR(64),
    salary        DECIMAL(10, 2),
    start_date    DATE,
    end_date      DATE,
    status        CHARACTER,
    CONSTRAINT fk_department FOREIGN KEY (department_id) REFERENCES department (id)
);
