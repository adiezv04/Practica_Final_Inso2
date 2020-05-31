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
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla hospital.cita: ~0 rows (aproximadamente)
DELETE FROM `cita`;
/*!40000 ALTER TABLE `cita` DISABLE KEYS */;
INSERT INTO `cita` (`idCita`, `idDoctor`, `idPaciente`, `fecha`) VALUES
	(79, 15, 11, '2020-06-01 13:00:30');
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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla hospital.doctor: ~6 rows (aproximadamente)
DELETE FROM `doctor`;
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;
INSERT INTO `doctor` (`idDoctor`, `nombre`, `apellidos`, `especialidad`, `idUsuario`) VALUES
	(13, 'María Ángeles', 'Rayo Durán', 'Neumología', 37),
	(14, 'José Manuel', 'Esquinas Sáez', 'Cardología', 38),
	(15, 'Miguel', 'Cifuentes Machado', 'Traumatología', 39),
	(16, 'Emilio', 'Roca Nuño', 'Pediatría', 40),
	(17, 'Mónica', 'Bosch Daza', 'Neurología', 41),
	(18, 'Dolores', 'Machín Candel', 'Oncología', 42);
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
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla hospital.habitacion: ~34 rows (aproximadamente)
DELETE FROM `habitacion`;
/*!40000 ALTER TABLE `habitacion` DISABLE KEYS */;
INSERT INTO `habitacion` (`idHabitacion`, `numero`, `idPaciente`, `idPlanta`, `idDoctor`, `Detalles`) VALUES
	(1, 101, 12, 1, 13, 'Asma bronquial'),
	(2, 102, NULL, 1, NULL, NULL),
	(3, 201, 18, 2, 14, 'Arritmias'),
	(4, 202, NULL, 2, NULL, NULL),
	(5, 301, 11, 3, 15, 'Fractura de fémur'),
	(6, 302, 9, 3, 15, 'Esguince de rodilla'),
	(8, 103, 13, 1, 13, 'Neumonía'),
	(9, 401, NULL, 4, NULL, NULL),
	(10, 402, NULL, 4, NULL, NULL),
	(11, 104, 14, 1, 13, 'Fibrosis pulmonar idiopática'),
	(12, 105, NULL, 1, NULL, NULL),
	(13, 203, NULL, 2, NULL, NULL),
	(14, 204, NULL, 2, NULL, NULL),
	(15, 205, 17, 2, 14, 'Angina de pecho'),
	(16, 303, 10, 3, 15, 'Artritis'),
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
	(31, 605, NULL, 6, NULL, NULL),
	(32, 106, NULL, 1, NULL, NULL),
	(33, 107, NULL, 1, NULL, NULL),
	(34, 306, NULL, 3, NULL, NULL),
	(35, 506, NULL, 5, NULL, NULL);
/*!40000 ALTER TABLE `habitacion` ENABLE KEYS */;

-- Volcando estructura para tabla hospital.medicamento
CREATE TABLE IF NOT EXISTS `medicamento` (
  `idMedicamento` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` text CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`idMedicamento`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla hospital.medicamento: ~8 rows (aproximadamente)
DELETE FROM `medicamento`;
/*!40000 ALTER TABLE `medicamento` DISABLE KEYS */;
INSERT INTO `medicamento` (`idMedicamento`, `nombre`, `descripcion`) VALUES
	(8, 'Simvastatina', 'Se emplea para reducir el colesterol y los triglicéridos (tipo de grasa) en la sangre. '),
	(9, 'Omeprazol', 'Disminuye la producción de ácido al bloquear la enzima de la pared del estómago que se encarga de producir esta sustancia.\r\nPrincipalmente para acidez de estómago.'),
	(10, 'Lexotiroxina sódica', 'Se encarga de sustituir una hormona que se suele producir en nuestra glándula tiroidea para regular la energía y el metabolismo del cuerpo.'),
	(11, 'Ramipril', 'Trata la presión arterial alta (hipertensión) o la insuficiencia cardíaca congestiva. También mejora la supervivencia después de un infarto de miocardio y previene la insuficiencia renal por presión alterial alta y diabetes.'),
	(12, 'Amlodipina', 'Bloqueador de los canales de calcio.\r\nEnsancha los vasos sanguíneos y mejora el flujo de la sangre, por lo que se usa para reducir la presión arterial y tratar la hipertensión.'),
	(13, 'Atorvastatina', 'Disminuye la cantidad de colesterol que fabrica el hígado. Sirve para reducir los niveles de trigliricéridos en sangre y colesterol \'malo\', al tiempo que aumenta los niveles de colesterol \'bueno\'.'),
	(14, 'Salbutamol', 'Se usa como prevención de bronco-espasmos en pacientes con asma, bronquitis, enfisema y otras enfermedades del pulmón. \r\nAlivia la tos, la falta de aire y la respiración dificultosa al aumentar el flujo de aire que pasa a través de los tubos bronquiales.'),
	(15, 'Lansoprazol', 'Disminuye la cantidad de ácido producido en el estómago y se usa para tratar y prevenir las úlceras en este órgano y en el intestino y para controlar el ardor.');
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
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla hospital.notificacion: ~14 rows (aproximadamente)
DELETE FROM `notificacion`;
/*!40000 ALTER TABLE `notificacion` DISABLE KEYS */;
INSERT INTO `notificacion` (`idNotificacion`, `texto`, `leida`, `fecha`, `idUsuario`, `emisor`) VALUES
	(93, 'Usted debe ingresar en la habitacion 302', b'0', '2020-05-27 22:12:40', 43, 'Dr. Cifuentes Machado'),
	(94, 'Usted debe ingresar en la habitacion 303', b'0', '2020-05-27 22:12:47', 44, 'Dr. Cifuentes Machado'),
	(95, 'Usted debe ingresar en la habitacion 301', b'0', '2020-05-27 22:12:54', 45, 'Dr. Cifuentes Machado'),
	(96, 'Usted debe ingresar en la habitacion 101', b'0', '2020-05-27 22:13:24', 46, 'Dr. Rayo Durán'),
	(97, 'Usted debe ingresar en la habitacion 103', b'0', '2020-05-27 22:13:28', 47, 'Dr. Rayo Durán'),
	(98, 'Usted debe ingresar en la habitacion 104', b'0', '2020-05-27 22:13:33', 48, 'Dr. Rayo Durán'),
	(99, 'Usted debe ingresar en la habitacion 201', b'0', '2020-05-27 22:14:02', 52, 'Dr. Esquinas Sáez'),
	(100, 'Usted debe ingresar en la habitacion 205', b'0', '2020-05-27 22:14:08', 51, 'Dr. Esquinas Sáez'),
	(101, 'Tiene una nueva receta.', b'0', '2020-05-27 22:28:13', 47, 'Dr. Cifuentes Machado'),
	(102, 'Tiene una nueva receta.', b'0', '2020-05-27 22:28:18', 45, 'Dr. Cifuentes Machado'),
	(103, 'Tiene una nueva receta.', b'0', '2020-05-27 22:28:22', 45, 'Dr. Cifuentes Machado'),
	(104, 'Tiene una nueva receta.', b'0', '2020-05-27 22:28:45', 45, 'Dr. Cifuentes Machado'),
	(105, 'Tiene una nueva receta.', b'0', '2020-05-27 22:28:49', 45, 'Dr. Cifuentes Machado'),
	(106, 'Tiene una nueva receta.', b'0', '2020-05-27 22:29:12', 45, 'Dr. Cifuentes Machado'),
	(107, 'Tiene una nueva cita.', b'0', '2020-05-31 12:41:31', 45, 'Admin'),
	(108, 'Tiene una nueva cita.', b'0', '2020-05-31 12:41:32', 39, 'Admin');
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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla hospital.paciente: ~8 rows (aproximadamente)
DELETE FROM `paciente`;
/*!40000 ALTER TABLE `paciente` DISABLE KEYS */;
INSERT INTO `paciente` (`idPaciente`, `nombre`, `apellidos`, `dni`, `fechaNacimiento`, `ingresado`, `idUsuario`) VALUES
	(9, 'Marina', 'Marquina Garriga', '75512051S', '2014-03-19', b'1', 43),
	(10, 'José Carlos', 'Estrada Chueca', '86443956C', '1957-04-12', b'1', 44),
	(11, 'Ricardo', 'Jove Roma', '16455437P', '1992-01-26', b'1', 45),
	(12, 'Carmen', 'Queralt Quero', '63469400A', '1953-03-19', b'1', 46),
	(13, 'María', 'Sicilia Sala', '30884513K', '1960-02-14', b'1', 47),
	(14, 'Ricardo', 'Pedrosa Cazorla', '64109293J', '2007-12-11', b'1', 48),
	(15, 'Juan', 'Alcalá Hermoso', '21534718W', '2003-06-24', b'0', 49),
	(16, 'Alfonso', 'Herreros Gomis', '42385954J', '1953-07-22', b'0', 50),
	(17, 'Yolanda', 'Rufo Conesa', '26011704S', '1983-11-30', b'1', 51),
	(18, 'Rosa', 'Antón Igual', '96619459R', '1976-03-10', b'1', 52);
/*!40000 ALTER TABLE `paciente` ENABLE KEYS */;

-- Volcando estructura para tabla hospital.planta
CREATE TABLE IF NOT EXISTS `planta` (
  `idPlanta` int NOT NULL AUTO_INCREMENT,
  `especialidad` varchar(500) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `numero` int NOT NULL,
  PRIMARY KEY (`idPlanta`),
  UNIQUE KEY `numero` (`numero`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla hospital.planta: ~7 rows (aproximadamente)
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla hospital.receta: ~6 rows (aproximadamente)
DELETE FROM `receta`;
/*!40000 ALTER TABLE `receta` DISABLE KEYS */;
INSERT INTO `receta` (`idReceta`, `idPaciente`, `idMedicamento`, `Comentario`) VALUES
	(10, 13, 8, '1 vez al día durante una semana.'),
	(11, 11, 8, '1 vez al día durante una semana.'),
	(12, 14, 8, '1 vez al día durante una semana.'),
	(13, 15, 9, 'Tomar uno antes de ir a dormir hasta que se acabe la caja.'),
	(14, 17, 9, 'Tomar uno antes de ir a dormir hasta que se acabe la caja.'),
	(15, 18, 12, '3 veces al día durante 2 semanas.');
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
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla hospital.solicitud: ~0 rows (aproximadamente)
DELETE FROM `solicitud`;
/*!40000 ALTER TABLE `solicitud` DISABLE KEYS */;
/*!40000 ALTER TABLE `solicitud` ENABLE KEYS */;

-- Volcando estructura para tabla hospital.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `idUsuario` int NOT NULL AUTO_INCREMENT,
  `nombreUsuario` varchar(100) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `clave` varchar(100) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `tipoUsuario` enum('paciente','doctor','admin') CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE KEY `nombreUsuario` (`nombreUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla hospital.usuario: ~15 rows (aproximadamente)
DELETE FROM `usuario`;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`idUsuario`, `nombreUsuario`, `clave`, `tipoUsuario`) VALUES
	(4, 'admin', 'admin', 'admin'),
	(37, 'mangeles', 'mangeles', 'doctor'),
	(38, 'josema', 'josema', 'doctor'),
	(39, 'miguel', 'miguel', 'doctor'),
	(40, 'emilio', 'emilio', 'doctor'),
	(41, 'monica', 'monica', 'doctor'),
	(42, 'dolo', 'dolo', 'doctor'),
	(43, 'marquina', 'marquina', 'paciente'),
	(44, 'joseca', 'joseca', 'paciente'),
	(45, 'richi', 'richi', 'paciente'),
	(46, 'carmen', 'carmen', 'paciente'),
	(47, 'mery', 'mery', 'paciente'),
	(48, 'richard', 'richard', 'paciente'),
	(49, 'juan', 'juan', 'paciente'),
	(50, 'alfon', 'alfon', 'paciente'),
	(51, 'yola', 'yola', 'paciente'),
	(52, 'rosa', 'rosa', 'paciente');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
