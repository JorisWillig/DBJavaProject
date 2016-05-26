DROP TABLE IF EXISTS `Student`;
CREATE TABLE `Student`
(
`student_id` int(15) NOT NULL,
`naam` varchar(45) NOT NULL,
`tussenvoegsel` varchar(15),
`achternaam` varchar(45) NOT NULL,
`geslacht` enum('M', 'V') NOT NULL,
PRIMARY KEY (`student_id`));

DROP TABLE IF EXISTS `Bedrijf`;
CREATE TABLE `Bedrijf`
(
`bedrijf_id` int(15) NOT NULL,
`bedrijfsnaam` varchar(45) NOT NULL,
`adres` varchar(45) NOT NULL,
`postcode` varchar(10) NOT NULL,
`stad` varchar(45) NOT NULL,
`land` varchar(45) NOT NULL,
PRIMARY KEY (`bedrijf_id`));

DROP TABLE IF EXISTS `School`;
CREATE TABLE `School`
(
`school_id` int(15) NOT NULL,
`schoolnaam` varchar(45) NOT NULL,
`stad` varchar(45) NOT NULL,
`land` varchar(45) NOT NULL,
PRIMARY KEY (`school_id`));

DROP TABLE IF EXISTS `Contactpersoon`;
CREATE TABLE `Contactpersoon`
(
`contpers_id` int(15) NOT NULL,
`voornaam` varchar(45) NOT NULL,
`tussenvoegsel` varchar(15) NOT NULL,
`achternaam` varchar(45) NOT NULL,
`email` varchar(45) NOT NULL,
`telnr` varchar(45) NOT NULL,
PRIMARY KEY (`contpers_id`));

DROP TABLE IF EXISTS `Semester`;
CREATE TABLE `Semester`
(
`semester_code` varchar(8) NOT NULL,
`start_datum` date NOT NULL,
`eind_datum` date NOT NULL,
PRIMARY KEY (`semester_code`));

DROP TABLE IF EXISTS `Opleiding`;
CREATE TABLE `Opleiding`
(
`opleiding_id` int(15) NOT NULL,
`opleiding_naam` varchar(45) NOT NULL,
`contpers_id` int(15) NOT NULL,
`school_id` int(15) NOT NULL,
PRIMARY KEY (`opleiding_id`),
FOREIGN KEY (contpers_id) REFERENCES Contactpersoon(contpers_id),
FOREIGN KEY (school_id) REFERENCES School(school_id));