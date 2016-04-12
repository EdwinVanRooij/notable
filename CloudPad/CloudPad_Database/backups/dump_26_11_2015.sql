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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(15) NOT NULL,
  `email` varchar(25) NOT NULL,
  `password` text NOT NULL,
  `creation` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `blocked` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'username1','email1','password1','2015-11-13 08:54:06',0),(2,'swag','email2','password2','2015-11-13 08:54:06',0),(3,'username3','email3','password3','2015-11-13 08:54:06',0),(4,'admin','a','televisie9','2015-11-13 09:06:25',0),(6,'a','b','b','2015-11-15 11:52:30',0),(7,'c','c','c','2015-11-15 11:52:30',0),(8,'d','d','d','2015-11-15 11:57:46',0),(10,'f','f','f','2015-11-15 11:57:46',0),(11,'g','g','g','2015-11-15 11:57:46',0),(12,'gummybeerninja','nancy-bust@hotmail.com','nancy1997','2015-11-16 11:26:24',0);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `boodschappen_product`
--

DROP TABLE IF EXISTS `boodschappen_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `boodschappen_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `amount` int(11) DEFAULT '1',
  `remark` varchar(255) DEFAULT 'Geen opmerking.',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `boodschappen_product`
--

LOCK TABLES `boodschappen_product` WRITE;
/*!40000 ALTER TABLE `boodschappen_product` DISABLE KEYS */;
INSERT INTO `boodschappen_product` VALUES (42,'Kwark naturel',1,'Merk: skyr'),(43,'Kwark aardbei/framboos',1,'Merk jumbo');
/*!40000 ALTER TABLE `boodschappen_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `connection`
--

DROP TABLE IF EXISTS `connection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `connection` (
  `word_id` int(11) NOT NULL,
  `after_word_id` int(11) NOT NULL,
  `count` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `connection`
--

LOCK TABLES `connection` WRITE;
/*!40000 ALTER TABLE `connection` DISABLE KEYS */;
/*!40000 ALTER TABLE `connection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` int(11) NOT NULL,
  `type` varchar(150) NOT NULL,
  `description` text NOT NULL,
  `date_sent` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `active` int(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_account_feedback_idx` (`account_id`),
  CONSTRAINT `fk_feedback_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (8,4,'Bug','Fout een','2015-11-19 12:50:49',0),(11,4,'Bug','Jaa','2015-11-19 22:49:50',0),(12,4,'Bug','Gekke fout','2015-11-20 20:57:38',0),(13,4,'Suggestion','Swag','2015-11-23 19:28:16',0),(14,4,'Suggestion','Jaa','2015-11-23 21:33:23',0);
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `note`
--

DROP TABLE IF EXISTS `note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `note` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject` text,
  `text` text,
  `active` varchar(1) DEFAULT '1',
  `creation` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_edited` timestamp NULL DEFAULT NULL,
  `owner_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `owner_of_note_idx` (`owner_id`),
  CONSTRAINT `owner_of_note` FOREIGN KEY (`owner_id`) REFERENCES `account` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=367 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `note`
--

LOCK TABLES `note` WRITE;
/*!40000 ALTER TABLE `note` DISABLE KEYS */;
INSERT INTO `note` VALUES (1,'subject1','text1','1','2015-11-13 08:54:06','2015-11-16 17:57:10',1),(2,'subject2','text2','1','2015-11-13 08:54:07','2015-11-16 17:57:10',3),(9,'Waarom CloudPad?','Hier alvast wa dingen opsommen waarvoor je het kunt gebruiken \n\n- snelle aantekeningen maken voor je studie\n- alles online opgeslagen dus je raakt het nooit kwijt & kan er overal bij\n- hoor je een liedje en wil je deze downloaden maar heb je weinig tijd, maak snel een cptje aan\n- gedeelde afspraken maken met je klasgenoten,  vriendengroep, gezin/familie\n- vergeet nooit meer je boodschappenlijstje, maak er gewoon een cptje voor en je kan het in de winkel gewoon weer bekijken\n- gebruiksvriendelijk','0','2015-11-13 09:23:27','2015-11-26 09:22:28',4),(110,'Prive','','0','2015-11-16 18:10:43','2015-11-22 18:01:51',4),(113,'subject test','text test','1','2015-11-17 12:06:33','2015-11-17 12:06:23',NULL),(120,'PrivÃ©','Iban:\nNL92RABO0157701484\n\nNL92 RABO 0157 7014 48\n\nHuisarts nr: 541440\n\nKaspersky activate code: \n4SYAK-D4HKA-D3T9E-74624','0','2015-11-17 18:51:58','2015-11-23 15:32:11',4),(125,'Kroaats','Ja - da\nNee - ne\n\nIk - ja\nJij - ti\nHij - on\nZij - ona\nHet - ono\nJullie - vi\nWij - mi\nU - vi\n\nHallo - bok\nDoei - pozdrav\n\nGoed - dobro\nDankje - hvala\nNatuurlijk - naravno','0','2015-11-18 09:35:33','2015-11-23 15:32:11',4),(142,'Opstap','Hallo','0','2015-11-21 10:50:21','2015-11-26 09:22:32',4),(146,'Huiswerk','------------------------------\nBeer voor nanc kerst','0','2015-11-21 20:37:52','2015-11-23 15:32:11',4),(148,'','','1','2015-11-22 12:57:13','2015-11-22 17:44:28',4),(160,'Wifi','Oma no:\n%_!V@ND3NBR03K!_%','0','2015-11-22 16:18:12','2015-11-23 15:32:11',4),(163,'','','0','2015-11-22 17:44:07','2015-11-22 17:44:31',4),(164,'Jacuzzi verschonen','J','0','2015-11-22 21:03:44','2015-11-23 15:32:11',4),(165,'Jj','Jj','0','2015-11-23 14:28:24','2015-11-26 09:22:30',4),(166,'Kk','Kkk','0','2015-11-23 14:30:11','2015-11-23 22:20:19',4),(167,'','','0','2015-11-23 14:32:10','2015-11-23 14:37:53',4),(168,'','','0','2015-11-23 14:37:38','2015-11-23 14:37:51',4),(169,'','','0','2015-11-23 14:38:14','2015-11-23 14:42:48',4),(170,'','','0','2015-11-23 14:40:03','2015-11-23 14:42:51',4),(171,'','','0','2015-11-23 14:40:26','2015-11-23 14:42:45',4),(172,'','','0','2015-11-23 14:41:27','2015-11-23 14:42:41',4),(173,'','','0','2015-11-23 14:41:40','2015-11-23 15:32:11',4),(174,'','','0','2015-11-23 14:43:46','2015-11-23 15:32:11',4),(175,'','','0','2015-11-23 15:16:21','2015-11-23 15:32:11',4),(176,'','','0','2015-11-23 15:16:23','2015-11-23 15:32:11',4),(177,'','','0','2015-11-23 15:16:42','2015-11-23 15:32:11',4),(178,'','','0','2015-11-23 15:33:55','2015-11-23 15:33:57',4),(179,'','','0','2015-11-23 15:41:31','2015-11-23 15:41:31',4),(180,'PrivÃ©','Iban:\nNL92RABO0157701484\n\nNL92 RABO 0157 7014 48\n\nHuisarts nr: 541440\n\nKaspersky activate code: \n4SYAK-D4HKA-D3T9E-74624','1','2015-11-23 15:41:31',NULL,4),(181,'Kroaats','Ja - da\nNee - ne\n\nIk - ja\nJij - ti\nHij - on\nZij - ona\nHet - ono\nJullie - vi\nWij - mi\nU - vi\n\nHallo - bok\nDoei - pozdrav\n\nGoed - dobro\nDankje - hvala\nNatuurlijk - naravno','1','2015-11-23 15:41:31',NULL,4),(182,'Huiswerk','------------------------------\nBeer voor nanc kerst','1','2015-11-23 15:41:31',NULL,4),(183,'Wifi','Oma no:\n%_!V@ND3NBR03K!_%','1','2015-11-23 15:41:31',NULL,4),(184,'Jacuzzi verschonen','J','1','2015-11-23 15:41:31',NULL,4),(185,'','','1','2015-11-23 15:41:31',NULL,4),(186,'','','1','2015-11-23 15:41:31',NULL,4),(187,'','','0','2015-11-23 15:41:37','2015-11-23 15:41:37',4),(188,'PrivÃ©','Iban:\nNL92RABO0157701484\n\nNL92 RABO 0157 7014 48\n\nHuisarts nr: 541440\n\nKaspersky activate code: \n4SYAK-D4HKA-D3T9E-74624','1','2015-11-23 15:41:37',NULL,4),(189,'Kroaats','Ja - da\nNee - ne\n\nIk - ja\nJij - ti\nHij - on\nZij - ona\nHet - ono\nJullie - vi\nWij - mi\nU - vi\n\nHallo - bok\nDoei - pozdrav\n\nGoed - dobro\nDankje - hvala\nNatuurlijk - naravno','1','2015-11-23 15:41:37',NULL,4),(190,'Huiswerk','------------------------------\nBeer voor nanc kerst','1','2015-11-23 15:41:37',NULL,4),(191,'Wifi','Oma no:\n%_!V@ND3NBR03K!_%','1','2015-11-23 15:41:37',NULL,4),(192,'Jacuzzi verschonen','J','1','2015-11-23 15:41:37',NULL,4),(193,'','','1','2015-11-23 15:41:37',NULL,4),(194,'','','1','2015-11-23 15:41:37',NULL,4),(195,'','','0','2015-11-23 15:41:48','2015-11-23 15:42:30',4),(196,'PrivÃ©','Iban:\nNL92RABO0157701484\n\nNL92 RABO 0157 7014 48\n\nHuisarts nr: 541440\n\nKaspersky activate code: \n4SYAK-D4HKA-D3T9E-74624','1','2015-11-23 15:41:49',NULL,4),(197,'Kroaats','Ja - da\nNee - ne\n\nIk - ja\nJij - ti\nHij - on\nZij - ona\nHet - ono\nJullie - vi\nWij - mi\nU - vi\n\nHallo - bok\nDoei - pozdrav\n\nGoed - dobro\nDankje - hvala\nNatuurlijk - naravno','1','2015-11-23 15:41:49',NULL,4),(198,'Huiswerk','------------------------------\nBeer voor nanc kerst','1','2015-11-23 15:41:49',NULL,4),(199,'Wifi','Oma no:\n%_!V@ND3NBR03K!_%','1','2015-11-23 15:41:49',NULL,4),(200,'Jacuzzi verschonen','J','1','2015-11-23 15:41:49',NULL,4),(201,'','','1','2015-11-23 15:41:49',NULL,4),(202,'','','1','2015-11-23 15:41:49',NULL,4),(203,'','','1','2015-11-23 15:46:08',NULL,4),(204,'','','1','2015-11-23 15:46:13',NULL,4),(205,'','','1','2015-11-23 15:47:27',NULL,4),(206,'','','0','2015-11-23 15:48:11','2015-11-23 15:48:12',4),(207,'PrivÃ©','Iban:\nNL92RABO0157701484\n\nNL92 RABO 0157 7014 48\n\nHuisarts nr: 541440\n\nKaspersky activate code: \n4SYAK-D4HKA-D3T9E-74624','0','2015-11-23 15:48:12','2015-11-23 15:48:42',4),(208,'Kroaats','Ja - da\nNee - ne\n\nIk - ja\nJij - ti\nHij - on\nZij - ona\nHet - ono\nJullie - vi\nWij - mi\nU - vi\n\nHallo - bok\nDoei - pozdrav\n\nGoed - dobro\nDankje - hvala\nNatuurlijk - naravno','0','2015-11-23 15:48:12','2015-11-23 15:48:42',4),(209,'Huiswerk','------------------------------\nBeer voor nanc kerst','0','2015-11-23 15:48:12','2015-11-23 15:48:42',4),(210,'Wifi','Oma no:\n%_!V@ND3NBR03K!_%','0','2015-11-23 15:48:12','2015-11-23 15:48:42',4),(211,'Jacuzzi verschonen','J','0','2015-11-23 15:48:12','2015-11-23 15:48:42',4),(212,'','','0','2015-11-23 15:48:12','2015-11-23 15:48:42',4),(213,'','','0','2015-11-23 15:48:12','2015-11-23 15:48:42',4),(214,'PrivÃ©','Iban:\nNL92RABO0157701484\n\nNL92 RABO 0157 7014 48\n\nHuisarts nr: 541440\n\nKaspersky activate code: \n4SYAK-D4HKA-D3T9E-74624','0','2015-11-23 15:48:42','2015-11-23 15:48:46',4),(215,'Kroaats','Ja - da\nNee - ne\n\nIk - ja\nJij - ti\nHij - on\nZij - ona\nHet - ono\nJullie - vi\nWij - mi\nU - vi\n\nHallo - bok\nDoei - pozdrav\n\nGoed - dobro\nDankje - hvala\nNatuurlijk - naravno','0','2015-11-23 15:48:42','2015-11-23 15:48:46',4),(216,'Huiswerk','------------------------------\nBeer voor nanc kerst','0','2015-11-23 15:48:42','2015-11-23 15:48:46',4),(217,'Wifi','Oma no:\n%_!V@ND3NBR03K!_%','0','2015-11-23 15:48:42','2015-11-23 15:48:46',4),(218,'Jacuzzi verschonen','J','0','2015-11-23 15:48:42','2015-11-23 15:48:46',4),(219,'','','0','2015-11-23 15:48:42','2015-11-23 15:48:46',4),(220,'','','0','2015-11-23 15:48:42','2015-11-23 15:48:46',4),(221,'','','0','2015-11-23 15:48:45','2015-11-23 15:48:46',4),(222,'PrivÃ©','Iban:\nNL92RABO0157701484\n\nNL92 RABO 0157 7014 48\n\nHuisarts nr: 541440\n\nKaspersky activate code: \n4SYAK-D4HKA-D3T9E-74624','0','2015-11-23 15:48:46','2015-11-23 15:48:50',4),(223,'Kroaats','Ja - da\nNee - ne\n\nIk - ja\nJij - ti\nHij - on\nZij - ona\nHet - ono\nJullie - vi\nWij - mi\nU - vi\n\nHallo - bok\nDoei - pozdrav\n\nGoed - dobro\nDankje - hvala\nNatuurlijk - naravno','0','2015-11-23 15:48:46','2015-11-23 15:48:50',4),(224,'Huiswerk','------------------------------\nBeer voor nanc kerst','0','2015-11-23 15:48:46','2015-11-23 15:48:50',4),(225,'Wifi','Oma no:\n%_!V@ND3NBR03K!_%','0','2015-11-23 15:48:46','2015-11-23 15:48:50',4),(226,'Jacuzzi verschonen','J','0','2015-11-23 15:48:46','2015-11-23 15:48:50',4),(227,'','','0','2015-11-23 15:48:46','2015-11-23 15:48:50',4),(228,'','','0','2015-11-23 15:48:46','2015-11-23 15:48:50',4),(229,'','','0','2015-11-23 15:48:50','2015-11-23 15:48:50',4),(230,'PrivÃ©','Iban:\nNL92RABO0157701484\n\nNL92 RABO 0157 7014 48\n\nHuisarts nr: 541440\n\nKaspersky activate code: \n4SYAK-D4HKA-D3T9E-74624','0','2015-11-23 15:48:51','2015-11-23 15:48:53',4),(231,'Kroaats','Ja - da\nNee - ne\n\nIk - ja\nJij - ti\nHij - on\nZij - ona\nHet - ono\nJullie - vi\nWij - mi\nU - vi\n\nHallo - bok\nDoei - pozdrav\n\nGoed - dobro\nDankje - hvala\nNatuurlijk - naravno','0','2015-11-23 15:48:51','2015-11-23 15:48:53',4),(232,'Huiswerk','------------------------------\nBeer voor nanc kerst','0','2015-11-23 15:48:51','2015-11-23 15:48:53',4),(233,'Wifi','Oma no:\n%_!V@ND3NBR03K!_%','0','2015-11-23 15:48:51','2015-11-23 15:48:53',4),(234,'Jacuzzi verschonen','J','0','2015-11-23 15:48:51','2015-11-23 15:48:53',4),(235,'','','0','2015-11-23 15:48:51','2015-11-23 15:48:53',4),(236,'','','0','2015-11-23 15:48:51','2015-11-23 15:48:53',4),(237,'','','0','2015-11-23 15:48:53','2015-11-23 15:48:53',4),(238,'PrivÃ©','Iban:\nNL92RABO0157701484\n\nNL92 RABO 0157 7014 48\n\nHuisarts nr: 541440\n\nKaspersky activate code: \n4SYAK-D4HKA-D3T9E-74624','0','2015-11-23 15:48:53','2015-11-23 15:49:02',4),(239,'Kroaats','Ja - da\nNee - ne\n\nIk - ja\nJij - ti\nHij - on\nZij - ona\nHet - ono\nJullie - vi\nWij - mi\nU - vi\n\nHallo - bok\nDoei - pozdrav\n\nGoed - dobro\nDankje - hvala\nNatuurlijk - naravno','0','2015-11-23 15:48:53','2015-11-23 15:49:02',4),(240,'Huiswerk','------------------------------\nBeer voor nanc kerst','0','2015-11-23 15:48:53','2015-11-23 15:49:02',4),(241,'Wifi','Oma no:\n%_!V@ND3NBR03K!_%','0','2015-11-23 15:48:53','2015-11-23 15:49:02',4),(242,'Jacuzzi verschonen','J','0','2015-11-23 15:48:53','2015-11-23 15:49:02',4),(243,'','','0','2015-11-23 15:48:54','2015-11-23 15:49:02',4),(244,'','','0','2015-11-23 15:48:54','2015-11-23 15:49:02',4),(245,'','','0','2015-11-23 15:49:01','2015-11-23 15:49:02',4),(246,'PrivÃ©','Iban:\nNL92RABO0157701484\n\nNL92 RABO 0157 7014 48\n\nHuisarts nr: 541440\n\nKaspersky activate code: \n4SYAK-D4HKA-D3T9E-74624','0','2015-11-23 15:49:02','2015-11-23 15:51:29',4),(247,'Kroaats','Ja - da\nNee - ne\n\nIk - ja\nJij - ti\nHij - on\nZij - ona\nHet - ono\nJullie - vi\nWij - mi\nU - vi\n\nHallo - bok\nDoei - pozdrav\n\nGoed - dobro\nDankje - hvala\nNatuurlijk - naravno','0','2015-11-23 15:49:02','2015-11-23 15:51:29',4),(248,'Huiswerk','------------------------------\nBeer voor nanc kerst','0','2015-11-23 15:49:02','2015-11-23 15:51:29',4),(249,'Wifi','Oma no:\n%_!V@ND3NBR03K!_%','0','2015-11-23 15:49:02','2015-11-23 15:51:29',4),(250,'Jacuzzi verschonen','J','0','2015-11-23 15:49:02','2015-11-23 15:51:29',4),(251,'','','0','2015-11-23 15:49:02','2015-11-23 15:51:34',4),(252,'','','0','2015-11-23 15:49:02','2015-11-23 15:51:29',4),(253,'PrivÃ©','Iban:\nNL92RABO0157701484\n\nNL92 RABO 0157 7014 48\n\nHuisarts nr: 541440\n\nKaspersky activate code: \n4SYAK-D4HKA-D3T9E-74624','0','2015-11-23 15:51:29','2015-11-23 15:52:15',4),(254,'Kroaats','Ja - da\nNee - ne\n\nIk - ja\nJij - ti\nHij - on\nZij - ona\nHet - ono\nJullie - vi\nWij - mi\nU - vi\n\nHallo - bok\nDoei - pozdrav\n\nGoed - dobro\nDankje - hvala\nNatuurlijk - naravno','0','2015-11-23 15:51:29','2015-11-23 15:52:15',4),(255,'Huiswerk','------------------------------\nBeer voor nanc kerst','0','2015-11-23 15:51:29','2015-11-23 15:52:15',4),(256,'Wifi','Oma no:\n%_!V@ND3NBR03K!_%','0','2015-11-23 15:51:29','2015-11-23 15:52:15',4),(257,'Jacuzzi verschonen','J','0','2015-11-23 15:51:29','2015-11-23 15:52:15',4),(258,'','','0','2015-11-23 15:51:29','2015-11-23 15:52:15',4),(259,'','','0','2015-11-23 15:51:30','2015-11-23 15:52:15',4),(260,'','','0','2015-11-23 15:51:51','2015-11-23 15:52:15',4),(261,'','','0','2015-11-23 15:52:08','2015-11-23 15:52:15',4),(262,'PrivÃ©','Iban:\nNL92RABO0157701484\n\nNL92 RABO 0157 7014 48\n\nHuisarts nr: 541440\n\nKaspersky activate code: \n4SYAK-D4HKA-D3T9E-74624','0','2015-11-23 15:52:15','2015-11-23 15:52:36',4),(263,'Kroaats','Ja - da\nNee - ne\n\nIk - ja\nJij - ti\nHij - on\nZij - ona\nHet - ono\nJullie - vi\nWij - mi\nU - vi\n\nHallo - bok\nDoei - pozdrav\n\nGoed - dobro\nDankje - hvala\nNatuurlijk - naravno','0','2015-11-23 15:52:15','2015-11-23 15:52:36',4),(264,'Huiswerk','------------------------------\nBeer voor nanc kerst','0','2015-11-23 15:52:15','2015-11-23 15:52:37',4),(265,'Wifi','Oma no:\n%_!V@ND3NBR03K!_%','0','2015-11-23 15:52:15','2015-11-23 15:52:37',4),(266,'Jacuzzi verschonen','J','0','2015-11-23 15:52:15','2015-11-23 15:52:37',4),(267,'','J','0','2015-11-23 15:52:15','2015-11-23 15:52:44',4),(268,'','','0','2015-11-23 15:52:15','2015-11-23 15:52:37',4),(269,'','','0','2015-11-23 15:52:15','2015-11-23 15:52:37',4),(270,'','','0','2015-11-23 15:52:15','2015-11-23 15:52:37',4),(271,'PrivÃ©','Iban:\nNL92RABO0157701484\n\nNL92 RABO 0157 7014 48\n\nHuisarts nr: 541440\n\nKaspersky activate code: \n4SYAK-D4HKA-D3T9E-74624','0','2015-11-23 15:52:37','2015-11-23 15:53:44',4),(272,'Kroaats','Ja - da\nNee - ne\n\nIk - ja\nJij - ti\nHij - on\nZij - ona\nHet - ono\nJullie - vi\nWij - mi\nU - vi\n\nHallo - bok\nDoei - pozdrav\n\nGoed - dobro\nDankje - hvala\nNatuurlijk - naravno','0','2015-11-23 15:52:37','2015-11-23 15:53:45',4),(273,'Huiswerk','------------------------------\nBeer voor nanc kerst','0','2015-11-23 15:52:37','2015-11-23 15:53:45',4),(274,'Wifi','Oma no:\n%_!V@ND3NBR03K!_%','0','2015-11-23 15:52:37','2015-11-23 15:53:45',4),(275,'Jacuzzi verschonen','Jk','0','2015-11-23 15:52:38','2015-11-23 15:53:45',4),(276,'','Jjjj','0','2015-11-23 15:52:38','2015-11-23 15:54:09',4),(277,'','','0','2015-11-23 15:52:38','2015-11-23 15:53:45',4),(278,'','','0','2015-11-23 15:52:38','2015-11-23 15:53:45',4),(279,'','','0','2015-11-23 15:52:38','2015-11-23 15:53:45',4),(280,'PrivÃ©','Iban:\nNL92RABO0157701484\n\nNL92 RABO 0157 7014 48\n\nHuisarts nr: 541440\n\nKaspersky activate code: \n4SYAK-D4HKA-D3T9E-74624','0','2015-11-23 15:53:45','2015-11-23 18:09:09',4),(281,'Kroaats','Ja - da\nNee - ne\n\nIk - ja\nJij - ti\nHij - on\nZij - ona\nHet - ono\nJullie - vi\nWij - mi\nU - vi\n\nHallo - bok\nDoei - pozdrav\n\nGoed - dobro\nDankje - hvala\nNatuurlijk - naravno','0','2015-11-23 15:53:45','2015-11-23 18:09:09',4),(282,'Huiswerk','------------------------------\nBeer voor nanc kerst','0','2015-11-23 15:53:45','2015-11-23 18:09:09',4),(283,'Wifi','Oma no:\n%_!V@ND3NBR03K!_%','0','2015-11-23 15:53:45','2015-11-23 18:09:09',4),(284,'Jacuzzi verschonen','J','0','2015-11-23 15:53:45','2015-11-23 18:09:09',4),(285,'','Jkk','0','2015-11-23 15:53:45','2015-11-23 18:09:09',4),(286,'','','0','2015-11-23 15:53:45','2015-11-23 18:09:12',4),(287,'','','0','2015-11-23 15:53:45','2015-11-23 18:09:09',4),(288,'','','0','2015-11-23 15:53:45','2015-11-23 18:09:09',4),(289,'PrivÃ©','Iban:\nNL92RABO0157701484\n\nNL92 RABO 0157 7014 48\n\nHuisarts nr: 541440\n\nKaspersky activate code: \n4SYAK-D4HKA-D3T9E-74624','0','2015-11-23 18:09:09','2015-11-23 18:09:22',4),(290,'Kroaats','Ja - da\nNee - ne\n\nIk - ja\nJij - ti\nHij - on\nZij - ona\nHet - ono\nJullie - vi\nWij - mi\nU - vi\n\nHallo - bok\nDoei - pozdrav\n\nGoed - dobro\nDankje - hvala\nNatuurlijk - naravno','0','2015-11-23 18:09:09','2015-11-23 18:09:22',4),(291,'Huiswerk','------------------------------\nBeer voor nanc kerst','0','2015-11-23 18:09:09','2015-11-23 18:09:22',4),(292,'Wifi','Oma no:\n%_!V@ND3NBR03K!_%','0','2015-11-23 18:09:09','2015-11-23 18:09:22',4),(293,'Jacuzzi verschonen','J','0','2015-11-23 18:09:09','2015-11-23 18:09:22',4),(294,'','','0','2015-11-23 18:09:09','2015-11-23 18:09:17',4),(295,'','','0','2015-11-23 18:09:09','2015-11-23 18:09:15',4),(296,'','','0','2015-11-23 18:09:09','2015-11-23 18:09:22',4),(297,'','','0','2015-11-23 18:09:09','2015-11-23 18:09:22',4),(298,'PrivÃ©','Iban:\nNL92RABO0157701484\n\nNL92 RABO 0157 7014 48\n\nHuisarts nr: 541440\n\nKaspersky activate code: \n4SYAK-D4HKA-D3T9E-74624','1','2015-11-23 18:09:23',NULL,4),(299,'Kroatisch','Ja - da\nNee - ne\n\nIk - ja\nJij - ti\nHij - on\nZij - ona\nHet - ono\nJullie - vi\nWij - mi\nU - vi\n\nHallo - bok\nDoei - pozdrav\n\nGoed - dobro\nDankje - hvala\nNatuurlijk - naravno','1','2015-11-23 18:09:23','2015-11-26 19:04:05',4),(300,'Huiswerk','------------------------------\nBeer voor nanc kerst','1','2015-11-23 18:09:23','2015-11-23 22:17:12',4),(301,'Wifi','Oma no:\n%_!V@ND3NBR03K!_%','1','2015-11-23 18:09:23',NULL,4),(302,'Jacuzzi verschonen','J','0','2015-11-23 18:09:23','2015-11-25 19:30:16',4),(303,'','','0','2015-11-23 18:09:23','2015-11-23 21:08:35',4),(304,'','','0','2015-11-23 18:09:23','2015-11-23 21:08:33',4),(305,'','','0','2015-11-23 19:28:00','2015-11-23 22:20:21',4),(306,'','','0','2015-11-23 19:28:02','2015-11-23 21:08:37',4),(307,'','','0','2015-11-23 20:37:42','2015-11-23 22:20:23',4),(308,'','','0','2015-11-23 20:37:47','2015-11-23 21:08:31',4),(309,'','','0','2015-11-23 20:38:10','2015-11-23 21:08:29',4),(310,'null','null','0','2015-11-23 21:04:11','2015-11-23 21:08:26',4),(311,'null','null','0','2015-11-23 21:04:16','2015-11-23 21:08:41',4),(312,'Boodschappen','Voor â‚¬13 boodschappen gedaan','1','2015-11-24 10:37:29','2015-11-25 19:25:30',4),(313,'','','0','2015-11-24 13:08:39','2015-11-24 13:51:31',4),(314,'','','0','2015-11-24 13:08:53','2015-11-24 13:09:05',4),(315,'','','0','2015-11-24 13:09:11','2015-11-24 13:51:28',4),(316,'','E portfolio maken\nUitnodiging sturen \n\nDossier erin zetten \n\nPresentatie bekijken','0','2015-11-24 13:18:46','2015-11-25 19:14:00',4),(317,'','','0','2015-11-25 10:39:28','2015-11-25 11:12:41',4),(318,'Onderwerptest','Teksttest','1','2015-11-25 11:08:39',NULL,2),(319,'subject test','text test','1','2015-11-25 11:11:15',NULL,NULL),(320,'subject test','text test','1','2015-11-25 11:11:25',NULL,NULL),(321,'subject test','text test','1','2015-11-25 11:11:43',NULL,NULL),(322,'subject test','text test','0','2015-11-25 11:12:50','2015-11-25 19:11:32',4),(323,'','Jjj','0','2015-11-25 11:14:58','2015-11-25 19:09:41',4),(324,'Jj','','0','2015-11-25 11:15:12','2015-11-25 19:09:38',4),(325,'Qoo','Wee','0','2015-11-25 11:15:21','2015-11-25 23:56:55',4),(326,'','','0','2015-11-25 14:42:19','2015-11-25 19:09:04',4),(327,'','','0','2015-11-25 14:53:51','2015-11-25 19:08:59',4),(328,'','','0','2015-11-25 15:28:34','2015-11-25 19:10:48',4),(329,'','','0','2015-11-25 15:28:35','2015-11-25 19:08:57',4),(330,'','','0','2015-11-25 15:28:43','2015-11-25 19:10:55',4),(331,'','','0','2015-11-25 19:11:38','2015-11-25 19:17:51',4),(332,'','','0','2015-11-25 19:14:07','2015-11-25 19:17:49',4),(333,'','','0','2015-11-25 19:17:53','2015-11-25 19:19:20',4),(334,'','','0','2015-11-25 19:17:56','2015-11-25 19:19:18',4),(335,'','','0','2015-11-25 19:17:59','2015-11-25 19:19:22',4),(336,'','','0','2015-11-25 19:19:10','2015-11-25 19:19:16',4),(337,'','','0','2015-11-25 19:19:25','2015-11-25 19:31:04',4),(338,'','','0','2015-11-25 19:19:38','2015-11-25 19:19:43',4),(339,'Link','Some text http://Www.google.nl dsa\nWww.google.nl','0','2015-11-25 19:35:30','2015-11-25 20:04:23',4),(340,'Url','URL test www.google.nl','0','2015-11-25 20:05:30','2015-11-25 20:06:10',4),(341,'URL','Test www.foofle.nl l','0','2015-11-25 20:08:00','2015-11-26 18:37:55',4),(342,'','Stiekem ben ik zelf gay..\r\nIk durf het alleen niet toe te geven :(.','0','2015-11-25 20:09:30','2015-11-25 20:35:40',4),(343,'Ja','Edwin is','0','2015-11-25 20:22:10','2015-11-25 20:22:16',4),(344,'','','0','2015-11-25 23:56:16','2015-11-25 23:56:19',4),(345,'','','0','2015-11-25 23:56:21','2015-11-25 23:56:44',4),(346,'','','0','2015-11-25 23:56:23','2015-11-25 23:56:35',4),(347,'','','0','2015-11-25 23:56:28','2015-11-25 23:56:32',4),(348,'','','0','2015-11-26 06:45:10','2015-11-26 07:43:35',4),(349,'','','0','2015-11-26 06:45:11','2015-11-26 07:43:48',4),(350,'Waarom CloudPad?','Hier alvast wa dingen opsommen waarvoor je het kunt gebruiken \n\n- snelle aantekeningen maken voor je studie\n- alles online opgeslagen dus je raakt het nooit kwijt & kan er overal bij\n- hoor je een liedje en wil je deze downloaden maar heb je weinig tijd, maak snel een cptje aan\n- gedeelde afspraken maken met je klasgenoten,  vriendengroep, gezin/familie\n- vergeet nooit meer je boodschappenlijstje, maak er gewoon een cptje voor en je kan het in de winkel gewoon weer bekijken\n- gebruiksvriendelijk','0','2015-11-26 09:27:10','2015-11-26 09:29:18',4),(351,'Waarom CloudPad?','Hier alvast wa dingen opsommen waarvoor je het kunt gebruiken \n\n- snelle aantekeningen maken voor je studie\n- alles online opgeslagen dus je raakt het nooit kwijt & kan er overal bij\n- hoor je een liedje en wil je deze downloaden maar heb je weinig tijd, maak snel een cptje aan\n- gedeelde afspraken maken met je klasgenoten,  vriendengroep, gezin/familie\n- vergeet nooit meer je boodschappenlijstje, maak er gewoon een cptje voor en je kan het in de winkel gewoon weer bekijken\n- gebruiksvriendelijk','0','2015-11-26 09:29:34','2015-11-26 09:32:05',4),(352,'','','0','2015-11-26 09:30:45','2015-11-26 09:32:07',4),(353,'A','A','0','2015-11-26 09:31:47','2015-11-26 09:31:57',4),(354,'Waarom CloudPad?','Hier alvast wa dingen opsommen waarvoor je het kunt gebruiken \n\n- snelle aantekeningen maken voor je studie\n- alles online opgeslagen dus je raakt het nooit kwijt & kan er overal bij\n- hoor je een liedje en wil je deze downloaden maar heb je weinig tijd, maak snel een cptje aan\n- gedeelde afspraken maken met je klasgenoten,  vriendengroep, gezin/familie\n- vergeet nooit meer je boodschappenlijstje, maak er gewoon een cptje voor en je kan het in de winkel gewoon weer bekijken\n- gebruiksvriendelijk','1','2015-11-26 09:36:05',NULL,4),(355,'Test','Test','0','2015-11-26 09:47:09','2015-11-26 09:47:13',4),(356,'Test','Test','0','2015-11-26 09:47:28','2015-11-26 09:47:34',4),(357,'Test','Test','0','2015-11-26 09:47:54','2015-11-26 09:47:57',4),(358,'','','0','2015-11-26 10:05:00','2015-11-26 10:08:27',4),(359,'','','0','2015-11-26 10:05:05','2015-11-26 10:05:07',4),(360,'','','0','2015-11-26 10:09:13','2015-11-26 11:32:14',4),(361,'','','0','2015-11-26 10:09:17','2015-11-26 10:09:20',4),(362,'','','0','2015-11-26 10:11:32','2015-11-26 10:11:34',4),(363,'Mj','','0','2015-11-26 10:48:53','2015-11-26 11:03:31',4),(364,'Shared','','0','2015-11-26 11:03:43','2015-11-26 13:10:32',4),(365,'','','1','2015-11-26 13:10:38','2015-11-26 13:10:44',4),(366,'','Amanda cerny','1','2015-11-26 14:00:45',NULL,4);
/*!40000 ALTER TABLE `note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `share`
--

DROP TABLE IF EXISTS `share`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `share` (
  `account_id` int(11) NOT NULL,
  `note_id` int(11) NOT NULL,
  KEY `fk_account_note_account_idx` (`account_id`),
  KEY `fk_account_note_note_idx` (`note_id`),
  CONSTRAINT `fk_account_note_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_account_note_note` FOREIGN KEY (`note_id`) REFERENCES `note` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `share`
--

LOCK TABLES `share` WRITE;
/*!40000 ALTER TABLE `share` DISABLE KEYS */;
INSERT INTO `share` VALUES (2,1),(3,1),(4,9),(1,9),(2,9),(3,9),(4,110),(4,120),(4,125),(4,142),(12,142),(4,146),(4,160),(4,163),(4,164),(4,165),(10,165),(11,165),(4,166),(6,166),(4,167),(3,167),(6,167),(4,168),(3,168),(6,168),(4,169),(4,170),(4,171),(4,172),(4,173),(4,174),(4,175),(4,176),(4,177),(4,178),(4,179),(4,187),(4,195),(4,206),(4,207),(4,208),(4,209),(4,210),(4,211),(4,212),(4,213),(4,214),(4,215),(4,216),(4,217),(4,218),(4,219),(4,220),(4,221),(4,222),(4,223),(4,224),(4,225),(4,226),(4,227),(4,228),(4,229),(4,230),(4,231),(4,232),(4,233),(4,234),(4,235),(4,236),(4,237),(4,238),(4,239),(4,240),(4,241),(4,242),(4,243),(4,244),(4,245),(4,246),(4,247),(4,248),(4,249),(4,250),(4,251),(4,252),(4,253),(4,254),(4,255),(4,256),(4,257),(4,258),(4,259),(4,260),(4,261),(4,262),(4,263),(4,264),(4,265),(4,266),(4,267),(4,268),(4,269),(4,270),(4,271),(4,272),(4,273),(4,274),(4,275),(4,276),(4,277),(4,278),(4,279),(4,280),(4,281),(4,282),(4,283),(4,284),(4,285),(4,286),(4,287),(4,288),(4,289),(4,290),(4,291),(4,292),(4,293),(4,294),(4,295),(4,296),(4,297),(4,298),(4,299),(4,300),(4,301),(4,302),(4,303),(4,304),(4,305),(6,305),(12,305),(4,306),(4,307),(3,307),(6,307),(7,307),(4,308),(4,309),(4,310),(4,311),(4,312),(4,313),(4,314),(2,314),(3,314),(7,314),(4,315),(4,316),(4,317),(4,322),(4,323),(4,324),(4,325),(3,325),(6,325),(4,326),(4,327),(4,328),(4,329),(4,330),(4,331),(4,332),(4,333),(4,334),(4,335),(4,336),(4,337),(4,338),(3,338),(4,339),(4,340),(4,341),(4,342),(4,343),(4,344),(4,345),(4,346),(4,347),(4,348),(4,349),(4,350),(1,350),(2,350),(3,350),(4,351),(1,351),(2,351),(3,351),(4,352),(3,352),(6,352),(4,353),(2,353),(3,353),(4,354),(4,355),(2,355),(3,355),(4,356),(2,356),(3,356),(4,357),(3,357),(4,358),(6,358),(4,359),(3,359),(4,360),(4,361),(3,361),(6,361),(4,362),(3,362),(6,362),(4,363),(2,363),(4,364),(3,364),(6,364),(4,365),(3,365),(6,365),(4,366);
/*!40000 ALTER TABLE `share` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `update_log`
--

DROP TABLE IF EXISTS `update_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `update_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` decimal(4,3) DEFAULT '0.000',
  `description` text,
  `creation` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `update_log`
--

LOCK TABLES `update_log` WRITE;
/*!40000 ALTER TABLE `update_log` DISABLE KEYS */;
INSERT INTO `update_log` VALUES (1,1.000,'Initiatie van de app.','2015-10-26 11:00:00'),(28,1.001,'Update_log fancified. Admin app gemaakt.','2015-11-04 12:23:41'),(31,1.002,'Kan nu update logs aanmaken in admin app.','2015-11-04 13:45:41'),(32,1.003,'Feedback formulier toegevoegd. Je kunt nu feedback sturen die ik kan teruglezen.','2015-11-04 22:18:17'),(36,1.004,'Gui update. Reformat on string resources. Overall infrastructure improvements, specifically async task reusability.','2015-11-06 20:34:19'),(37,1.005,'Kan nu push notificaties ontvangen & sturen vanuit de app.','2015-11-07 15:44:52'),(38,1.006,'Database update om gedeelde cloudpads toe te staan.','2015-11-07 15:54:32'),(39,1.007,'Json class volledig geÃ¯mplementeerd & een aantal php scripts dynamisch gemaakt. Verder readability updates in de source files.','2015-11-09 01:19:19'),(41,1.008,'& lots of reusability improvements, php scripts reduced to 2 instead of increasingly over 10. Cleaned up http requests.','2015-11-10 00:40:27'),(44,1.009,'Notes delen werkt nu. PrivÃ© notes maken ook. Compleet openbare notes weggehaald.','2015-11-11 20:09:44'),(45,1.010,'Bug fixes.','2015-11-12 21:37:29'),(46,1.011,'Feedback bekijken vanuit admin app werkt.','2015-11-19 13:00:13'),(47,1.012,'Cleaned up some shit.','2015-11-19 13:00:20'),(48,1.013,'PrivÃ© en shared notes onderscheid gemaakt & layout differences ook.','2015-11-19 13:00:49'),(49,1.014,'Tab layout aan het implementeren.','2015-11-20 12:31:47'),(50,1.015,'Tab layout implementatie compleet.','2015-11-20 18:53:25'),(51,1.016,'Onderscheid in tabs tussen shared en privÃ©.','2015-11-20 18:53:41'),(52,1.017,'+-knop blijft staan nu','2015-11-20 18:54:24'),(53,1.018,'Reorganisatie in classes, 3-lagenmodel concept gedeeltelijk toegepast.','2015-11-24 11:32:05'),(54,1.019,'Sqlite handler voor local private notes begin gemaakt.','2015-11-24 11:32:28'),(55,1.020,'Kan feedback nu zien & behandelen in admin app.','2015-11-24 11:33:22'),(57,1.022,'Material design implemented.','2015-11-25 14:50:30'),(58,1.023,'Eerste recyclerview geÃ¯mplementeerd ipv listview.','2015-11-25 16:07:45'),(59,1.024,'Implemented recycleviews for sharednotes and privatenotes.','2015-11-25 19:26:08'),(60,1.025,'Implemented structural exception handling.','2015-11-26 11:11:17');
/*!40000 ALTER TABLE `update_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `version`
--

DROP TABLE IF EXISTS `version`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `version` (
  `number` int(11) NOT NULL,
  PRIMARY KEY (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `version`
--

LOCK TABLES `version` WRITE;
/*!40000 ALTER TABLE `version` DISABLE KEYS */;
INSERT INTO `version` VALUES (0);
/*!40000 ALTER TABLE `version` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `word`
--

DROP TABLE IF EXISTS `word`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `word` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `content_UNIQUE` (`content`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `word`
--

LOCK TABLES `word` WRITE;
/*!40000 ALTER TABLE `word` DISABLE KEYS */;
/*!40000 ALTER TABLE `word` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-26 20:11:08
