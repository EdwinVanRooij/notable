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
INSERT INTO `account` VALUES (1,'username1','email1','password1','2015-11-13 08:54:06',0),(2,'swag','email2','password2','2015-11-13 08:54:06',0),(3,'username3','email3','password3','2015-11-13 08:54:06',0),(4,'admin','a','a','2015-11-13 09:06:25',0),(6,'a','b','b','2015-11-15 11:52:30',0),(7,'c','c','c','2015-11-15 11:52:30',0),(8,'d','d','d','2015-11-15 11:57:46',0),(10,'f','f','f','2015-11-15 11:57:46',0),(11,'g','g','g','2015-11-15 11:57:46',0),(12,'gummybeerninja','nancy-bust@hotmail.com','nancy1997','2015-11-16 11:26:24',0);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_note`
--

DROP TABLE IF EXISTS `account_note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_note` (
  `account_id` int(11) NOT NULL,
  `note_id` int(11) NOT NULL,
  KEY `fk_account_note_account_idx` (`account_id`),
  KEY `fk_account_note_note_idx` (`note_id`),
  CONSTRAINT `fk_account_note_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_account_note_note` FOREIGN KEY (`note_id`) REFERENCES `note` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_note`
--

LOCK TABLES `account_note` WRITE;
/*!40000 ALTER TABLE `account_note` DISABLE KEYS */;
INSERT INTO `account_note` VALUES (2,1),(3,1),(4,9),(1,9),(2,9),(3,9),(12,96),(4,96),(4,97),(12,97),(4,110),(4,111),(4,112),(4,114),(4,115),(3,115),(4,116),(12,116),(4,117),(4,118),(3,118),(4,119),(3,119),(4,120),(4,121),(12,121),(4,122),(12,122),(4,123),(12,123),(4,124),(12,124),(4,125),(4,126),(4,127),(4,128),(1,128),(2,128),(3,128),(7,128),(4,129);
/*!40000 ALTER TABLE `account_note` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (8,4,'Bug','Fout een','2015-11-19 12:50:49',0);
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `gekke_view`
--

DROP TABLE IF EXISTS `gekke_view`;
/*!50001 DROP VIEW IF EXISTS `gekke_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `gekke_view` AS SELECT 
 1 AS `username`,
 1 AS `password`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `login_details_view`
--

DROP TABLE IF EXISTS `login_details_view`;
/*!50001 DROP VIEW IF EXISTS `login_details_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `login_details_view` AS SELECT 
 1 AS `username`,
 1 AS `password`*/;
SET character_set_client = @saved_cs_client;

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
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `note`
--

LOCK TABLES `note` WRITE;
/*!40000 ALTER TABLE `note` DISABLE KEYS */;
INSERT INTO `note` VALUES (1,'subject1','text1','1','2015-11-13 08:54:06','2015-11-16 17:57:10',1),(2,'subject2','text2','1','2015-11-13 08:54:07','2015-11-16 17:57:10',3),(9,'Waarom CloudPad?','Hier alvast wa dingen opsommen waarvoor je het kunt gebruiken \n\n- snelle aantekeningen maken voor je studie\n- alles online opgeslagen dus je raakt het nooit kwijt & kan er overal bij\n- hoor je een liedje en wil je deze downloaden maar heb je weinig tijd, maak snel een cptje aan\n- gedeelde afspraken maken met je klasgenoten,  vriendengroep, gezin/familie\n- vergeet nooit meer je boodschappenlijstje, maak er gewoon een cptje voor en je kan het in de winkel gewoon weer bekijken\n- gebruiksvriendelijk','1','2015-11-13 09:23:27','2015-03-16 18:24:00',4),(96,'test','Test','0','2015-11-16 11:26:50','2015-11-17 20:52:13',12),(97,'nog een','haj','0','2015-11-16 11:27:30','2015-11-17 20:52:11',4),(110,'Prive','','0','2015-11-16 18:10:43','2015-11-17 13:07:50',4),(111,'Boodschappenlijstje','Wattenstaafjes \nPindas','0','2015-11-16 18:10:48','2015-11-17 15:41:54',4),(112,'','','0','2015-11-16 18:10:53',NULL,4),(113,'subject test','text test','1','2015-11-17 12:06:33','2015-11-17 12:06:23',NULL),(114,'subject test','text test','0','2015-11-17 12:07:49','2015-11-17 12:08:13',NULL),(115,'subject test','text test','0','2015-11-17 12:08:06','2015-11-17 20:51:15',NULL),(116,'ðŸ˜¡ðŸ˜¡','','0','2015-11-17 13:08:32','2015-11-17 15:34:16',4),(117,'Kijken','http://www.rtlnieuws.nl/nieuws/buitenland/vier-vrienden-overleefden-bataclan-die-stilte-tussen-de-schoten-door-ondraaglijk\n\nhttp://www.rtlnieuws.nl/nieuws/buitenland/vier-vrienden-overleefden-bataclan-die-stilte-tussen-de-schoten-door-ondraaglijk','0','2015-11-17 13:51:53','2015-11-17 15:34:07',4),(118,'subject test','text test','0','2015-11-17 15:31:52','2015-11-17 15:34:10',NULL),(119,'subject test','text test','0','2015-11-17 15:33:58','2015-11-17 15:34:13',NULL),(120,'Iban','NL92RABO0157701484\n\nNL92 RABO 0157 7014 48','1','2015-11-17 18:51:58',NULL,4),(121,'Haaj','Haaj','0','2015-11-17 20:51:25','2015-11-17 20:52:08',4),(122,'Haa','','0','2015-11-17 20:51:48','2015-11-17 20:52:04',4),(123,'Haa','','1','2015-11-17 20:52:24',NULL,4),(124,'Ha','','1','2015-11-17 20:52:37','2015-11-18 08:12:07',4),(125,'To do','Kroatisch leren','1','2015-11-18 09:35:33',NULL,4),(126,'','','0','2015-11-18 22:24:48','2015-11-18 22:40:12',4),(127,'','','0','2015-11-18 22:40:05','2015-11-18 22:40:10',4),(128,'Onderwerp 2','','0','2015-11-18 22:40:33','2015-11-18 23:44:13',4),(129,'','','1','2015-11-19 12:57:39',NULL,4);
/*!40000 ALTER TABLE `note` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `update_log`
--

LOCK TABLES `update_log` WRITE;
/*!40000 ALTER TABLE `update_log` DISABLE KEYS */;
INSERT INTO `update_log` VALUES (1,1.000,'Initiatie van de app.','2015-10-26 11:00:00'),(28,1.001,'Update_log fancified. Admin app gemaakt.','2015-11-04 12:23:41'),(31,1.002,'Kan nu update logs aanmaken in admin app.','2015-11-04 13:45:41'),(32,1.003,'Feedback formulier toegevoegd. Je kunt nu feedback sturen die ik kan teruglezen.','2015-11-04 22:18:17'),(36,1.004,'Gui update. Reformat on string resources. Overall infrastructure improvements, specifically async task reusability.','2015-11-06 20:34:19'),(37,1.005,'Kan nu push notificaties ontvangen & sturen vanuit de app.','2015-11-07 15:44:52'),(38,1.006,'Database update om gedeelde cloudpads toe te staan.','2015-11-07 15:54:32'),(39,1.007,'Json class volledig geÃ¯mplementeerd & een aantal php scripts dynamisch gemaakt. Verder readability updates in de source files.','2015-11-09 01:19:19'),(41,1.008,'& lots of reusability improvements, php scripts reduced to 2 instead of increasingly over 10. Cleaned up http requests.','2015-11-10 00:40:27'),(44,1.009,'Notes delen werkt nu. PrivÃ© notes maken ook. Compleet openbare notes weggehaald.','2015-11-11 20:09:44'),(45,1.010,'Bug fixes.','2015-11-12 21:37:29'),(46,1.011,'Feedback bekijken vanuit admin app werkt.','2015-11-19 13:00:13'),(47,1.012,'Cleaned up some shit.','2015-11-19 13:00:20'),(48,1.013,'PrivÃ© en shared notes onderscheid gemaakt & layout differences ook.','2015-11-19 13:00:49');
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
INSERT INTO `version` VALUES (4);
/*!40000 ALTER TABLE `version` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `gekke_view`
--

/*!50001 DROP VIEW IF EXISTS `gekke_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`dbi324201`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `gekke_view` AS select `account`.`username` AS `username`,`account`.`password` AS `password` from `account` where (`account`.`blocked` = 0) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `login_details_view`
--

/*!50001 DROP VIEW IF EXISTS `login_details_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`dbi324201`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `login_details_view` AS select `account`.`username` AS `username`,`account`.`password` AS `password` from `account` where (`account`.`blocked` = 0) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-19 17:50:59
