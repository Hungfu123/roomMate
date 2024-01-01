create table room(
                     id serial primary key,
                     name varchar(500)
);

create table work_place (
                            id serial primary key,
                            room int references room(id),
                            room_key integer,
                            equipment varchar(500)[]
);

create table booking (
                         booking_ID serial primary key,
                         user_Name varchar(100),
                         room_Id int,
                         workplace_id int references work_place(id),
                         room_name varchar(100),
                         date_Time_From TIMESTAMP,
                         date_Time_To TIMESTAMP,
                         equipments varchar(500)[]

);

-- Raum hinzufügen
INSERT INTO room (name) VALUES ('Python');
INSERT INTO room (name) VALUES ('Anoconda');
INSERT INTO room (name) VALUES ('Cobra');
INSERT INTO room (name) VALUES ('Diablo');
INSERT INTO room (name) VALUES ('Attila');

-- Arbeitsplatz hinzufügen
INSERT INTO work_place (room, room_key, equipment) VALUES (1, 12, ARRAY['DOCKINGSTATION', 'TABLE_ADJUSTABLE', 'WEBCAM']::varchar[]);
INSERT INTO work_place (room, room_key, equipment) VALUES (1, 13, ARRAY['DOCKINGSTATION_USBC', 'TABLE_ADJUSTABLE', 'TASTATUR']::varchar[]);
INSERT INTO work_place (room, room_key, equipment) VALUES (1, 14, ARRAY['MONITOR']::varchar[]);
INSERT INTO work_place (room, room_key, equipment) VALUES (2, 21, ARRAY['DOCKINGSTATION', 'TABLE_ADJUSTABLE', 'MAUS']::varchar[]);
INSERT INTO work_place (room, room_key, equipment) VALUES (2, 22, ARRAY[ 'HEADSET', 'WEBCAM']::varchar[]);
INSERT INTO work_place (room, room_key, equipment) VALUES (3, 31, ARRAY['MONITOR', 'TASTATUR', 'WEBCAM']::varchar[]);
INSERT INTO work_place (room, room_key, equipment) VALUES (4, 41, ARRAY['DOCKINGSTATION_USBC', 'TASTATUR', 'TABLE_ADJUSTABLE','TASTATUR']::varchar[]);
INSERT INTO work_place (room, room_key, equipment) VALUES (4, 42, ARRAY['MONITOR', 'TASTATUR', 'WEBCAM']::varchar[]);
INSERT INTO work_place (room, room_key, equipment) VALUES (5, 51, ARRAY['MONITOR', 'TASTATUR', 'WEBCAM','TABLE_ADJUSTABLE','HEADSET']::varchar[]);


