CREATE DATABASE  IF NOT EXISTS `insulindb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `insulindb`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: insulindb
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.26-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `instruments`
--

DROP TABLE IF EXISTS `instruments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `instruments` (
  `idPowerSupply` int(11) DEFAULT NULL,
  `idReservoir` int(11) DEFAULT NULL,
  `idSensor` int(11) DEFAULT NULL,
  KEY `idPowerSupply_idx` (`idPowerSupply`),
  KEY `idReservoir_idx` (`idReservoir`),
  KEY `idSensor_idx` (`idSensor`),
  CONSTRAINT `idPowerSupply` FOREIGN KEY (`idPowerSupply`) REFERENCES `powersupply` (`idPowersupply`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idReservoir` FOREIGN KEY (`idReservoir`) REFERENCES `reservoir` (`idReservori`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idSensor` FOREIGN KEY (`idSensor`) REFERENCES `sensor` (`idSensor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instruments`
--

LOCK TABLES `instruments` WRITE;
/*!40000 ALTER TABLE `instruments` DISABLE KEYS */;
/*!40000 ALTER TABLE `instruments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient` (
  `idPatient` int(11) NOT NULL,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `bloodType` varchar(45) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `height` double DEFAULT NULL,
  `weight` double DEFAULT NULL,
  PRIMARY KEY (`idPatient`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `powersupply`
--

DROP TABLE IF EXISTS `powersupply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `powersupply` (
  `idPowersupply` int(11) NOT NULL,
  `batteryLevel` int(11) DEFAULT NULL,
  `mode` varchar(45) DEFAULT NULL,
  `wattage` int(11) DEFAULT NULL,
  `model` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idPowersupply`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `powersupply`
--

LOCK TABLES `powersupply` WRITE;
/*!40000 ALTER TABLE `powersupply` DISABLE KEYS */;
/*!40000 ALTER TABLE `powersupply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservoir`
--

DROP TABLE IF EXISTS `reservoir`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservoir` (
  `idReservori` int(11) NOT NULL,
  `maxLevel` int(11) DEFAULT NULL,
  `minLevel` int(11) DEFAULT NULL,
  `currentLevel` int(11) DEFAULT NULL,
  PRIMARY KEY (`idReservori`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservoir`
--

LOCK TABLES `reservoir` WRITE;
/*!40000 ALTER TABLE `reservoir` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservoir` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sensor`
--

DROP TABLE IF EXISTS `sensor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sensor` (
  `idSensor` int(11) NOT NULL,
  `glucoseReading` int(11) DEFAULT NULL,
  `model` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idSensor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sensor`
--

LOCK TABLES `sensor` WRITE;
/*!40000 ALTER TABLE `sensor` DISABLE KEYS */;
/*!40000 ALTER TABLE `sensor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-08 23:27:31
