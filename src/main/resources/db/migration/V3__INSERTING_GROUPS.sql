USE `scheduler`;

ALTER TABLE `group` DROP FOREIGN KEY schedule;
ALTER TABLE `group` DROP INDEX schedule;

ALTER TABLE `group` DROP COLUMN schedule_id;

INSERT INTO `group` (name) VALUES ('cKC-16');
INSERT INTO `group` (name) VALUES ('1P-12');
INSERT INTO `group` (name) VALUES ('CA-152');
INSERT INTO `group` (name) VALUES ('KC-14');
INSERT INTO `group` (name) VALUES ('2P-12');

INSERT INTO schedule (calendar_id, group_id) VALUES (1, 1);
INSERT INTO schedule (calendar_id, group_id) VALUES (2, 2);
INSERT INTO schedule (calendar_id, group_id) VALUES (3, 3);
INSERT INTO schedule (calendar_id, group_id) VALUES (4, 4);
INSERT INTO schedule (calendar_id, group_id) VALUES (5, 5);