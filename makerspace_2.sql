-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: makerspace
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `new`
--

DROP TABLE IF EXISTS `new`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `new` (
  `new_id` varchar(255) NOT NULL,
  `credit_code` varchar(255) DEFAULT NULL,
  `organization_code` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `picture` mediumblob,
  `represent` varchar(255) DEFAULT NULL,
  `represent_card` mediumblob,
  `represent_phone` varchar(255) DEFAULT NULL,
  `represent_email` varchar(255) DEFAULT NULL,
  `agent` varchar(255) DEFAULT NULL,
  `agent_phone` varchar(255) DEFAULT NULL,
  `agent_email` varchar(255) DEFAULT NULL,
  `register_capital` varchar(255) DEFAULT NULL,
  `real_capital` varchar(255) DEFAULT NULL,
  `origin_number` varchar(255) DEFAULT NULL,
  `register_time` varchar(255) DEFAULT NULL,
  `nature` varchar(255) DEFAULT NULL,
  `certificate` mediumblob,
  `involved` varchar(255) DEFAULT NULL,
  `main_business` varchar(255) DEFAULT NULL,
  `business` varchar(255) DEFAULT NULL,
  `new_demand_id` varchar(255) DEFAULT NULL,
  `new_shareholder_id` varchar(255) DEFAULT NULL,
  `new_mainperson_id` varchar(255) DEFAULT NULL,
  `new_project_id` varchar(255) DEFAULT NULL,
  `new_intellectual_id` varchar(255) DEFAULT NULL,
  `suggestion` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `submit_time` varchar(255) DEFAULT NULL,
  `room` varchar(255) DEFAULT NULL,
  `old_inapply_id` varchar(255) DEFAULT NULL,
  `old_outapply_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`new_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='新成立企业或非独立注册企业';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `new`
--

LOCK TABLES `new` WRITE;
/*!40000 ALTER TABLE `new` DISABLE KEYS */;
/*!40000 ALTER TABLE `new` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `new_demand`
--

DROP TABLE IF EXISTS `new_demand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `new_demand` (
  `id` varchar(255) NOT NULL,
  `new_demand_id` varchar(255) DEFAULT NULL,
  `lease_area` varchar(255) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  `lease` varchar(255) DEFAULT NULL,
  `floor` varchar(255) DEFAULT NULL,
  `electric` varchar(255) DEFAULT NULL,
  `water` varchar(255) DEFAULT NULL,
  `web` varchar(255) DEFAULT NULL,
  `others` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `new_demand`
--

LOCK TABLES `new_demand` WRITE;
/*!40000 ALTER TABLE `new_demand` DISABLE KEYS */;
/*!40000 ALTER TABLE `new_demand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `new_intellectual`
--

DROP TABLE IF EXISTS `new_intellectual`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `new_intellectual` (
  `id` varchar(255) NOT NULL,
  `new_intellectual_id` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `kind` varchar(255) DEFAULT NULL,
  `apply_time` varchar(255) DEFAULT NULL,
  `approval_time` varchar(255) DEFAULT NULL,
  `intellectual_file` mediumblob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `new_intellectual`
--

LOCK TABLES `new_intellectual` WRITE;
/*!40000 ALTER TABLE `new_intellectual` DISABLE KEYS */;
/*!40000 ALTER TABLE `new_intellectual` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `new_mainperson`
--

DROP TABLE IF EXISTS `new_mainperson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `new_mainperson` (
  `id` varchar(255) NOT NULL,
  `new_shareholder_id` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `born` varchar(255) DEFAULT NULL,
  `job` varchar(255) DEFAULT NULL,
  `school` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `background` varchar(255) DEFAULT NULL,
  `professional` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `new_mainperson`
--

LOCK TABLES `new_mainperson` WRITE;
/*!40000 ALTER TABLE `new_mainperson` DISABLE KEYS */;
/*!40000 ALTER TABLE `new_mainperson` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `new_project`
--

DROP TABLE IF EXISTS `new_project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `new_project` (
  `id` varchar(255) NOT NULL,
  `new_project_id` varchar(255) DEFAULT NULL,
  `project_brief` varchar(255) DEFAULT NULL,
  `advantage` varchar(255) DEFAULT NULL,
  `market` varchar(255) DEFAULT NULL,
  `energy` varchar(255) DEFAULT NULL,
  `pollution` varchar(255) DEFAULT NULL,
  `noise` varchar(255) DEFAULT NULL,
  `others` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `new_project`
--

LOCK TABLES `new_project` WRITE;
/*!40000 ALTER TABLE `new_project` DISABLE KEYS */;
/*!40000 ALTER TABLE `new_project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `new_shareholder`
--

DROP TABLE IF EXISTS `new_shareholder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `new_shareholder` (
  `id` varchar(255) NOT NULL,
  `new_shareholder_id` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `stake` varchar(255) DEFAULT NULL,
  `nature` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `new_shareholder`
--

LOCK TABLES `new_shareholder` WRITE;
/*!40000 ALTER TABLE `new_shareholder` DISABLE KEYS */;
/*!40000 ALTER TABLE `new_shareholder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-29 21:06:21
