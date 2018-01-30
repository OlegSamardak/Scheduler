-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               5.5.23 - MySQL Community Server (GPL)
-- Операционная система:         Win64
-- HeidiSQL Версия:              9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Дамп структуры базы данных scheduler
CREATE DATABASE IF NOT EXISTS `scheduler` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `scheduler`;

-- Дамп структуры для таблица scheduler.admin
CREATE TABLE IF NOT EXISTS `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `login` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Дамп данных таблицы scheduler.admin: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;

-- Дамп структуры для таблица scheduler.attachment
CREATE TABLE IF NOT EXISTS `attachment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Дамп данных таблицы scheduler.attachment: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `attachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `attachment` ENABLE KEYS */;

-- Дамп структуры для таблица scheduler.faculty
CREATE TABLE IF NOT EXISTS `faculty` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Дамп данных таблицы scheduler.faculty: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `faculty` DISABLE KEYS */;
/*!40000 ALTER TABLE `faculty` ENABLE KEYS */;

-- Дамп структуры для таблица scheduler.lessonDTO
CREATE TABLE IF NOT EXISTS `lessonDTO` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `schedule_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKni07g89mm7l9g7k8vbu7lf5r` (`schedule_id`),
  CONSTRAINT `FKni07g89mm7l9g7k8vbu7lf5r` FOREIGN KEY (`schedule_id`) REFERENCES `schedule` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Дамп данных таблицы scheduler.lessonDTO: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `lessonDTO` DISABLE KEYS */;
/*!40000 ALTER TABLE `lessonDTO` ENABLE KEYS */;

-- Дамп структуры для таблица scheduler.schedule
CREATE TABLE IF NOT EXISTS `schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `day` int(11) NOT NULL,
  `week` int(11) NOT NULL,
  `group_id` int(11) DEFAULT NULL,
  `subject_id` int(11) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK69oakeanwehikps300emu9sy4` (`subject_id`),
  KEY `FKof1t073hsxioayk2covxfe8um` (`teacher_id`),
  CONSTRAINT `FKof1t073hsxioayk2covxfe8um` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`),
  CONSTRAINT `FK69oakeanwehikps300emu9sy4` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Дамп данных таблицы scheduler.schedule: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;

-- Дамп структуры для таблица scheduler.secretary
CREATE TABLE IF NOT EXISTS `secretary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `login` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `faculty_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1t46gg3akgmmayae0v10k1vek` (`faculty_id`),
  CONSTRAINT `FK1t46gg3akgmmayae0v10k1vek` FOREIGN KEY (`faculty_id`) REFERENCES `faculty` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Дамп данных таблицы scheduler.secretary: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `secretary` DISABLE KEYS */;
/*!40000 ALTER TABLE `secretary` ENABLE KEYS */;

-- Дамп структуры для таблица scheduler.status
CREATE TABLE IF NOT EXISTS `status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Дамп данных таблицы scheduler.status: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
/*!40000 ALTER TABLE `status` ENABLE KEYS */;

-- Дамп структуры для таблица scheduler.subject
CREATE TABLE IF NOT EXISTS `subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Дамп данных таблицы scheduler.subject: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;

-- Дамп структуры для таблица scheduler.teacher
CREATE TABLE IF NOT EXISTS `teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `last_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `faculty_id` int(11) DEFAULT NULL,
  `status_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqbtffr96yb132f3vi87okefkh` (`faculty_id`),
  KEY `FKqjjpyx1qej4lap68dvj9y2jp9` (`status_id`),
  CONSTRAINT `FKqjjpyx1qej4lap68dvj9y2jp9` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`),
  CONSTRAINT `FKqbtffr96yb132f3vi87okefkh` FOREIGN KEY (`faculty_id`) REFERENCES `faculty` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Дамп данных таблицы scheduler.teacher: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
