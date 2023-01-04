CREATE DATABASE `employee_registration` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;


DROP TABLE IF EXISTS `assessment`;

CREATE TABLE `assessment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `atype` varchar(45) DEFAULT NULL,
  `aname` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `aname_UNIQUE` (`aname`)
) ENGINE=InnoDB AUTO_INCREMENT=132 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `empid` int DEFAULT NULL,
  `atype` varchar(45) DEFAULT NULL,
  `aname` varchar(45) DEFAULT NULL,
  `adate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `empid` int NOT NULL,
  `Password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`empid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


commit;

