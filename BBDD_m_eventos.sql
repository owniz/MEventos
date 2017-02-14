-- Creación base de datos --

CREATE DATABASE IF NOT EXISTS m_eventos;

-- conexión con la base de datos --

USE m_eventos;

-- Creacion tablas --

-- TABLA USUARIO --

CREATE TABLE usuario (
    id_usuario INT(3) NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50),
    apellidos VARCHAR(50),
    edad INT(3),
    telefono VARCHAR(9),
    email VARCHAR(20),
    pass_usuario VARCHAR(20),
    admin TINYINT(1),
    PRIMARY KEY (id_usuario)
)engine=InnoDB;

-- TABLA EVENTO --

CREATE TABLE evento (
    id_evento INT(3) NOT NULL AUTO_INCREMENT,
    denominacion VARCHAR(50),
    hora_inicio VARCHAR(5),
    hora_fin VARCHAR(5),
    fecha DATE,
    descripcion VARCHAR(80),
    path VARCHAR(100),
    PRIMARY KEY (id_evento)
)engine=InnoDB;

-- TABLA CIUDAD --

CREATE TABLE ciudad (
    id_ciudad INT(3) NOT NULL AUTO_INCREMENT,
    nombre_ciudad VARCHAR(50),
    provincia VARCHAR(50),
    PRIMARY KEY (id_ciudad)
)engine=InnoDB;

-- TABLA EVENTO_SUSCRITO --

CREATE TABLE evento_suscrito (
    id_evento_suscrito INT(3) NOT NULL AUTO_INCREMENT,
    id_usuario INT(3),
    id_evento INT(3),
    valoracion VARCHAR(200),
    path VARCHAR(100),
    PRIMARY KEY (id_evento_suscrito),
     FOREIGN KEY(id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE,
     FOREIGN KEY(id_evento) REFERENCES evento(id_evento) ON DELETE CASCADE ON UPDATE CASCADE
)engine=InnoDB;

-- TABLA CIUDAD_EVENTO --

CREATE TABLE ciudad_evento (
    id_ciudad_evento INT(3) NOT NULL AUTO_INCREMENT,
    id_ciudad INT(3),
    id_evento INT(3),
    PRIMARY KEY (id_ciudad_evento),
     FOREIGN KEY(id_ciudad) REFERENCES ciudad(id_ciudad) ON DELETE CASCADE ON UPDATE CASCADE,
     FOREIGN KEY(id_evento) REFERENCES evento(id_evento) ON DELETE CASCADE ON UPDATE CASCADE
)engine=InnoDB;

-- insercciones --

-- TABLA USUARIO --

INSERT INTO usuario (nombre, apellidos, edad, telefono, email, pass_usuario, admin)
VALUES ('Javier', 'Morales Montesinos', 34, '651457896', 'javi@javi.com', 'javi', 1);

INSERT INTO usuario (nombre, apellidos, edad, telefono, email, pass_usuario, admin)
VALUES ('Luis', 'Morales Cotolí', 22, '645123785', 'luis@luis.com', 'luis', 1);

INSERT INTO usuario (nombre, apellidos, edad, telefono, email, pass_usuario, admin)
VALUES ('Alvaro', 'Giner Oltra', 19, '694521467', 'alvaro@alvaro.com', 'alvaro', 0);

INSERT INTO usuario (nombre, apellidos, edad, telefono, email, pass_usuario, admin)
VALUES ('Ricardo', 'Miralles Bernal', 20, '612547512', 'richar@richar.com', 'ricardo', 0);

INSERT INTO usuario (nombre, apellidos, edad, telefono, email, pass_usuario, admin)
VALUES ('Anabel', 'Chacón Fernandez', 22, '678125476', 'anabel@anabel.com', 'anabel', 0);

-- TABLA EVENTO --

INSERT INTO evento (denominacion, hora_inicio, hora_fin, fecha, descripcion, path)
VALUES ('Medusa Sunbeach Festival', '18:00', '08:00', '2017-01-28', 'Festival de música electrónica', '/img/medusa.png');

INSERT INTO evento (denominacion, hora_inicio, hora_fin, fecha, descripcion, path)
VALUES ('Oktoberfest', '11:00', '00:00', '2017-05-10', 'Festival de origen aleman centrado en la cerveza', '/img/oktoberfest_valencia.jpg');

INSERT INTO evento (denominacion, hora_inicio, hora_fin, fecha, descripcion, path)
VALUES ('Red Bull X-Fighters', '21:00', '01:00', '2017-09-20', 'Exhibición de habilidades con motos de motocross', '/img/red_bull_x_fighters.png');

INSERT INTO evento (denominacion, hora_inicio, hora_fin, fecha, descripcion, path)
VALUES ('El intercambio', '19:00', '20:00', '2017-04-30', 'Obra teatral', '/img/el_intercambio.jpg');

INSERT INTO evento (denominacion, hora_inicio, hora_fin, fecha, descripcion, path)
VALUES ('Marenostrum Xperience', '18:00', '08:00', '2017-01-20', 'Festival de música electrónica', '/img/marenostrum_xperience.png');

INSERT INTO evento (denominacion, hora_inicio, hora_fin, fecha, descripcion, path)
VALUES ('Borrame por favor', '10:00', '12:00', '2017-04-30', 'Borrado para la presentación', '/img/smile.png');

-- TABLA CIUDAD --

INSERT INTO ciudad (nombre_ciudad, provincia)
VALUES ('Valencia', 'Valencia');

INSERT INTO ciudad (nombre_ciudad, provincia)
VALUES ('Madrid', 'Madrid');

INSERT INTO ciudad (nombre_ciudad, provincia)
VALUES ('Barcelona', 'Cataluña');

INSERT INTO ciudad (nombre_ciudad, provincia)
VALUES ('Cullera', 'Valencia');

INSERT INTO ciudad (nombre_ciudad, provincia)
VALUES ('Málaga', 'Andalucía');

-- TABLA EVENTO_SUSCRITO --

InSERT INTO evento_suscrito (id_usuario, id_evento, valoracion, path)
VALUES (1, 2, '', '/img/oktoberfest_valencia.jpg');

InSERT INTO evento_suscrito (id_usuario, id_evento, valoracion, path)
VALUES (1, 1, 'El aforo era excesivo para el recinto', '/img/medusa.png');

InSERT INTO evento_suscrito (id_usuario, id_evento, valoracion, path)
VALUES (2, 1, 'El aforo era excesivo para el recinto', '/img/medusa.png');

InSERT INTO evento_suscrito (id_usuario, id_evento, valoracion, path)
VALUES (3, 5, 'La bebida era demasiado cara', '/img/marenostrum_xperience.png');

InSERT INTO evento_suscrito (id_usuario, id_evento, valoracion, path)
VALUES (5, 4, '', '/img/el_intercambio.jpg');

InSERT INTO evento_suscrito (id_usuario, id_evento, valoracion, path)
VALUES (5, 3, '', '/img/red_bull_x_fighters.png');

-- CIUDAD_EVENTO --

INSERT INTO ciudad_evento (id_ciudad, id_evento)
VALUES (4, 1);

INSERT INTO ciudad_evento (id_ciudad, id_evento)
VALUES (1, 5);

INSERT INTO ciudad_evento (id_ciudad, id_evento)
VALUES (1, 2);

INSERT INTO ciudad_evento (id_ciudad, id_evento)
VALUES (2, 3);

INSERT INTO ciudad_evento (id_ciudad, id_evento)
VALUES (3, 4);

INSERT INTO ciudad_evento (id_ciudad, id_evento)
VALUES (3, 6);

-- COMPROBACIÓN --

SELECT * FROM usuario;
SELECT * FROM evento;
SELECT * FROM ciudad;
SELECT * FROM evento_suscrito;
SELECT * FROM ciudad_evento;

