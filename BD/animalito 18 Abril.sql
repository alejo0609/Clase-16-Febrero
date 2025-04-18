-- MySQL dump 10.13  Distrib 8.0.13, for macos10.14 (x86_64)
--
-- Host: localhost    Database: animalitos
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `adopcion`
--

DROP TABLE IF EXISTS `adopcion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `adopcion` (
  `idadopcion` int(11) NOT NULL AUTO_INCREMENT,
  `aprobado` bit(1) NOT NULL,
  `ciudad` varchar(255) NOT NULL,
  `correo` varchar(255) NOT NULL,
  `direccion` varchar(255) NOT NULL,
  `dni` varchar(255) NOT NULL,
  `motivo_adopcion` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `ocupacion` varchar(255) NOT NULL,
  `telefono` varchar(255) NOT NULL,
  `tipo_vivienda` varchar(255) NOT NULL,
  `animal_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`idadopcion`),
  KEY `fk_animal` (`animal_id`),
  CONSTRAINT `FKtiaq77icui5k0p446quipwcdx` FOREIGN KEY (`animal_id`) REFERENCES `animal` (`id_animal`),
  CONSTRAINT `fk_animal` FOREIGN KEY (`animal_id`) REFERENCES `animal` (`id_animal`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adopcion`
--

LOCK TABLES `adopcion` WRITE;
/*!40000 ALTER TABLE `adopcion` DISABLE KEYS */;
INSERT INTO `adopcion` VALUES (1,_binary '\0','Medellín','alejoRAMIREZ@example.com','Calle 123','3456789','Me encantan los animales y quiero darles un hogar','Alejandro PérezRamirez','Ingeniero de software','3001234567','Apartamento',NULL),(2,_binary '\0','Medellín','alejo0609@hotmail.com','Calle 123','8029','Me encantan los animales y quiero darles un hogar','Alejandro PérezRamirez','Ingeniero de software','3148354534','Apartamento',NULL),(3,_binary '\0','Medellín','alejo0609@hotmail.com','Calle 123','80342329','Me encantan los animales y quiero darles un hogar','carol PérezRamirez','Ingeniero de software','3148354534','Apartamento',NULL),(4,_binary '\0','Medellin','jdgm701@gmail.com','UdeA','938912084','No tengo hijos','Julian Giraldo','Est','02940394','Propia',NULL),(5,_binary '\0','djksljf','andresescobarvasquez1987@gmail.com','sjfkldsj','309483908','elkfrioweurid','Andres Escobar','kfjdskfj','432984092','efuiowef',NULL),(6,_binary '\0','Medellín','yepesgilcarolina@gmail.com','Calle 54 # 86a-60','1128450','Me encantan los perros bien perros','carolina yepes','Cuidadora de Perros','3148354534','Apartamento',NULL),(7,_binary '\0','medellin','yepesgilcarolina@gmail.com','Calle 54 ','1128450009','sdlsakdl','Carolina','Señora perro','4340934','dskdlk',NULL),(8,_binary '\0','Medellin','yepesgilcarolina@gmail.com','cALLE 54 # 86A','112845009','Nada de nada','Carolina','Ama de casa','349003\'2','Propia',NULL),(9,_binary '\0','Medellin','0609izquierdo1@gmail.com','Calle 54 # 86a-55','802987','todo good','Alejandro','Asesor','402394','propia',NULL),(10,_binary '\0','medellin','0609izquierdo1@gmail.com','kdkskfñld','094320\'49','fdksfkñlsdkflñ','Alejandro','smdmsal','042034','dlkaslñkdsmdmslk',NULL),(11,_binary '\0','Medellín','yepesgilcarolina@gmail.com','Calle 54 # 86a-60','1128451110','Me encantan los perros bien perros','carolina yepes','Cuidadora de Perros','3148354534','Apartamento',NULL),(12,_binary '\0','Medellín','alejandroperezramirez0609@gmail.com','Calle 54 # 86a-60','11284511310','Me encantan los perros bien perros','Alejandro Perez','Cuidadora de Perros','3148354534','Apartamento',NULL),(13,_binary '\0','Medellin','alejo0609@hotmail.com','Calle 54 # 86a-60 int. 207','434234','vcxv','Juan Pablo Perez','cvxcv','3044122036','vxcv',NULL),(14,_binary '\0','Medellín','carlos@example.com','Calle 123 #45-67','12345678','Me encantan los animales y quiero adoptar uno.','Carlos Gómez','Ingeniero','3001234567','Apartamento',41);
/*!40000 ALTER TABLE `adopcion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `animal`
--

DROP TABLE IF EXISTS `animal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `animal` (
  `id_animal` int(11) NOT NULL AUTO_INCREMENT,
  `edad` varchar(4) NOT NULL,
  `esterilizado` bit(1) NOT NULL,
  `imagen_animal` varchar(255) NOT NULL,
  `nombre_animal` varchar(155) NOT NULL,
  `raza` varchar(45) NOT NULL,
  `estado_animal` bit(1) NOT NULL,
  `correo_tienda` varchar(255) DEFAULT NULL,
  `tienda_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_animal`),
  KEY `FK53kji5ljmiv9fl52lehujg4t1` (`tienda_id`),
  CONSTRAINT `FK53kji5ljmiv9fl52lehujg4t1` FOREIGN KEY (`tienda_id`) REFERENCES `tienda` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `animal`
--

LOCK TABLES `animal` WRITE;
/*!40000 ALTER TABLE `animal` DISABLE KEYS */;
INSERT INTO `animal` VALUES (1,'3',_binary '','perro1.jpg','Max','Labrador',_binary '','alejo0609@hotmail.com',22),(2,'2',_binary '\0','perro2.jpg','Bella','Beagle',_binary '','alejo0609@hotmail.com',25),(3,'4',_binary '','perro3.jpg','Rocky','Pastor Alemán',_binary '\0','alejo0609@hotmail.com',22),(4,'5',_binary '\0','perro4.jpg','Luna','Golden Retriever',_binary '','alejo0609@hotmail.com',25),(5,'3',_binary '','perro5.jpg','Charlie','Bulldog',_binary '\0','alejo0609@hotmail.com',22),(6,'2',_binary '','perro6.jpg','Daisy','Poodle',_binary '','alejo0609@hotmail.com',22),(7,'4',_binary '\0','perro7.jpg','Milo','Dálmata',_binary '','alejo0609@hotmail.com',25),(8,'1',_binary '','perro8.jpg','Sadie','Chihuahua',_binary '','alejo0609@hotmail.com',25),(9,'3',_binary '\0','perro9.jpg','Cooper','Husky Siberiano',_binary '\0',NULL,25),(10,'2',_binary '','perro10.jpg','Bailey','Cocker Spaniel',_binary '',NULL,22),(11,'3',_binary '\0','perro11.jpg','Toby','Shih Tzu',_binary '',NULL,22),(12,'2',_binary '','perro12.jpg','Lola','Pug',_binary '\0',NULL,25),(13,'5',_binary '\0','perro13.jpg','Oscar','Doberman',_binary '',NULL,22),(14,'4',_binary '','perro14.jpg','Chloe','Bóxer',_binary '\0',NULL,25),(15,'3',_binary '\0','perro15.jpg','Buster','Pitbull',_binary '',NULL,25),(16,'2',_binary '','perro16.jpg','Maggie','Border Collie',_binary '',NULL,25),(17,'4',_binary '\0','perro17.jpg','Rex','Rottweiler',_binary '\0',NULL,22),(18,'3',_binary '','cat4.jpg','Whiskers','Gato Siamés',_binary '',NULL,25),(19,'5',_binary '\0','vaca1.jpg','Margarita','Vaca Holstein',_binary '',NULL,25),(20,'7',_binary '\0','oso1.jpg','Baloo','Oso Pardo',_binary '\0',NULL,25),(21,'4',_binary '','perro7.jpg','Max','Pastor Alemán',_binary '',NULL,25),(22,'3',_binary '\0','dog4.jpg','Bruno','Labrador Retriever',_binary '',NULL,25),(23,'2',_binary '','perro5.jpg','Chester','Dálmata',_binary '',NULL,25),(24,'4',_binary '\0','perro8.jpg','Lobo','Husky Siberiano',_binary '\0',NULL,22),(25,'3',_binary '','dog2.jpg','Toby','Beagle',_binary '',NULL,22),(26,'5',_binary '\0','perro1.jpg','Rocky','Bulldog Inglés',_binary '',NULL,22),(27,'2',_binary '','cat5.jpg','Nube','Gato Persa',_binary '',NULL,22),(28,'3',_binary '\0','perro2.jpg','Titan','Pitbull',_binary '',NULL,22),(29,'4',_binary '','dog1.jpg','Duke','Golden Retriever',_binary '\0',NULL,22),(30,'3',_binary '','cat1.jpg','Simba','Gato Bengalí',_binary '',NULL,22),(31,'2',_binary '\0','cat6.jpg','Bailey','Cocker Spaniel',_binary '',NULL,25),(32,'5',_binary '\0','perro9.jpg','Zeus','Rottweiler',_binary '\0',NULL,25),(33,'2',_binary '','cat7.jpg','Mimi','Chihuahua',_binary '',NULL,25),(34,'4',_binary '','cat3.jpg','Leo','Gato Maine Coon',_binary '',NULL,25),(35,'3',_binary '\0','dog3.jpg','Flash','Border Collie',_binary '',NULL,22),(36,'5',_binary '\0','perro4.jpg','Thor','Dogo Argentino',_binary '\0',NULL,25),(37,'6',_binary '','perro10.jpg','Hércules','San Bernardo',_binary '',NULL,25),(38,'38',_binary '','perro1.jpg','Maximiliano','Labrador',_binary '','0609derecho1@gmail.com',22),(39,'38',_binary '','perro4.jpg','Cucaracho','Labrador',_binary '','0609derecho1@gmail.com',22),(40,'3',_binary '','https://ejemplo.com/luna.jpg','Luna','Labrador',_binary '','tienda123@email.com',22),(41,'3',_binary '','perro7.jpg','Lunatica','Labrador',_binary '','0609derecho1@gmail.com',25);
/*!40000 ALTER TABLE `animal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `datos_personales`
--

DROP TABLE IF EXISTS `datos_personales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `datos_personales` (
  `id_datos_personales` int(11) NOT NULL AUTO_INCREMENT,
  `ciudad` varchar(55) NOT NULL,
  `direccion` varchar(255) NOT NULL,
  `email` varchar(155) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(155) NOT NULL,
  `dni` bigint(20) NOT NULL,
  PRIMARY KEY (`id_datos_personales`),
  UNIQUE KEY `UKlbmttbb0j82ogk5xm579ikitt` (`email`),
  UNIQUE KEY `UKdpry7ftc29rs0ov9tn6x8cqdj` (`dni`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datos_personales`
--

LOCK TABLES `datos_personales` WRITE;
/*!40000 ALTER TABLE `datos_personales` DISABLE KEYS */;
INSERT INTO `datos_personales` VALUES (1,'Medellin','Calasanz','alejo@alejo.com','314835','Alejandro Perez','$2a$10$/FIBBAOYHGd6Z0PdyryGIOp8qXO.P1qOYBL1wqPyAxin2J0vJi1za',8029),(2,'Medellin','Calasanz','carol@carol.com','3012222','Carolina','$2a$10$qfOWwHJegl7xr6Woju49V.CnZ6gBB.IWnw2IMm7HevjcTfON6OrUu',112845),(3,'medellin','udea','julian@julian.com','948329048','Julian','$2a$10$QTsejJB6Pi.mGyoYk73lnOg2tyIcsfsu1t.OIf04YHWFwCU/XPZ8C',123456),(4,'Medellin','Calasanz','alejopr@alejo.com','314835','Alejandro Peredsfdsfz','$2a$10$bnyM193nGDEkZiD0QNcV8ey3zg9wvWz41vZHQncFcFKM...xWB8uW',8029432424),(5,'smdjksah','sdjklsa','olga@olga.com','37128937','olga','$2a$10$mfHJz36lb6POMXh2G1x3V.o6gMS/SujtXLgy9e5/Kmv1E6oY1ezQi',987654321),(6,'jkfhdjsfh','dksfkdhfgkñ','eder@eder.com','5734985','Eder Loro','$2a$10$J6AVJ5UBTH9XBc7rYILCfestEBVGdg9HXHdl7ObQ.rdls8Cnz1DLC',498230948),(7,'medellin','daskldjk','daniel@daniel.com','34920\'49','Daniel','$2a$10$W1Sl/8YXJBT9ILyqT99wwO2Na9N0U3REM4AwKecCqhZiPzcpD2Kl2',1234567890);
/*!40000 ALTER TABLE `datos_personales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_adopcion`
--

DROP TABLE IF EXISTS `detalle_adopcion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `detalle_adopcion` (
  `iddetalleadopcion` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`iddetalleadopcion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_adopcion`
--

LOCK TABLES `detalle_adopcion` WRITE;
/*!40000 ALTER TABLE `detalle_adopcion` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_adopcion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historia_animal`
--

DROP TABLE IF EXISTS `historia_animal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `historia_animal` (
  `idhistoriaanimal` int(11) NOT NULL AUTO_INCREMENT,
  `chip` bit(1) NOT NULL,
  `estado_salud` text NOT NULL,
  `fecha_creado` datetime(6) NOT NULL,
  `fecha_modificado` datetime(6) NOT NULL,
  PRIMARY KEY (`idhistoriaanimal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historia_animal`
--

LOCK TABLES `historia_animal` WRITE;
/*!40000 ALTER TABLE `historia_animal` DISABLE KEYS */;
/*!40000 ALTER TABLE `historia_animal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `roles` (
  `idroles` int(11) NOT NULL AUTO_INCREMENT,
  `creado` datetime(6) NOT NULL,
  `modificado` datetime(6) NOT NULL,
  PRIMARY KEY (`idroles`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tienda`
--

DROP TABLE IF EXISTS `tienda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tienda` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `barrio` varchar(100) NOT NULL,
  `ciudad` varchar(100) NOT NULL,
  `correo_electronico` varchar(100) NOT NULL,
  `direccion` varchar(150) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `sitio_web` varchar(100) DEFAULT NULL,
  `telefono` varchar(20) NOT NULL,
  `latitud` double DEFAULT NULL,
  `longitud` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tienda`
--

LOCK TABLES `tienda` WRITE;
/*!40000 ALTER TABLE `tienda` DISABLE KEYS */;
INSERT INTO `tienda` VALUES (22,'Estadio','Medellín','alejandroperezramirez0609@gmail.com','Carrera 82 #50-50','Peludos Barrio','1234','https://calasanz.com','300123566',6.2400175,-75.6046903),(23,'Santa Lucia','Medellín','alejandroperezramirez0609@gmail.com','Carrera 92 #47A-45','Peludos Alejo','1234','https://calasanz.com','300123566',6.2572123,-75.6086315),(24,'Robledo','Medellin','alejandroperezramirez0609@gmail.com','Carrera 86 #66 - 39','Catrolin','1234','ldskdl.com','2862513',6.285239086568147,-75.58684076108608),(25,'Robledo','Medellín','alejandroperezramirez0609@gmail.com','Carrera 86 #66-39','Sandra ','1234','sdñlfñs.com','e324',6.2852465,-75.5868307);
/*!40000 ALTER TABLE `tienda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_animal`
--

DROP TABLE IF EXISTS `tipo_animal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tipo_animal` (
  `idtipoanimal` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_tipo_animal` varchar(155) NOT NULL,
  PRIMARY KEY (`idtipoanimal`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_animal`
--

LOCK TABLES `tipo_animal` WRITE;
/*!40000 ALTER TABLE `tipo_animal` DISABLE KEYS */;
INSERT INTO `tipo_animal` VALUES (2,'Perro'),(3,'Gato');
/*!40000 ALTER TABLE `tipo_animal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_documentos`
--

DROP TABLE IF EXISTS `tipo_documentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tipo_documentos` (
  `idtipodocumentos` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_tipo_documento` varchar(45) NOT NULL,
  PRIMARY KEY (`idtipodocumentos`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_documentos`
--

LOCK TABLES `tipo_documentos` WRITE;
/*!40000 ALTER TABLE `tipo_documentos` DISABLE KEYS */;
INSERT INTO `tipo_documentos` VALUES (1,'Cédula de Ciudadanía'),(2,'Tarjeta de Identidad'),(3,'Registro Civil'),(4,'Cédula de Extranjería'),(5,'Pasaporte'),(6,'Permiso Especial de Permanencia'),(7,'NIT'),(8,'Documento Nacional de Identidad'),(9,'Carné Diplomático'),(10,'Salvoconducto de Permanencia'),(11,'Otro');
/*!40000 ALTER TABLE `tipo_documentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_usuario`
--

DROP TABLE IF EXISTS `tipo_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tipo_usuario` (
  `idTipoUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_tipo_usuario` varchar(100) NOT NULL,
  `estado_tipoUsuario` tinyint(4) NOT NULL DEFAULT '1',
  `estado_tipo_usuario` bit(1) NOT NULL,
  PRIMARY KEY (`idTipoUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_usuario`
--

LOCK TABLES `tipo_usuario` WRITE;
/*!40000 ALTER TABLE `tipo_usuario` DISABLE KEYS */;
INSERT INTO `tipo_usuario` VALUES (1,'Cuidador',1,_binary '\0'),(2,'Tienda',1,_binary '\0'),(3,'Albergue',1,_binary '\0'),(4,'Adoptante',1,_binary '\0'),(5,'Donante',1,_binary '\0');
/*!40000 ALTER TABLE `tipo_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_vacuna`
--

DROP TABLE IF EXISTS `tipo_vacuna`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tipo_vacuna` (
  `idvacunas` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_tipo_vacuna` varchar(155) NOT NULL,
  PRIMARY KEY (`idvacunas`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_vacuna`
--

LOCK TABLES `tipo_vacuna` WRITE;
/*!40000 ALTER TABLE `tipo_vacuna` DISABLE KEYS */;
INSERT INTO `tipo_vacuna` VALUES (1,'Rabia'),(2,'Moquillo'),(3,'Parvovirus'),(4,'Hepatitis Canina'),(5,'Leptospirosis'),(6,'Coronavirus Canino'),(7,'Bordetella'),(8,'Parainfluenza Canina'),(9,'Giardia'),(10,'Calicivirus Felino'),(11,'Rinotraqueitis Felina'),(12,'Panleucopenia Felina'),(13,'Leucemia Felina'),(14,'Peritonitis Infecciosa Felina'),(15,'Inmunodeficiencia Felina');
/*!40000 ALTER TABLE `tipo_vacuna` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `estado_usuario` bit(1) NOT NULL,
  `password` varchar(155) NOT NULL,
  `email` varchar(155) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `UK5171l57faosmj8myawaucatdw` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,_binary '','$2a$10$TJseiBaF8eM6AUB/uecYI.5IcL28yUSPNz6rsw2zEmKmID6igafxW','alejo@caro.com'),(2,_binary '','$2a$10$w14bC66NrDGp.pKqrTsRduFoWwV74nkpzoCUz/8.d.Uwb.AFfwR9S','alejo@alejo.com'),(3,_binary '','$2a$10$JM3H.WhpdjmMjkDQ1KCrG.paAIFms/vj6M6AJ7ZHApM090HtByuce','carol@carol.com');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-18 13:46:12
