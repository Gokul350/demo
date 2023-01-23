

alter table employee add column employeeDivisionId INT ;



CREATE table IF NOT EXISTS division(
id int NOT NULL AUTO_INCREMENT,
divisionName varchar(200) not null,
 PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET = utf8;


ALTER TABLE employee
ADD FOREIGN KEY (employeeDivisionId) REFERENCES division(id);
