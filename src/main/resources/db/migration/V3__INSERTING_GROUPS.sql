USE `scheduler`;

INSERT INTO schedule ('calendar_id', 'group_id') VALUES (1, 1);
INSERT INTO schedule ('calendar_id', 'group_id') VALUES (2, 2);
INSERT INTO schedule ('calendar_id', 'group_id') VALUES (3, 3);
INSERT INTO schedule ('calendar_id', 'group_id') VALUES (4, 4);
INSERT INTO schedule ('calendar_id', 'group_id') VALUES (5, 5);

INSERT INTO group ('name', 'schedule_id') VALUES ('KC-14', 1);
INSERT INTO group ('name', 'schedule_id') VALUES ('cKC-16', 2);
INSERT INTO group ('name', 'schedule_id') VALUES ('1P-12', 3);
INSERT INTO group ('name', 'schedule_id') VALUES ('2P-12', 4);
INSERT INTO group ('name', 'schedule_id') VALUES ('CA-152', 5);