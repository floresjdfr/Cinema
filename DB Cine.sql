CREATE TABLE `Administrador` (
  `id_Administrador` int NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`id_Administrador`),
  UNIQUE KEY `id_Administrador_UNIQUE` (`id_Administrador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


CREATE TABLE `Sala` (
  `id_Sala` varchar(2) NOT NULL,
  `total_Butacas` int NOT NULL,
  PRIMARY KEY (`id_Sala`),
  UNIQUE KEY `id_Sala_UNIQUE` (`id_Sala`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


CREATE TABLE `Pelicula` (
  `id_Pelicula` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `duracion` varchar(6) NOT NULL,
  `descripcion` varchar(1000) NOT NULL,
  `precio` varchar(5) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_Pelicula`),
  UNIQUE KEY `id_Pelicula_UNIQUE` (`id_Pelicula`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb3;


CREATE TABLE `Cliente` (
  `id_Cliente` int NOT NULL,
  `password` varchar(45) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `numero_Cuenta` varchar(25) NOT NULL,
  PRIMARY KEY (`id_Cliente`),
  UNIQUE KEY `id_Cliente_UNIQUE` (`id_Cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


CREATE TABLE `Cartelera` (
  `id_Cartelera` int NOT NULL AUTO_INCREMENT,
  `fecha_Funcion` varchar(12) NOT NULL,
  `hora_Inicio` varchar(8) NOT NULL,
  `hora_Fin` varchar(8) NOT NULL,
  `id_Pelicula` int NOT NULL,
  `id_Sala` varchar(2) NOT NULL,
  `estado` int DEFAULT NULL,
  PRIMARY KEY (`id_Cartelera`),
  UNIQUE KEY `idCartelera_UNIQUE` (`id_Cartelera`),
  KEY `id_Pelicula_idx` (`id_Pelicula`),
  KEY `id_Sala_idx` (`id_Sala`),
  CONSTRAINT `id_Pelicula` FOREIGN KEY (`id_Pelicula`) REFERENCES `Pelicula` (`id_Pelicula`),
  CONSTRAINT `id_Sala` FOREIGN KEY (`id_Sala`) REFERENCES `Sala` (`id_Sala`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb3;


CREATE TABLE `Ticket` (
  `id_Ticket` int NOT NULL AUTO_INCREMENT,
  `numero_Butaca` varchar(100) NOT NULL,
  `id_Cliente` int NOT NULL,
  `id_Cartelera` int NOT NULL,
  PRIMARY KEY (`id_Ticket`),
  UNIQUE KEY `id_Ticket_UNIQUE` (`id_Ticket`) /*!80000 INVISIBLE */,
  KEY `id_Cliente_idx` (`id_Cliente`),
  KEY `id_Cartelera_idx` (`id_Cartelera`),
  CONSTRAINT `id_Cartelera` FOREIGN KEY (`id_Cartelera`) REFERENCES `Cartelera` (`id_Cartelera`),
  CONSTRAINT `id_Cliente` FOREIGN KEY (`id_Cliente`) REFERENCES `Cliente` (`id_Cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=166 DEFAULT CHARSET=utf8mb3;
