INSERT INTO student (id, name, secondname, email, password)
VALUES ('7d045dd1-0088-4282-86e5-38f336ef05f2', 'Pepe', 'Pino', 'pepe@tecnocampus.cat',
        '{bcrypt}$2a$10$fVKfcc47q6lrNbeXangjYeY000dmjdjkdBxEOilqhapuTO5ZH0co2');
INSERT INTO student (id, name, secondname, email, password)
VALUES ('74a6522f-706a-49a9-b98f-a0c8fa272434', 'Pepa', 'Pino', 'pepa@tecnocampus.cat',
        '{bcrypt}$2a$10$fVKfcc47q6lrNbeXangjYeY000dmjdjkdBxEOilqhapuTO5ZH0co2');
INSERT INTO student (id, name, secondname, email, password)
VALUES ('3104e2a2-126c-4473-ac78-304fc04e337f', 'Maria', 'Fontaneda', 'maria@tecnocampus.cat',
        '{bcrypt}$2a$10$fVKfcc47q6lrNbeXangjYeY000dmjdjkdBxEOilqhapuTO5ZH0co2');
INSERT INTO student (id, name, secondname, email, password)
VALUES ('cd4fa027-36b6-4639-86b8-834fe83b2e4a', 'Mario', 'Fontaneda', 'mario@tecnocampus.cat',
        '{bcrypt}$2a$10$fVKfcc47q6lrNbeXangjYeY000dmjdjkdBxEOilqhapuTO5ZH0co2');

INSERT INTO classroom (name, capacity, orientation, plugs)
VALUES ('104', 4, 'S', 1);
INSERT INTO classroom (name, capacity, orientation, plugs)
VALUES ('105', 2, 'SW', 1);
INSERT INTO classroom (name, capacity, orientation, plugs)
VALUES ('106', 3, 'N', 1);
INSERT INTO classroom (name, capacity, orientation, plugs)
VALUES ('107', 2, 'W', 1);

INSERT INTO allocation (student, classroom, dayofweek)
VALUES ('7d045dd1-0088-4282-86e5-38f336ef05f2', '104', 'MONDAY');
INSERT INTO allocation (student, classroom, dayofweek)
VALUES ('7d045dd1-0088-4282-86e5-38f336ef05f2', '104', 'TUESDAY');
INSERT INTO allocation (student, classroom, dayofweek)
VALUES ('7d045dd1-0088-4282-86e5-38f336ef05f2', '104', 'WEDNESDAY');
INSERT INTO allocation (student, classroom, dayofweek)
VALUES ('7d045dd1-0088-4282-86e5-38f336ef05f2', '105', 'THURSDAY');
INSERT INTO allocation (student, classroom, dayofweek)
VALUES ('7d045dd1-0088-4282-86e5-38f336ef05f2', '105', 'FRIDAY');
INSERT INTO allocation (student, classroom, dayofweek)
VALUES ('74a6522f-706a-49a9-b98f-a0c8fa272434', '105', 'FRIDAY');

INSERT INTO authorities (username, role)
VALUES ('Pepe', 'ROLE_STUDENT');
INSERT INTO authorities (username, role)
VALUES ('Pepa', 'ROLE_ADMIN');
INSERT INTO authorities (username, role)
VALUES ('Mario', 'ROLE_STUDENT');
INSERT INTO authorities (username, role)
VALUES ('Maria', 'ROLE_ADMIN');