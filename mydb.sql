-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 03, 2017 at 04:43 AM
-- Server version: 10.1.26-MariaDB
-- PHP Version: 7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mydb`
--

-- --------------------------------------------------------

--
-- Table structure for table `controller`
--There shouldn't be tables for controllers and views. In the MVC architecture, tables are generally created for the model. Please focus mainly on your models when designing your database

CREATE TABLE `controller` (
  `idCONTROLLER` int(11) NOT NULL,
  `status` varchar(45) DEFAULT NULL,
  `maxDailyDose` int(11) DEFAULT NULL,
  `maxSingleDose` int(11) DEFAULT NULL,
  `minDose` int(11) DEFAULT NULL,
  `currentDose` int(11) DEFAULT NULL,
  `doseDelivered` int(11) DEFAULT NULL,
  `PUMP_idPUMP` int(11) NOT NULL,
  `POWERSUPPLY_idPOWERSUPPLY` int(11) NOT NULL,
  `RESERVOIR_idRESERVOIR` int(11) NOT NULL,
  `SENSOR_idSENSOR` int(11) NOT NULL,
  `DISPLAY_idDisplay` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `controller`
--
-- There shouldn't be tables for controllers and views. In the MVC architecture, tables are generally created for the model. Please focus mainly on your models when designing your database
INSERT INTO `controller` (`idCONTROLLER`, `status`, `maxDailyDose`, `maxSingleDose`, `minDose`, `currentDose`, `doseDelivered`, `PUMP_idPUMP`, `POWERSUPPLY_idPOWERSUPPLY`, `RESERVOIR_idRESERVOIR`, `SENSOR_idSENSOR`, `DISPLAY_idDisplay`) VALUES
(1, 'Running', 555, 34, 8, 20, 120, 1, 2, 1, 1, 3),
(2, 'Stopped', 600, 32, 14, 30, 240, 2, 1, 2, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `display`
--

CREATE TABLE `display` (
  `idDisplay` int(11) NOT NULL,
  `brightness` varchar(45) DEFAULT NULL,
  `orientation` varchar(45) DEFAULT NULL,
  `textSize` int(11) DEFAULT NULL,
  `resolution` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `display`
--

INSERT INTO `display` (`idDisplay`, `brightness`, `orientation`, `textSize`, `resolution`) VALUES
(1, NULL, 'Landscape', 12, '1024x768'),
(2, NULL, 'Portraid', 15, '900x1600'),
(3, NULL, 'Landscape', 14, '1920x1080');

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `idPATIENT` int(11) NOT NULL,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `bloodType` varchar(45) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `height` int(11) NOT NULL,
  `weight` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `patient`
-- good, this is a model

INSERT INTO `patient` (`idPATIENT`, `firstName`, `lastName`, `bloodType`, `age`, `gender`, `height`, `weight`) VALUES
(1, 'Freddie', 'Brian', 'O-', 34, 'Male', 6, 180),
(2, 'Marie', 'Young', 'A+', 55, 'Female', 6, 160);

-- --------------------------------------------------------

--
-- Table structure for table `powersupply`
-- this is good

CREATE TABLE `powersupply` (
  `idPOWERSUPPLY` int(11) NOT NULL,
  `batteryLevel` int(11) DEFAULT NULL,
  `mode` varchar(45) DEFAULT NULL,
  `currentUsage` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `powersupply`
--

INSERT INTO `powersupply` (`idPOWERSUPPLY`, `batteryLevel`, `mode`, `currentUsage`) VALUES
(1, 88, 'AC', 11),
(2, 100, 'DC', 0),
(3, 44, 'DC', 14);

-- --------------------------------------------------------

--
-- Table structure for table `pump`
-- this could be an attribute in another table

CREATE TABLE `pump` (
  `idPUMP` int(11) NOT NULL,
  `doseRate` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `pump`
--

INSERT INTO `pump` (`idPUMP`, `doseRate`) VALUES
(1, 10),
(2, 20),
(3, 30);

-- --------------------------------------------------------

--
-- Table structure for table `reservoir`
--good

CREATE TABLE `reservoir` (
  `idRESERVOIR` int(11) NOT NULL,
  `maxLevel` int(11) NOT NULL,
  `minLevel` int(11) NOT NULL,
  `currenLevel` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `reservoir`
--

INSERT INTO `reservoir` (`idRESERVOIR`, `maxLevel`, `minLevel`, `currenLevel`) VALUES
(1, 200, 20, 155),
(2, 400, 20, 399);

-- --------------------------------------------------------

--
-- Table structure for table `sensor`
--could be an attribute

CREATE TABLE `sensor` (
  `idSENSOR` int(11) NOT NULL,
  `glucoseReading` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sensor`
--

INSERT INTO `sensor` (`idSENSOR`, `glucoseReading`) VALUES
(1, 121),
(2, 110),
(3, 131),
(4, 111);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `controller`
--There shouldn't be tables for controllers and views. In the MVC architecture, tables are generally created for the model. Please focus mainly on your models when designing your database
ALTER TABLE `controller`
  ADD PRIMARY KEY (`idCONTROLLER`),
  ADD UNIQUE KEY `idCONTROLLER_UNIQUE` (`idCONTROLLER`),
  ADD KEY `fk_CONTROLLER_PUMP1_idx` (`PUMP_idPUMP`),
  ADD KEY `fk_CONTROLLER_POWERSUPPLY1_idx` (`POWERSUPPLY_idPOWERSUPPLY`),
  ADD KEY `fk_CONTROLLER_RESERVOIR1_idx` (`RESERVOIR_idRESERVOIR`),
  ADD KEY `fk_CONTROLLER_SENSOR1_idx` (`SENSOR_idSENSOR`),
  ADD KEY `fk_CONTROLLER_DISPLAY1_idx` (`DISPLAY_idDisplay`);

--
-- Indexes for table `display`
--There shouldn't be tables for controllers and views. In the MVC architecture, tables are generally created for the model. Please focus mainly on your models when designing your database
ALTER TABLE `display`
  ADD PRIMARY KEY (`idDisplay`),
  ADD UNIQUE KEY `DISPLAYcol_UNIQUE` (`idDisplay`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`idPATIENT`),
  ADD UNIQUE KEY `idPATIENT_UNIQUE` (`idPATIENT`);

--
-- Indexes for table `powersupply`
--
ALTER TABLE `powersupply`
  ADD PRIMARY KEY (`idPOWERSUPPLY`),
  ADD UNIQUE KEY `idPOWERSUPPLY_UNIQUE` (`idPOWERSUPPLY`);

--
-- Indexes for table `pump`
--
ALTER TABLE `pump`
  ADD PRIMARY KEY (`idPUMP`),
  ADD UNIQUE KEY `idPUMP_UNIQUE` (`idPUMP`);

--
-- Indexes for table `reservoir`
--
ALTER TABLE `reservoir`
  ADD PRIMARY KEY (`idRESERVOIR`),
  ADD UNIQUE KEY `idRESERVOIR_UNIQUE` (`idRESERVOIR`);

--
-- Indexes for table `sensor`
--
ALTER TABLE `sensor`
  ADD PRIMARY KEY (`idSENSOR`),
  ADD UNIQUE KEY `idSENSOR_UNIQUE` (`idSENSOR`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `controller`
--
ALTER TABLE `controller`
  MODIFY `idCONTROLLER` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `display`
--
ALTER TABLE `display`
  MODIFY `idDisplay` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `patient`
--
ALTER TABLE `patient`
  MODIFY `idPATIENT` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `powersupply`
--
ALTER TABLE `powersupply`
  MODIFY `idPOWERSUPPLY` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `pump`
--
ALTER TABLE `pump`
  MODIFY `idPUMP` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `reservoir`
--
ALTER TABLE `reservoir`
  MODIFY `idRESERVOIR` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `sensor`
--
ALTER TABLE `sensor`
  MODIFY `idSENSOR` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `controller`
--There shouldn't be tables for controllers and views. In the MVC architecture, tables are generally created for the model. Please focus mainly on your models when designing your database
ALTER TABLE `controller`
  ADD CONSTRAINT `fk_CONTROLLER_DISPLAY1` FOREIGN KEY (`DISPLAY_idDisplay`) REFERENCES `display` (`idDisplay`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_CONTROLLER_POWERSUPPLY1` FOREIGN KEY (`POWERSUPPLY_idPOWERSUPPLY`) REFERENCES `powersupply` (`idPOWERSUPPLY`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_CONTROLLER_PUMP1` FOREIGN KEY (`PUMP_idPUMP`) REFERENCES `pump` (`idPUMP`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_CONTROLLER_RESERVOIR1` FOREIGN KEY (`RESERVOIR_idRESERVOIR`) REFERENCES `reservoir` (`idRESERVOIR`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_CONTROLLER_SENSOR1` FOREIGN KEY (`SENSOR_idSENSOR`) REFERENCES `sensor` (`idSENSOR`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `patient`
--
ALTER TABLE `patient`
  ADD CONSTRAINT `patient_ibfk_1` FOREIGN KEY (`idPATIENT`) REFERENCES `controller` (`idCONTROLLER`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
