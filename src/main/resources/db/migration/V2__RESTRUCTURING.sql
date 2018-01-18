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

DROP TABLE IF EXISTS `group`;
DROP TABLE IF EXISTS  `admin`;
DROP TABLE IF EXISTS  `attachment`;
DROP TABLE IF EXISTS  `faculty`;
DROP TABLE IF EXISTS  `lesson`;
DROP TABLE IF EXISTS  `schedule`;
DROP TABLE IF EXISTS  `secretary`;
DROP TABLE IF EXISTS  `status`;
DROP TABLE IF EXISTS  `subject`;
DROP TABLE IF EXISTS  `teacher`;








-- Дамп структуры для таблица scheduler.group
CREATE TABLE IF NOT EXISTS `group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT '0',
  `schedule_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `schedule` (`schedule_id`),
  CONSTRAINT `schedule` FOREIGN KEY (`schedule_id`) REFERENCES `group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Дамп данных таблицы scheduler.group: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `group` DISABLE KEYS */;
/*!40000 ALTER TABLE `group` ENABLE KEYS */;

-- Дамп структуры для таблица scheduler.schedule
CREATE TABLE IF NOT EXISTS `schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `calendar_id` varchar(255) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `group` (`group_id`),
  CONSTRAINT `group` FOREIGN KEY (`group_id`) REFERENCES `group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Дамп данных таблицы scheduler.schedule: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
