-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: crypto
-- ------------------------------------------------------
-- Server version	5.6.21-log


DROP database IF EXISTS `crypto`;
create database crypto; 
use crypto;
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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `mail` varchar(45) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `password` varchar(45) NOT NULL,
  `max_notes` int(11) NOT NULL DEFAULT '50',
  `max_note_length` int(11) DEFAULT '100',
  `registration_date` date NOT NULL,
  `last_visit_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `mail_UNIQUE` (`mail`)
) ENGINE=InnoDB AUTO_INCREMENT=1121 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Serega','serg290696@gmail.com','Serega','dbc27ed91b17625fa091ae27ea1b8047',500,10000,'2015-01-01','2015-07-28'),(1100,'Bob','mailboba@gmail.com','Bob','3fc0a7acf087f549ac2b266baf94b8b1',50,200,'2015-07-28','2015-07-28'),(1101,'exam32064','mail27404@gmail.com','user','d8578edf8458ce06fbc5bb76a58c5ca4',50,100,'2015-07-28','2015-07-28'),(1102,'exam51717','mail44057@gmail.com','user','d8578edf8458ce06fbc5bb76a58c5ca4',50,100,'2015-07-28','2015-07-28'),(1103,'exam18176','mail67042@gmail.com','user','d8578edf8458ce06fbc5bb76a58c5ca4',50,100,'2015-07-28','2015-07-28'),(1104,'exam8726','mail34717@gmail.com','user','d8578edf8458ce06fbc5bb76a58c5ca4',50,100,'2015-07-28','2015-07-28'),(1105,'exam99571','mail13362@gmail.com','user','d8578edf8458ce06fbc5bb76a58c5ca4',50,100,'2015-07-28','2015-07-28'),(1106,'exam79842','mail60928@gmail.com','user','d8578edf8458ce06fbc5bb76a58c5ca4',50,100,'2015-07-28','2015-07-28'),(1107,'exam73120','mail80219@gmail.com','user','d8578edf8458ce06fbc5bb76a58c5ca4',50,100,'2015-07-28','2015-07-28'),(1108,'exam35520','mail52503@gmail.com','user','d8578edf8458ce06fbc5bb76a58c5ca4',50,100,'2015-07-28','2015-07-28'),(1109,'exam1727','mail20542@gmail.com','user','d8578edf8458ce06fbc5bb76a58c5ca4',50,100,'2015-07-28','2015-07-28'),(1110,'exam68187','mail99568@gmail.com','user','d8578edf8458ce06fbc5bb76a58c5ca4',50,100,'2015-07-28','2015-07-28'),(1111,'exam68565','mail99256@gmail.com','user','d8578edf8458ce06fbc5bb76a58c5ca4',50,100,'2015-07-28','2015-07-28'),(1112,'exam62168','mail48527@gmail.com','user','d8578edf8458ce06fbc5bb76a58c5ca4',50,100,'2015-07-28','2015-07-28'),(1113,'exam56492','mail65692@gmail.com','user','d8578edf8458ce06fbc5bb76a58c5ca4',50,100,'2015-07-28','2015-07-28'),(1114,'exam23880','mail72430@gmail.com','user','d8578edf8458ce06fbc5bb76a58c5ca4',50,100,'2015-07-28','2015-07-28'),(1115,'exam29791','mail31102@gmail.com','user','d8578edf8458ce06fbc5bb76a58c5ca4',50,100,'2015-07-28','2015-07-28'),(1116,'exam69310','mail24587@gmail.com','user','d8578edf8458ce06fbc5bb76a58c5ca4',50,100,'2015-07-28','2015-07-28'),(1117,'exam55598','mail56748@gmail.com','user','d8578edf8458ce06fbc5bb76a58c5ca4',50,100,'2015-07-28','2015-07-28'),(1118,'exam33015','mail60874@gmail.com','user','d8578edf8458ce06fbc5bb76a58c5ca4',50,100,'2015-07-28','2015-07-28'),(1119,'exam22581','mail10356@gmail.com','user','d8578edf8458ce06fbc5bb76a58c5ca4',50,100,'2015-07-28','2015-07-28'),(1120,'exam66908','mail72647@gmail.com','user','d8578edf8458ce06fbc5bb76a58c5ca4',50,100,'2015-07-28','2015-07-28');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notes`
--

DROP TABLE IF EXISTS `notes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(25) NOT NULL,
  `value` varchar(10000) NOT NULL,
  `id_user` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_note_UNIQUE` (`id`),
  KEY `id_user_idx` (`id_user`),
  CONSTRAINT `id_user` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notes`
--

LOCK TABLES `notes` WRITE;
/*!40000 ALTER TABLE `notes` DISABLE KEYS */;
INSERT INTO `notes` VALUES (20,'Note','Random text: 533604',1),(21,'Note404','Random text: 986268',1),(22,'Note772','Random text: 355308',1),(23,'Note553','Random text: 260361',1),(24,'Note409','Random text: 81424',1),(25,'Note405','Random text: 13322',1),(26,'Note823','Random text: 148020',1),(27,'Note761','Random text: 21224',1),(28,'Note484','Random text: 767638',1),(29,'Note715','Random text: 57890',1),(30,'Note468','Random text: 857179',1),(31,'Note989','Random text: 766851',1),(32,'Note893','Random text: 377873',1),(33,'Note717','Random text: 811758',1),(34,'Note662','Random text: 155979',1),(35,'Note587','Random text: 896479',1),(36,'Note944','Random text: 90197',1),(37,'Note520','Random text: 684055',1),(38,'Note714','Random text: 162119',1),(39,'Note400','Random text: 908603',1),(40,'Note72','Random text: 577989',1),(41,'Note245','Random text: 749528',1),(42,'Note415','Random text: 378286',1),(43,'Note62','Random text: 81176',1),(44,'Note584','Random text: 245283',1),(45,'Note961','Random text: 884169',1),(47,'Note23','Random text: 131888',1),(48,'Note355','Random text: 481645',1),(49,'Note130','Random text: 816239',1),(50,'Note363','Random text: 82013',1),(51,'Note186','Random text: 449576',1),(52,'Note499','Random text: 19895',1),(53,'Note474','Random text: 958089',1),(54,'Note741','Random text: 48645',1),(55,'Note763','Random text: 925009',1),(56,'Note959','Random text: 424132',1),(57,'Note27','Random text: 739400',1),(58,'Note88','Random text: 731977',1),(59,'Note347','Random text: 627777',1),(60,'Note627','Random text: 172557',1),(61,'Note430','Random text: 323494',1),(62,'Note174','Random text: 586489',1),(63,'Note12','Random text: 168525',1),(64,'Note557','Random text: 189499',1),(65,'Note727','Random text: 670694',1),(66,'Note209','Random text: 806154',1),(67,'Note163','Random text: 747490',1),(68,'Note389','Random text: 268900',1),(69,'Note128','Random text: 458042',1),(70,'Note2','Random text: 573366',1),(71,'Note670','Random text: 730821',1),(72,'Note116','Random text: 112748',1),(73,'Note397','Random text: 671395',1),(74,'Note907','Random text: 810148',1),(75,'Note757','Random text: 569518',1),(76,'Note660','Random text: 563312',1),(77,'Note432','Random text: 949349',1),(78,'Note931','Random text: 120676',1),(79,'Note410','Random text: 580362',1),(80,'Note476','Random text: 293095',1),(81,'Note413','Random text: 276736',1),(82,'Note863','Random text: 645955',1),(83,'Note635','Random text: 434960',1),(84,'Note633','Random text: 908375',1),(85,'Test note','Random text: 594482',1),(86,'Test note','Random text: 418594',1),(87,'my note 1. name','Hello! I`mBob!key',1100),(88,'TOP SECRET #1','ONgwdoGSFXGaM7C+zlisuh0xzIfRo/pFlTmVuAP/Z12dw1FuDb0cQQ==',1);
/*!40000 ALTER TABLE `notes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'crypto'
--

--
-- Dumping routines for database 'crypto'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-08-05 21:42:43
