/*M!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19-11.7.2-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: tienda
-- ------------------------------------------------------
-- Server version	12.1.2-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*M!100616 SET @OLD_NOTE_VERBOSITY=@@NOTE_VERBOSITY, NOTE_VERBOSITY=0 */;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `email` varchar(250) NOT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `telefono` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES
(1,'Juan','Pérez Gómez','juan.perez@email.com','1990-03-12',600123123),
(2,'María','López Ruiz','maria.lopez@email.com','1987-11-25',600321321),
(3,'Carlos','Martín Vega','carlos.martin@email.com','1995-06-02',611111111),
(4,'Laura','Sánchez Pérez','laura.sanchez@email.com','1992-01-18',622222222),
(5,'Pedro','Gómez Ruiz','pedro.gomez@email.com','1984-09-09',633333333),
(6,'Ana','Torres Díaz','ana.torres@email.com','1998-04-30',644444444),
(7,'Luis','Navarro Gil','luis.navarro@email.com','1979-12-07',655555555),
(8,'Marta','Hernández Soto','marta.hernandez@email.com','1991-08-21',666666666),
(9,'Jorge','Iglesias Mora','jorge.iglesias@email.com','1989-02-14',677777777),
(10,'Elena','Vega Castro','elena.vega@email.com','1996-10-05',688888888),
(11,'Raúl','Romero Peña','raul.romero@email.com','1983-05-27',699999999),
(12,'Lucía','Campos León','lucia.campos@email.com','2000-07-16',610101010);
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedidos` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `numero_pedido` int(11) NOT NULL,
  `precio` float NOT NULL,
  `fecha_pedido` date NOT NULL DEFAULT curdate(),
  `entregado` tinyint(1) DEFAULT 0,
  `observaciones` varchar(500) DEFAULT NULL,
  `id_cliente` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_cliente` (`id_cliente`),
  CONSTRAINT `1` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
INSERT INTO `pedidos` VALUES
(1,1001,8.6,'2026-01-15',1,'Entrega en portería',1),
(2,1002,16.25,'2026-01-20',0,'Llamar antes de subir',2),
(3,1003,5.1,'2026-01-22',1,NULL,3),
(4,1004,32.89,'2026-01-25',0,'Pedido con electrónica',4),
(5,1005,12.4,'2026-02-01',1,'Sin observaciones',5),
(6,1006,9.95,'2026-02-03',0,NULL,6),
(7,1007,21.6,'2026-02-04',1,'Entrega por la tarde',7),
(8,1008,6.55,'2026-02-05',1,NULL,8);
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto_pedido`
--

DROP TABLE IF EXISTS `producto_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto_pedido` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_producto` int(10) unsigned DEFAULT NULL,
  `id_pedido` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_producto` (`id_producto`),
  KEY `id_pedido` (`id_pedido`),
  CONSTRAINT `1` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `2` FOREIGN KEY (`id_pedido`) REFERENCES `pedidos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto_pedido`
--

LOCK TABLES `producto_pedido` WRITE;
/*!40000 ALTER TABLE `producto_pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `producto_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `tipo` varchar(150) DEFAULT NULL,
  `precio` float NOT NULL,
  `stock` tinyint(1) DEFAULT 0,
  `descripcion` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES
(1,'Pan de molde integral','Alimentación',2.15,1,'Rebanadas integrales 500g'),
(2,'Leche semidesnatada 1L','Alimentación',1.05,1,'Pack individual 1 litro'),
(3,'Café molido 250g','Alimentación',3.75,1,'Tueste natural'),
(4,'Aceite de oliva 1L','Alimentación',7.9,1,'AOVE 1 litro'),
(5,'Pasta espagueti 500g','Alimentación',1.3,1,'Trigo duro'),
(6,'Arroz redondo 1Kg','Alimentación',1.85,1,'Ideal para paella'),
(7,'Agua mineral 1.5L','Bebidas',0.7,1,'Sin gas'),
(8,'Refresco cola 2L','Bebidas',1.6,1,'Botella 2 litros'),
(9,'Zumo de naranja 1L','Bebidas',1.45,1,'Naranja 100%'),
(10,'Detergente lavadora','Limpieza',6.5,1,'25 lavados'),
(11,'Lavavajillas a mano','Limpieza',2.25,1,'Limón 750ml'),
(12,'Papel higiénico 12u','Hogar',4.3,1,'Doble capa'),
(13,'Servilletas 100u','Hogar',1.1,1,'Blancas'),
(14,'Champú 400ml','Higiene',3.2,1,'Uso diario'),
(15,'Gel de ducha 750ml','Higiene',2.9,1,'Aloe'),
(16,'Pasta de dientes','Higiene',1.8,1,'Menta fresca'),
(17,'Cargador USB-C','Electrónica',12.99,1,'Carga rápida 20W'),
(18,'Auriculares inalámbricos','Electrónica',24.9,1,'Bluetooth 5.3'),
(19,'Cuaderno A4','Papelería',2.4,1,'Cuadriculado 80 hojas'),
(20,'Bolígrafo azul','Papelería',0.65,1,'Tinta suave');
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'tienda'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*M!100616 SET NOTE_VERBOSITY=@OLD_NOTE_VERBOSITY */;

-- Dump completed on 2026-02-07  9:21:48
