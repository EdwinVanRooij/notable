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
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `boodschappen_product`
--

LOCK TABLES `boodschappen_product` WRITE;
/*!40000 ALTER TABLE `boodschappen_product` DISABLE KEYS */;
INSERT INTO `boodschappen_product` VALUES (54,'-',1,'');
/*!40000 ALTER TABLE `boodschappen_product` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (8,4,'Bug','Fout een','2015-11-19 12:50:49',0),(11,4,'Bug','Jaa','2015-11-19 22:49:50',0),(12,4,'Bug','Gekke fout','2015-11-20 20:57:38',0),(13,4,'Suggestion','Swag','2015-11-23 19:28:16',0),(14,4,'Suggestion','Jaa','2015-11-23 21:33:23',0),(15,4,'Suggestion','Gekke feedback','2015-11-29 20:59:01',0),(16,4,'Bug','Gg','2015-11-29 20:59:18',0),(17,4,'Bug','Hallo Marcus en ik ben klein beetje te laat om te zien dat het niet zo veel uit te leggen op het gebied waar ik me af wat er komt te staan wanneer Timer die aftelt ipv optelt Instellen.','2015-11-29 21:00:58',0);
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
) ENGINE=InnoDB AUTO_INCREMENT=423 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `note`
--

LOCK TABLES `note` WRITE;
/*!40000 ALTER TABLE `note` DISABLE KEYS */;
INSERT INTO `note` VALUES (368,'why','Hier alvast wa dingen opsommen waarvoor je het kunt gebruiken \n\n- snelle aantekeningen maken voor je studie\n- alles online opgeslagen dus je raakt het nooit kwijt & kan er overal bij\n- hoor je een liedje en wil je deze downloaden maar heb je weinig tijd, maak snel een cptje aan\n- gedeelde afspraken maken met je klasgenoten,  vriendengroep, gezin/familie\n- vergeet nooit meer je boodschappenlijstje, maak er gewoon een cptje voor en je kan het in de winkel gewoon weer bekijken\n- gebruiksvriendelijk','1','2015-11-26 20:52:54','2015-11-26 21:05:44',4),(371,'PrivÃ©','Iban:\nNL92RABO0157701484\n\nNL92 RABO 0157 7014 48\n\nHuisarts nr: 541440\n\nKaspersky activate code: \n4SYAK-D4HKA-D3T9E-74624','1','2015-11-26 20:57:09','2015-11-29 11:47:58',4),(372,'Kroaats','Ja - da\nNee - ne\n\nIk - ja\nJij - ti\nHij - on\nZij - ona\nHet - ono\nJullie - vi\nWij - mi\nU - vi\n\nHallo - bok\nDoei - pozdrav\n\nGoed - dobro\nDankje - hvala\nNatuurlijk - naravno','1','2015-11-26 20:57:22','2015-11-27 10:34:23',4),(373,'Wifi','Oma no:\n%_!V@ND3NBR03K!_%\n\nThuis:\n5571LR52015','1','2015-11-26 20:58:02','2015-11-28 17:20:06',4),(390,'','Jj','1','2015-11-27 22:47:39','2015-11-27 23:12:25',4),(391,'','','1','2015-11-27 22:48:40','2015-11-29 13:55:16',4),(409,'','Smam â‚¬35-â‚¬26=9 geleend van mij','0','2015-11-29 11:12:15','2015-11-30 11:33:47',4),(410,'Bedrijfsnaam','Notable','1','2015-11-29 13:54:31','2015-11-29 13:54:31',4),(411,'Nieuw','','1','2015-11-29 15:58:17','2015-11-29 15:58:17',4),(412,'','','0','2015-11-29 19:39:10','2015-11-30 07:24:53',4),(413,'','','0','2015-11-29 20:55:00','2015-11-30 07:24:46',4),(414,'','','0','2015-11-29 20:55:05','2015-11-30 07:24:51',4),(415,'','','1','2015-11-29 23:03:23','2015-11-29 23:03:23',4),(416,'','','0','2015-11-30 07:22:28','2015-11-30 07:24:48',4),(417,'Indrinken zaterdag','Wie gaan er mee?','1','2015-11-30 14:56:22','2015-11-30 14:56:22',4),(418,'','A','1','2015-11-30 15:04:41','2015-11-30 15:43:54',4),(419,'','Ab','1','2015-11-30 15:04:43','2015-11-30 15:43:59',4),(420,'','Abc','1','2015-11-30 15:04:44','2015-11-30 15:44:04',4),(421,'','Abcd','1','2015-11-30 15:04:44','2015-11-30 15:44:09',4),(422,'Abcde','','1','2015-11-30 15:04:45','2015-11-30 15:44:15',4);
/*!40000 ALTER TABLE `note` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`dbi324201`@`%`*/ /*!50003 TRIGGER `test_before_insert` BEFORE INSERT ON `note`
FOR EACH ROW
BEGIN
    IF new.active < 0 || new.active > 1 THEN
        SIGNAL SQLSTATE '12345'
            SET MESSAGE_TEXT = 'check constraint on note failed';
    END IF;
    
    set new.last_edited = current_timestamp();
    
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`dbi324201`@`%`*/ /*!50003 TRIGGER `insert_query_for_self` AFTER INSERT ON `note`
FOR EACH ROW
BEGIN
	insert into share (account_id, note_id) values (new.owner_id, new.id);
    
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`dbi324201`@`%`*/ /*!50003 TRIGGER `update_note_last_edited` BEFORE UPDATE ON `note`
FOR EACH ROW
BEGIN
SET new.last_edited = current_timestamp();
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

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
INSERT INTO `share` VALUES (4,368),(4,371),(4,372),(4,373),(4,390),(3,390),(6,390),(4,391),(3,391),(6,391),(4,409),(4,410),(4,411),(8,411),(10,411),(4,412),(4,413),(4,414),(4,415),(7,415),(8,415),(4,416),(4,417),(2,417),(3,417),(7,417),(8,417),(11,417),(4,418),(4,419),(4,420),(4,421),(4,422);
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
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `update_log`
--

LOCK TABLES `update_log` WRITE;
/*!40000 ALTER TABLE `update_log` DISABLE KEYS */;
INSERT INTO `update_log` VALUES (1,1.000,'Initiatie van de app.','2015-10-26 11:00:00'),(28,1.001,'Update_log fancified. Admin app gemaakt.','2015-11-04 12:23:41'),(31,1.002,'Kan nu update logs aanmaken in admin app.','2015-11-04 13:45:41'),(32,1.003,'Feedback formulier toegevoegd. Je kunt nu feedback sturen die ik kan teruglezen.','2015-11-04 22:18:17'),(36,1.004,'Gui update. Reformat on string resources. Overall infrastructure improvements, specifically async task reusability.','2015-11-06 20:34:19'),(37,1.005,'Kan nu push notificaties ontvangen & sturen vanuit de app.','2015-11-07 15:44:52'),(38,1.006,'Database update om gedeelde cloudpads toe te staan.','2015-11-07 15:54:32'),(39,1.007,'Json class volledig geÃ¯mplementeerd & een aantal php scripts dynamisch gemaakt. Verder readability updates in de source files.','2015-11-09 01:19:19'),(41,1.008,'& lots of reusability improvements, php scripts reduced to 2 instead of increasingly over 10. Cleaned up http requests.','2015-11-10 00:40:27'),(44,1.009,'Notes delen werkt nu. PrivÃ© notes maken ook. Compleet openbare notes weggehaald.','2015-11-11 20:09:44'),(45,1.010,'Bug fixes.','2015-11-12 21:37:29'),(46,1.011,'Feedback bekijken vanuit admin app werkt.','2015-11-19 13:00:13'),(47,1.012,'Cleaned up some shit.','2015-11-19 13:00:20'),(48,1.013,'PrivÃ© en shared notes onderscheid gemaakt & layout differences ook.','2015-11-19 13:00:49'),(49,1.014,'Tab layout aan het implementeren.','2015-11-20 12:31:47'),(50,1.015,'Tab layout implementatie compleet.','2015-11-20 18:53:25'),(51,1.016,'Onderscheid in tabs tussen shared en privÃ©.','2015-11-20 18:53:41'),(52,1.017,'+-knop blijft staan nu','2015-11-20 18:54:24'),(53,1.018,'Reorganisatie in classes, 3-lagenmodel concept gedeeltelijk toegepast.','2015-11-24 11:32:05'),(54,1.019,'Sqlite handler voor local private notes begin gemaakt.','2015-11-24 11:32:28'),(55,1.020,'Kan feedback nu zien & behandelen in admin app.','2015-11-24 11:33:22'),(57,1.022,'Material design implemented.','2015-11-25 14:50:30'),(58,1.023,'Eerste recyclerview geÃ¯mplementeerd ipv listview.','2015-11-25 16:07:45'),(59,1.024,'Implemented recycleviews for sharednotes and privatenotes.','2015-11-25 19:26:08'),(60,1.025,'Implemented structural exception handling.','2015-11-26 11:11:17'),(61,1.026,'Parcelable geÃ¯mplementeerd in de klassen waar mogelijk. Op weg naar meer object georiÃ«nteerd programmeren.','2015-11-27 16:49:57'),(62,1.027,'Login smoothified. Aparte activity gemaakt voor launch handling.','2015-11-27 19:30:44'),(63,1.028,'Objecten worden nu door activities meegegeven en account object wordt opgeslagen. Veel overbodige constructors weggehaald uit physical objects.','2015-11-27 19:31:20'),(64,1.029,'Color scheme aangepast. Custom tablayout aangepast zodat het een wit accent pakt ipv accent color. Hoogte van de tab balk onder de naam dikker.','2015-11-30 07:24:31'),(65,1.030,'Search algoritme gemaakt, gedeeltelijke front-end functionaliteit geÃ¯mplementeerd.','2015-11-30 13:55:56'),(66,1.031,'Search functionaliteit basis functionaliteit afgerond.','2015-11-30 15:53:59');
/*!40000 ALTER TABLE `update_log` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`dbi324201`@`%`*/ /*!50003 TRIGGER `generate_version` BEFORE INSERT ON `update_log`
FOR EACH ROW
BEGIN
DECLARE message text;
    IF new.version = 0 THEN
			SET new.version = (select version from update_log where id = (select max(id) from update_log)) + 0.001;
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

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

-- Dump completed on 2015-11-30 16:57:47
