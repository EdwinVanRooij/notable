-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: athena01.fhict.local    Database: dbi324201
-- ------------------------------------------------------
-- Server version	5.5.11

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
-- Table structure for table `admin_connection`
--

DROP TABLE IF EXISTS `admin_connection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_connection` (
  `first_word_id` int(11) NOT NULL,
  `second_word_id` int(11) NOT NULL,
  `count` int(11) DEFAULT '0',
  PRIMARY KEY (`first_word_id`,`second_word_id`),
  KEY `first_word_idx` (`first_word_id`),
  KEY `after_word_idx` (`second_word_id`),
  CONSTRAINT `first_word` FOREIGN KEY (`first_word_id`) REFERENCES `admin_word` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `second_word` FOREIGN KEY (`second_word_id`) REFERENCES `admin_word` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_connection`
--

LOCK TABLES `admin_connection` WRITE;
/*!40000 ALTER TABLE `admin_connection` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin_connection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_word`
--

DROP TABLE IF EXISTS `admin_word`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_word` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `content_UNIQUE` (`content`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_word`
--

LOCK TABLES `admin_word` WRITE;
/*!40000 ALTER TABLE `admin_word` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin_word` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'dbi324201'
--

--
-- Dumping routines for database 'dbi324201'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-04  9:25:14
