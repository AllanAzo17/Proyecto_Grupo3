drop schema if exists videojuego;
drop user if exists usuario_db;
CREATE SCHEMA videojuego;

create user 'usuario_db'@'%' identified by 'Usuar1o_db.';

grant all privileges on videojuego.* to 'usuario_db'@'%';
flush privileges;

use videojuego;

create table categoria (
  id_categoria INT NOT NULL AUTO_INCREMENT,
  descripcion VARCHAR(30) NOT NULL,
  activo bool,
  PRIMARY KEY (id_categoria))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

INSERT INTO categoria (descripcion, activo) VALUES 
('Acción', TRUE),
('Aventura', TRUE),
('RPG', TRUE),
('Simulación', TRUE),
('Deportes', TRUE),
('Horror', TRUE);

create table producto (
  id_producto INT NOT NULL AUTO_INCREMENT,
  id_categoria INT NOT NULL,
  descripcion VARCHAR(1000) NOT NULL,  
  detalle VARCHAR(1600) NOT NULL, 
  precio double NOT NULL,
  existencias int NOT NULL,  
  ruta_imagen varchar(1024),
  activo bool,
  PRIMARY KEY (id_producto),
  foreign key fk_producto_caregoria (id_categoria) references categoria(id_categoria)
    ON DELETE CASCADE
    ON UPDATE CASCADE  
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

INSERT INTO producto (id_producto, id_categoria, descripcion, detalle, precio, existencias, ruta_imagen, activo) VALUES 
(1, 1, 'Cyberpunk 2077', 'Juego de acción y aventura en un mundo abierto en Night City', 59000.0, 100, 'https://img2.wallspic.com/previews/7/3/0/0/6/160037/160037-cyberpunk-cyberpunk_2077-cyberpunk_2020-adam_badowski-cd_projekt-x750.jpg', TRUE),
(2, 2, 'The Legend of Zelda: Breath of the Wild', 'Juego de aventura épica en un Hyrule de mundo abierto', 49000.0, 50, 'https://assets.nintendo.com/image/upload/ar_16:9,c_lpad,w_1065/b_white/f_auto/q_auto/ncom/software/switch/70010000000025/7137262b5a64d921e193653f8aa0b722925abc5680380ca0e18a5cfd91697f58', TRUE),
(3, 3, 'Final Fantasy XV', 'RPG de fantasía con un mundo expansivo y una historia cautivadora', 39000.0, 75, 'https://upload.wikimedia.org/wikipedia/en/5/5a/FF_XV_cover_art.jpg', TRUE),
(4, 4, 'Los Sims 4', 'Simulador de vida donde controlas personas virtuales', 29000.0, 150, 'https://upload.wikimedia.org/wikipedia/en/7/7f/Sims4_Rebrand.png', TRUE),
(5, 5, 'FIFA 21', 'Simulador de fútbol con equipos y jugadores reales', 59000.0, 120, 'https://answers.ea.com/t5/image/serverpage/image-id/119426iFE5EC699EE73690A/image-size/original?v=v2&px=-1', TRUE),
(6, 6, 'Resident Evil Village', 'Juego de horror ambientado en un pueblo misterioso', 55000.0, 0, 'https://i.blogs.es/c01beb/20200615-resident-evil-village-1591981440118_v2_1524x804/1366_2000.webp', FALSE),
(7, 1, 'God of War Ragnarok', 'Juego de acción y aventura en la mitología nórdica', 70000.0, 80, 'https://upload.wikimedia.org/wikipedia/en/e/ee/God_of_War_Ragnar%C3%B6k_cover.jpg', TRUE),
(8, 2, 'Hollow Knight', 'Juego de plataformas metroidvania ambientado en un mundo subterráneo', 29000.0, 60, 'https://upload.wikimedia.org/wikipedia/en/thumb/0/04/Hollow_Knight_first_cover_art.webp/274px-Hollow_Knight_first_cover_art.webp.png', TRUE),
(9, 3, 'Elden Ring', 'RPG de acción en un vasto mundo abierto creado por George R.R. Martin', 75000.0, 90, 'https://upload.wikimedia.org/wikipedia/en/b/b9/Elden_Ring_Box_art.jpg', TRUE),
(10, 4, 'Animal Crossing: New Horizons', 'Simulador de vida en una isla desierta', 35000.0, 100, 'https://upload.wikimedia.org/wikipedia/en/1/1f/Animal_Crossing_New_Horizons.jpg', TRUE),
(11, 5, 'NBA 2K23', 'Simulador de baloncesto con los mejores equipos y jugadores', 65000.0, 40, 'https://upload.wikimedia.org/wikipedia/en/d/d7/NBA_2K23_cover_art.jpg', TRUE),
(12, 6, 'The Last of Us Part II', 'Juego de aventura y acción en un mundo post-apocalíptico', 60000.0, 50, 'https://upload.wikimedia.org/wikipedia/en/4/4f/TLOU_P2_Box_Art_2.png', TRUE),
(13, 1, 'Assassin’s Creed Valhalla', 'Juego de acción y aventura basado en la era vikinga', 62000.0, 70, 'https://upload.wikimedia.org/wikipedia/en/f/ff/Assassin%27s_Creed_Valhalla_cover.jpg', TRUE),
(14, 2, 'Stardew Valley', 'Simulador de granja con elementos de aventura y RPG', 25000.0, 150, 'https://upload.wikimedia.org/wikipedia/en/f/fd/Logo_of_Stardew_Valley.png', TRUE),
(15, 3, 'Dark Souls III', 'RPG de acción conocido por su dificultad y ambientación oscura', 40000.0, 30, 'https://upload.wikimedia.org/wikipedia/en/b/bb/Dark_souls_3_cover_art.jpg', TRUE);

CREATE TABLE usuario (
  id_usuario INT NOT NULL AUTO_INCREMENT,
  username varchar(20) NOT NULL,
  password varchar(512) NOT NULL,
  nombre VARCHAR(20) NOT NULL,
  apellidos VARCHAR(30) NOT NULL,
  correo VARCHAR(75) NULL,
  telefono VARCHAR(15) NULL,
  ruta_imagen varchar(1024),
  activo boolean,
  PRIMARY KEY (`id_usuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

INSERT INTO usuario (id_usuario, username,password,nombre, apellidos, correo, telefono,ruta_imagen,activo) VALUES 
(1,'rosalia','$2a$10$P1.w58XvnaYQUQgZUCk4aO/RTRl8EValluCqB3S2VMLTbRt.tlre.','Rosa', 'Avila Mendez',     'avema@gmail.com',      '1234-8936','https://media.revistavanityfair.es/photos/60e84fd9b710ef1e877f8960/master/w_1600,c_limit/11074.jpg',true),
(2,'arthur','$2a$10$koGR7eS22Pv5KdaVJKDcge04ZB53iMiw76.UjHPY.XyVYlYqXnPbO', 'Arthur', 'King', 'arthurking@gmail.com', '5678-1234', 'https://upload.wikimedia.org/wikipedia/commons/thumb/9/9f/Roger_Clark_-_Great_Philadelphia_Comic_Con.jpg/800px-Roger_Clark_-_Great_Philadelphia_Comic_Con.jpg', true),
(3,'rebeca','$2a$10$GkEj.ZzmQa/aEfDmtLIh3udIH5fMphx/35d0EYeqZL5uzgCJ0lQRi','Rebeca',  'Contreras Mora', 'acontreras@gmail.com', '5456-8789','https://upload.wikimedia.org/wikipedia/commons/0/06/Photo_of_Rebeca_Arthur.jpg',true);

create table role (  
  rol varchar(20),
  PRIMARY KEY (rol)  
);

insert into role (rol) values ('ADMIN'), ('USER');

create table rol (
  id_rol INT NOT NULL AUTO_INCREMENT,
  nombre varchar(20),
  id_usuario int,
  PRIMARY KEY (id_rol),
  FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

insert into rol (id_rol, nombre, id_usuario) values
(1,'ADMIN',1), 
(2,'USER',2), 
(3,'USER',3);

CREATE TABLE ruta (
    id_ruta INT AUTO_INCREMENT NOT NULL,
    patron VARCHAR(255) NOT NULL,
    rol_name VARCHAR(50) NOT NULL,
	PRIMARY KEY (id_ruta))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

INSERT INTO ruta (patron, rol_name) VALUES
('/producto/listado', 'ADMIN'),
('/categoria/listado', 'ADMIN'), 
('/producto/nuevo', 'ADMIN'),
('/producto/guardar', 'ADMIN'),
('/producto/reporte/excel', 'ADMIN'),
('/producto/modificar/**', 'ADMIN'),
('/producto/eliminar/**','ADMIN'),
('/categoria/nuevo', 'ADMIN'),
('/categoria/guardar', 'ADMIN'),
('/categoria/modificar/**', 'ADMIN'),
('/categoria/eliminar/**', 'ADMIN'),
('/usuario/**','ADMIN'),
('/constante/**', 'ADMIN'),
('/role/**', 'ADMIN'),
('/usuario_role/**', 'ADMIN'),
('/ruta/**', 'ADMIN'),
('/facturar/carrito', 'USER'),
('/payment/**', 'USER'),
('/pruebas/listado', 'USER'),
('/pruebas/listado/**', 'USER'),
('/favoritos/**', 'USER'),
('/producto/resenas', 'USER'),
('/producto/formulario', 'USER'),
('/producto/resenas/**', 'USER'),
('/producto/detalle/**', 'USER');

CREATE TABLE ruta_permit (
    id_ruta INT AUTO_INCREMENT NOT NULL,
    patron VARCHAR(255) NOT NULL,
	PRIMARY KEY (id_ruta))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

INSERT INTO ruta_permit (patron) VALUES 
('/'),
('/index'),
('/errores/**'),
('/carrito/**'),
('/registro/**'),
('/js/**'),
('/favoritos/**'),
('/webjars/**'),
('/tiquete/**'),
('/usuario/perfil'),
('/usuario/perfil/guardar');

CREATE TABLE resena (
  id_resena INT NOT NULL AUTO_INCREMENT,
  id_producto INT NOT NULL,
  id_usuario int NOT NULL,
  calificacion INT NOT NULL CHECK (calificacion BETWEEN 1 AND 5),
  comentario TEXT,
  fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id_resena),
  FOREIGN KEY fk_producto_resena (id_producto) REFERENCES producto(id_producto),
  FOREIGN KEY fk_resena_usuario (id_usuario) references usuario(id_usuario)  
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE tiquete (
  id_tiquete INT NOT NULL AUTO_INCREMENT,
  estado varchar(20) NOT NULL,
  descripcion_problema varchar(200),
  descripcion_solucion varchar(100),
  id_usuario INT NOT NULL,
  PRIMARY KEY (id_tiquete),
  FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

INSERT INTO tiquete (id_tiquete, estado, descripcion_problema, descripcion_solucion, id_usuario)
VALUES 
  (1, 'NUEVO', 'El sistema no responde al intentar iniciar sesión.', NULL, 2),
  (2, 'PROGRESO', 'La aplicación muestra un error al cargar los datos del usuario.', 'Se identificó un problema con la conexión a la base de datos.', 2);

create table factura (
  id_factura INT NOT NULL AUTO_INCREMENT,
  id_usuario INT NOT NULL,
  fecha date,  
  total double,
  estado int,
  PRIMARY KEY (id_factura),
  foreign key fk_resena_usuario (id_usuario) references usuario(id_usuario)  
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

create table venta (
  id_venta INT NOT NULL AUTO_INCREMENT,
  id_factura INT NOT NULL,
  id_producto INT NOT NULL,
  precio double, 
  cantidad int,
  PRIMARY KEY (id_venta),
  foreign key fk_ventas_factura (id_factura) references factura(id_factura),
  foreign key fk_ventas_producto (id_producto) references producto(id_producto) 
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;
