DROP TABLE IF EXISTS `Traject_Semester`;
DROP TABLE IF EXISTS `Student_Email`;
DROP TABLE IF EXISTS `Student_Telnr`;
DROP TABLE IF EXISTS `Student_Traject`;
DROP TABLE IF EXISTS `Studie`;
DROP TABLE IF EXISTS `Stage`;
DROP TABLE IF EXISTS `Traject`;
DROP TABLE IF EXISTS `Exchange_Student`;
DROP TABLE IF EXISTS `HHS_Student`;
DROP TABLE IF EXISTS `Opleiding`;
DROP TABLE IF EXISTS `Bedrijf`;
DROP TABLE IF EXISTS `School`;
DROP TABLE IF EXISTS `Contactpersoon`;
DROP TABLE IF EXISTS `Student`;
CREATE TABLE `Student`
(
`student_id` int(15) NOT NULL AUTO_INCREMENT,
`voornaam` varchar(45) NOT NULL,
`tussenvoegsel` varchar(15),
`achternaam` varchar(45) NOT NULL,
`geslacht` enum('Male', 'Female') NOT NULL,
`email` char(30) NOT NULL,
`telnr_vast` char(20),
`telnr_mob` char(20),
PRIMARY KEY (`student_id`)) ENGINE=InnoDB;

CREATE TABLE `Bedrijf`
(
`bedrijf_id` int(15) NOT NULL AUTO_INCREMENT,
`bedrijfsnaam` varchar(45) NOT NULL,
`adres` varchar(45) NOT NULL,
`stad` varchar(45) NOT NULL,
`land` varchar(45) NOT NULL,
PRIMARY KEY (`bedrijf_id`)) ENGINE=InnoDB;

CREATE TABLE `School`
(
`school_id` int(15) NOT NULL AUTO_INCREMENT,
`schoolnaam` varchar(45) NOT NULL,
`stad` varchar(45) NOT NULL,
`land` varchar(45) NOT NULL,
PRIMARY KEY (`school_id`)) ENGINE=InnoDB;

CREATE TABLE `Contactpersoon`
(
`contpers_id` int(15) NOT NULL AUTO_INCREMENT,
`voornaam` varchar(45) NOT NULL,
`tussenvoegsel` varchar(15),
`achternaam` varchar(45) NOT NULL,
`email` varchar(45) NOT NULL,
`telnr` varchar(45) NOT NULL,
PRIMARY KEY (`contpers_id`)) ENGINE=InnoDB;

CREATE TABLE `Opleiding`
(
`opleiding_id` int(15) NOT NULL AUTO_INCREMENT,
`opleiding_naam` varchar(45) NOT NULL,
`contpers_id` int(15) NOT NULL,
`school_id` int(15) NOT NULL,
PRIMARY KEY (`opleiding_id`),
FOREIGN KEY (`contpers_id`) REFERENCES Contactpersoon(`contpers_id`) 
ON UPDATE CASCADE
ON DELETE RESTRICT,
FOREIGN KEY (`school_id`) REFERENCES School(`school_id`)
ON UPDATE CASCADE
ON DELETE RESTRICT) ENGINE=InnoDB;

CREATE TABLE `HHS_Student`
(
`student_id` int(15) NOT NULL,
`opleiding_id` int(15) NOT NULL,
PRIMARY KEY (`student_id`),
FOREIGN KEY (`student_id`) REFERENCES Student(`student_id`) 
ON UPDATE CASCADE
ON DELETE RESTRICT,
FOREIGN KEY (`opleiding_id`) REFERENCES Opleiding(`opleiding_id`)
ON UPDATE CASCADE
ON DELETE RESTRICT) ENGINE=InnoDB;

CREATE TABLE `Exchange_Student`
(
`student_id` int(15) NOT NULL,
`huisnummer` int(10) NOT NULL,
`straat` varchar(45) NOT NULL,
`woonplaats` varchar(45) NOT NULL,
`land` varchar(45) NOT NULL,
`school_id` int(15) NOT NULL, 
PRIMARY KEY (`student_id`),
FOREIGN KEY (`student_id`) REFERENCES Student(`student_id`) 
ON UPDATE CASCADE
ON DELETE RESTRICT,
FOREIGN KEY (`school_id`) REFERENCES School(`school_id`)
ON UPDATE CASCADE
ON DELETE RESTRICT) ENGINE=InnoDB;

CREATE TABLE `Traject`
(
`traject_id` int(15) NOT NULL AUTO_INCREMENT,
`naam` varchar(20) NOT NULL,
`jaar` int(4) NOT NULL,
`punten` int(6) NOT NULL,
`opleiding_id` int(15) NOT NULL,
PRIMARY KEY (`traject_id`),
FOREIGN KEY (`opleiding_id`) REFERENCES Opleiding(`opleiding_id`) 
ON UPDATE CASCADE
ON DELETE RESTRICT) ENGINE=InnoDB;

CREATE TABLE `Stage`
(
`traject_id` int(15) NOT NULL,
`bedrijf_id` int(15) NOT NULL,
PRIMARY KEY (`traject_id`),
FOREIGN KEY (`traject_id`) REFERENCES Traject(`traject_id`) 
ON UPDATE CASCADE
ON DELETE RESTRICT,
FOREIGN KEY (`bedrijf_id`) REFERENCES Bedrijf(`bedrijf_id`) 
ON UPDATE CASCADE
ON DELETE RESTRICT) ENGINE=InnoDB;

CREATE TABLE `Studie`
(
`traject_id` int(15) NOT NULL,
`school_id` int(15) NOT NULL,
`soort` enum('EPS', 'Summerschool', 'Minor'),
PRIMARY KEY (`traject_id`),
FOREIGN KEY (`traject_id`) REFERENCES Traject(`traject_id`) 
ON UPDATE CASCADE
ON DELETE RESTRICT,
FOREIGN KEY (`school_id`) REFERENCES School(`school_id`) 
ON UPDATE CASCADE
ON DELETE RESTRICT) ENGINE=InnoDB;

CREATE TABLE `Student_Traject`
(
`student_id` int(15) NOT NULL,
`traject_id` int(15) NOT NULL,
`behaalde_punten` int(10) NOT NULL,
`inschrijf_datum` date NOT NULL,
PRIMARY KEY (`student_id`,`traject_id`),
FOREIGN KEY (`student_id`) REFERENCES Student(`student_id`) 
ON UPDATE CASCADE
ON DELETE RESTRICT,
FOREIGN KEY (`traject_id`) REFERENCES Traject(`traject_id`) 
ON UPDATE CASCADE
ON DELETE RESTRICT) ENGINE=InnoDB;

CREATE TABLE `Traject_Semester`
(
`traject_id` int(15) NOT NULL,
`semester` enum('1','2','3','4','5') NOT NULL,
PRIMARY KEY (`traject_id`, `semester`),
FOREIGN KEY (`traject_id`) REFERENCES Traject(`traject_id`)
ON UPDATE CASCADE
ON DELETE RESTRICT) ENGINE=InnoDB;