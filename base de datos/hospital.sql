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
  CONSTRAINT `cita_ibfk_1` FOREIGN KEY (`idDoctor`) REFERENCES `doctor` (`idDoctor`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cita_ibfk_2` FOREIGN KEY (`idPaciente`) REFERENCES `paciente` (`idPaciente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla hospital.cita: ~3 rows (aproximadamente)
DELETE FROM `cita`;
/*!40000 ALTER TABLE `cita` DISABLE KEYS */;
INSERT INTO `cita` (`idCita`, `idDoctor`, `idPaciente`, `fecha`) VALUES
	(44, 7, 6, '2020-05-19 09:30:18'),
	(45, 7, 6, '2020-05-19 16:30:21'),
	(46, 7, 6, '2020-05-19 10:00:23');
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
  CONSTRAINT `doctor_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla hospital.doctor: ~3 rows (aproximadamente)
DELETE FROM `doctor`;
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;
INSERT INTO `doctor` (`idDoctor`, `nombre`, `apellidos`, `especialidad`, `idUsuario`) VALUES
	(3, 'Antonio', 'Capa Álamo', 'Dermatología', 18),
	(7, 'Lucas', 'Rodriguez', 'Cardio', 25),
	(8, 'Lucas', 'Rodriguez', 'Cardio', 26),
	(9, 'Prudencio', 'Antoñez', 'Traumatologo', 31);
/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;

-- Volcando estructura para tabla hospital.habitacion
CREATE TABLE IF NOT EXISTS `habitacion` (
  `idHabitacion` int NOT NULL AUTO_INCREMENT,
  `numero` int NOT NULL,
  `idPaciente` int DEFAULT NULL,
  `idPlanta` int NOT NULL,
  `idDoctor` int DEFAULT NULL,
  `Detalles` text COLLATE utf8_spanish_ci,
  PRIMARY KEY (`idHabitacion`),
  UNIQUE KEY `numero` (`numero`),
  KEY `idPaciente` (`idPaciente`),
  KEY `idPlanta` (`idPlanta`),
  KEY `idDoctor` (`idDoctor`),
  CONSTRAINT `habitacion_ibfk_1` FOREIGN KEY (`idPlanta`) REFERENCES `planta` (`idPlanta`),
  CONSTRAINT `habitacion_ibfk_2` FOREIGN KEY (`idPaciente`) REFERENCES `paciente` (`idPaciente`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `habitacion_ibfk_3` FOREIGN KEY (`idDoctor`) REFERENCES `doctor` (`idDoctor`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla hospital.habitacion: ~30 rows (aproximadamente)
DELETE FROM `habitacion`;
/*!40000 ALTER TABLE `habitacion` DISABLE KEYS */;
INSERT INTO `habitacion` (`idHabitacion`, `numero`, `idPaciente`, `idPlanta`, `idDoctor`, `Detalles`) VALUES
	(1, 101, NULL, 1, NULL, NULL),
	(2, 102, NULL, 1, NULL, NULL),
	(3, 201, NULL, 2, NULL, NULL),
	(4, 202, NULL, 2, NULL, NULL),
	(5, 301, NULL, 3, NULL, NULL),
	(6, 302, NULL, 3, NULL, NULL),
	(8, 103, NULL, 1, NULL, NULL),
	(9, 401, NULL, 4, NULL, NULL),
	(10, 402, NULL, 4, NULL, NULL),
	(11, 104, NULL, 1, NULL, NULL),
	(12, 105, NULL, 1, NULL, NULL),
	(13, 203, NULL, 2, NULL, NULL),
	(14, 204, NULL, 2, NULL, NULL),
	(15, 205, NULL, 2, NULL, NULL),
	(16, 303, NULL, 3, NULL, NULL),
	(17, 304, NULL, 3, NULL, NULL),
	(18, 305, NULL, 3, NULL, NULL),
	(19, 403, NULL, 4, NULL, NULL),
	(20, 404, NULL, 4, NULL, NULL),
	(21, 405, NULL, 4, NULL, NULL),
	(22, 501, NULL, 5, NULL, NULL),
	(23, 502, NULL, 5, NULL, NULL),
	(24, 503, NULL, 5, NULL, NULL),
	(25, 504, NULL, 5, NULL, NULL),
	(26, 505, NULL, 5, NULL, NULL),
	(27, 601, NULL, 6, NULL, NULL),
	(28, 602, NULL, 6, NULL, NULL),
	(29, 603, NULL, 6, NULL, NULL),
	(30, 604, NULL, 6, NULL, NULL),
	(31, 605, NULL, 6, NULL, NULL);
/*!40000 ALTER TABLE `habitacion` ENABLE KEYS */;

-- Volcando estructura para tabla hospital.medicamento
CREATE TABLE IF NOT EXISTS `medicamento` (
  `idMedicamento` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` text CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`idMedicamento`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla hospital.medicamento: ~5 rows (aproximadamente)
DELETE FROM `medicamento`;
/*!40000 ALTER TABLE `medicamento` DISABLE KEYS */;
INSERT INTO `medicamento` (`idMedicamento`, `nombre`, `descripcion`) VALUES
	(1, 'Levodropropizina', 'Se trata del isómero levorrotorio del antitusígeno dropropizina, del cual difiere porque sus efectos sedantes son menos marcados y porque tiene mejor tolerancia. La levodropropizina es un antitusígeno de acción periférica que ejerce un efecto anestésico e inhibe las vías aferentes que regulan el reflejo de la tos.'),
	(2, 'Simvastatina', 'Se emplea para reducir el colesterol y los triglicéridos (tipo de grasa) en la sangre'),
	(3, 'Aspirina ', 'Reduce las sustancias en el cuerpo que producen dolor, fiebre e inflamación.'),
	(4, 'Omeprazol', 'Para la acidez de estómago'),
	(5, 'Amlodipina', 'Ensancha los vasos sanguíneos y mejora el flujo de la sangre, por lo que se usa para reducir la presión arterial y tratar la hipertensión.');
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
  CONSTRAINT `notificacion_ibfk` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla hospital.notificacion: ~5 rows (aproximadamente)
DELETE FROM `notificacion`;
/*!40000 ALTER TABLE `notificacion` DISABLE KEYS */;
INSERT INTO `notificacion` (`idNotificacion`, `texto`, `leida`, `fecha`, `idUsuario`, `emisor`) VALUES
	(18, 'Tiene una nueva cita.', b'0', '2020-05-17 20:48:13', 30, 'Admin'),
	(19, 'Tiene una nueva cita.', b'0', '2020-05-17 20:48:15', 30, 'Admin'),
	(20, 'Tiene una nueva cita.', b'0', '2020-05-17 20:48:18', 30, 'Admin'),
	(21, 'Tiene una nueva cita.', b'0', '2020-05-17 20:48:21', 30, 'Admin'),
	(22, 'Tiene una nueva cita.', b'0', '2020-05-17 20:48:23', 30, 'Admin');
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
  CONSTRAINT `paciente_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla hospital.paciente: ~0 rows (aproximadamente)
DELETE FROM `paciente`;
/*!40000 ALTER TABLE `paciente` DISABLE KEYS */;
INSERT INTO `paciente` (`idPaciente`, `nombre`, `apellidos`, `dni`, `fechaNacimiento`, `ingresado`, `idUsuario`) VALUES
	(6, 'Adrián', 'Diez Valbuena', '71445275F', '1997-10-14', b'0', 30);
/*!40000 ALTER TABLE `paciente` ENABLE KEYS */;

-- Volcando estructura para tabla hospital.planta
CREATE TABLE IF NOT EXISTS `planta` (
  `idPlanta` int NOT NULL AUTO_INCREMENT,
  `especialidad` varchar(500) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `numero` int NOT NULL,
  PRIMARY KEY (`idPlanta`),
  UNIQUE KEY `numero` (`numero`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla hospital.planta: ~6 rows (aproximadamente)
DELETE FROM `planta`;
/*!40000 ALTER TABLE `planta` DISABLE KEYS */;
INSERT INTO `planta` (`idPlanta`, `especialidad`, `numero`) VALUES
	(1, 'Neumología', 1),
	(2, 'Cardiología', 2),
	(3, 'Traumatología', 3),
	(4, 'Pediatría', 4),
	(5, 'Neurología', 5),
	(6, 'Oncología', 6);
/*!40000 ALTER TABLE `planta` ENABLE KEYS */;

-- Volcando estructura para tabla hospital.receta
CREATE TABLE IF NOT EXISTS `receta` (
  `idReceta` int NOT NULL AUTO_INCREMENT,
  `idPaciente` int NOT NULL,
  `idMedicamento` int NOT NULL,
  `Comentario` text COLLATE utf8_spanish_ci,
  PRIMARY KEY (`idReceta`),
  KEY `idPaciente` (`idPaciente`),
  KEY `idMedicamento` (`idMedicamento`),
  CONSTRAINT `receta_ibfk_1` FOREIGN KEY (`idMedicamento`) REFERENCES `medicamento` (`idMedicamento`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `receta_ibfk_2` FOREIGN KEY (`idPaciente`) REFERENCES `paciente` (`idPaciente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla hospital.receta: ~5 rows (aproximadamente)
DELETE FROM `receta`;
/*!40000 ALTER TABLE `receta` DISABLE KEYS */;
INSERT INTO `receta` (`idReceta`, `idPaciente`, `idMedicamento`, `Comentario`) VALUES
	(3, 6, 3, 'DOs vecxes alds didsa 1 easwmabn'),
	(4, 6, 4, 'DOs vecxes alds didsa 1 easwmabn'),
	(5, 6, 4, 'sadfsdafdsafsd'),
	(6, 6, 5, 'dsadffdsdfsfads'),
	(7, 6, 4, 'sadfasdfdsaf');
/*!40000 ALTER TABLE `receta` ENABLE KEYS */;

-- Volcando estructura para tabla hospital.solicitud
CREATE TABLE IF NOT EXISTS `solicitud` (
  `idSolicitud` int NOT NULL AUTO_INCREMENT,
  `idPaciente` int NOT NULL,
  `descripcion` text CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `horario` enum('Mañanas','Tardes','Ambas') COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`idSolicitud`),
  KEY `idPaciente` (`idPaciente`),
  CONSTRAINT `solicitud_ibfk_1` FOREIGN KEY (`idPaciente`) REFERENCES `paciente` (`idPaciente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla hospital.solicitud: ~0 rows (aproximadamente)
DELETE FROM `solicitud`;
/*!40000 ALTER TABLE `solicitud` DISABLE KEYS */;
INSERT INTO `solicitud` (`idSolicitud`, `idPaciente`, `descripcion`, `horario`) VALUES
	(42, 6, 'me dirljbytf ytf ytfiuyv', 'Mañanas');
/*!40000 ALTER TABLE `solicitud` ENABLE KEYS */;

-- Volcando estructura para tabla hospital.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `idUsuario` int NOT NULL AUTO_INCREMENT,
  `nombreUsuario` varchar(100) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `clave` varchar(100) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `tipoUsuario` enum('paciente','doctor','admin') CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE KEY `nombreUsuario` (`nombreUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla hospital.usuario: ~6 rows (aproximadamente)
DELETE FROM `usuario`;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`idUsuario`, `nombreUsuario`, `clave`, `tipoUsuario`) VALUES
	(4, 'admin', 'admin', 'admin'),
	(18, 'antonio', 'antonio', 'doctor'),
	(25, 'lucas', 'lucas', 'doctor'),
	(26, 'lucas2', 'lucas2', 'doctor'),
	(30, 'kun', 'kun', 'paciente'),
	(31, 'prudencio', 'prudencio', 'doctor');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
