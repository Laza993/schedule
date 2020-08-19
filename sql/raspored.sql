DROP SCHEMA IF EXISTS schedulee;
CREATE SCHEMA schedulee DEFAULT CHARACTER SET utf8;
USE schedulee;

CREATE TABLE teaching (
	id INT AUTO_INCREMENT, 
	namee VARCHAR(20) NOT NULL, 
	PRIMARY KEY(id)
);

CREATE TABLE lecure (
	id BIGINT AUTO_INCREMENT,
	days ENUM('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday') DEFAULT 'Monday', 
	groupp VARCHAR(3) NOT NULL, 
	fromm time NOT NULL, 
	too time NOT NULL, 
	clasroom VARCHAR(3) NOT NULL, 
	teaching INT NOT NULL, 
	Subjectt VARCHAR(50) NOT NULL, 
	teacher VARCHAR(50) NOT NULL, 
	PRIMARY KEY(id), 
	FOREIGN KEY(teaching) REFERENCES teaching(id)
		ON DELETE RESTRICT
);

CREATE TABLE users (
	user_name VARCHAR(20),
	passwords VARCHAR(20) NOT NULL,
	roles ENUM('teacher', 'student') DEFAULT 'student', 
	PRIMARY KEY(user_name)
);

INSERT INTO teaching (id, namee) VALUES (1, 'theory');
INSERT INTO teaching (id, namee) VALUES (2, 'practice');

INSERT INTO lecure (days, groupp, fromm, too, clasroom, teaching, Subjectt, teacher) VALUES ('Monday', 'svi', '09:00:00', '10:30:00', '101', 1, 'mathematics','teacher 1');
INSERT INTO lecure (days, groupp, fromm, too, clasroom, teaching, Subjectt, teacher) VALUES ('Monday', '1', '10:45:00', '12:15:00', '201', 2, 'mathematics','techer 2');
INSERT INTO lecure (days, groupp, fromm, too, clasroom, teaching, Subjectt, teacher) VALUES ('Monday', '2', '10:45:00', '12:15:00', '202', 2, 'mathematics','techer 3');
INSERT INTO lecure (days, groupp, fromm, too, clasroom, teaching, Subjectt, teacher) VALUES ('Tuesday', 'svi', '09:00:00', '10:30:00', '101', 1, 'physics','techer 4');
INSERT INTO lecure (days, groupp, fromm, too, clasroom, teaching, Subjectt, teacher) VALUES ('Wednesday', '1', '09:00:00', '12:15:00', '201', 2, 'physics','techer 2');
INSERT INTO lecure (days, groupp, fromm, too, clasroom, teaching, Subjectt, teacher) VALUES ('Thursday', '2', '09:00:00', '12:15:00', '202', 2, 'physics','techer 3');

INSERT INTO users (user_name, passwords, roles) VALUES ('n', 'n', 'teacher');
INSERT INTO users (user_name, passwords, roles) VALUES ('s', 's', 'student');
