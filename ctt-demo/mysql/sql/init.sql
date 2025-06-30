-- Initialize University Database
CREATE DATABASE IF NOT EXISTS university;
USE university;

-- ========================================
-- Table structure for table `address`
-- ========================================

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
  `id` int NOT NULL AUTO_INCREMENT,
  `street` varchar(100) NOT NULL,
  `city` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Insert initial data into `address`
INSERT INTO `address` VALUES 
  (1,'Jalan Gemilang','Petaling Jaya'),
  (2,'Jalan Terbilang','Taman Tun Dr Ismail');

-- ========================================
-- Table structure for table `student`
-- ========================================

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `address_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Insert initial data into `student`
INSERT INTO `student` VALUES 
  (1,'Samad','Bahari','samadbahari@gmail.com',1),
  (2,'Mokhtar','Darul','mokhtardarul@gmail.com',2);
