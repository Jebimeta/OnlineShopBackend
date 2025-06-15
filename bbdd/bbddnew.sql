CREATE DATABASE  IF NOT EXISTS `online_shop` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `online_shop`;
-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: online_shop
-- ------------------------------------------------------
-- Server version	8.4.4

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `customer_id` bigint NOT NULL,
  `date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (2,26,'2025-06-07 21:59:32.385694'),(3,14,'2025-06-09 19:09:21.577152'),(4,14,'2025-06-09 19:10:21.642524'),(5,14,'2025-06-11 20:08:36.819135'),(7,28,'2025-06-11 20:33:59.107155'),(8,28,'2025-06-11 21:06:51.141374'),(9,28,'2025-06-11 21:20:08.396109'),(10,28,'2025-06-11 21:21:52.647634'),(11,28,'2025-06-12 00:14:24.973525'),(12,28,'2025-06-12 00:14:54.046166'),(13,28,'2025-06-12 17:07:22.451136'),(14,28,'2025-06-12 17:09:34.943657'),(15,28,'2025-06-12 17:10:15.655161'),(16,28,'2025-06-12 17:10:36.401547'),(17,28,'2025-06-12 17:12:26.710805'),(18,28,'2025-06-12 17:14:49.703257');
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_details`
--

DROP TABLE IF EXISTS `cart_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_details` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cart_id` bigint NOT NULL,
  `product_id` bigint NOT NULL,
  `price` double NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cart_id` (`cart_id`,`product_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `cart_details_ibfk_1` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`) ON DELETE CASCADE,
  CONSTRAINT `cart_details_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_details`
--

LOCK TABLES `cart_details` WRITE;
/*!40000 ALTER TABLE `cart_details` DISABLE KEYS */;
INSERT INTO `cart_details` VALUES (2,2,6,14.95,1),(3,3,21,109.95,1),(4,4,7,14.95,1),(5,5,25,24.95,1),(7,7,7,14.95,1),(8,8,10,14.95,1),(9,9,6,14.95,1),(10,10,15,19.95,1),(11,11,20,19.95,1),(12,12,12,45.95,1),(13,13,7,14.95,1),(14,14,17,19.95,1),(15,15,17,19.95,1),(16,16,15,19.95,1),(17,17,25,24.95,1),(18,18,22,9.95,1);
/*!40000 ALTER TABLE `cart_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `surname2` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `province` varchar(255) NOT NULL,
  `region` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `status` tinyint(1) NOT NULL,
  `rol` varchar(50) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `verification_token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'jmarchenaroldan@outlook.es','$2a$10$ke80AvtDVnRI1qmdgXw8pOF4pG1U9tpmhTsj/VBL0OwYgHoqkPIVW','Juan','Alora','Jimenez','Vicario','Sevilla','','jmarchenaroldan@outlook.es','',1,'ADMIN',NULL,NULL,'2e0f948f-5aa8-4270-8115-a38e7413f864'),(14,'jebilongo@gmail.com','$2a$10$St5w4b1VMcnG0l8rewXLA.eqNIdclao7XPW2spzv5G.VYuDHxWx2K','Sandra','Brenes','Valdivieso','Calle del Deposito, 11','Sevilla','Andalucía','jebilongo@gmail.com','666999666',1,'USER',NULL,NULL,NULL),(16,'laura.garcia','$2a$10$ke80AvtDVnRI1qmdgXw8pOF4pG1U9tpmhTsj/VBL0OwYgHoqkPIVW','Laura','García','López','Calle Mayor, 45','Madrid','Comunidad de Madrid','laura.garcia@example.com','611223344',1,'USER','Madrid','28013',NULL),(17,'carlos.martin','$2a$10$ke80AvtDVnRI1qmdgXw8pOF4pG1U9tpmhTsj/VBL0OwYgHoqkPIVW','Carlos','Martín','Sánchez','Avenida de la Constitución, 12','Barcelona','Cataluña','carlos.martin@example.com','622334455',1,'USER','Barcelona','08001',NULL),(18,'ana.rodriguez','$2a$10$ke80AvtDVnRI1qmdgXw8pOF4pG1U9tpmhTsj/VBL0OwYgHoqkPIVW','Ana','Rodríguez','Fernández','Plaza España, 3','Valencia','Comunidad Valenciana','ana.rodriguez@example.com','633445566',1,'USER','Valencia','46004',NULL),(19,'david.lopez','$2a$10$ke80AvtDVnRI1qmdgXw8pOF4pG1U9tpmhTsj/VBL0OwYgHoqkPIVW','David','López','Gómez','Calle Gran Vía, 67','Sevilla','Andalucía','david.lopez@example.com','644556677',1,'USER','Sevilla','41001',NULL),(20,'marta.sanchez','$2a$10$ke80AvtDVnRI1qmdgXw8pOF4pG1U9tpmhTsj/VBL0OwYgHoqkPIVW','Marta','Sánchez','Pérez','Calle Real, 89','Zaragoza','Aragón','marta.sanchez@example.com','655667788',1,'USER','Zaragoza','50001',NULL),(21,'javier.gomez','$2a$10$ke80AvtDVnRI1qmdgXw8pOF4pG1U9tpmhTsj/VBL0OwYgHoqkPIVW','Javier','Gómez','Martínez','Paseo de la Habana, 34','Málaga','Andalucía','javier.gomez@example.com','666778899',1,'USER','Málaga','29001',NULL),(22,'sofia.hernandez','$2a$10$ke80AvtDVnRI1qmdgXw8pOF4pG1U9tpmhTsj/VBL0OwYgHoqkPIVW','Sofía','Hernández','Díaz','Calle Alcalá, 56','Murcia','Región de Murcia','sofia.hernandez@example.com','677889900',1,'USER','Murcia','30001',NULL),(23,'pablo.diaz','$2a$10$ke80AvtDVnRI1qmdgXw8pOF4pG1U9tpmhTsj/VBL0OwYgHoqkPIVW','Pablo','Díaz','Ruiz','Avenida de Andalucía, 78','Granada','Andalucía','pablo.diaz@example.com','688990011',1,'USER','Granada','18001',NULL),(24,'elena.martinez','$2a$10$ke80AvtDVnRI1qmdgXw8pOF4pG1U9tpmhTsj/VBL0OwYgHoqkPIVW','Elena','Martínez','Alonso','Calle Sierpes, 23','Cádiz','Andalucía','elena.martinez@example.com','699001122',1,'USER','Cádiz','11001',NULL),(25,'raul.fernandez','$2a$10$ke80AvtDVnRI1qmdgXw8pOF4pG1U9tpmhTsj/VBL0OwYgHoqkPIVW','Raúl','Fernández','Gutiérrez','Plaza del Ayuntamiento, 1','Bilbao','País Vasco','raul.fernandez@example.com','600112233',1,'USER','Bilbao','48001',NULL),(26,'proyectoopositaweb@gmail.com','$2a$10$mdp4vdWNqq29z0ipN3ezneJgQloEdkuEY69UJEnCc4aqy6G3gPrs6','José','Marchena','Roldan','Vicario','Sevilla','Andalucía','proyectoopositaweb@gmail.com','666666666',1,'USER',NULL,NULL,NULL),(28,'jose.marchena-roldan@iesruizgijon.com','$2a$10$9ocHOriqunpwIl9EDfaOO.lSvcBCBFhxn4l/FxLvSjyputRxOezMG','Prueba','Prueba','Prueba','vicario','Sevilla','Andalucía','jose.marchena-roldan@iesruizgijon.com','666666666',1,'USER',NULL,NULL,NULL);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `size` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (6,'Mano Herética','Mano herética con runas y símbolos','Lámina',14.95,'21-the-hand_orig.png','20 x 20'),(7,'Vampiro Gótico','Calavera de murcielago estilo gótico','Lámina',14.95,'bat_orig.png','20 x 20'),(9,'Nosferatu','Recreación del vampiro nosferatu personalizado.','Lámina',19.95,'nosferatu_orig.png','20 x 20'),(10,'Caduceo de Hermes','Simbolo de la enfermeria y la salud','Lámina',14.95,'serpientes.webp','20 x 20'),(11,'Sailor Moon','Varita mágica con la gata Luna al estilo siniestro','Lámina',19.95,'sailormoon.png','20 x 20'),(12,'Bárbaro','Lámina de bárbaro de espada y brujeria','Lámina',45.95,'barbaro.webp','40 x 40'),(13,'Psicodelia','Cuadro que atrapa el estilo psicodélico de los años 60 y 70','Cartel',59.95,'psicodelico.webp','60 x 60'),(14,'Cristo del Perdón','Jesucristo implorando a su padre a que perdone por lo que hacen','Lámina',9.95,'Cristo.png','10 x 10'),(15,'Zombie','Zombie purulento con detalles en rojo(sangre)','Lámina',19.95,'Zombi.webp','20 x 20'),(17,'Medusa','Recreación de Medusa con puntillismo.','Lámina',19.95,'Medusa.webp','20 x 20'),(18,'Vida y Muerte','Frasco de vida con puñales al fondo.','Lámina',24.95,'img-0366_orig.png','20 x 20'),(19,'Naturalis','Creación de vida a traves de la fotografía.','Lámina',19.95,'img-0379_orig.png','20 x 20'),(20,'Rayo Demoledor','Rayo cayendo sobre la tierra.','Ilustración',19.95,'rayo.webp','20 x 20'),(21,'Enano Guerrero','Enano listo para la batalla','Cuadro',109.95,'Enano.webp','60 x 60'),(22,'Helecho','Planta de la época del precambrico.','Lámina',9.95,'helecho.webp','10 x 10'),(25,'Mano Herética 32 bits','Versión de la mano herética en 32 bits','Lámina',24.95,'Mano 32 bits.png','20 x 20');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `customer_id` bigint NOT NULL,
  `purchase_date` date DEFAULT NULL,
  `total_amount` double NOT NULL,
  `status` varchar(50) NOT NULL,
  `shipping_address` varchar(255) NOT NULL,
  `order_date` datetime(6) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `purchase_ibfk_1` (`customer_id`),
  CONSTRAINT `purchase_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase`
--

LOCK TABLES `purchase` WRITE;
/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
INSERT INTO `purchase` VALUES (1,28,NULL,14.95,'PLACED','Dirección de ejemplo','2025-06-11 21:15:54.911058');
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_details`
--

DROP TABLE IF EXISTS `purchase_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase_details` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `purchase_id` bigint NOT NULL,
  `product_id` bigint NOT NULL,
  `price` double NOT NULL,
  `quantity` int NOT NULL,
  `order_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`),
  KEY `FKm8bxki3lrflhwrjardp786uga` (`order_id`),
  KEY `purchase_details_ibfk_1` (`purchase_id`),
  CONSTRAINT `FKm8bxki3lrflhwrjardp786uga` FOREIGN KEY (`order_id`) REFERENCES `purchase` (`id`),
  CONSTRAINT `purchase_details_ibfk_1` FOREIGN KEY (`purchase_id`) REFERENCES `purchase` (`id`) ON DELETE CASCADE,
  CONSTRAINT `purchase_details_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_details`
--

LOCK TABLES `purchase_details` WRITE;
/*!40000 ALTER TABLE `purchase_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchase_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `token` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `access_token` varchar(255) DEFAULT NULL,
  `is_logged_out` bit(1) DEFAULT NULL,
  `refresn_token` varchar(255) DEFAULT NULL,
  `customer_id` bigint DEFAULT NULL,
  `refresh_token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKke9j5rnuwqfkmnyyfa9k2p3ug` (`customer_id`),
  CONSTRAINT `FKke9j5rnuwqfkmnyyfa9k2p3ug` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` VALUES (1,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0NjY1MzYxNSwiZXhwIjoxNzQ2NzQwMDE1fQ._MSwV1JzG1IQGVVhDcjGMjNReYp9wc8Am0FJrOzYjvYbDY4XmfaOrtliXYMNiqjF',_binary '',NULL,1,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0NjY1MzYxNSwiZXhwIjoxNzQ3MjU4NDE1fQ.ueZpnMFVdJ5df3MEYazPvB91VMOvyvln68-NuLIQssWprwvMkZ6ygYGs7XywO3jt'),(2,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0NjgxOTU2NSwiZXhwIjoxNzQ2OTA1OTY1fQ.P-TACpjSzRuMwl3Y0iY0Uf3WnlLAtFHnpydFdjKXdT99kWbEcwp5_f2aN1MTuimI',_binary '',NULL,1,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0NjgxOTU2NiwiZXhwIjoxNzQ3NDI0MzY2fQ.luAK6HOKaIiH7pAUMYBrPayRAiUWtCUYwb3b08_Ar11VoTo2p-NkvnvqUWr5hDTr'),(3,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0Njg5NDUyOSwiZXhwIjoxNzQ2OTgwOTI5fQ.g67nYObAN7gJKTWvTzSRYNPQzR-kMBP-oqitP7LN55TE_dwgKEv3wl9bAZpfnApo',_binary '',NULL,1,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0Njg5NDUyOSwiZXhwIjoxNzQ3NDk5MzI5fQ.4ow3ens1IATIMaNI7SEYE0EG68x_AiAsSKet5m_3Gp_cKp9ZhDMxUaxGl-d-Em-c'),(4,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0Njg5NDY5MiwiZXhwIjoxNzQ2OTgxMDkyfQ.SQ-R4UwSyH-HQ-9AnmtwCdKt9UzeQd18Z4mpKfF7r7e47ceOtiMWQ-gR1fSe4IDI',_binary '',NULL,1,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0Njg5NDY5MiwiZXhwIjoxNzQ3NDk5NDkyfQ.JVA6s8i0O4aIgSGugouPO2VA1diOg8wKyV9pqqVq5DJDIi9vm2rE1NOpSe6dBZGf'),(14,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0NzA2NDQyMywiZXhwIjoxNzQ3MTUwODIzfQ.oJoDt35YcYRNaMp1_HdANHa00DX0kgtQeVh8UtaBq938__BfpxsvuHbI7ejCZL24',_binary '',NULL,1,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0NzA2NDQyMywiZXhwIjoxNzQ3NjY5MjIzfQ.evtk4Z4ehC7swP1aacrIJxxm0qyHk-Sw8rHhXvr9nrLMBcQQrI2JyO03Iapwhj63'),(15,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0NzE1MjcxMSwiZXhwIjoxNzQ3MjM5MTExfQ.7TlLWEZTGm1kpx9g3YKGOze3Tb28kvOqRZ3_pqiOzmniyQRaOlLaRw9xJ_EfylQn',_binary '',NULL,1,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0NzE1MjcxMSwiZXhwIjoxNzQ3NzU3NTExfQ.foh1jZVNwRHouB0-dxgCKErMe4xeXnYx-xRQtjPdE5q2BtDnC1MReMtTGGv2InT9'),(17,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqZWJpbG9uZ29AZ21haWwuY29tIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NDcxNTMwNzksImV4cCI6MTc0NzIzOTQ3OX0.VSQx_pA5sMt2a-SUbYMaHmcBVWIyuIDdy0flaEck2UiDgzziBb1qnLiygP8zU3dw',_binary '',NULL,14,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqZWJpbG9uZ29AZ21haWwuY29tIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NDcxNTMwNzksImV4cCI6MTc0Nzc1Nzg3OX0.xgtK0YUazUq85TJODdvpfLdVS8gz_CF17pY2FmL4Gl-NMA4uKVh0hePiBCq-ZkoJ'),(18,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0NzE1OTg1OCwiZXhwIjoxNzQ3MjQ2MjU4fQ.PXSGt2bDELrtPyrSqUzdcUDPK-pCl7fwufIFykpp2MWm-Exw8bhSiqAsUYzzwYqd',_binary '',NULL,1,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0NzE1OTg1OCwiZXhwIjoxNzQ3NzY0NjU4fQ.ktymC0dP1pv0Nsqe4BUQCzOWImhu76RY4amoyOAhxNiYlION2jHIXpzmUdcR5aTU'),(19,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0NzIzNzc0OCwiZXhwIjoxNzQ3MzI0MTQ3fQ.Y30qdo5tYiKydZy0BuSHNXfzYJNYhAmreitmaLivBjvcuFE3eYv2LqNmymjhsYLr',_binary '',NULL,1,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0NzIzNzc0OCwiZXhwIjoxNzQ3ODQyNTQ4fQ.7AJrAw32Ve3bLbeeus9PPtSs2TbpppUYtqlGmGbCFQ2RM1WLQJivK-VwtFYNA86B'),(20,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0NzM0MTk2OSwiZXhwIjoxNzQ3NDI4MzY5fQ.AofvBeOUbUrx1cCnD-cIlNIKzfntyKC6oLIfR0l0KEuXK8YXc9ar0Otzst-5oT1r',_binary '',NULL,1,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0NzM0MTk2OSwiZXhwIjoxNzQ3OTQ2NzY5fQ.GfVt6xEx-tIV5qKnP0Cg-XLAupRGTEqaCRbR9UBJGiV8AXG3tgp6NGSmp1o-rG9a'),(21,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqZWJpbG9uZ29AZ21haWwuY29tIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NDczNDE5ODYsImV4cCI6MTc0NzQyODM4Nn0.5HUozWdZHE62LgpMA2Wr8S0MSK84khWT6ObzJjpLx1D7s2in6k53S3oRDSsWps30',_binary '',NULL,14,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqZWJpbG9uZ29AZ21haWwuY29tIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NDczNDE5ODYsImV4cCI6MTc0Nzk0Njc4Nn0.-qT98XSqPjHNMyZUgCiKAwJzWPe20rEpzHVHkofWrQddDGQqx6IzD8F_egPDsO3h'),(22,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqZWJpbG9uZ29AZ21haWwuY29tIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NDczNDQyMzIsImV4cCI6MTc0NzQzMDYzMn0._gh2vGoZ4GKxjbd4Ke0vnRHggEjLxNhAqpvaItHONNeQRoGEsPnFWNsyN3XPJTpY',_binary '',NULL,14,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqZWJpbG9uZ29AZ21haWwuY29tIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NDczNDQyMzIsImV4cCI6MTc0Nzk0OTAzMn0.wiIdPCmtAoXVIVzA0UEBXFZdp_B30eCzNzoLL2Bgz0EGWn3KGY3KFxJYzL1iWM9v'),(23,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqZWJpbG9uZ29AZ21haWwuY29tIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NDc1MDU5NTYsImV4cCI6MTc0NzU5MjM1Nn0.We5WhaCERbNdRQyCqs6GEMxNp5xqSgNJpGHvVqghFEbuMrgSk-O7tdDMze41NxQJ',_binary '',NULL,14,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqZWJpbG9uZ29AZ21haWwuY29tIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NDc1MDU5NTYsImV4cCI6MTc0ODExMDc1Nn0.f39S6Rl5o3uLIrvVbB4VuYY2NbYVcbYg27zr45FB7j7lAyZfKEtA3p9EXM3H-w9J'),(24,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0NzUwODE1NCwiZXhwIjoxNzQ3NTk0NTU0fQ.vlHtqu14ErWq84mQQVSBMpP8fiyB4ZTSRKI5xzXe6WQBRny9_5EpmS2TghydpwWg',_binary '',NULL,1,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0NzUwODE1NCwiZXhwIjoxNzQ4MTEyOTU0fQ.oNCcKTUQLw955WwhABHnIP-k2PLVDkLBa8ZmzMXsa0hgRXsc7SEWBmZszGisAQ5w'),(25,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0NzUxNTMwOSwiZXhwIjoxNzQ3NjAxNzA5fQ.YwXB_W_DoXJcfugLUH1c_7Nlfe2aqGhtFytGF_AMJTkI-My9XcxsHvLd0_uF5cvT',_binary '',NULL,1,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0NzUxNTMwOSwiZXhwIjoxNzQ4MTIwMTA5fQ.9NuIv-hrjihcSyh7fgBN77QBbsdzKBVtajTrTUhnhDDgbw3wGZGHVp8OaRI10zds'),(26,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0NzkzMjk5NiwiZXhwIjoxNzQ4MDE5Mzk2fQ.H38oKfwMAVI5JL03fIqLcCyS58kxkQkecPdicQZVE96WCsy-OaDzm7VOBDIXZA7q',_binary '',NULL,1,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0NzkzMjk5NiwiZXhwIjoxNzQ4NTM3Nzk2fQ.yDpOjCxvf89UN-Sb7HtQJpyCnKdtsg9n6MKZpUF9Yyp2WL-NXtPd9enPvmORh01x'),(27,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqZWJpbG9uZ29AZ21haWwuY29tIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NDc5MzMxMTcsImV4cCI6MTc0ODAxOTUxN30.oRtKJ_6RT2VBQbAp5AyQ3bi63dpiSG0PzNvMPsdOyG96TVTKfKoZoA4Q8OJ6TBx3',_binary '',NULL,14,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqZWJpbG9uZ29AZ21haWwuY29tIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NDc5MzMxMTcsImV4cCI6MTc0ODUzNzkxN30.9eXlMhMsNHBvjEK_lQqkKRUdLur0zewgE3Gz25-kEsNds-HN3mOJlF5mO6prqvxB'),(28,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0NzkzMzI1OSwiZXhwIjoxNzQ4MDE5NjU5fQ.68jglLQF4QZ344S5VG5jBLvv1PhVFdQWzSzTzEZ54g4_2BasULHVnbeWaxVZtJlp',_binary '',NULL,1,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0NzkzMzI1OSwiZXhwIjoxNzQ4NTM4MDU5fQ.no5YqsVCmP-VvBkdrZa7tPph7F8p_T3iHdprAQq7tFyoyzAxs6Bhe8uqriR0LCEm'),(29,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqZWJpbG9uZ29AZ21haWwuY29tIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NDc5Mzc5NDMsImV4cCI6MTc0ODAyNDM0M30.cFMvZvAHyZsxCcnLlwnj9RcXcjyAlecqwczDlYcdpOipvkTJ8XDIglweongGshM8',_binary '',NULL,14,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqZWJpbG9uZ29AZ21haWwuY29tIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NDc5Mzc5NDMsImV4cCI6MTc0ODU0Mjc0M30.4c8ppRa1n0eeW08dOV7qvnT0U-NrDi2lrGXosQNQ8huRcmli7qNEIMPDpsX9rBxW'),(30,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqZWJpbG9uZ29AZ21haWwuY29tIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NDc5Mzc5OTksImV4cCI6MTc0ODAyNDM5OX0.lEoTfK2Zsxt_zCWg8n5_5MZ0vLDS5K5Bvc_3xbvhRcRiZg8A747zvYtsC1xWTEaC',_binary '',NULL,14,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqZWJpbG9uZ29AZ21haWwuY29tIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NDc5Mzc5OTksImV4cCI6MTc0ODU0Mjc5OX0.TviYnrhea0xgKW9nul68wxOlOdppPek2zXjviPe0iOXeoCyIc2Y6tX3n5ktNOYIM'),(32,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0Nzk5MTA5MSwiZXhwIjoxNzQ4MDc3NDkxfQ.YyCV9qhjZq8RiGCwD2da52K4uWeRguMJMUKdz0rw4V5kdAri0bWcfcXn8oJZad7U',_binary '',NULL,1,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0Nzk5MTA5MSwiZXhwIjoxNzQ4NTk1ODkxfQ.SBSCsUGoi48DOrzRCjgLrrD-8ZTDNHDMwlhKCWZxarZqjxU_ITRkpW4oGow2LIYK'),(33,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0ODExOTk3MSwiZXhwIjoxNzQ4MjA2MzcwfQ.ysSO8LVrqwVIK66fYb2d0FhNWK54GC55llderQSyLyTk6wwuOwPpj0xkk4G70Hmt',_binary '',NULL,1,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0ODExOTk3MSwiZXhwIjoxNzQ4NzI0NzcxfQ.L2rT33CtIzdiCW49ycQJ85WVFzJQe6brM5tj6tplPt1Yu9-nosDhMGe9c4UwHcu3'),(34,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0ODExOTk3MSwiZXhwIjoxNzQ4MjA2MzcxfQ.k5gdl8HvYc5NI2Vl6OHPu-han02FTs-zP6a7DoLcYDRnWH2fTbl-bpJca6tRF9V3',_binary '',NULL,1,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0ODExOTk3MSwiZXhwIjoxNzQ4NzI0NzcxfQ.L2rT33CtIzdiCW49ycQJ85WVFzJQe6brM5tj6tplPt1Yu9-nosDhMGe9c4UwHcu3'),(35,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqZWJpbG9uZ29AZ21haWwuY29tIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NDgxOTg5MDYsImV4cCI6MTc0ODI4NTMwNn0.qR1iKbE3CRtxah99sMmhvdI-Ml4HqCbQi4A-ZLnebX5cKAOqOvQQiGV6NadtHvc3',_binary '',NULL,14,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqZWJpbG9uZ29AZ21haWwuY29tIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NDgxOTg5MDYsImV4cCI6MTc0ODgwMzcwNn0.ZBJRxmSoR8y62WDIySLUbDvsNrbGt6qajAqfnFEscBTrUstw177HgE6IZmaFktlu'),(36,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0ODIwMzA3NiwiZXhwIjoxNzQ4Mjg5NDc2fQ.Ro2pjli94d4bVvCRLZzJv6CVOlksxlhZeGaZeXmNFr7Qs56grYZPNYvnM81kPvL5',_binary '',NULL,1,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0ODIwMzA3NiwiZXhwIjoxNzQ4ODA3ODc2fQ.-GQGpSH-jw7_rvN9Uf3akLytW4IW2A5WaoAUR6W6bvZY6kVuRpJLru-QTmbvzq7c'),(37,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0ODI3NzA5NiwiZXhwIjoxNzQ4MzYzNDk2fQ.0xbBWG5pt6n8PIYnffzE5O7Ye0_j8eMGsIS9nObTdPuZcUSv-d74p5jkNk9tOj02',_binary '',NULL,1,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0ODI3NzA5NiwiZXhwIjoxNzQ4ODgxODk2fQ.04HF9QzCq0S-KdRrgo0_E_Obyx4-PgYx_44Ykkb0Pe8eLfeU0bWr1wamp2BPRFQV'),(38,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0ODI3NzIyNSwiZXhwIjoxNzQ4MzYzNjI1fQ.p-BM7HmqvFyd437NG714ynyzVlEf_ixv0aQYuHukPeJ-XtnqD7jlPBF9p58rNpBo',_binary '',NULL,1,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0ODI3NzIyNSwiZXhwIjoxNzQ4ODgyMDI1fQ.WTfrm9Xq34bYcvOdtNbHROKvn0Eq0YA84MUFCkmo0SPniPwph_kmk8fS9ozqE4Ri'),(39,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqZWJpbG9uZ29AZ21haWwuY29tIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NDgyNzcyNjksImV4cCI6MTc0ODM2MzY2OX0.SpBvjCeSv5XTCBZMxexmIaBAdluJd-QcCRwIXkV9aUvch9qQ1THfEV77ep5mjS6x',_binary '',NULL,14,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqZWJpbG9uZ29AZ21haWwuY29tIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NDgyNzcyNjksImV4cCI6MTc0ODg4MjA2OX0.CUit8I3WVMdqwaKKEIPc_HbfO_NOGuR8mwlCub4AFgKUWw-Vf_AaeF0E26NcRYZ9'),(40,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0ODI3ODM2NCwiZXhwIjoxNzQ4MzY0NzY0fQ.5whzaCrN3Oy9WYUfC7ZRLgPPsWw1XQ3Efhyegv5fvDwf_SbUDK1loYpta63eAh3r',_binary '',NULL,1,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0ODI3ODM2NCwiZXhwIjoxNzQ4ODgzMTY0fQ.bw7NkmY7HvT64KI3H-V2-iLWo8dT4NB6ylITyIPVg3-R-SicOT1_DlFSold3fwGl'),(41,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqZWJpbG9uZ29AZ21haWwuY29tIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NDg3OTExODAsImV4cCI6MTc0ODg3NzU4MH0.4X4tz_8T5vWUVp4rJXgOFQU0_GzI3PEr-qTqtMoHF69eO-QBd_xKFNPKBawJ-zrL',_binary '',NULL,14,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqZWJpbG9uZ29AZ21haWwuY29tIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NDg3OTExODAsImV4cCI6MTc0OTM5NTk4MH0.rTdua0Qi9dUtbr4r-DKEb0g7PJXJqJhy56Wo4J_4mevfQTvpOdsDazkd7oa8WUNO'),(42,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0ODc5MTI3NSwiZXhwIjoxNzQ4ODc3Njc1fQ.wiYBYmDMAmrfCmpb0M1wlUHPiKyuw1JJ8m5pGFEXOErqDmdSuVjwCQMo0AbHadce',_binary '',NULL,1,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0ODc5MTI3NSwiZXhwIjoxNzQ5Mzk2MDc1fQ.DtK6O1YApQEbObzY5evntmgBsLHkHKtHJHmNDiBmBHpRejEBeqvC3l7B4tdWayPV'),(43,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqZWJpbG9uZ29AZ21haWwuY29tIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NDg3OTE3NDUsImV4cCI6MTc0ODg3ODE0NX0.-_gNI7tMlhY_GqVAJCbFVqYNf5mvOk0_GTt0a1EkrgCTnz_mmh8viyEGZiKtG85j',_binary '',NULL,14,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqZWJpbG9uZ29AZ21haWwuY29tIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NDg3OTE3NDUsImV4cCI6MTc0OTM5NjU0NX0.MxG44co-UpLJN62NTo7GJNZK4KDm_oTWSVfnInRLN2B7LWB3QO9dlb6G3nTw9SwJ'),(44,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqZWJpbG9uZ29AZ21haWwuY29tIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NDkwNjQwMzIsImV4cCI6MTc0OTE1MDQzMn0.__H6zrCSgdNNt0znnA5GUEjTMh62GyFJof6lJnE3aW5y-aOn2Vr7SL-J4xvz0VXn',_binary '',NULL,14,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqZWJpbG9uZ29AZ21haWwuY29tIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NDkwNjQwMzIsImV4cCI6MTc0OTY2ODgzMn0.cjaoFKA40lqNB3vP0Ur5-O7OXxJQSugaJZEY1s1ouhzMlDjuakUPHer-kmgCFIuz'),(45,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0OTE1ODEwNSwiZXhwIjoxNzQ5MjQ0NTA1fQ.nwe0TmOHjPNOGtrEkfuV7hCdRU0i0kTeo8bvuiLgQtJ6lOUc3TWr7J9TXWqdMb1g',_binary '',NULL,1,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0OTE1ODEwNSwiZXhwIjoxNzQ5NzYyOTA1fQ.Vw-VUoNmvdzwJTkuSnXZ7_CrVzawN7RyN4UOfrRJ_uJS9_nhmgUvpH-aECnMEHOe'),(46,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0OTI0MDAwMiwiZXhwIjoxNzQ5MzI2NDAyfQ.67wS0ltGw5rYoG8HOiF-5lYeCMyUFckHczFZ9IwnVz4iDu6Nb5QO8_ekJPt15LYB',_binary '',NULL,1,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0OTI0MDAwMywiZXhwIjoxNzQ5ODQ0ODAzfQ.IulFkziWkfW1vIPLOpG_ewiAzkr3kRsej7tOd0HwlXXjbbFU6nEVgfv8SZsiqCDK'),(47,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0OTI0MDAwMywiZXhwIjoxNzQ5MzI2NDAzfQ.irrR9naN6GiOwk4ptv3p9ceT81H-itUALAE7x_Vbo2dYJJjVp7z93JlVDcKjmU2T',_binary '',NULL,1,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0OTI0MDAwMywiZXhwIjoxNzQ5ODQ0ODAzfQ.IulFkziWkfW1vIPLOpG_ewiAzkr3kRsej7tOd0HwlXXjbbFU6nEVgfv8SZsiqCDK'),(48,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0OTMxNTM5OCwiZXhwIjoxNzQ5NDAxNzk4fQ.DjXgoK0JaaAhVg0FQ5TSgNRH1s_qR-LxcKo9Fj9RnIT_PDBk4R7zS4JeLGl7IM1k',_binary '',NULL,1,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0OTMxNTM5OCwiZXhwIjoxNzQ5OTIwMTk4fQ.TsDyXBwIfIgRWgoAkYf6ozG3xpAaVm81OYyq8iPSewfW8xK5nTilD6Da_cWY0pMz'),(49,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJwcm95ZWN0b29wb3NpdGF3ZWJAZ21haWwuY29tIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NDkzMjIwNTAsImV4cCI6MTc0OTQwODQ1MH0.aSBZdbYoV44Y_UE8KlLK2kohvGiqGHz3KNFsgd-qF0Bw9G50cBi7fKXw0yHvqi6G',_binary '\0',NULL,26,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJwcm95ZWN0b29wb3NpdGF3ZWJAZ21haWwuY29tIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NDkzMjIwNTAsImV4cCI6MTc0OTkyNjg1MH0.4b5hUK0LI5B5yGHhkWu-q_cypICskJ5dMvkey3pINf7xDtNcXKOWJH9lmyVduTha'),(50,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqZWJpbG9uZ29AZ21haWwuY29tIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NDkzMjgzNDEsImV4cCI6MTc0OTQxNDc0MX0.ilZWwPBLL7Z3-mqSHWZzMEkBP51qoHlXJq8wckeEs8FMPhIsyHW5atcnGlJlY8OE',_binary '',NULL,14,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqZWJpbG9uZ29AZ21haWwuY29tIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NDkzMjgzNDEsImV4cCI6MTc0OTkzMzE0MX0.v7N_LiluGsj-dGoo4RnLjCjV2_6RMJMXySJaut3XfyDcfClw8G3anfkzz50VErdb'),(51,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0OTM5ODcyOSwiZXhwIjoxNzQ5NDg1MTI5fQ.ibyPSsYt7D0ny3OWZDoDwPXz3eRBRK4zSAIICSTytGBSjyfejSg-0wdDB1Eg_lF3',_binary '',NULL,1,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0OTM5ODcyOSwiZXhwIjoxNzUwMDAzNTI5fQ.udC6ZFb9vyJ_CpuJm0PVjgmWblz4TWXzemA6kbQQuOumOk-4YTOMxIx13jok8ID2'),(52,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqZWJpbG9uZ29AZ21haWwuY29tIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NDk0ODgzNzIsImV4cCI6MTc0OTU3NDc3Mn0.oaMgp4-IYcFX6yfKgoqfbQ1vTrezXYNDw4YJa45MQtOehJiPajIArEQlPDnoLJyY',_binary '',NULL,14,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqZWJpbG9uZ29AZ21haWwuY29tIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NDk0ODgzNzIsImV4cCI6MTc1MDA5MzE3Mn0.fYSOARAp9e4udwXhmeX-qU6dpEdTNTKq-kWIkVE-6UhKHifBpZFKaruilokjGcvK'),(53,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqZWJpbG9uZ29AZ21haWwuY29tIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NDk0OTMxMTcsImV4cCI6MTc0OTU3OTUxN30.DyPdmjgitNaoHDYzrU87XzqZr8V1ALrCAXdwtfS5YMD7IiNkikIADXcBoB7EAkmL',_binary '',NULL,14,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqZWJpbG9uZ29AZ21haWwuY29tIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NDk0OTMxMTcsImV4cCI6MTc1MDA5NzkxN30.R3K_Tam0NAGZ8Fad4w0AmsXx3EpZlWRZJM7t5RKdoLGta_DQka181-ciIykRukLG'),(54,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0OTQ5MzU4MCwiZXhwIjoxNzQ5NTc5OTgwfQ.nvbn7q9bhvHMOKEZwM_vjH67vghhZamVO-9OoSud-2-wO3iyICyhHZGvWwdeZN2m',_binary '',NULL,1,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0OTQ5MzU4MCwiZXhwIjoxNzUwMDk4MzgwfQ.lvHkPQdFMunzopYYVbl979LararNiZ7-bnMcKrBx-EzvDW-nxVHZa5LM78gLaGWk'),(55,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqZWJpbG9uZ29AZ21haWwuY29tIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NDk0OTM1OTMsImV4cCI6MTc0OTU3OTk5M30.uOFKJPS9dH7jhiQzLPnq7DxMO6ka22t8rZnre3rOnuqzCqa9pcSKyZXST1iamgos',_binary '',NULL,14,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqZWJpbG9uZ29AZ21haWwuY29tIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NDk0OTM1OTMsImV4cCI6MTc1MDA5ODM5M30.DwLsZdhhpMsYkiuISE3f5-XLO3VrFkfqtSX_KlCf-kE8pJNpRtVrRlWNiNK3jVR9'),(56,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqZWJpbG9uZ29AZ21haWwuY29tIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NDk1ODMwODEsImV4cCI6MTc0OTY2OTQ4MX0.VKrg2DTCh40FCKNqgNcKbF2JF_k-8xpNUz-pbnVvlFp5QKv_zpi8OEG3-2cfGiUH',_binary '',NULL,14,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqZWJpbG9uZ29AZ21haWwuY29tIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NDk1ODMwODEsImV4cCI6MTc1MDE4Nzg4MX0.UduPtjjwJI0j7vJVTDxz7NzBtQP0naW6TpSW8LbQMY-5E1psVN9_2TO8JzTX4mb1'),(57,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqZWJpbG9uZ29AZ21haWwuY29tIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NDk2NjUyMDUsImV4cCI6MTc0OTc1MTYwNX0.w1kMK1LYZ2KI8gwrbmYAh0E86bED7YO1JtvmSmemYQxZrxDZNZj4ue38lxu-9fn8',_binary '\0',NULL,14,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqZWJpbG9uZ29AZ21haWwuY29tIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3NDk2NjUyMDUsImV4cCI6MTc1MDI3MDAwNX0.FP0E30nhzK3ReKz5R-DAAV5u-QVT7-b7JdSvQ5aPGvOI0U9ERUb5asq-c_NGbrtZ'),(58,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0OTY2NjM2MiwiZXhwIjoxNzQ5NzUyNzYyfQ.1IVvlvRshOwZ351k6fZPUew5IRWBmBvkdbGjA5bE4x8qERK0OzV0-eln7V21ADDr',_binary '',NULL,1,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0OTY2NjM2MiwiZXhwIjoxNzUwMjcxMTYyfQ.Y7RuvbLNSMhkt9DHU04LBaAufg0Es3pAy1TgaU5eQ24t1hcDT-mmOnVNBziux3IJ'),(60,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsInVzZXJJZCI6MSwiaWF0IjoxNzQ5NjY2NjE3LCJleHAiOjE3NDk3NTMwMTd9.bDTnbYnKthZDy3vVSOPFVqWRqkk8uNv6LUVpW3AOEIZgxGuMBu1Uvu0W1ci7mHNl',_binary '',NULL,1,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsInVzZXJJZCI6MSwiaWF0IjoxNzQ5NjY2NjE3LCJleHAiOjE3NTAyNzE0MTd9.haFN8kqv3Gek377e6q-8LaJ0vuN5tivTuebEr3G2U4ZPoiv7ebMR78FtQ2MTlRFl'),(61,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqb3NlLm1hcmNoZW5hLXJvbGRhbkBpZXNydWl6Z2lqb24uY29tIiwicm9sZSI6IlVTRVIiLCJ1c2VySWQiOjI4LCJpYXQiOjE3NDk2NjY4MzEsImV4cCI6MTc0OTc1MzIzMX0.KBYStzZPbD3mgkDuDwD5t0sIBrWyESf-vd0LFuF-ELcwfsa-deewc16KYZ_o5UAQ',_binary '',NULL,28,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqb3NlLm1hcmNoZW5hLXJvbGRhbkBpZXNydWl6Z2lqb24uY29tIiwicm9sZSI6IlVTRVIiLCJ1c2VySWQiOjI4LCJpYXQiOjE3NDk2NjY4MzEsImV4cCI6MTc1MDI3MTYzMX0.rzEriciJ-g2ilortLApX2cBJmMXXQ6HomYHl0w7xu_HoM1upxfQwTLqyByuxPvco'),(62,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqb3NlLm1hcmNoZW5hLXJvbGRhbkBpZXNydWl6Z2lqb24uY29tIiwicm9sZSI6IlVTRVIiLCJ1c2VySWQiOjI4LCJpYXQiOjE3NDk2Njc1MzMsImV4cCI6MTc0OTc1MzkzM30.BwJw3ythUh2YocTUuv--uDw4o51V3g38GgjMtlkcMhU-WkxqkEp-sth8ExJIXkVg',_binary '',NULL,28,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqb3NlLm1hcmNoZW5hLXJvbGRhbkBpZXNydWl6Z2lqb24uY29tIiwicm9sZSI6IlVTRVIiLCJ1c2VySWQiOjI4LCJpYXQiOjE3NDk2Njc1MzMsImV4cCI6MTc1MDI3MjMzM30.fJp_QfO0VRGsXmySlUSmvX3DqeLD4h97ulWHHcwhd-SRWPp2-1ABGs6D0lo1GcWW'),(63,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqb3NlLm1hcmNoZW5hLXJvbGRhbkBpZXNydWl6Z2lqb24uY29tIiwicm9sZSI6IlVTRVIiLCJ1c2VySWQiOjI4LCJpYXQiOjE3NDk2NjgwMDIsImV4cCI6MTc0OTc1NDQwMn0.7e3IPoRJFRLNX3Lw1yN4NOTAFTJN08toWsKLXehe2VShTz3kE0EJ6A-jdWXes3uj',_binary '',NULL,28,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqb3NlLm1hcmNoZW5hLXJvbGRhbkBpZXNydWl6Z2lqb24uY29tIiwicm9sZSI6IlVTRVIiLCJ1c2VySWQiOjI4LCJpYXQiOjE3NDk2NjgwMDIsImV4cCI6MTc1MDI3MjgwMn0.Kfz3S5WIyCMElAGTX0KJ018JtZgPU5GFaGKHidUByOrbP2YRclP8A0BosVKW69l_'),(64,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqb3NlLm1hcmNoZW5hLXJvbGRhbkBpZXNydWl6Z2lqb24uY29tIiwicm9sZSI6IlVTRVIiLCJ1c2VySWQiOjI4LCJpYXQiOjE3NDk2Njg3NzksImV4cCI6MTc0OTc1NTE3OX0.jloSKF8zOawo7GWQ4q8tFeXy_gR8IxyvuzbUqSHU5v46jveNAZ2ucyqsZqhYth5e',_binary '',NULL,28,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqb3NlLm1hcmNoZW5hLXJvbGRhbkBpZXNydWl6Z2lqb24uY29tIiwicm9sZSI6IlVTRVIiLCJ1c2VySWQiOjI4LCJpYXQiOjE3NDk2Njg3NzksImV4cCI6MTc1MDI3MzU3OX0.-YFF6wOy9VyFdIrV2ctP_cYhTF6U0XuI4gpHyF4g0h3MfroACyqKKQ13i0Owr1jw'),(65,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqb3NlLm1hcmNoZW5hLXJvbGRhbkBpZXNydWl6Z2lqb24uY29tIiwicm9sZSI6IlVTRVIiLCJ1c2VySWQiOjI4LCJpYXQiOjE3NDk2Njk4NjMsImV4cCI6MTc0OTc1NjI2M30.JyIX9qS_m8tMwk5WKxzDpcZ6t3EhZI0AAx1cdstM2Wl3KRG4QHXphlZQzD4Pq0tN',_binary '',NULL,28,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqb3NlLm1hcmNoZW5hLXJvbGRhbkBpZXNydWl6Z2lqb24uY29tIiwicm9sZSI6IlVTRVIiLCJ1c2VySWQiOjI4LCJpYXQiOjE3NDk2Njk4NjMsImV4cCI6MTc1MDI3NDY2M30.qBahNts2j-ExDFN4FPNFMXTOt5Fu31povz96RShHQIZ2rM1FF2A39OZFM7DuJDcC'),(66,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqb3NlLm1hcmNoZW5hLXJvbGRhbkBpZXNydWl6Z2lqb24uY29tIiwicm9sZSI6IlVTRVIiLCJ1c2VySWQiOjI4LCJpYXQiOjE3NDk2NzczMTMsImV4cCI6MTc0OTc2MzcxM30.R2f9NdUU2S5BYhLqpOm3txzwE9u7cFXCe6IptOQGxbGrNUU0Ss2SgfFnnjTTh3ek',_binary '',NULL,28,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqb3NlLm1hcmNoZW5hLXJvbGRhbkBpZXNydWl6Z2lqb24uY29tIiwicm9sZSI6IlVTRVIiLCJ1c2VySWQiOjI4LCJpYXQiOjE3NDk2NzczMTMsImV4cCI6MTc1MDI4MjExM30.fMHboBmqtemGRw0Pd4v95xK-rNk9nGALfcAWaQ9o1iFMVtss-8NHHt-I721ZESXe'),(67,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsInVzZXJJZCI6MSwiaWF0IjoxNzQ5NjgwMjAyLCJleHAiOjE3NDk3NjY2MDJ9.LonUOYjs1W4KM1RteD79B1_KJmdIoEZ2cMAo0IdE78QxggdEm_gcYGa4kVLjAqKc',_binary '\0',NULL,1,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqbWFyY2hlbmFyb2xkYW5Ab3V0bG9vay5lcyIsInJvbGUiOiJBRE1JTiIsInVzZXJJZCI6MSwiaWF0IjoxNzQ5NjgwMjAyLCJleHAiOjE3NTAyODUwMDJ9.CExiCwC9e0US5lGx5kygnYQl355YWMy5R8tpAlanUmj8WSX071avq88Kj9ymItDs'),(68,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqb3NlLm1hcmNoZW5hLXJvbGRhbkBpZXNydWl6Z2lqb24uY29tIiwicm9sZSI6IlVTRVIiLCJ1c2VySWQiOjI4LCJpYXQiOjE3NDk3NDAwNTYsImV4cCI6MTc0OTgyNjQ1Nn0.fpMk-Rk_eA8aUqdTwZ6jq6Q-t7zLgCSc1xB66gFpIEPlU25mjshIKgw5BvdBsbmk',_binary '',NULL,28,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqb3NlLm1hcmNoZW5hLXJvbGRhbkBpZXNydWl6Z2lqb24uY29tIiwicm9sZSI6IlVTRVIiLCJ1c2VySWQiOjI4LCJpYXQiOjE3NDk3NDAwNTYsImV4cCI6MTc1MDM0NDg1Nn0.pRS-ZeOLqaH4ttiIg7KH6xyw8GpkD4X5Di1qocjOHrn7yqnGaBWqvXbLxYp1Eo9_'),(69,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqb3NlLm1hcmNoZW5hLXJvbGRhbkBpZXNydWl6Z2lqb24uY29tIiwicm9sZSI6IlVTRVIiLCJ1c2VySWQiOjI4LCJpYXQiOjE3NDk3NDA2MzAsImV4cCI6MTc0OTgyNzAzMH0.8RqWVjF7vE1nP1ZiY0lKw539cSOnkXdcyTiXExOx13kOaBhjWVDQbcofqZ4FEPKa',_binary '',NULL,28,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqb3NlLm1hcmNoZW5hLXJvbGRhbkBpZXNydWl6Z2lqb24uY29tIiwicm9sZSI6IlVTRVIiLCJ1c2VySWQiOjI4LCJpYXQiOjE3NDk3NDA2MzAsImV4cCI6MTc1MDM0NTQzMH0.JAjBqcSrHovR0hzNJLefH1M9PjrHOPDei4FjRkKTuGQde6JmvzZ2JPri7VHwv_Cg'),(70,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqb3NlLm1hcmNoZW5hLXJvbGRhbkBpZXNydWl6Z2lqb24uY29tIiwicm9sZSI6IlVTRVIiLCJ1c2VySWQiOjI4LCJpYXQiOjE3NDk3NDIyMTksImV4cCI6MTc0OTgyODYxOX0.tLXqdBSr58POAtsNCTopfiMIb_9IE0TkhTl9es7i-8NwxkxX2x3YFRcYEF9i282i',_binary '',NULL,28,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqb3NlLm1hcmNoZW5hLXJvbGRhbkBpZXNydWl6Z2lqb24uY29tIiwicm9sZSI6IlVTRVIiLCJ1c2VySWQiOjI4LCJpYXQiOjE3NDk3NDIyMTksImV4cCI6MTc1MDM0NzAxOX0.P75lrFfq0DL9opOvNzb6f_bsRaP7JtEeA-s1fj4WNJ2DV2yVCVGRmz3-cw4xTGTc'),(71,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqb3NlLm1hcmNoZW5hLXJvbGRhbkBpZXNydWl6Z2lqb24uY29tIiwicm9sZSI6IlVTRVIiLCJ1c2VySWQiOjI4LCJpYXQiOjE3NDk3NDIyNjAsImV4cCI6MTc0OTgyODY2MH0.5JAipUkUuy_onLL8HgI3syurPiAe5sCrB9vCY0tuFq_h7jSYqWvDNY-YUYHUaXmu',_binary '\0',NULL,28,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqb3NlLm1hcmNoZW5hLXJvbGRhbkBpZXNydWl6Z2lqb24uY29tIiwicm9sZSI6IlVTRVIiLCJ1c2VySWQiOjI4LCJpYXQiOjE3NDk3NDIyNjAsImV4cCI6MTc1MDM0NzA2MH0.A0G6lkEhettyL57upXqk7fvZ61USB8LMpOzUkZ0VN4hZTKrLKnyLsfoT7YqeZSip');
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-12 17:48:29
