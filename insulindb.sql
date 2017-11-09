-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 02, 2017 at 05:50 PM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 7.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `insulindb`
--

-- --------------------------------------------------------

--
-- Table structure for table `controller`
--

CREATE TABLE `controller` (
  `idPowerSupply` int(11) DEFAULT NULL,
  `idReservoir` int(11) DEFAULT NULL,
  `idSensor` int(11) DEFAULT NULL,
  `idPatient` int(11) DEFAULT NULL,
  `idTimeClock` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `idPatient` int(11) NOT NULL,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `bloodType` varchar(45) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `height` double DEFAULT NULL,
  `weight` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `powersupply`
--

CREATE TABLE `powersupply` (
  `idPowersupply` int(11) NOT NULL,
  `batteryLevel` int(11) DEFAULT NULL,
  `mode` varchar(45) DEFAULT NULL,
  `wattage` int(11) DEFAULT NULL,
  `model` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `reservoir`
--

CREATE TABLE `reservoir` (
  `idReservori` int(11) NOT NULL,
  `maxLevel` int(11) DEFAULT NULL,
  `minLevel` int(11) DEFAULT NULL,
  `currentLevel` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `sensor`
--

CREATE TABLE `sensor` (
  `idSensor` int(11) NOT NULL,
  `glucoseReading` int(11) DEFAULT NULL,
  `model` varchar(45) DEFAULT NULL,
  `safetyMin` int(14) NOT NULL,
  `safetyMax` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `timeclock`
--

CREATE TABLE `timeclock` (
  `Date` date NOT NULL,
  `Time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `idTimeClock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `controller`
--
ALTER TABLE `controller`
  ADD KEY `idPowerSupply_idx` (`idPowerSupply`),
  ADD KEY `idReservoir_idx` (`idReservoir`),
  ADD KEY `idSensor_idx` (`idSensor`),
  ADD KEY `idPatient` (`idPatient`),
  ADD KEY `idTimeClock` (`idTimeClock`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`idPatient`);

--
-- Indexes for table `powersupply`
--
ALTER TABLE `powersupply`
  ADD PRIMARY KEY (`idPowersupply`);

--
-- Indexes for table `reservoir`
--
ALTER TABLE `reservoir`
  ADD PRIMARY KEY (`idReservori`);

--
-- Indexes for table `sensor`
--
ALTER TABLE `sensor`
  ADD PRIMARY KEY (`idSensor`);

--
-- Indexes for table `timeclock`
--
ALTER TABLE `timeclock`
  ADD PRIMARY KEY (`idTimeClock`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `controller`
--
ALTER TABLE `controller`
  ADD CONSTRAINT `idPatient` FOREIGN KEY (`idPatient`) REFERENCES `patient` (`idPatient`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `idPowerSupply` FOREIGN KEY (`idPowerSupply`) REFERENCES `powersupply` (`idPowersupply`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `idReservoir` FOREIGN KEY (`idReservoir`) REFERENCES `reservoir` (`idReservori`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `idSensor` FOREIGN KEY (`idSensor`) REFERENCES `sensor` (`idSensor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `idTimeClock` FOREIGN KEY (`idTimeClock`) REFERENCES `timeclock` (`idTimeClock`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `patient`
--
ALTER TABLE `patient`
  ADD CONSTRAINT `patient_ibfk_1` FOREIGN KEY (`idPatient`) REFERENCES `controller` (`idPatient`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
