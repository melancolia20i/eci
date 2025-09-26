-- TABLAS

CREATE TABLE Staff (
    Staff_code VARCHAR(6),
    First_name VARCHAR(50),
    Last_name  VARCHAR(50),
    Level_code NUMBER(11)
)

CREATE TABLE Shift (
    Shift_date   DATE,
    Shift_type   VARCHAR(7),
    Manager      VARCHAR(7),
    Operator     VARCHAR(7),
    Engineer1    VARCHAR(7),
    Engineer2    VARCHAR(7)
)

CREATE TABLE Shift_type (
    Shift_type   VARCHAR(7),
    Start_time   VARCHAR(5),
    End_time     VARCHAR(5)
)

CREATE TABLE Levels (
    Level_code   NUMBER(11),
    Manager      CHAR(1),
    Operator     CHAR(1),
    Engineer     CHAR(1)
)

-- Atributos, Primarias, Unicas, Foraneas
ALTER TABLE Shift
ADD CONSTRAINT pk_shift PRIMARY KEY (Shift_date, Shift_type)

ALTER TABLE Staff
ADD CONSTRAINT pk_staff PRIMARY KEY (Staff_code)

ALTER TABLE Levels
ADD CONSTRAINT pk_levels PRIMARY KEY (Level_code)

ALTER TABLE Shift_type
ADD CONSTRAINT pk_shifttype PRIMARY KEY (Shift_type)

ALTER TABLE Shift
ADD CONSTRAINT fk_shift_shifttype
FOREIGN KEY (Shift_type)
REFERENCES Shift_type(Shift_type)

ALTER TABLE Shift
ADD CONSTRAINT fk_shift_manager_staff
FOREIGN KEY (Manager)
REFERENCES Staff(Staff_code)

ALTER TABLE Shift
ADD CONSTRAINT fk_shift_operator_staff
FOREIGN KEY (Operator)
REFERENCES Staff(Staff_code)

ALTER TABLE Shift
ADD CONSTRAINT fk_shift_eng1_staff
FOREIGN KEY (Engineer1)
REFERENCES Staff(Staff_code)

ALTER TABLE Shift
ADD CONSTRAINT fk_shift_eng2_staff
FOREIGN KEY (Engineer2)
REFERENCES Staff(Staff_code)

ALTER TABLE Staff
ADD CONSTRAINT fk_staff_levels
FOREIGN KEY (Level_code)
REFERENCES Levels(Level_code)

-- PoblarOK
INSERT INTO Levels VALUES (1, NULL, 'Y', NULL);
INSERT INTO Levels VALUES (2, NULL, NULL, 'Y');
INSERT INTO Levels VALUES (3, NULL, 'Y', 'Y');
INSERT INTO Levels VALUES (4, 'Y', NULL, 'Y');
INSERT INTO Levels VALUES (5, 'Y', 'Y', NULL);
INSERT INTO Levels VALUES (7, 'Y', NULL, 'Y');

INSERT INTO Staff VALUES ('AB1', 'Anthony', 'Butler', 1);
INSERT INTO Staff VALUES ('AB2', 'Alexis', 'Butler', 3);
INSERT INTO Staff VALUES ('AE1', 'Ava', 'Ellis', 7);
INSERT INTO Staff VALUES ('AL1', 'Alexander', 'Lawson', 3);
INSERT INTO Staff VALUES ('AW1', 'Alyssa', 'White', 1);
INSERT INTO Staff VALUES ('BJ1', 'Briony', 'Jones', 2);
INSERT INTO Staff VALUES ('DJ1', 'David', 'Jones', 2);
INSERT INTO Staff VALUES ('EB1', 'Emily', 'Butler', 2);
INSERT INTO Staff VALUES ('EB2', 'Emily', 'Best', 5);
INSERT INTO Staff VALUES ('EH1', 'Ethan', 'Hopgood', 3);

INSERT INTO Shift_type VALUES ('Early', '08:00', '14:00');
INSERT INTO Shift_type VALUES ('Late', '14:00', '20:00');

INSERT INTO Staff VALUES ('JE1', 'James', 'Evans', 3);
INSERT INTO Staff VALUES ('IM1', 'Isabel', 'Martinez', 2);
INSERT INTO Staff VALUES ('MM1', 'Michael', 'Moore', 3);
INSERT INTO Staff VALUES ('MW1', 'Mia', 'White', 3);
INSERT INTO Staff VALUES ('JP1', 'Julia', 'Parker', 3);
INSERT INTO Staff VALUES ('NS1', 'Nathan', 'Smith', 2);
INSERT INTO Staff VALUES ('HP1', 'Hannah', 'Peterson', 2);
INSERT INTO Staff VALUES ('EH1', 'Ethan', 'Hughes', 3);
INSERT INTO Staff VALUES ('ME1', 'Megan', 'Edwards', 3);
INSERT INTO Staff VALUES ('ME2', 'Mark', 'Ellis', 3);
INSERT INTO Staff VALUES ('MB1', 'Matthew', 'Brooks', 3);
INSERT INTO Staff VALUES ('LB1', 'Laura', 'Brown', 1);

INSERT INTO Shift VALUES (TO_DATE('2017-08-12', 'YYYY-MM-DD'), 'Early', 'LB1', 'AW1', 'AE1', 'JE1');
INSERT INTO Shift VALUES (TO_DATE('2017-08-12', 'YYYY-MM-DD'), 'Late', 'AE1', 'IM1', 'AL1', 'BJ1');
INSERT INTO Shift VALUES (TO_DATE('2017-08-13', 'YYYY-MM-DD'), 'Early', 'AE1', 'MM1', 'MW1', NULL);
INSERT INTO Shift VALUES (TO_DATE('2017-08-13', 'YYYY-MM-DD'), 'Late', 'AE1', 'EB1', 'AL1', NULL);
INSERT INTO Shift VALUES (TO_DATE('2017-08-14', 'YYYY-MM-DD'), 'Early', 'LB1', 'AB1', 'DJ1', 'JP1');
INSERT INTO Shift VALUES (TO_DATE('2017-08-14', 'YYYY-MM-DD'), 'Late', 'LB1', 'JE1', 'AB2', 'BJ1');
INSERT INTO Shift VALUES (TO_DATE('2017-08-15', 'YYYY-MM-DD'), 'Early', 'LB1', 'NS1', 'AE1', 'MB1');
INSERT INTO Shift VALUES (TO_DATE('2017-08-15', 'YYYY-MM-DD'), 'Late', 'LB1', 'HP1', 'EH1', 'AL1');
INSERT INTO Shift VALUES (TO_DATE('2017-08-16', 'YYYY-MM-DD'), 'Early', 'LB1', 'EB2', 'ME1', 'MM1');
INSERT INTO Shift VALUES (TO_DATE('2017-08-16', 'YYYY-MM-DD'), 'Late', 'LB1', 'ME2', 'AB2', 'DJ1');

-- Consultas

SELECT * FROM Shift




