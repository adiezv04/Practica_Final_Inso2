-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         8.0.19 - MySQL Community Server - GPL
-- SO del servidor:              Win64
-- HeidiSQL Versión:             10.3.0.5771
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para hospital
CREATE DATABASE IF NOT EXISTS `hospital` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `hospital`;

-- Volcando estructura para tabla hospital.cita
CREATE TABLE IF NOT EXISTS `cita` (
  `idCita` int NOT NULL AUTO_INCREMENT,
  `idDoctor` int NOT NULL,
  `idPaciente` int NOT NULL,
  `fecha` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idCita`),
  KEY `idDoctor` (`idDoctor`),
  KEY `idPaciente` (`idPaciente`),
  CONSTRAINT `cita_ibfk_1` FOREIGN KEY (`idDoctor`) REFERENCES `doctor` (`idDoctor`),
  CONSTRAINT `cita_ibfk_2` FOREIGN KEY (`idPaciente`) REFERENCES `paciente` (`idPaciente`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla hospital.cita: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `cita` DISABLE KEYS */;
/*!40000 ALTER TABLE `cita` ENABLE KEYS */;

-- Volcando estructura para tabla hospital.doctor
CREATE TABLE IF NOT EXISTS `doctor` (
  `idDoctor` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `apellidos` varchar(100) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `especialidad` varchar(500) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `idUsuario` int NOT NULL,
  PRIMARY KEY (`idDoctor`),
  KEY `idUsuario` (`idUsuario`),
  CONSTRAINT `doctor_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla hospital.doctor: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;
INSERT INTO `doctor` (`idDoctor`, `nombre`, `apellidos`, `especialidad`, `idUsuario`) VALUES
	(1, 'Jose Luis', 'Robles López', 'Cardiología', 1),
	(2, 'Antonio', 'Correoso', 'Traumatología', 7);
/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;

-- Volcando estructura para tabla hospital.habitacion
CREATE TABLE IF NOT EXISTS `habitacion` (
  `idHabitacion` int NOT NULL AUTO_INCREMENT,
  `numero` int NOT NULL,
  `idPaciente` int DEFAULT NULL,
  `idPlanta` int NOT NULL,
  `idDoctor` int DEFAULT NULL,
  PRIMARY KEY (`idHabitacion`),
  UNIQUE KEY `numero` (`numero`),
  KEY `idPaciente` (`idPaciente`),
  KEY `idPlanta` (`idPlanta`),
  KEY `idDoctor` (`idDoctor`),
  CONSTRAINT `habitacion_ibfk_1` FOREIGN KEY (`idPlanta`) REFERENCES `planta` (`idPlanta`),
  CONSTRAINT `habitacion_ibfk_2` FOREIGN KEY (`idPaciente`) REFERENCES `paciente` (`idPaciente`),
  CONSTRAINT `habitacion_ibfk_3` FOREIGN KEY (`idDoctor`) REFERENCES `doctor` (`idDoctor`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla hospital.habitacion: ~6 rows (aproximadamente)
/*!40000 ALTER TABLE `habitacion` DISABLE KEYS */;
INSERT INTO `habitacion` (`idHabitacion`, `numero`, `idPaciente`, `idPlanta`, `idDoctor`) VALUES
	(1, 101, NULL, 1, NULL),
	(2, 102, NULL, 1, NULL),
	(3, 201, NULL, 2, NULL),
	(4, 202, NULL, 2, NULL),
	(5, 301, NULL, 3, NULL),
	(6, 302, NULL, 3, NULL);
/*!40000 ALTER TABLE `habitacion` ENABLE KEYS */;

-- Volcando estructura para tabla hospital.medicamento
CREATE TABLE IF NOT EXISTS `medicamento` (
  `idMedicamento` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` text CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`idMedicamento`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla hospital.medicamento: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `medicamento` DISABLE KEYS */;
INSERT INTO `medicamento` (`idMedicamento`, `nombre`, `descripcion`) VALUES
	(1, 'Levodropropizina', 'Se trata del isómero levorrotorio del antitusígeno dropropizina, del cual difiere porque sus efectos sedantes son menos marcados y porque tiene mejor tolerancia. La levodropropizina es un antitusígeno de acción periférica que ejerce un efecto anestésico e inhibe las vías aferentes que regulan el reflejo de la tos.');
/*!40000 ALTER TABLE `medicamento` ENABLE KEYS */;

-- Volcando estructura para tabla hospital.notificacion
CREATE TABLE IF NOT EXISTS `notificacion` (
  `idNotificacion` int NOT NULL AUTO_INCREMENT,
  `texto` text NOT NULL,
  `leida` bit(1) NOT NULL,
  `fecha` datetime NOT NULL,
  `idUsuario` int NOT NULL,
  `emisor` varchar(100) NOT NULL,
  PRIMARY KEY (`idNotificacion`),
  KEY `idUsuario` (`idUsuario`),
  CONSTRAINT `notificacion_ibfk` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla hospital.notificacion: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `notificacion` DISABLE KEYS */;
INSERT INTO `notificacion` (`idNotificacion`, `texto`, `leida`, `fecha`, `idUsuario`, `emisor`) VALUES
	(3, 'safsdafsdaf', b'0', '2020-04-29 22:19:38', 8, 'pepe');
/*!40000 ALTER TABLE `notificacion` ENABLE KEYS */;

-- Volcando estructura para tabla hospital.paciente
CREATE TABLE IF NOT EXISTS `paciente` (
  `idPaciente` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `apellidos` varchar(100) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `dni` varchar(9) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `fechaNacimiento` date NOT NULL,
  `ingresado` bit(1) NOT NULL,
  `idUsuario` int NOT NULL,
  PRIMARY KEY (`idPaciente`),
  KEY `idUsuario` (`idUsuario`),
  CONSTRAINT `paciente_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla hospital.paciente: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `paciente` DISABLE KEYS */;
INSERT INTO `paciente` (`idPaciente`, `nombre`, `apellidos`, `dni`, `fechaNacimiento`, `ingresado`, `idUsuario`) VALUES
	(1, 'Anacarda', 'Ramírez', '72455376P', '1980-06-22', b'0', 3),
	(2, 'adrian', 'diez', '71445275F', '2020-04-15', b'0', 8),
	(3, 'adrian', 'diez', '71445275F', '2020-04-15', b'0', 9);
/*!40000 ALTER TABLE `paciente` ENABLE KEYS */;

-- Volcando estructura para tabla hospital.planta
CREATE TABLE IF NOT EXISTS `planta` (
  `idPlanta` int NOT NULL AUTO_INCREMENT,
  `especialidad` varchar(500) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `numero` int NOT NULL,
  PRIMARY KEY (`idPlanta`),
  UNIQUE KEY `numero` (`numero`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla hospital.planta: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `planta` DISABLE KEYS */;
INSERT INTO `planta` (`idPlanta`, `especialidad`, `numero`) VALUES
	(1, 'Respiratoria', 1),
	(2, 'Cardiología', 2),
	(3, 'traumatología', 3);
/*!40000 ALTER TABLE `planta` ENABLE KEYS */;

-- Volcando estructura para tabla hospital.receta
CREATE TABLE IF NOT EXISTS `receta` (
  `idReceta` int NOT NULL AUTO_INCREMENT,
  `idPaciente` int NOT NULL,
  `idMedicamento` int NOT NULL,
  PRIMARY KEY (`idReceta`),
  KEY `idPaciente` (`idPaciente`),
  KEY `idMedicamento` (`idMedicamento`),
  CONSTRAINT `receta_ibfk_1` FOREIGN KEY (`idMedicamento`) REFERENCES `medicamento` (`idMedicamento`),
  CONSTRAINT `receta_ibfk_2` FOREIGN KEY (`idPaciente`) REFERENCES `paciente` (`idPaciente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla hospital.receta: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `receta` DISABLE KEYS */;
/*!40000 ALTER TABLE `receta` ENABLE KEYS */;

-- Volcando estructura para tabla hospital.solicitud
CREATE TABLE IF NOT EXISTS `solicitud` (
  `idSolicitud` int NOT NULL AUTO_INCREMENT,
  `idPaciente` int NOT NULL,
  `descripcion` text CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `horario` enum('Mañanas','Tardes','Ambas') COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`idSolicitud`),
  KEY `idPaciente` (`idPaciente`),
  CONSTRAINT `solicitud_ibfk_1` FOREIGN KEY (`idPaciente`) REFERENCES `paciente` (`idPaciente`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla hospital.solicitud: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `solicitud` DISABLE KEYS */;
INSERT INTO `solicitud` (`idSolicitud`, `idPaciente`, `descripcion`, `horario`) VALUES
	(1, 2, 'sdfsdfsa', 'Mañanas'),
	(2, 2, 'sdfsdfsa', 'Ambas');
/*!40000 ALTER TABLE `solicitud` ENABLE KEYS */;

-- Volcando estructura para tabla hospital.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `idUsuario` int NOT NULL AUTO_INCREMENT,
  `nombreUsuario` varchar(100) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `clave` varchar(100) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `tipoUsuario` enum('paciente','doctor','admin') CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE KEY `nombreUsuario` (`nombreUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla hospital.usuario: ~6 rows (aproximadamente)
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`idUsuario`, `nombreUsuario`, `clave`, `tipoUsuario`) VALUES
	(1, 'doctor', 'doctor', 'doctor'),
	(3, 'paciente', 'paciente', 'paciente'),
	(4, 'admin', 'admin', 'admin'),
	(7, 'adri', 'adri', 'doctor'),
	(8, 'kun', 'kun', 'paciente'),
	(9, 'kun2', 'kun', 'paciente');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
