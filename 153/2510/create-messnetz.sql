-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: messnetz
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.16-MariaDB

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
-- Table structure for table `messnetz`
--

DROP TABLE IF EXISTS `messnetz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messnetz` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messnetz`
--

LOCK TABLES `messnetz` WRITE;
/*!40000 ALTER TABLE `messnetz` DISABLE KEYS */;
INSERT INTO `messnetz` VALUES (1,'messnetz-zuerich');
/*!40000 ALTER TABLE `messnetz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messung`
--

DROP TABLE IF EXISTS `messung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messung` (
  `station_id` int(11) NOT NULL,
  `wetter_datum` date NOT NULL,
  `temperatur` float(3,1) DEFAULT NULL,
  `so2` int(11) DEFAULT NULL,
  PRIMARY KEY (`wetter_datum`,`station_id`),
  KEY `fk_messung_station1_idx` (`station_id`),
  KEY `fk_messung_wetter1_idx` (`wetter_datum`),
  CONSTRAINT `fk_messung_station1` FOREIGN KEY (`station_id`) REFERENCES `station` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_messung_wetter1` FOREIGN KEY (`wetter_datum`) REFERENCES `wetter` (`datum`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messung`
--

LOCK TABLES `messung` WRITE;
/*!40000 ALTER TABLE `messung` DISABLE KEYS */;
INSERT INTO `messung` VALUES (1,'2016-05-13',35.2,4);
/*!40000 ALTER TABLE `messung` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `station`
--

DROP TABLE IF EXISTS `station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `station` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `messnetz_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_station_messnetz1_idx` (`messnetz_id`),
  CONSTRAINT `fk_station_messnetz1` FOREIGN KEY (`messnetz_id`) REFERENCES `messnetz` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `station`
--

LOCK TABLES `station` WRITE;
/*!40000 ALTER TABLE `station` DISABLE KEYS */;
INSERT INTO `station` VALUES (1,'Adlikon',1),(2,'Adliswil',1),(3,'Bassersdorf',1),(4,'Egg',1);
/*!40000 ALTER TABLE `station` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wetter`
--

DROP TABLE IF EXISTS `wetter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wetter` (
  `datum` date NOT NULL,
  `bewoelkung` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`datum`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wetter`
--

LOCK TABLES `wetter` WRITE;
/*!40000 ALTER TABLE `wetter` DISABLE KEYS */;
INSERT INTO `wetter` VALUES ('2016-05-13','schwach');
/*!40000 ALTER TABLE `wetter` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-20 13:54:41
