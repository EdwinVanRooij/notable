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
-- Temporary view structure for view `all_nouns`
--

DROP TABLE IF EXISTS `all_nouns`;
/*!50001 DROP VIEW IF EXISTS `all_nouns`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `all_nouns` AS SELECT 
 1 AS `language`,
 1 AS `phrase`,
 1 AS `word`,
 1 AS `sex`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `all_phrases`
--

DROP TABLE IF EXISTS `all_phrases`;
/*!50001 DROP VIEW IF EXISTS `all_phrases`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `all_phrases` AS SELECT 
 1 AS `id`,
 1 AS `name`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `all_syntaxes`
--

DROP TABLE IF EXISTS `all_syntaxes`;
/*!50001 DROP VIEW IF EXISTS `all_syntaxes`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `all_syntaxes` AS SELECT 
 1 AS `syntax_id`,
 1 AS `syntax_name`,
 1 AS `phrase`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `all_verbs`
--

DROP TABLE IF EXISTS `all_verbs`;
/*!50001 DROP VIEW IF EXISTS `all_verbs`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `all_verbs` AS SELECT 
 1 AS `language`,
 1 AS `phrase`,
 1 AS `infinitive`,
 1 AS `first_person_singular`,
 1 AS `second_person_singular`,
 1 AS `third_person_singular`,
 1 AS `first_person_plural`,
 1 AS `second_person_plural`,
 1 AS `third_person_plural`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `donny_noun`
--

DROP TABLE IF EXISTS `donny_noun`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `donny_noun` (
  `id` int(11) NOT NULL,
  `word` varchar(45) NOT NULL,
  `sex` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `word_id_fk_noun` FOREIGN KEY (`id`) REFERENCES `donny_word` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donny_noun`
--

LOCK TABLES `donny_noun` WRITE;
/*!40000 ALTER TABLE `donny_noun` DISABLE KEYS */;
INSERT INTO `donny_noun` VALUES (4,'boom',0),(5,'aap',0),(6,'schaap',2);
/*!40000 ALTER TABLE `donny_noun` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `donny_phrase`
--

DROP TABLE IF EXISTS `donny_phrase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `donny_phrase` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donny_phrase`
--

LOCK TABLES `donny_phrase` WRITE;
/*!40000 ALTER TABLE `donny_phrase` DISABLE KEYS */;
INSERT INTO `donny_phrase` VALUES (1,'onderwerp'),(2,'persoonsvorm'),(3,'lijdend_voorwerp'),(4,'test_purposes_1'),(5,'test_purposes_2'),(6,'test_purposes_3'),(7,'test_purposes_4'),(8,'test_purposes_5'),(9,'test_purposes_6'),(10,'test_purposes_7');
/*!40000 ALTER TABLE `donny_phrase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `donny_position`
--

DROP TABLE IF EXISTS `donny_position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `donny_position` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `syntax_id` int(11) NOT NULL,
  `phrase_id` int(11) NOT NULL,
  `position` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `position_syntax_fk_idx` (`syntax_id`),
  KEY `position_phrase_fk_idx` (`phrase_id`),
  CONSTRAINT `position_phrase_fk` FOREIGN KEY (`phrase_id`) REFERENCES `donny_phrase` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `position_syntax_fk` FOREIGN KEY (`syntax_id`) REFERENCES `donny_syntax` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donny_position`
--

LOCK TABLES `donny_position` WRITE;
/*!40000 ALTER TABLE `donny_position` DISABLE KEYS */;
INSERT INTO `donny_position` VALUES (1,1,1,1),(2,1,2,2),(3,2,1,2),(4,2,2,1),(5,3,1,1),(6,3,2,2),(7,3,3,3),(8,4,1,2),(9,4,2,1),(10,4,3,3);
/*!40000 ALTER TABLE `donny_position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `donny_syntax`
--

DROP TABLE IF EXISTS `donny_syntax`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `donny_syntax` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sentence_type` varchar(45) NOT NULL COMMENT 'Has to be something similar to ''Answer'', ''Question'', ''Imperative'', etc.',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donny_syntax`
--

LOCK TABLES `donny_syntax` WRITE;
/*!40000 ALTER TABLE `donny_syntax` DISABLE KEYS */;
INSERT INTO `donny_syntax` VALUES (1,'normale_zin'),(2,'vraagzin'),(3,'normale_zin_uitgebreid'),(4,'vraagzin_uitgebreid');
/*!40000 ALTER TABLE `donny_syntax` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `donny_verb`
--

DROP TABLE IF EXISTS `donny_verb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `donny_verb` (
  `id` int(11) NOT NULL,
  `infinitive` varchar(45) DEFAULT NULL,
  `first_person_singular` varchar(45) DEFAULT NULL,
  `second_person_singular` varchar(45) DEFAULT NULL,
  `third_person_singular` varchar(45) DEFAULT NULL,
  `first_person_plural` varchar(45) DEFAULT NULL,
  `second_person_plural` varchar(45) DEFAULT NULL,
  `third_person_plural` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `word_id` FOREIGN KEY (`id`) REFERENCES `donny_word` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donny_verb`
--

LOCK TABLES `donny_verb` WRITE;
/*!40000 ALTER TABLE `donny_verb` DISABLE KEYS */;
INSERT INTO `donny_verb` VALUES (1,'lopen','loop','loopt','loopt','lopen','lopen','lopen'),(2,'praten','praat','praat','praat','praten','praten','praten'),(3,'roepen','roep','roept','roept','roepen','roepen','roepen');
/*!40000 ALTER TABLE `donny_verb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `donny_word`
--

DROP TABLE IF EXISTS `donny_word`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `donny_word` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `language` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donny_word`
--

LOCK TABLES `donny_word` WRITE;
/*!40000 ALTER TABLE `donny_word` DISABLE KEYS */;
INSERT INTO `donny_word` VALUES (1,'dutch'),(2,'dutch'),(3,'dutch'),(4,'dutch'),(5,'dutch'),(6,'dutch');
/*!40000 ALTER TABLE `donny_word` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `donny_word_phrase`
--

DROP TABLE IF EXISTS `donny_word_phrase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `donny_word_phrase` (
  `phrase_id` int(11) NOT NULL,
  `word_id` int(11) NOT NULL,
  PRIMARY KEY (`phrase_id`,`word_id`),
  KEY `word_phrase_word_fk_idx` (`word_id`),
  CONSTRAINT `word_phrase_phrase_fk` FOREIGN KEY (`phrase_id`) REFERENCES `donny_phrase` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `word_phrase_word_fk` FOREIGN KEY (`word_id`) REFERENCES `donny_word` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donny_word_phrase`
--

LOCK TABLES `donny_word_phrase` WRITE;
/*!40000 ALTER TABLE `donny_word_phrase` DISABLE KEYS */;
INSERT INTO `donny_word_phrase` VALUES (1,1),(1,2),(2,4),(2,5);
/*!40000 ALTER TABLE `donny_word_phrase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'dbi324201'
--

--
-- Dumping routines for database 'dbi324201'
--
/*!50003 DROP PROCEDURE IF EXISTS `procedure_get_words` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`dbi324201`@`%` PROCEDURE `procedure_get_words`(p_first_word VARCHAR(100))
BEGIN

select content from admin_word w, admin_combination c where w.id = c.second_word_id and first_word_id = (select id from admin_word w2 where w2.content = p_first_word) order by count desc limit 3;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `procedure_insert_word` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`dbi324201`@`%` PROCEDURE `procedure_insert_word`(IN p_first_word VARCHAR(100),
IN p_second_word VARCHAR(100))
BEGIN
-- does_word_exist is the boolean to check wether the word already exists or not, 0 means it doesn't, 1 means it does
	DECLARE does_first_word_exist INT DEFAULT 0;
    DECLARE does_second_word_exist INT DEFAULT 0;
    DECLARE does_combination_exist INT DEFAULT 0;
    DECLARE v_first_word_id INT;
    DECLARE v_second_word_id INT;

-- initialize variables
	select count(*) into does_first_word_exist from admin_word where content = p_first_word;
    select count(*) into does_second_word_exist from admin_word where content = p_second_word;

-- if the word does not already exist, insert the word
	if does_first_word_exist = 0 THEN
		insert into admin_word (content) values (p_first_word);
	end if;
    select id into v_first_word_id from admin_word where content = p_first_word;
    
    if does_second_word_exist = 0 THEN
		insert into admin_word (content) values (p_second_word);
	end if;
    select id into v_second_word_id from admin_word where content = p_second_word;
    
    select count(*) 
    into does_combination_exist 
    from admin_combination
    where first_word_id = v_first_word_id
    and second_word_id = v_second_word_id;
    
    if does_combination_exist = 0 THEN
		insert into admin_combination (first_word_id, second_word_id) values (v_first_word_id, v_second_word_id);
	else
		update admin_combination
        set count = count + 1
        where first_word_id = v_first_word_id 
        and second_word_id = v_second_word_id;
	end if;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `all_nouns`
--

/*!50001 DROP VIEW IF EXISTS `all_nouns`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`dbi324201`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `all_nouns` AS select `w`.`language` AS `language`,`p`.`name` AS `phrase`,`n`.`word` AS `word`,`n`.`sex` AS `sex` from (((`donny_noun` `n` join `donny_word` `w`) join `donny_word_phrase` `wp`) join `donny_phrase` `p`) where ((`n`.`id` = `w`.`id`) and (`w`.`id` = `wp`.`word_id`) and (`wp`.`phrase_id` = `p`.`id`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `all_phrases`
--

/*!50001 DROP VIEW IF EXISTS `all_phrases`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`dbi324201`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `all_phrases` AS select `donny_phrase`.`id` AS `id`,`donny_phrase`.`name` AS `name` from `donny_phrase` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `all_syntaxes`
--

/*!50001 DROP VIEW IF EXISTS `all_syntaxes`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`dbi324201`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `all_syntaxes` AS select `s`.`id` AS `syntax_id`,`s`.`sentence_type` AS `syntax_name`,`ph`.`name` AS `phrase` from ((`donny_phrase` `ph` join `donny_position` `po`) join `donny_syntax` `s`) where ((`s`.`id` = `po`.`syntax_id`) and (`ph`.`id` = `po`.`phrase_id`)) order by `s`.`id`,`po`.`position` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `all_verbs`
--

/*!50001 DROP VIEW IF EXISTS `all_verbs`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`dbi324201`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `all_verbs` AS select `w`.`language` AS `language`,`p`.`name` AS `phrase`,`v`.`infinitive` AS `infinitive`,`v`.`first_person_singular` AS `first_person_singular`,`v`.`second_person_singular` AS `second_person_singular`,`v`.`third_person_singular` AS `third_person_singular`,`v`.`first_person_plural` AS `first_person_plural`,`v`.`second_person_plural` AS `second_person_plural`,`v`.`third_person_plural` AS `third_person_plural` from (((`donny_verb` `v` join `donny_word` `w`) join `donny_word_phrase` `wp`) join `donny_phrase` `p`) where ((`v`.`id` = `w`.`id`) and (`w`.`id` = `wp`.`word_id`) and (`wp`.`phrase_id` = `p`.`id`)) */;
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

-- Dump completed on 2015-12-09 19:26:05
