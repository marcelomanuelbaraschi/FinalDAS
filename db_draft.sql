DROP DATABASE  IF EXISTS db_indec_service
CREATE DATABASE db_indec_service
USE db_indec_service


CREATE TABLE tecnologia (
	   id BIGINT IDENTITY (1,1)
      ,nombre VARCHAR(20) NOT NULL
	  ,PRIMARY KEY(id)
      ,UNIQUE (nombre)
);
GO

INSERT INTO tecnologia (nombre)
    VALUES ('SOAP')
         , ('REST')
GO

CREATE TABLE cadena (
	  id BIGINT IDENTITY (1,1)
	, nombre VARCHAR(50) NOT NULL
	, PRIMARY KEY(id)
    , UNIQUE (nombre)
);
GO

INSERT INTO cadena (nombre)
VALUES
      ('Walmart')   
    , ('Jumbo')    
    , ('Carrefour')
GO 

CREATE TABLE cadenaServiceConfig (
	  id BIGINT IDENTITY (1,1)
	, nombreCadena VARCHAR(50) NOT NULL
	, tecnologia VARCHAR(20) NOT NULL
    , url VARCHAR(200) NOT NULL
	, FOREIGN KEY(tecnologia) REFERENCES tecnologia(nombre)
    , FOREIGN KEY(nombreCadena) REFERENCES cadena(nombre)
	, PRIMARY KEY(id)
);
GO


INSERT INTO cadenaServiceConfig (nombreCadena,tecnologia,url)
VALUES
      ('Walmart','SOAP', 'http://localhost:8000/supermercado_axis_one/services/SupermercadoAxisOne?wsdl')
    , ('Jumbo','SOAP', 'http://localhost:8003/supermercado_cxf_one/services/supermercado_cxf_one?wsdl')
    , ('Carrefour','REST', 'http://localhost:8001/supermercado_rest_one/supermercadoRestOne')
GO

CREATE TABLE marca (
	  id BIGINT IDENTITY (1,1)
	, nombre VARCHAR(50) NOT NULL
	, PRIMARY KEY(id)
    , UNIQUE (nombre)
);
GO

CREATE TABLE categoria (
	  id BIGINT IDENTITY (1,1)
	, nombre VARCHAR(50) NOT NULL
	, PRIMARY KEY(id)
    , UNIQUE (nombre)
);
GO

CREATE TABLE producto (
	  id BIGINT IDENTITY (1,1)
	, nombre VARCHAR(50) NOT NULL
    , idCategoria BIGINT NOT NULL
    , idMarca BIGINT NOT NULL
    , imagen VARBINARY(256) NULL
    , FOREIGN KEY(idCategoria) REFERENCES categoria(id)
    , FOREIGN KEY(idMarca) REFERENCES marca(id)
	, PRIMARY KEY(id)
);
GO


--------------------------------------------------------------------------------------

DROP DATABASE  IF EXISTS db_cadenas
CREATE DATABASE db_cadenas
USE db_cadenas


CREATE TABLE marca (
	  id BIGINT IDENTITY (1,1)
	, nombre VARCHAR(50) NOT NULL
	, PRIMARY KEY(id)
    , UNIQUE (nombre)
);
GO

CREATE TABLE producto (
	  id BIGINT IDENTITY (1,1)
	, nombre VARCHAR(50) NOT NULL
    , idMarca BIGINT NOT NULL
    , imagen VARBINARY(256) NULL
    , FOREIGN KEY(idMarca) REFERENCES marca(id)
	, PRIMARY KEY(id)
);
GO

DROP TABLE [provincia] 
CREATE TABLE [provincia] (
    id BIGINT IDENTITY (1,1)
   ,nombre VARCHAR(255) NOT NULL
    PRIMARY KEY (id)
);
GO


INSERT INTO [provincia] (nombre) VALUES
('Buenos Aires'),
('Catamarca'),
('Chaco'),
('Chubut'),
('Córdoba'),
('Corrientes'),
('Entre Ríos'),
('Formosa'),
('Jujuy'),
('La Pampa'),
('La Rioja'),
('Mendoza'),
('Misiones'),
('Neuquén'),
('Río Negro'),
('Salta'),
('San Juan'),
('San Luis'),
('Santa Cruz'),
('Santa Fe'),
('Santiago del Estero'),
('Tierra del Fuego'),
('Tucumán')
GO





DROP TABLE localidades;

CREATE TABLE localidad (
   id BIGINT IDENTITY (1,1)
  ,idProvincia BIGINT NOT NULL
  ,nombre   VARCHAR(255) NOT NULL
  ,FOREIGN KEY (idProvincia) REFERENCES provincia (id)
  ,PRIMARY KEY (id)
);
GO

INSERT INTO localidad (idProvincia,nombre) VALUES -- CATAMARCA
(2,'Ancasti'),
(2,'Andalgalá'),
(2,'Antofagasta'),
(2,'Belén'),
(2,'Capayán'),
(2,'Capital'),
(2,'4'),
(2,'Corral Quemado'),
(2,'El Alto'),
(2,'El Rodeo'),
(2,'F.Mamerto Esquiú'),
(2,'Fiambalá'),
(2,'Hualfín'),
(2,'Huillapima'),
(2,'Icaño'),
(2,'La Puerta'),
(2,'Las Juntas'),
(2,'Londres'),
(2,'Los Altos'),
(2,'Los Varela'),
(2,'Mutquín'),
(2,'Paclín'),
(2,'Poman'),
(2,'Pozo de La Piedra'),
(2,'Puerta de Corral'),
(2,'Puerta San José'),
(2,'Recreo'),
(2,'S.F.V de 4'),
(2,'San Fernando'),
(2,'San Fernando del Valle'),
(2,'San José'),
(2,'Santa María'),
(2,'Santa Rosa'),
(2,'Saujil'),
(2,'Tapso'),
(2,'Tinogasta'),
(2,'Valle Viejo'),
(2,'Villa Vil')
GO


INSERT INTO localidad (idProvincia,nombre) VALUES -- CHACO
(3,'Aviá Teraí'),
(3,'Barranqueras'),
(3,'Basail'),
(3,'Campo Largo'),
(3,'Capital'),
(3,'Capitán Solari'),
(3,'Charadai'),
(3,'Charata'),
(3,'Chorotis'),
(3,'Ciervo Petiso'),
(3,'Cnel. Du Graty'),
(3,'Col. Benítez'),
(3,'Col. Elisa'),
(3,'Col. Popular'),
(3,'Colonias Unidas'),
(3,'Concepción'),
(3,'Corzuela'),
(3,'Cote Lai'),
(3,'El Sauzalito'),
(3,'Enrique Urien'),
(3,'Fontana'),
(3,'Fte. Esperanza'),
(3,'Gancedo'),
(3,'Gral. Capdevila'),
(3,'Gral. Pinero'),
(3,'Gral. San Martín'),
(3,'Gral. Vedia'),
(3,'Hermoso Campo'),
(3,'I. del Cerrito'),
(3,'J.J. Castelli'),
(3,'La Clotilde'),
(3,'La Eduvigis'),
(3,'La Escondida'),
(3,'La Leonesa'),
(3,'La Tigra'),
(3,'La Verde'),
(3,'Laguna Blanca'),
(3,'Laguna Limpia'),
(3,'Lapachito'),
(3,'Las Breñas'),
(3,'Las Garcitas'),
(3,'Las Palmas'),
(3,'Los Frentones'),
(3,'Machagai'),
(3,'Makallé'),
(3,'Margarita Belén'),
(3,'Miraflores'),
(3,'Misión N. Pompeya'),
(3,'Napenay'),
(3,'Pampa Almirón'),
(3,'Pampa del Indio'),
(3,'Pampa del Infierno'),
(3,'Pdcia. de La Plaza'),
(3,'Pdcia. Roca'),
(3,'Pdcia. Roque Sáenz Peña'),
(3,'Pto. Bermejo'),
(3,'Pto. Eva Perón'),
(3,'Puero Tirol'),
(3,'Puerto Vilelas'),
(3,'Quitilipi'),
(3,'Resistencia'),
(3,'Sáenz Peña'),
(3,'Samuhú'),
(3,'San Bernardo'),
(3,'Santa Sylvina'),
(3,'Taco Pozo'),
(3,'Tres Isletas'),
(3,'Villa Ángela'),
(3,'Villa Berthet'),
(3,'Villa R. Bermejito')
GO

INSERT INTO localidad (idProvincia,nombre) VALUES -- CHUBUT
(4,'Aldea Apeleg'),
(4,'Aldea Beleiro'),
(4,'Aldea Epulef'),
(4,'Alto Río Sengerr'),
(4,'Buen Pasto'),
(4,'Camarones'),
(4,'Carrenleufú'),
(4,'Cholila'),
(4,'Co. Centinela'),
(4,'Colan Conhué'),
(4,'Comodoro Rivadavia'),
(4,'Corcovado'),
(4,'Cushamen'),
(4,'Dique F. Ameghino'),
(4,'Dolavón'),
(4,'Dr. R. Rojas'),
(4,'El Hoyo'),
(4,'El Maitén'),
(4,'Epuyén'),
(4,'Esquel'),
(4,'Facundo'),
(4,'Gaimán'),
(4,'Gan Gan'),
(4,'Gastre'),
(4,'Gdor. Costa'),
(4,'Gualjaina'),
(4,'J. de San Martín'),
(4,'Lago Blanco'),
(4,'Lago Puelo'),
(4,'Lagunita Salada'),
(4,'Las Plumas'),
(4,'Los Altares'),
(4,'Paso de los Indios'),
(4,'Paso del Sapo'),
(4,'Pto. Madryn'),
(4,'Pto. Pirámides'),
(4,'Rada Tilly'),
(4,'Rawson'),
(4,'Río Mayo'),
(4,'Río Pico'),
(4,'Sarmiento'),
(4,'Tecka'),
(4,'Telsen'),
(4,'Trelew'),
(4,'Trevelin'),
(4,'Veintiocho de Julio')
GO


INSERT INTO localidad (idProvincia,nombre) VALUES -- CORDOBA
(5, 'Achiras'),
(5, 'Adelia Maria'),
(5, 'Agua de Oro'),
(5, 'Alcira Gigena'),
(5, 'Aldea Santa Maria'),
(5, 'Alejandro Roca'),
(5, 'Alejo Ledesma'),
(5, 'Alicia'),
(5, 'Almafuerte'),
(5, 'Alpa Corral'),
(5, 'Alta Gracia'),
(5, 'Alto Alegre'),
(5, 'Alto de Los Quebrachos'),
(5, 'Altos de Chipion'),
(5, 'Amboy'),
(5, 'Ambul'),
(5, 'Ana Zumaran'),
(5, 'Anisacate'),
(5, 'Arguello'),
(5, 'Arias'),
(5, 'Arroyito'),
(5, 'Arroyo Algodon'),
(5, 'Arroyo Cabral'),
(5, 'Arroyo Los Patos'),
(5, 'Assunta'),
(5, 'Atahona'),
(5, 'Ausonia'),
(5, 'Avellaneda'),
(5, 'Ballesteros'),
(5, 'Ballesteros Sud'),
(5, 'Balnearia'),
(5, 'Bañado de Soto'),
(5, 'Bell Ville'),
(5, 'Bengolea'),
(5, 'Benjamin Gould'),
(5, 'Berrotaran'),
(5, 'Bialet Masse'),
(5, 'Bouwer'),
(5, 'Brinkmann'),
(5, 'Buchardo'),
(5, 'Bulnes'),
(5, 'Cabalango'),
(5, 'Calamuchita'),
(5, 'Calchin'),
(5, 'Calchin Oeste'),
(5, 'Calmayo'),
(5, 'Camilo Aldao'),
(5, 'Caminiaga'),
(5, 'Cañada de Luque'),
(5, 'Cañada de Machado'),
(5, 'Cañada de Rio Pinto'),
(5, 'Cañada del Sauce'),
(5, 'Canals'),
(5, 'Candelaria Sud'),
(5, 'Capilla de Remedios'),
(5, 'Capilla de Siton'),
(5, 'Capilla del Carmen'),
(5, 'Capilla del Monte'),
(5, 'Capital'),
(5, 'Capitan Gral B. O´Higgins'),
(5, 'Carnerillo'),
(5, 'Carrilobo'),
(5, 'Casa Grande'),
(5, 'Cavanagh'),
(5, 'Cerro Colorado'),
(5, 'Chaján'),
(5, 'Chalacea'),
(5, 'Chañar Viejo'),
(5, 'Chancaní'),
(5, 'Charbonier'),
(5, 'Charras'),
(5, 'Chazón'),
(5, 'Chilibroste'),
(5, 'Chucul'),
(5, 'Chuña'),
(5, 'Chuña Huasi'),
(5, 'Churqui Cañada'),
(5, 'Cienaga Del Coro'),
(5, 'Cintra'),
(5, 'Col. Almada'),
(5, 'Col. Anita'),
(5, 'Col. Barge'),
(5, 'Col. Bismark'),
(5, 'Col. Bremen'),
(5, 'Col. Caroya'),
(5, 'Col. Italiana'),
(5, 'Col. Iturraspe'),
(5, 'Col. Las Cuatro Esquinas'),
(5, 'Col. Las Pichanas'),
(5, 'Col. Marina'),
(5, 'Col. Prosperidad'),
(5, 'Col. San Bartolome'),
(5, 'Col. San Pedro'),
(5, 'Col. Tirolesa'),
(5, 'Col. Vicente Aguero'),
(5, 'Col. Videla'),
(5, 'Col. Vignaud'),
(5, 'Col. Waltelina'),
(5, 'Colazo'),
(5, 'Comechingones'),
(5, 'Conlara'),
(5, 'Copacabana'),
(5, '7'),
(5, 'Coronel Baigorria'),
(5, 'Coronel Moldes'),
(5, 'Corral de Bustos'),
(5, 'Corralito'),
(5, 'Cosquín'),
(5, 'Costa Sacate'),
(5, 'Cruz Alta'),
(5, 'Cruz de Caña'),
(5, 'Cruz del Eje'),
(5, 'Cuesta Blanca'),
(5, 'Dean Funes'),
(5, 'Del Campillo'),
(5, 'Despeñaderos'),
(5, 'Devoto'),
(5, 'Diego de Rojas'),
(5, 'Dique Chico'),
(5, 'El Arañado'),
(5, 'El Brete'),
(5, 'El Chacho'),
(5, 'El Crispín'),
(5, 'El Fortín'),
(5, 'El Manzano'),
(5, 'El Rastreador'),
(5, 'El Rodeo'),
(5, 'El Tío'),
(5, 'Elena'),
(5, 'Embalse'),
(5, 'Esquina'),
(5, 'Estación Gral. Paz'),
(5, 'Estación Juárez Celman'),
(5, 'Estancia de Guadalupe'),
(5, 'Estancia Vieja'),
(5, 'Etruria'),
(5, 'Eufrasio Loza'),
(5, 'Falda del Carmen'),
(5, 'Freyre'),
(5, 'Gral. Baldissera'),
(5, 'Gral. Cabrera'),
(5, 'Gral. Deheza'),
(5, 'Gral. Fotheringham'),
(5, 'Gral. Levalle'),
(5, 'Gral. Roca'),
(5, 'Guanaco Muerto'),
(5, 'Guasapampa'),
(5, 'Guatimozin'),
(5, 'Gutenberg'),
(5, 'Hernando'),
(5, 'Huanchillas'),
(5, 'Huerta Grande'),
(5, 'Huinca Renanco'),
(5, 'Idiazabal'),
(5, 'Impira'),
(5, 'Inriville'),
(5, 'Isla Verde'),
(5, 'Italó'),
(5, 'James Craik'),
(5, 'Jesús María'),
(5, 'Jovita'),
(5, 'Justiniano Posse'),
(5, 'Km 658'),
(5, 'L. V. Mansilla'),
(5, 'La Batea'),
(5, 'La Calera'),
(5, 'La Carlota'),
(5, 'La Carolina'),
(5, 'La Cautiva'),
(5, 'La Cesira'),
(5, 'La Cruz'),
(5, 'La Cumbre'),
(5, 'La Cumbrecita'),
(5, 'La Falda'),
(5, 'La Francia'),
(5, 'La Granja'),
(5, 'La Higuera'),
(5, 'La Laguna'),
(5, 'La Paisanita'),
(5, 'La Palestina'),
(5, '12'),
(5, 'La Paquita'),
(5, 'La Para'),
(5, 'La Paz'),
(5, 'La Playa'),
(5, 'La Playosa'),
(5, 'La Población'),
(5, 'La Posta'),
(5, 'La Puerta'),
(5, 'La Quinta'),
(5, 'La Rancherita'),
(5, 'La Rinconada'),
(5, 'La Serranita'),
(5, 'La Tordilla'),
(5, 'Laborde'),
(5, 'Laboulaye'),
(5, 'Laguna Larga'),
(5, 'Las Acequias'),
(5, 'Las Albahacas'),
(5, 'Las Arrias'),
(5, 'Las Bajadas'),
(5, 'Las Caleras'),
(5, 'Las Calles'),
(5, 'Las Cañadas'),
(5, 'Las Gramillas'),
(5, 'Las Higueras'),
(5, 'Las Isletillas'),
(5, 'Las Junturas'),
(5, 'Las Palmas'),
(5, 'Las Peñas'),
(5, 'Las Peñas Sud'),
(5, 'Las Perdices'),
(5, 'Las Playas'),
(5, 'Las Rabonas'),
(5, 'Las Saladas'),
(5, 'Las Tapias'),
(5, 'Las Varas'),
(5, 'Las Varillas'),
(5, 'Las Vertientes'),
(5, 'Leguizamón'),
(5, 'Leones'),
(5, 'Los Cedros'),
(5, 'Los Cerrillos'),
(5, 'Los Chañaritos (C.E)'),
(5, 'Los Chanaritos (R.S)'),
(5, 'Los Cisnes'),
(5, 'Los Cocos'),
(5, 'Los Cóndores'),
(5, 'Los Hornillos'),
(5, 'Los Hoyos'),
(5, 'Los Mistoles'),
(5, 'Los Molinos'),
(5, 'Los Pozos'),
(5, 'Los Reartes'),
(5, 'Los Surgentes'),
(5, 'Los Talares'),
(5, 'Los Zorros'),
(5, 'Lozada'),
(5, 'Luca'),
(5, 'Luque'),
(5, 'Luyaba'),
(5, 'Malagueño'),
(5, 'Malena'),
(5, 'Malvinas Argentinas'),
(5, 'Manfredi'),
(5, 'Maquinista Gallini'),
(5, 'Marcos Juárez'),
(5, 'Marull'),
(5, 'Matorrales'),
(5, 'Mattaldi'),
(5, 'Mayu Sumaj'),
(5, 'Media Naranja'),
(5, 'Melo'),
(5, 'Mendiolaza'),
(5, 'Mi Granja'),
(5, 'Mina Clavero'),
(5, 'Miramar'),
(5, 'Morrison'),
(5, 'Morteros'),
(5, 'Mte. Buey'),
(5, 'Mte. Cristo'),
(5, 'Mte. De Los Gauchos'),
(5, 'Mte. Leña'),
(5, 'Mte. Maíz'),
(5, 'Mte. Ralo'),
(5, 'Nicolás Bruzone'),
(5, 'Noetinger'),
(5, 'Nono'),
(5, 'Nueva 7'),
(5, 'Obispo Trejo'),
(5, 'Olaeta'),
(5, 'Oliva'),
(5, 'Olivares San Nicolás'),
(5, 'Onagolty'),
(5, 'Oncativo'),
(5, 'Ordoñez'),
(5, 'Pacheco De Melo'),
(5, 'Pampayasta N.'),
(5, 'Pampayasta S.'),
(5, 'Panaholma'),
(5, 'Pascanas'),
(5, 'Pasco'),
(5, 'Paso del Durazno'),
(5, 'Paso Viejo'),
(5, 'Pilar'),
(5, 'Pincén'),
(5, 'Piquillín'),
(5, 'Plaza de Mercedes'),
(5, 'Plaza Luxardo'),
(5, 'Porteña'),
(5, 'Potrero de Garay'),
(5, 'Pozo del Molle'),
(5, 'Pozo Nuevo'),
(5, 'Pueblo Italiano'),
(5, 'Puesto de Castro'),
(5, 'Punta del Agua'),
(5, 'Quebracho Herrado'),
(5, 'Quilino'),
(5, 'Rafael García'),
(5, 'Ranqueles'),
(5, 'Rayo Cortado'),
(5, 'Reducción'),
(5, 'Rincón'),
(5, 'Río Bamba'),
(5, 'Río Ceballos'),
(5, 'Río Cuarto'),
(5, 'Río de Los Sauces'),
(5, 'Río Primero'),
(5, 'Río Segundo'),
(5, 'Río Tercero'),
(5, 'Rosales'),
(5, 'Rosario del Saladillo'),
(5, 'Sacanta'),
(5, 'Sagrada Familia'),
(5, 'Saira'),
(5, 'Saladillo'),
(5, 'Saldán'),
(5, 'Salsacate'),
(5, 'Salsipuedes'),
(5, 'Sampacho'),
(5, 'San Agustín'),
(5, 'San Antonio de Arredondo'),
(5, 'San Antonio de Litín'),
(5, 'San Basilio'),
(5, 'San Carlos Minas'),
(5, 'San Clemente'),
(5, 'San Esteban'),
(5, 'San Francisco'),
(5, 'San Ignacio'),
(5, 'San Javier'),
(5, 'San Jerónimo'),
(5, 'San Joaquín'),
(5, 'San José de La Dormida'),
(5, 'San José de Las Salinas'),
(5, 'San Lorenzo'),
(5, 'San Marcos Sierras'),
(5, 'San Marcos Sud'),
(5, 'San Pedro'),
(5, 'San Pedro N.'),
(5, 'San Roque'),
(5, 'San Vicente'),
(5, 'Santa Catalina'),
(5, 'Santa Elena'),
(5, 'Santa Eufemia'),
(5, 'Santa Maria'),
(5, 'Sarmiento'),
(5, 'Saturnino M.Laspiur'),
(5, 'Sauce Arriba'),
(5, 'Sebastián Elcano'),
(5, 'Seeber'),
(5, 'Segunda Usina'),
(5, 'Serrano'),
(5, 'Serrezuela'),
(5, 'Sgo. Temple'),
(5, 'Silvio Pellico'),
(5, 'Simbolar'),
(5, 'Sinsacate'),
(5, 'Sta. Rosa de Calamuchita'),
(5, 'Sta. Rosa de Río Primero'),
(5, 'Suco'),
(5, 'Tala Cañada'),
(5, 'Tala Huasi'),
(5, 'Talaini'),
(5, 'Tancacha'),
(5, 'Tanti'),
(5, 'Ticino'),
(5, 'Tinoco'),
(5, 'Tío Pujio'),
(5, 'Toledo'),
(5, 'Toro Pujio'),
(5, 'Tosno'),
(5, 'Tosquita'),
(5, 'Tránsito'),
(5, 'Tuclame'),
(5, 'Tutti'),
(5, 'Ucacha'),
(5, 'Unquillo'),
(5, 'Valle de Anisacate'),
(5, 'Valle Hermoso'),
(5, 'Vélez Sarfield'),
(5, 'Viamonte'),
(5, 'Vicuña Mackenna'),
(5, 'Villa Allende'),
(5, 'Villa Amancay'),
(5, 'Villa Ascasubi'),
(5, 'Villa Candelaria N.'),
(5, 'Villa Carlos Paz'),
(5, 'Villa Cerro Azul'),
(5, 'Villa Ciudad de América'),
(5, 'Villa Ciudad Pque Los Reartes'),
(5, 'Villa Concepción del Tío'),
(5, 'Villa Cura Brochero'),
(5, 'Villa de Las Rosas'),
(5, 'Villa de María'),
(5, 'Villa de Pocho'),
(5, 'Villa de Soto'),
(5, 'Villa del Dique'),
(5, 'Villa del Prado'),
(5, 'Villa del Rosario'),
(5, 'Villa del Totoral'),
(5, 'Villa Dolores'),
(5, 'Villa El Chancay'),
(5, 'Villa Elisa'),
(5, 'Villa Flor Serrana'),
(5, 'Villa Fontana'),
(5, 'Villa Giardino'),
(5, 'Villa Gral. Belgrano'),
(5, 'Villa Gutierrez'),
(5, 'Villa Huidobro'),
(5, 'Villa La Bolsa'),
(5, 'Villa Los Aromos'),
(5, 'Villa Los Patos'),
(5, 'Villa María'),
(5, 'Villa Nueva'),
(5, 'Villa Pque. Santa Ana'),
(5, 'Villa Pque. Siquiman'),
(5, 'Villa Quillinzo'),
(5, 'Villa Rossi'),
(5, 'Villa Rumipal'),
(5, 'Villa San Esteban'),
(5, 'Villa San Isidro'),
(5, 'Villa 21'),
(5, 'Villa Sarmiento (G.R)'),
(5, 'Villa Sarmiento (S.A)'),
(5, 'Villa Tulumba'),
(5, 'Villa Valeria'),
(5, 'Villa Yacanto'),
(5, 'Washington'),
(5, 'Wenceslao Escalante'),
(5, 'Ycho Cruz Sierras')
GO

INSERT INTO localidad (idProvincia,nombre) VALUES -- CORRIENTES
(6,'Alvear'),
(6,'Bella Vista'),
(6,'Berón de Astrada'),
(6,'Bonpland'),
(6,'Caá Cati'),
(6,'Capital'),
(6,'Chavarría'),
(6,'Col. C. Pellegrini'),
(6,'Col. Libertad'),
(6,'Col. Liebig'),
(6,'Col. Sta Rosa'),
(6,'Concepción'),
(6,'Cruz de Los Milagros'),
(6,'Curuzú-Cuatiá'),
(6,'Empedrado'),
(6,'Esquina'),
(6,'Estación Torrent'),
(6,'Felipe Yofré'),
(6,'Garruchos'),
(6,'Gdor. Agrónomo'),
(6,'Gdor. Martínez'),
(6,'Goya'),
(6,'Guaviravi'),
(6,'Herlitzka'),
(6,'Ita-Ibate'),
(6,'Itatí'),
(6,'Ituzaingó'),
(6,'José Rafael Gómez'),
(6,'Juan Pujol'),
(6,'La Cruz'),
(6,'Lavalle'),
(6,'Lomas de Vallejos'),
(6,'Loreto'),
(6,'Mariano I. Loza'),
(6,'Mburucuyá'),
(6,'Mercedes'),
(6,'Mocoretá'),
(6,'Mte. Caseros'),
(6,'Nueve de Julio'),
(6,'Palmar Grande'),
(6,'Parada Pucheta'),
(6,'Paso de La Patria'),
(6,'Paso de Los Libres'),
(6,'Pedro R. Fernandez'),
(6,'Perugorría'),
(6,'Pueblo Libertador'),
(6,'Ramada Paso'),
(6,'Riachuelo'),
(6,'Saladas'),
(6,'San Antonio'),
(6,'San Carlos'),
(6,'San Cosme'),
(6,'San Lorenzo'),
(6,'20 del Palmar'),
(6,'San Miguel'),
(6,'San Roque'),
(6,'Santa Ana'),
(6,'Santa Lucía'),
(6,'Santo Tomé'),
(6,'Sauce'),
(6,'Tabay'),
(6,'Tapebicuá'),
(6,'Tatacua'),
(6,'Virasoro'),
(6,'Yapeyú'),
(6,'Yataití Calle')
GO


INSERT INTO localidad (idProvincia,nombre) VALUES -- ENTRE RIOS
(7,'Alarcón'),
(7,'Alcaraz'),
(7,'Alcaraz N.'),
(7,'Alcaraz S.'),
(7,'Aldea Asunción'),
(7,'Aldea Brasilera'),
(7,'Aldea Elgenfeld'),
(7,'Aldea Grapschental'),
(7,'Aldea Ma. Luisa'),
(7,'Aldea Protestante'),
(7,'Aldea Salto'),
(7,'Aldea San Antonio (G)'),
(7,'Aldea San Antonio (P)'),
(7,'Aldea 19'),
(7,'Aldea San Miguel'),
(7,'Aldea San Rafael'),
(7,'Aldea Spatzenkutter'),
(7,'Aldea Sta. María'),
(7,'Aldea Sta. Rosa'),
(7,'Aldea Valle María'),
(7,'Altamirano Sur'),
(7,'Antelo'),
(7,'Antonio Tomás'),
(7,'Aranguren'),
(7,'Arroyo Barú'),
(7,'Arroyo Burgos'),
(7,'Arroyo Clé'),
(7,'Arroyo Corralito'),
(7,'Arroyo del Medio'),
(7,'Arroyo Maturrango'),
(7,'Arroyo Palo Seco'),
(7,'Banderas'),
(7,'Basavilbaso'),
(7,'Betbeder'),
(7,'Bovril'),
(7,'Caseros'),
(7,'Ceibas'),
(7,'Cerrito'),
(7,'Chajarí'),
(7,'Chilcas'),
(7,'Clodomiro Ledesma'),
(7,'Col. Alemana'),
(7,'Col. Avellaneda'),
(7,'Col. Avigdor'),
(7,'Col. Ayuí'),
(7,'Col. Baylina'),
(7,'Col. Carrasco'),
(7,'Col. Celina'),
(7,'Col. Cerrito'),
(7,'Col. Crespo'),
(7,'Col. Elia'),
(7,'Col. Ensayo'),
(7,'Col. Gral. Roca'),
(7,'Col. La Argentina'),
(7,'Col. Merou'),
(7,'Col. Oficial Nª3'),
(7,'Col. Oficial Nº13'),
(7,'Col. Oficial Nº14'),
(7,'Col. Oficial Nº5'),
(7,'Col. Reffino'),
(7,'Col. Tunas'),
(7,'Col. Viraró'),
(7,'Colón'),
(7,'Concepción del Uruguay'),
(7,'Concordia'),
(7,'Conscripto Bernardi'),
(7,'Costa Grande'),
(7,'Costa San Antonio'),
(7,'Costa Uruguay N.'),
(7,'Costa Uruguay S.'),
(7,'Crespo'),
(7,'Crucecitas 3ª'),
(7,'Crucecitas 7ª'),
(7,'Crucecitas 8ª'),
(7,'Cuchilla Redonda'),
(7,'Curtiembre'),
(7,'Diamante'),
(7,'Distrito 6º'),
(7,'Distrito Chañar'),
(7,'Distrito Chiqueros'),
(7,'Distrito Cuarto'),
(7,'Distrito Diego López'),
(7,'Distrito Pajonal'),
(7,'Distrito Sauce'),
(7,'Distrito Tala'),
(7,'Distrito Talitas'),
(7,'Don Cristóbal 1ª Sección'),
(7,'Don Cristóbal 2ª Sección'),
(7,'Durazno'),
(7,'El Cimarrón'),
(7,'El Gramillal'),
(7,'El Palenque'),
(7,'El Pingo'),
(7,'El Quebracho'),
(7,'El Redomón'),
(7,'El Solar'),
(7,'Enrique Carbo'),
(7,'9'),
(7,'Espinillo N.'),
(7,'Estación Campos'),
(7,'Estación Escriña'),
(7,'Estación Lazo'),
(7,'Estación Raíces'),
(7,'Estación Yerúa'),
(7,'Estancia Grande'),
(7,'Estancia Líbaros'),
(7,'Estancia Racedo'),
(7,'Estancia Solá'),
(7,'Estancia Yuquerí'),
(7,'Estaquitas'),
(7,'Faustino M. Parera'),
(7,'Febre'),
(7,'Federación'),
(7,'Federal'),
(7,'Gdor. Echagüe'),
(7,'Gdor. Mansilla'),
(7,'Gilbert'),
(7,'González Calderón'),
(7,'Gral. Almada'),
(7,'Gral. Alvear'),
(7,'Gral. Campos'),
(7,'Gral. Galarza'),
(7,'Gral. Ramírez'),
(7,'Gualeguay'),
(7,'Gualeguaychú'),
(7,'Gualeguaycito'),
(7,'Guardamonte'),
(7,'Hambis'),
(7,'Hasenkamp'),
(7,'Hernandarias'),
(7,'Hernández'),
(7,'Herrera'),
(7,'Hinojal'),
(7,'Hocker'),
(7,'Ing. Sajaroff'),
(7,'Irazusta'),
(7,'Isletas'),
(7,'J.J De Urquiza'),
(7,'Jubileo'),
(7,'La Clarita'),
(7,'La Criolla'),
(7,'La Esmeralda'),
(7,'La Florida'),
(7,'La Fraternidad'),
(7,'La Hierra'),
(7,'La Ollita'),
(7,'La Paz'),
(7,'La Picada'),
(7,'La Providencia'),
(7,'La Verbena'),
(7,'Laguna Benítez'),
(7,'Larroque'),
(7,'Las Cuevas'),
(7,'Las Garzas'),
(7,'Las Guachas'),
(7,'Las Mercedes'),
(7,'Las Moscas'),
(7,'Las Mulitas'),
(7,'Las Toscas'),
(7,'Laurencena'),
(7,'Libertador San Martín'),
(7,'Loma Limpia'),
(7,'Los Ceibos'),
(7,'Los Charruas'),
(7,'Los Conquistadores'),
(7,'Lucas González'),
(7,'Lucas N.'),
(7,'Lucas S. 1ª'),
(7,'Lucas S. 2ª'),
(7,'Maciá'),
(7,'María Grande'),
(7,'María Grande 2ª'),
(7,'Médanos'),
(7,'Mojones N.'),
(7,'Mojones S.'),
(7,'Molino Doll'),
(7,'Monte Redondo'),
(7,'Montoya'),
(7,'Mulas Grandes'),
(7,'Ñancay'),
(7,'Nogoyá'),
(7,'Nueva Escocia'),
(7,'Nueva Vizcaya'),
(7,'Ombú'),
(7,'Oro Verde'),
(7,'Paraná'),
(7,'Pasaje Guayaquil'),
(7,'Pasaje Las Tunas'),
(7,'Paso de La Arena'),
(7,'Paso de La Laguna'),
(7,'Paso de Las Piedras'),
(7,'Paso Duarte'),
(7,'Pastor Britos'),
(7,'Pedernal'),
(7,'Perdices'),
(7,'Picada Berón'),
(7,'Piedras Blancas'),
(7,'Primer Distrito Cuchilla'),
(7,'Primero de Mayo'),
(7,'Pronunciamiento'),
(7,'Pto. Algarrobo'),
(7,'Pto. Ibicuy'),
(7,'Pueblo Brugo'),
(7,'Pueblo Cazes'),
(7,'Pueblo Gral. Belgrano'),
(7,'Pueblo Liebig'),
(7,'Puerto Yeruá'),
(7,'Punta del Monte'),
(7,'Quebracho'),
(7,'Quinto Distrito'),
(7,'Raices Oeste'),
(7,'Rincón de Nogoyá'),
(7,'Rincón del Cinto'),
(7,'Rincón del Doll'),
(7,'Rincón del Gato'),
(7,'Rocamora'),
(7,'Rosario del Tala'),
(7,'San Benito'),
(7,'San Cipriano'),
(7,'San Ernesto'),
(7,'San Gustavo'),
(7,'San Jaime'),
(7,'San José'),
(7,'San José de Feliciano'),
(7,'San Justo'),
(7,'San Marcial'),
(7,'San Pedro'),
(7,'San Ramírez'),
(7,'San Ramón'),
(7,'San Roque'),
(7,'San Salvador'),
(7,'San Víctor'),
(7,'Santa Ana'),
(7,'Santa Anita'),
(7,'Santa Elena'),
(7,'Santa Lucía'),
(7,'Santa Luisa'),
(7,'Sauce de Luna'),
(7,'Sauce Montrull'),
(7,'Sauce Pinto'),
(7,'Sauce Sur'),
(7,'Seguí'),
(7,'Sir Leonard'),
(7,'Sosa'),
(7,'Tabossi'),
(7,'Tezanos Pinto'),
(7,'Ubajay'),
(7,'Urdinarrain'),
(7,'Veinte de Septiembre'),
(7,'Viale'),
(7,'Victoria'),
(7,'Villa Clara'),
(7,'Villa del Rosario'),
(7,'Villa Domínguez'),
(7,'Villa Elisa'),
(7,'Villa Fontana'),
(7,'Villa Gdor. Etchevehere'),
(7,'Villa Mantero'),
(7,'Villa Paranacito'),
(7,'Villa Urquiza'),
(7,'Villaguay'),
(7,'Walter Moss'),
(7,'Yacaré'),
(7,'Yeso Oeste')
GO

INSERT INTO localidad (idProvincia,nombre) VALUES -- FORMOSA
(8,'Buena Vista'),
(8,'Clorinda'),
(8,'Col. Pastoril'),
(8,'Cte. Fontana'),
(8,'El Colorado'),
(8,'El Espinillo'),
(8,'Estanislao Del Campo'),
(8,'10'),
(8,'Fortín Lugones'),
(8,'Gral. Lucio V. Mansilla'),
(8,'Gral. Manuel Belgrano'),
(8,'Gral. Mosconi'),
(8,'Gran Guardia'),
(8,'Herradura'),
(8,'Ibarreta'),
(8,'Ing. Juárez'),
(8,'Laguna Blanca'),
(8,'Laguna Naick Neck'),
(8,'Laguna Yema'),
(8,'Las Lomitas'),
(8,'Los Chiriguanos'),
(8,'Mayor V. Villafañe'),
(8,'Misión San Fco.'),
(8,'Palo Santo'),
(8,'Pirané'),
(8,'Pozo del Maza'),
(8,'Riacho He-He'),
(8,'San Hilario'),
(8,'San Martín II'),
(8,'Siete Palmas'),
(8,'Subteniente Perín'),
(8,'Tres Lagunas'),
(8,'Villa Dos Trece'),
(8,'Villa Escolar'),
(8,'Villa Gral. Güemes')
GO

INSERT INTO localidad (idProvincia,nombre) VALUES -- JUJUY
(9,'Abdon Castro Tolay'),
(9,'Abra Pampa'),
(9,'Abralaite'),
(9,'Aguas Calientes'),
(9,'Arrayanal'),
(9,'Barrios'),
(9,'Caimancito'),
(9,'Calilegua'),
(9,'Cangrejillos'),
(9,'Caspala'),
(9,'Catuá'),
(9,'Cieneguillas'),
(9,'Coranzulli'),
(9,'Cusi-Cusi'),
(9,'El Aguilar'),
(9,'El Carmen'),
(9,'El Cóndor'),
(9,'El Fuerte'),
(9,'El Piquete'),
(9,'El Talar'),
(9,'Fraile Pintado'),
(9,'Hipólito Yrigoyen'),
(9,'Huacalera'),
(9,'Humahuaca'),
(9,'La Esperanza'),
(9,'La Mendieta'),
(9,'La Quiaca'),
(9,'Ledesma'),
(9,'Libertador Gral. San Martin'),
(9,'Maimara'),
(9,'Mina Pirquitas'),
(9,'Monterrico'),
(9,'Palma Sola'),
(9,'Palpalá'),
(9,'Pampa Blanca'),
(9,'Pampichuela'),
(9,'Perico'),
(9,'Puesto del Marqués'),
(9,'Puesto Viejo'),
(9,'Pumahuasi'),
(9,'Purmamarca'),
(9,'Rinconada'),
(9,'Rodeitos'),
(9,'Rosario de Río Grande'),
(9,'San Antonio'),
(9,'San Francisco'),
(9,'San Pedro'),
(9,'San Rafael'),
(9,'San Salvador'),
(9,'Santa Ana'),
(9,'Santa Catalina'),
(9,'Santa Clara'),
(9,'Susques'),
(9,'Tilcara'),
(9,'Tres Cruces'),
(9,'Tumbaya'),
(9,'Valle Grande'),
(9,'Vinalito'),
(9,'Volcán'),
(9,'Yala'),
(9,'Yaví'),
(9,'Yuto')
GO

INSERT INTO localidad (idProvincia,nombre) VALUES -- LA PAMPA
(10,'Abramo'),
(10,'Adolfo Van Praet'),
(10,'Agustoni'),
(10,'Algarrobo del Aguila'),
(10,'Alpachiri'),
(10,'Alta Italia'),
(10,'Anguil'),
(10,'Arata'),
(10,'Ataliva Roca'),
(10,'Bernardo Larroude'),
(10,'Bernasconi'),
(10,'Caleufú'),
(10,'Carro Quemado'),
(10,'Catriló'),
(10,'Ceballos'),
(10,'Chacharramendi'),
(10,'Col. Barón'),
(10,'Col. Santa María'),
(10,'Conhelo'),
(10,'Coronel Hilario Lagos'),
(10,'Cuchillo-Có'),
(10,'Doblas'),
(10,'Dorila'),
(10,'Eduardo Castex'),
(10,'Embajador Martini'),
(10,'Falucho'),
(10,'Gral. Acha'),
(10,'Gral. Manuel Campos'),
(10,'Gral. Pico'),
(10,'Guatraché'),
(10,'Ing. Luiggi'),
(10,'Intendente Alvear'),
(10,'Jacinto Arauz'),
(10,'La Adela'),
(10,'La Humada'),
(10,'La Maruja'),
(10,'12'),
(10,'La Reforma'),
(10,'Limay Mahuida'),
(10,'Lonquimay'),
(10,'Loventuel'),
(10,'Luan Toro'),
(10,'Macachín'),
(10,'Maisonnave'),
(10,'Mauricio Mayer'),
(10,'Metileo'),
(10,'Miguel Cané'),
(10,'Miguel Riglos'),
(10,'Monte Nievas'),
(10,'Parera'),
(10,'Perú'),
(10,'Pichi-Huinca'),
(10,'Puelches'),
(10,'Puelén'),
(10,'Quehue'),
(10,'Quemú Quemú'),
(10,'Quetrequén'),
(10,'Rancul'),
(10,'Realicó'),
(10,'Relmo'),
(10,'Rolón'),
(10,'Rucanelo'),
(10,'Sarah'),
(10,'Speluzzi'),
(10,'Sta. Isabel'),
(10,'Sta. Rosa'),
(10,'Sta. Teresa'),
(10,'Telén'),
(10,'Toay'),
(10,'Tomas M. de Anchorena'),
(10,'Trenel'),
(10,'Unanue'),
(10,'Uriburu'),
(10,'Veinticinco de Mayo'),
(10,'Vertiz'),
(10,'Victorica'),
(10,'Villa Mirasol'),
(10,'Winifreda')
GO

INSERT INTO localidad (idProvincia,nombre) VALUES -- LA RIOJA
(11,'Arauco'),
(11,'Capital'),
(11,'Castro Barros'),
(11,'Chamical'),
(11,'Chilecito'),
(11,'Coronel F. Varela'),
(11,'Famatina'),
(11,'Gral. A.V.Peñaloza'),
(11,'Gral. Belgrano'),
(11,'Gral. J.F. Quiroga'),
(11,'Gral. Lamadrid'),
(11,'Gral. Ocampo'),
(11,'Gral. San Martín'),
(11,'Independencia'),
(11,'Rosario Penaloza'),
(11,'San Blas de Los Sauces'),
(11,'Sanagasta'),
(11,'Vinchina')
GO

INSERT INTO localidad (idProvincia,nombre) VALUES -- MENDOZA
(12, 'Capital'),
(12, 'Chacras de Coria'),
(12, 'Dorrego'),
(12, 'Gllen'),
(12, 'Godoy Cruz'),
(12, 'Gral. Alvear'),
(12, 'Guaymallén'),
(12, 'Junín'),
(12, 'La Paz'),
(12, 'Las Heras'),
(12, 'Lavalle'),
(12, 'Luján'),
(12, 'Luján De Cuyo'),
(12, 'Maipú'),
(12, 'Malargüe'),
(12, 'Rivadavia'),
(12, 'San Carlos'),
(12, 'San Martín'),
(12, 'San Rafael'),
(12, 'Sta. Rosa'),
(12, 'Tunuyán'),
(12, 'Tupungato'),
(12, 'Villa Nueva')
GO


INSERT INTO localidad (idProvincia,nombre) VALUES -- MISIONES
(13, 'Alba Posse'),
(13, 'Almafuerte'),
(13, 'Apóstoles'),
(13, 'Aristóbulo Del Valle'),
(13, 'Arroyo Del Medio'),
(13, 'Azara'),
(13, 'Bdo. De Irigoyen'),
(13, 'Bonpland'),
(13, 'Caá Yari'),
(13, 'Campo Grande'),
(13, 'Campo Ramón'),
(13, 'Campo Viera'),
(13, 'Candelaria'),
(13, 'Capioví'),
(13, 'Caraguatay'),
(13, 'Cdte. Guacurarí'),
(13, 'Cerro Azul'),
(13, 'Cerro Corá'),
(13, 'Col. Alberdi'),
(13, 'Col. Aurora'),
(13, 'Col. Delicia'),
(13, 'Col. Polana'),
(13, 'Col. Victoria'),
(13, 'Col. Wanda'),
(13, 'Concepción De La Sierra'),
(13, 'Corpus'),
(13, 'Dos Arroyos'),
(13, 'Dos de Mayo'),
(13, 'El Alcázar'),
(13, 'El Dorado'),
(13, 'El Soberbio'),
(13, 'Esperanza'),
(13, 'F. Ameghino'),
(13, 'Fachinal'),
(13, 'Garuhapé'),
(13, 'Garupá'),
(13, 'Gdor. López'),
(13, 'Gdor. Roca'),
(13, 'Gral. Alvear'),
(13, 'Gral. Urquiza'),
(13, 'Guaraní'),
(13, 'H. Yrigoyen'),
(13, 'Iguazú'),
(13, 'Itacaruaré'),
(13, 'Jardín América'),
(13, 'Leandro N. Alem'),
(13, 'Libertad'),
(13, 'Loreto'),
(13, 'Los Helechos'),
(13, 'Mártires'),
(13, '15'),
(13, 'Mojón Grande'),
(13, 'Montecarlo'),
(13, 'Nueve de Julio'),
(13, 'Oberá'),
(13, 'Olegario V. Andrade'),
(13, 'Panambí'),
(13, 'Posadas'),
(13, 'Profundidad'),
(13, 'Pto. Iguazú'),
(13, 'Pto. Leoni'),
(13, 'Pto. Piray'),
(13, 'Pto. Rico'),
(13, 'Ruiz de Montoya'),
(13, 'San Antonio'),
(13, 'San Ignacio'),
(13, 'San Javier'),
(13, 'San José'),
(13, 'San Martín'),
(13, 'San Pedro'),
(13, 'San Vicente'),
(13, 'Santiago De Liniers'),
(13, 'Santo Pipo'),
(13, 'Sta. Ana'),
(13, 'Sta. María'),
(13, 'Tres Capones'),
(13, 'Veinticinco de Mayo'),
(13, 'Wanda')
GO

INSERT INTO localidad (idProvincia,nombre) VALUES -- NEUQUEN
(14,'Aguada San Roque'),
(14,'Aluminé'),
(14,'Andacollo'),
(14,'Añelo'),
(14,'Bajada del Agrio'),
(14,'Barrancas'),
(14,'Buta Ranquil'),
(14,'Capital'),
(14,'Caviahué'),
(14,'Centenario'),
(14,'Chorriaca'),
(14,'Chos Malal'),
(14,'Cipolletti'),
(14,'Covunco Abajo'),
(14,'Coyuco Cochico'),
(14,'Cutral Có'),
(14,'El Cholar'),
(14,'El Huecú'),
(14,'El Sauce'),
(14,'Guañacos'),
(14,'Huinganco'),
(14,'Las Coloradas'),
(14,'Las Lajas'),
(14,'Las Ovejas'),
(14,'Loncopué'),
(14,'Los Catutos'),
(14,'Los Chihuidos'),
(14,'Los Miches'),
(14,'Manzano Amargo'),
(14,'16'),
(14,'Octavio Pico'),
(14,'Paso Aguerre'),
(14,'Picún Leufú'),
(14,'Piedra del Aguila'),
(14,'Pilo Lil'),
(14,'Plaza Huincul'),
(14,'Plottier'),
(14,'Quili Malal'),
(14,'Ramón Castro'),
(14,'Rincón de Los Sauces'),
(14,'San Martín de Los Andes'),
(14,'San Patricio del Chañar'),
(14,'Santo Tomás'),
(14,'Sauzal Bonito'),
(14,'Senillosa'),
(14,'Taquimilán'),
(14,'Tricao Malal'),
(14,'Varvarco'),
(14,'Villa Curí Leuvu'),
(14,'Villa del Nahueve'),
(14,'Villa del Puente Picún Leuvú'),
(14,'Villa El Chocón'),
(14,'Villa La Angostura'),
(14,'Villa Pehuenia'),
(14,'Villa Traful'),
(14,'Vista Alegre'),
(14,'Zapala')
GO

INSERT INTO localidad (idProvincia,nombre) VALUES -- RIO NEGRO
(15,'Aguada Cecilio'),
(15,'Aguada de Guerra'),
(15,'Allén'),
(15,'Arroyo de La Ventana'),
(15,'Arroyo Los Berros'),
(15,'Bariloche'),
(15,'Calte. Cordero'),
(15,'Campo Grande'),
(15,'Catriel'),
(15,'Cerro Policía'),
(15,'Cervantes'),
(15,'Chelforo'),
(15,'Chimpay'),
(15,'Chinchinales'),
(15,'Chipauquil'),
(15,'Choele Choel'),
(15,'Cinco Saltos'),
(15,'Cipolletti'),
(15,'Clemente Onelli'),
(15,'Colán Conhue'),
(15,'Comallo'),
(15,'Comicó'),
(15,'Cona Niyeu'),
(15,'Coronel Belisle'),
(15,'Cubanea'),
(15,'Darwin'),
(15,'Dina Huapi'),
(15,'El Bolsón'),
(15,'El Caín'),
(15,'El Manso'),
(15,'Gral. Conesa'),
(15,'Gral. Enrique Godoy'),
(15,'Gral. Fernandez Oro'),
(15,'Gral. Roca'),
(15,'Guardia Mitre'),
(15,'Ing. Huergo'),
(15,'Ing. Jacobacci'),
(15,'Laguna Blanca'),
(15,'Lamarque'),
(15,'Las Grutas'),
(15,'Los Menucos'),
(15,'Luis Beltrán'),
(15,'Mainqué'),
(15,'Mamuel Choique'),
(15,'Maquinchao'),
(15,'Mencué'),
(15,'Mtro. Ramos Mexia'),
(15,'Nahuel Niyeu'),
(15,'Naupa Huen'),
(15,'Ñorquinco'),
(15,'Ojos de Agua'),
(15,'Paso de Agua'),
(15,'Paso Flores'),
(15,'Peñas Blancas'),
(15,'Pichi Mahuida'),
(15,'Pilcaniyeu'),
(15,'Pomona'),
(15,'Prahuaniyeu'),
(15,'Rincón Treneta'),
(15,'Río Chico'),
(15,'Río Colorado'),
(15,'Roca'),
(15,'San Antonio Oeste'),
(15,'San Javier'),
(15,'Sierra Colorada'),
(15,'Sierra Grande'),
(15,'Sierra Pailemán'),
(15,'Valcheta'),
(15,'Valle Azul'),
(15,'Viedma'),
(15,'Villa Llanquín'),
(15,'Villa Mascardi'),
(15,'Villa Regina'),
(15,'Yaminué')
GO


INSERT INTO localidad (idProvincia,nombre) VALUES -- SALTA
(16,'A. Saravia'),
(16,'Aguaray'),
(16,'Angastaco'),
(16,'Animaná'),
(16,'Cachi'),
(16,'Cafayate'),
(16,'Campo Quijano'),
(16,'Campo Santo'),
(16,'Capital'),
(16,'Cerrillos'),
(16,'Chicoana'),
(16,'Col. Sta. Rosa'),
(16,'Coronel Moldes'),
(16,'El Bordo'),
(16,'El Carril'),
(16,'El Galpón'),
(16,'El Jardín'),
(16,'El Potrero'),
(16,'El Quebrachal'),
(16,'El Tala'),
(16,'Embarcación'),
(16,'Gral. Ballivian'),
(16,'Gral. Güemes'),
(16,'Gral. Mosconi'),
(16,'Gral. Pizarro'),
(16,'Guachipas'),
(16,'Hipólito Yrigoyen'),
(16,'Iruyá'),
(16,'Isla De Cañas'),
(16,'J. V. Gonzalez'),
(16,'La Caldera'),
(16,'La Candelaria'),
(16,'La Merced'),
(16,'La Poma'),
(16,'La Viña'),
(16,'Las Lajitas'),
(16,'Los Toldos'),
(16,'Metán'),
(16,'Molinos'),
(16,'Nazareno'),
(16,'Orán'),
(16,'Payogasta'),
(16,'Pichanal'),
(16,'Prof. S. Mazza'),
(16,'Río Piedras'),
(16,'Rivadavia Banda Norte'),
(16,'Rivadavia Banda Sur'),
(16,'Rosario de La Frontera'),
(16,'Rosario de Lerma'),
(16,'Saclantás'),
(16,'18'),
(16,'San Antonio'),
(16,'San Carlos'),
(16,'San José De Metán'),
(16,'San Ramón'),
(16,'Santa Victoria E.'),
(16,'Santa Victoria O.'),
(16,'Tartagal'),
(16,'Tolar Grande'),
(16,'Urundel'),
(16,'Vaqueros'),
(16,'Villa San Lorenzo')
GO

INSERT INTO localidad (idProvincia,nombre) VALUES -- SAN JUAN
(17,'Albardón'),
(17,'Angaco'),
(17,'Calingasta'),
(17,'Capital'),
(17,'Caucete'),
(17,'Chimbas'),
(17,'Iglesia'),
(17,'Jachal'),
(17,'Nueve de Julio'),
(17,'Pocito'),
(17,'Rawson'),
(17,'Rivadavia'),
(17,'19'),
(17,'San Martín'),
(17,'Santa Lucía'),
(17,'Sarmiento'),
(17,'Ullum'),
(17,'Valle Fértil'),
(17,'Veinticinco de Mayo'),
(17,'Zonda')
GO


INSERT INTO localidad (idProvincia,nombre) VALUES -- SAN LUIS
(18,'Alto Pelado'),
(18,'Alto Pencoso'),
(18,'Anchorena'),
(18,'Arizona'),
(18,'Bagual'),
(18,'Balde'),
(18,'Batavia'),
(18,'Beazley'),
(18,'Buena Esperanza'),
(18,'Candelaria'),
(18,'Capital'),
(18,'Carolina'),
(18,'Carpintería'),
(18,'Concarán'),
(18,'Cortaderas'),
(18,'El Morro'),
(18,'El Trapiche'),
(18,'El Volcán'),
(18,'Fortín El Patria'),
(18,'Fortuna'),
(18,'Fraga'),
(18,'Juan Jorba'),
(18,'Juan Llerena'),
(18,'Juana Koslay'),
(18,'Justo Daract'),
(18,'La Calera'),
(18,'La Florida'),
(18,'La Punilla'),
(18,'La Toma'),
(18,'Lafinur'),
(18,'Las Aguadas'),
(18,'Las Chacras'),
(18,'Las Lagunas'),
(18,'Las Vertientes'),
(18,'Lavaisse'),
(18,'Leandro N. Alem'),
(18,'Los Molles'),
(18,'Luján'),
(18,'Mercedes'),
(18,'Merlo'),
(18,'Naschel'),
(18,'Navia'),
(18,'Nogolí'),
(18,'Nueva Galia'),
(18,'Papagayos'),
(18,'Paso Grande'),
(18,'Potrero de Los Funes'),
(18,'Quines'),
(18,'Renca'),
(18,'Saladillo'),
(18,'San Francisco'),
(18,'San Gerónimo'),
(18,'San Martín'),
(18,'San Pablo'),
(18,'Santa Rosa de Conlara'),
(18,'Talita'),
(18,'Tilisarao'),
(18,'Unión'),
(18,'Villa de La Quebrada'),
(18,'Villa de Praga'),
(18,'Villa del Carmen'),
(18,'Villa Gral. Roca'),
(18,'Villa Larca'),
(18,'Villa Mercedes'),
(18,'Zanjitas')
GO
INSERT INTO localidad (idProvincia,nombre) VALUES -- SANTA CRUZ
(19, 'Calafate'),
(19, 'Caleta Olivia'),
(19, 'Cañadón Seco'),
(19, 'Comandante Piedrabuena'),
(19, 'El Calafate'),
(19, 'El Chaltén'),
(19, 'Gdor. Gregores'),
(19, 'Hipólito Yrigoyen'),
(19, 'Jaramillo'),
(19, 'Koluel Kaike'),
(19, 'Las Heras'),
(19, 'Los Antiguos'),
(19, 'Perito Moreno'),
(19, 'Pico Truncado'),
(19, 'Pto. Deseado'),
(19, 'Pto. San Julián'),
(19, 'Pto. 21'),
(19, 'Río Cuarto'),
(19, 'Río Gallegos'),
(19, 'Río Turbio'),
(19, 'Tres Lagos'),
(19, 'Veintiocho De Noviembre')
GO

INSERT INTO localidad (idProvincia,nombre) VALUES -- SANTA FE
(20,'Aarón Castellanos'),
(20,'Acebal'),
(20,'Aguará Grande'),
(20,'Albarellos'),
(20,'Alcorta'),
(20,'Aldao'),
(20,'Alejandra'),
(20,'Álvarez'),
(20,'Ambrosetti'),
(20,'Amenábar'),
(20,'Angélica'),
(20,'Angeloni'),
(20,'Arequito'),
(20,'Arminda'),
(20,'Armstrong'),
(20,'Arocena'),
(20,'Arroyo Aguiar'),
(20,'Arroyo Ceibal'),
(20,'Arroyo Leyes'),
(20,'Arroyo Seco'),
(20,'Arrufó'),
(20,'Arteaga'),
(20,'Ataliva'),
(20,'Aurelia'),
(20,'Avellaneda'),
(20,'Barrancas'),
(20,'Bauer Y Sigel'),
(20,'Bella Italia'),
(20,'Berabevú'),
(20,'Berna'),
(20,'Bernardo de Irigoyen'),
(20,'Bigand'),
(20,'Bombal'),
(20,'Bouquet'),
(20,'Bustinza'),
(20,'Cabal'),
(20,'Cacique Ariacaiquin'),
(20,'Cafferata'),
(20,'Calchaquí'),
(20,'Campo Andino'),
(20,'Campo Piaggio'),
(20,'Cañada de Gómez'),
(20,'Cañada del Ucle'),
(20,'Cañada Rica'),
(20,'Cañada Rosquín'),
(20,'Candioti'),
(20,'Capital'),
(20,'Capitán Bermúdez'),
(20,'Capivara'),
(20,'Carcarañá'),
(20,'Carlos Pellegrini'),
(20,'Carmen'),
(20,'Carmen Del Sauce'),
(20,'Carreras'),
(20,'Carrizales'),
(20,'Casalegno'),
(20,'Casas'),
(20,'Casilda'),
(20,'Castelar'),
(20,'Castellanos'),
(20,'Cayastá'),
(20,'Cayastacito'),
(20,'Centeno'),
(20,'Cepeda'),
(20,'Ceres'),
(20,'Chabás'),
(20,'Chañar Ladeado'),
(20,'Chapuy'),
(20,'Chovet'),
(20,'Christophersen'),
(20,'Classon'),
(20,'Cnel. Arnold'),
(20,'Cnel. Bogado'),
(20,'Cnel. Dominguez'),
(20,'Cnel. Fraga'),
(20,'Col. Aldao'),
(20,'Col. Ana'),
(20,'Col. Belgrano'),
(20,'Col. Bicha'),
(20,'Col. Bigand'),
(20,'Col. Bossi'),
(20,'Col. Cavour'),
(20,'Col. Cello'),
(20,'Col. Dolores'),
(20,'Col. Dos Rosas'),
(20,'Col. Durán'),
(20,'Col. Iturraspe'),
(20,'Col. Margarita'),
(20,'Col. Mascias'),
(20,'Col. Raquel'),
(20,'Col. Rosa'),
(20,'Col. San José'),
(20,'Constanza'),
(20,'Coronda'),
(20,'Correa'),
(20,'Crispi'),
(20,'Cululú'),
(20,'Curupayti'),
(20,'Desvio Arijón'),
(20,'Diaz'),
(20,'Diego de Alvear'),
(20,'Egusquiza'),
(20,'El Arazá'),
(20,'El Rabón'),
(20,'El Sombrerito'),
(20,'El Trébol'),
(20,'Elisa'),
(20,'Elortondo'),
(20,'Emilia'),
(20,'Empalme San Carlos'),
(20,'Empalme Villa Constitucion'),
(20,'Esmeralda'),
(20,'Esperanza'),
(20,'Estación Alvear'),
(20,'Estacion Clucellas'),
(20,'Esteban Rams'),
(20,'Esther'),
(20,'Esustolia'),
(20,'Eusebia'),
(20,'Felicia'),
(20,'Fidela'),
(20,'Fighiera'),
(20,'Firmat'),
(20,'Florencia'),
(20,'Fortín Olmos'),
(20,'Franck'),
(20,'Fray Luis Beltrán'),
(20,'Frontera'),
(20,'Fuentes'),
(20,'Funes'),
(20,'Gaboto'),
(20,'Galisteo'),
(20,'Gálvez'),
(20,'Garabalto'),
(20,'Garibaldi'),
(20,'Gato Colorado'),
(20,'Gdor. Crespo'),
(20,'Gessler'),
(20,'Godoy'),
(20,'Golondrina'),
(20,'Gral. Gelly'),
(20,'Gral. Lagos'),
(20,'Granadero Baigorria'),
(20,'Gregoria Perez De Denis'),
(20,'Grutly'),
(20,'Guadalupe N.'),
(20,'Gödeken'),
(20,'Helvecia'),
(20,'Hersilia'),
(20,'Hipatía'),
(20,'Huanqueros'),
(20,'Hugentobler'),
(20,'Hughes'),
(20,'Humberto 1º'),
(20,'Humboldt'),
(20,'Ibarlucea'),
(20,'Ing. Chanourdie'),
(20,'Intiyaco'),
(20,'Ituzaingó'),
(20,'Jacinto L. Aráuz'),
(20,'Josefina'),
(20,'Juan B. Molina'),
(20,'Juan de Garay'),
(20,'Juncal'),
(20,'La Brava'),
(20,'La Cabral'),
(20,'La Camila'),
(20,'La Chispa'),
(20,'La Clara'),
(20,'La Criolla'),
(20,'La Gallareta'),
(20,'La Lucila'),
(20,'La Pelada'),
(20,'La Penca'),
(20,'La Rubia'),
(20,'La Sarita'),
(20,'La Vanguardia'),
(20,'Labordeboy'),
(20,'Laguna Paiva'),
(20,'Landeta'),
(20,'Lanteri'),
(20,'Larrechea'),
(20,'Las Avispas'),
(20,'Las Bandurrias'),
(20,'Las Garzas'),
(20,'Las Palmeras'),
(20,'Las Parejas'),
(20,'Las Petacas'),
(20,'Las Rosas'),
(20,'Las Toscas'),
(20,'Las Tunas'),
(20,'Lazzarino'),
(20,'Lehmann'),
(20,'Llambi Campbell'),
(20,'Logroño'),
(20,'Loma Alta'),
(20,'López'),
(20,'Los Amores'),
(20,'Los Cardos'),
(20,'Los Laureles'),
(20,'Los Molinos'),
(20,'Los Quirquinchos'),
(20,'Lucio V. Lopez'),
(20,'Luis Palacios'),
(20,'Ma. Juana'),
(20,'Ma. Luisa'),
(20,'Ma. Susana'),
(20,'Ma. Teresa'),
(20,'Maciel'),
(20,'Maggiolo'),
(20,'Malabrigo'),
(20,'Marcelino Escalada'),
(20,'Margarita'),
(20,'Matilde'),
(20,'Mauá'),
(20,'Máximo Paz'),
(20,'Melincué'),
(20,'Miguel Torres'),
(20,'Moisés Ville'),
(20,'Monigotes'),
(20,'Monje'),
(20,'Monte Obscuridad'),
(20,'Monte Vera'),
(20,'Montefiore'),
(20,'Montes de Oca'),
(20,'Murphy'),
(20,'Ñanducita'),
(20,'Naré'),
(20,'Nelson'),
(20,'Nicanor E. Molinas'),
(20,'Nuevo Torino'),
(20,'Oliveros'),
(20,'Palacios'),
(20,'Pavón'),
(20,'Pavón Arriba'),
(20,'Pedro Gómez Cello'),
(20,'Pérez'),
(20,'Peyrano'),
(20,'Piamonte'),
(20,'Pilar'),
(20,'Piñero'),
(20,'Plaza Clucellas'),
(20,'Portugalete'),
(20,'Pozo Borrado'),
(20,'Progreso'),
(20,'Providencia'),
(20,'Pte. Roca'),
(20,'Pueblo Andino'),
(20,'Pueblo Esther'),
(20,'Pueblo Gral. San Martín'),
(20,'Pueblo Irigoyen'),
(20,'Pueblo Marini'),
(20,'Pueblo Muñoz'),
(20,'Pueblo Uranga'),
(20,'Pujato'),
(20,'Pujato N.'),
(20,'Rafaela'),
(20,'Ramayón'),
(20,'Ramona'),
(20,'Reconquista'),
(20,'Recreo'),
(20,'Ricardone'),
(20,'Rivadavia'),
(20,'Roldán'),
(20,'Romang'),
(20,'Rosario'),
(20,'Rueda'),
(20,'Rufino'),
(20,'Sa Pereira'),
(20,'Saguier'),
(20,'Saladero M. Cabal'),
(20,'Salto Grande'),
(20,'San Agustín'),
(20,'San Antonio de Obligado'),
(20,'San Bernardo (N.J.)'),
(20,'San Bernardo (S.J.)'),
(20,'San Carlos Centro'),
(20,'San Carlos N.'),
(20,'San Carlos S.'),
(20,'San Cristóbal'),
(20,'San Eduardo'),
(20,'San Eugenio'),
(20,'San Fabián'),
(20,'San Fco. de Santa Fé'),
(20,'San Genaro'),
(20,'San Genaro N.'),
(20,'San Gregorio'),
(20,'San Guillermo'),
(20,'San Javier'),
(20,'San Jerónimo del Sauce'),
(20,'San Jerónimo N.'),
(20,'San Jerónimo S.'),
(20,'San Jorge'),
(20,'San José de La Esquina'),
(20,'San José del Rincón'),
(20,'San Justo'),
(20,'San Lorenzo'),
(20,'San Mariano'),
(20,'San Martín de Las Escobas'),
(20,'San Martín N.'),
(20,'San Vicente'),
(20,'Sancti Spititu'),
(20,'Sanford'),
(20,'Santo Domingo'),
(20,'Santo Tomé'),
(20,'Santurce'),
(20,'Sargento Cabral'),
(20,'Sarmiento'),
(20,'Sastre'),
(20,'Sauce Viejo'),
(20,'Serodino'),
(20,'Silva'),
(20,'Soldini'),
(20,'Soledad'),
(20,'Soutomayor'),
(20,'Sta. Clara de Buena Vista'),
(20,'Sta. Clara de Saguier'),
(20,'Sta. Isabel'),
(20,'Sta. Margarita'),
(20,'Sta. Maria Centro'),
(20,'Sta. María N.'),
(20,'Sta. Rosa'),
(20,'Sta. Teresa'),
(20,'Suardi'),
(20,'Sunchales'),
(20,'Susana'),
(20,'Tacuarendí'),
(20,'Tacural'),
(20,'Tartagal'),
(20,'Teodelina'),
(20,'Theobald'),
(20,'Timbúes'),
(20,'Toba'),
(20,'Tortugas'),
(20,'Tostado'),
(20,'Totoras'),
(20,'Traill'),
(20,'Venado Tuerto'),
(20,'Vera'),
(20,'Vera y Pintado'),
(20,'Videla'),
(20,'Vila'),
(20,'Villa Amelia'),
(20,'Villa Ana'),
(20,'Villa Cañas'),
(20,'Villa Constitución'),
(20,'Villa Eloísa'),
(20,'Villa Gdor. Gálvez'),
(20,'Villa Guillermina'),
(20,'Villa Minetti'),
(20,'Villa Mugueta'),
(20,'Villa Ocampo'),
(20,'Villa San José'),
(20,'Villa Saralegui'),
(20,'Villa Trinidad'),
(20,'Villada'),
(20,'Virginia'),
(20,'Wheelwright'),
(20,'Zavalla'),
(20,'Zenón Pereira')
GO


INSERT INTO localidad (idProvincia,nombre) VALUES -- SANTIAGO DEL ESTERO
(21, 'Añatuya'),
(21, 'Árraga'),
(21, 'Bandera'),
(21, 'Bandera Bajada'),
(21, 'Beltrán'),
(21, 'Brea Pozo'),
(21, 'Campo Gallo'),
(21, 'Capital'),
(21, 'Chilca Juliana'),
(21, 'Choya'),
(21, 'Clodomira'),
(21, 'Col. Alpina'),
(21, 'Col. Dora'),
(21, 'Col. El Simbolar Robles'),
(21, 'El Bobadal'),
(21, 'El Charco'),
(21, 'El Mojón'),
(21, 'Estación Atamisqui'),
(21, 'Estación Simbolar'),
(21, 'Fernández'),
(21, 'Fortín Inca'),
(21, 'Frías'),
(21, 'Garza'),
(21, 'Gramilla'),
(21, 'Guardia Escolta'),
(21, 'Herrera'),
(21, 'Icaño'),
(21, 'Ing. Forres'),
(21, 'La Banda'),
(21, 'La Cañada'),
(21, 'Laprida'),
(21, 'Lavalle'),
(21, 'Loreto'),
(21, 'Los Juríes'),
(21, 'Los Núñez'),
(21, 'Los Pirpintos'),
(21, 'Los Quiroga'),
(21, 'Los Telares'),
(21, 'Lugones'),
(21, 'Malbrán'),
(21, 'Matara'),
(21, 'Medellín'),
(21, 'Monte Quemado'),
(21, 'Nueva Esperanza'),
(21, 'Nueva Francia'),
(21, 'Palo Negro'),
(21, 'Pampa de Los Guanacos'),
(21, 'Pinto'),
(21, 'Pozo Hondo'),
(21, 'Quimilí'),
(21, 'Real Sayana'),
(21, 'Sachayoj'),
(21, 'San Pedro de Guasayán'),
(21, 'Selva'),
(21, 'Sol de Julio'),
(21, 'Sumampa'),
(21, 'Suncho Corral'),
(21, 'Taboada'),
(21, 'Tapso'),
(21, 'Termas de Rio Hondo'),
(21, 'Tintina'),
(21, 'Tomas Young'),
(21, 'Vilelas'),
(21, 'Villa Atamisqui'),
(21, 'Villa La Punta'),
(21, 'Villa Ojo de Agua'),
(21, 'Villa Río Hondo'),
(21, 'Villa Salavina'),
(21, 'Villa Unión'),
(21, 'Vilmer'),
(21, 'Weisburd')
GO


INSERT INTO localidad (idProvincia,nombre) VALUES -- TIERRA DEL FUEGO
(22,'Río Grande'),
(22,'Tolhuin'),
(22,'Ushuaia')
GO

INSERT INTO localidad (idProvincia,nombre) VALUES -- TUCUMAN
(23, 'Acheral'),
(23, 'Agua Dulce'),
(23, 'Aguilares'),
(23, 'Alderetes'),
(23, 'Alpachiri'),
(23, 'Alto Verde'),
(23, 'Amaicha del Valle'),
(23, 'Amberes'),
(23, 'Ancajuli'),
(23, 'Arcadia'),
(23, 'Atahona'),
(23, 'Banda del Río Sali'),
(23, 'Bella Vista'),
(23, 'Buena Vista'),
(23, 'Burruyacú'),
(23, 'Capitán Cáceres'),
(23, 'Cevil Redondo'),
(23, 'Choromoro'),
(23, 'Ciudacita'),
(23, 'Colalao del Valle'),
(23, 'Colombres'),
(23, 'Concepción'),
(23, 'Delfín Gallo'),
(23, 'El Bracho'),
(23, 'El Cadillal'),
(23, 'El Cercado'),
(23, 'El Chañar'),
(23, 'El Manantial'),
(23, 'El Mojón'),
(23, 'El Mollar'),
(23, 'El Naranjito'),
(23, 'El Naranjo'),
(23, 'El Polear'),
(23, 'El Puestito'),
(23, 'El Sacrificio'),
(23, 'El Timbó'),
(23, 'Escaba'),
(23, 'Esquina'),
(23, 'Estación Aráoz'),
(23, 'Famaillá'),
(23, 'Gastone'),
(23, 'Gdor. Garmendia'),
(23, 'Gdor. Piedrabuena'),
(23, 'Graneros'),
(23, 'Huasa Pampa'),
(23, 'J. B. Alberdi'),
(23, 'La Cocha'),
(23, 'La Esperanza'),
(23, 'La Florida'),
(23, 'La Ramada'),
(23, 'La Trinidad'),
(23, 'Lamadrid'),
(23, 'Las Cejas'),
(23, 'Las Talas'),
(23, 'Las Talitas'),
(23, 'Los Bulacio'),
(23, 'Los Gómez'),
(23, 'Los Nogales'),
(23, 'Los Pereyra'),
(23, 'Los Pérez'),
(23, 'Los Puestos'),
(23, 'Los Ralos'),
(23, 'Los Sarmientos'),
(23, 'Los Sosa'),
(23, 'Lules'),
(23, 'M. García Fernández'),
(23, 'Manuela Pedraza'),
(23, 'Medinas'),
(23, 'Monte Bello'),
(23, 'Monteagudo'),
(23, 'Monteros'),
(23, 'Padre Monti'),
(23, 'Pampa Mayo'),
(23, 'Quilmes'),
(23, 'Raco'),
(23, 'Ranchillos'),
(23, 'Río Chico'),
(23, 'Río Colorado'),
(23, 'Río Seco'),
(23, 'Rumi Punco'),
(23, 'San Andrés'),
(23, 'San Felipe'),
(23, 'San Ignacio'),
(23, 'San Javier'),
(23, 'San José'),
(23, 'San Miguel de 25'),
(23, 'San Pedro'),
(23, 'San Pedro de Colalao'),
(23, 'Santa Rosa de Leales'),
(23, 'Sgto. Moya'),
(23, 'Siete de Abril'),
(23, 'Simoca'),
(23, 'Soldado Maldonado'),
(23, 'Sta. Ana'),
(23, 'Sta. Cruz'),
(23, 'Sta. Lucía'),
(23, 'Taco Ralo'),
(23, 'Tafí del Valle'),
(23, 'Tafí Viejo'),
(23, 'Tapia'),
(23, 'Teniente Berdina'),
(23, 'Trancas'),
(23, 'Villa Belgrano'),
(23, 'Villa Benjamín Araoz'),
(23, 'Villa Chiligasta'),
(23, 'Villa de Leales'),
(23, 'Villa Quinteros'),
(23, 'Yánima'),
(23, 'Yerba Buena'),
(23, 'Yerba Buena (S)')
GO

drop procedure spCadenasServicesConfigs
CREATE PROCEDURE spCadenasServicesConfigs AS
BEGIN
select id, nombreCadena ,tecnologia, url
 from cadenaServiceConfig
END
GO

EXEC  spCadenasServicesConfigs
 select * from cadenaServiceConfig

drop procedure spCategorias
CREATE PROCEDURE spCategorias AS
BEGIN
select id, nombre
 from categoria
END
GO






drop table marca;
CREATE TABLE marca (
	  id BIGINT IDENTITY (1,1)
	, nombre VARCHAR(50) NOT NULL
	, PRIMARY KEY(id)
    , UNIQUE (nombre)
);
GO

drop table producto;

CREATE TABLE producto (
      id BIGINT
	, nombre VARCHAR(100) NOT NULL
    , marca  VARCHAR(100) NOT NULL
    , imagen VARCHAR(256) NULL
    , precio_min decimal (5,2)
    , precio_max decimal (5,2)
    , PRIMARY KEY (ID)
--  , FOREIGN KEY(marca) REFERENCES marca(id)
);

DROP TABLE productotemp;
CREATE TABLE productotemp (
      id BIGINT
	, nombre VARCHAR(100) NOT NULL
    , marca  VARCHAR(100) NOT NULL
    , imagen VARCHAR(256) NULL
    , precio_desde decimal (5,2)
    , precio_hasta decimal (5,2)
--  , FOREIGN KEY(marca) REFERENCES marca(id)
);


INSERT INTO producto  (id,nombre,marca,precio_hasta,precio_desde)
SELECT DISTINCT max (id) as id,nombre,marca,precio_hasta,precio_desde
FROM productotemp
GROUP BY id,nombre,marca,precio_hasta,precio_desde



--var productosarr = arr.map(p=>"("+p.id+",'"+p.nombre+"','"+p.marca+"',"+p.precioMax+","+p.precioMin+")").toString();

INSERT INTO producto (id,nombre,marca,imagen,precio_min,precio_max)
VALUES 
 (40084107,'Huevo de Chocolate Sorpresa Kinder 20 Gr','KINDER',NULL,46.00,55.00)
,(77903518,'Galletitas Obleas Rellena Opera 92 Gr','OPERA',NULL,28.86,40.00)
,(77914217,'Tableta de Chocolate Shot 35 Gr','SHOT',NULL,26.97,35.90)
,(77935274,'Barra de Cereales con Frutilla Cereal Mix 28 Gr','CEREAL MIX',NULL,19.50,33.90)
,(77939500,'Huevo de Chocolate con Sorpresa Lei Kinder 20 Gr','KINDER',NULL,55.00,55.00)
,(77939753,'Chicle Seven Strong 14.7 Gr','TOP LINE',NULL,16.90,31.90)
,(77953124,'Chocolate con Mani Cofler Block 38 Gr','COFLER',NULL,30.44,48.30)
,(77956262,'Sal Fina en Salero Celusal 250 Gr','CELUSAL',NULL,62.90,89.00)
,(77995407,'Barra de Cereales Light con Yogur Frutilla y Fresa Cereal Mix 28 Gr','CEREAL MIX',NULL,19.50,33.90)
,(77995513,'Chocolate con Extra Menta Cofler 30 Gr','COFLER',NULL,25.90,29.90)
,(77995681,'Obleas Ban~adas de Chocolate Rhodesia 22 Gr','RHODESIA',NULL,13.22,22.70)
,(78600010,'Pastillas Tic Tac Menta Caja 16 Gr','TIC TAC',NULL,18.00,19.50)
,(78600027,'Pastillas Tic Tac Naranja Caja 16 Gr','TIC TAC',NULL,18.00,19.50)
,(78602731,'Huevo de Chocolate con Sorpresa Kinder 20 Gr','KINDER',NULL,36.00,55.00)
,(78909434,'Bombones Chocolate Avellana Ferrero Rocher 37.5 Gr','FERRERO ROCHER',NULL,55.00,61.00)
,(80050315,'Tableta de Chocolate Kinder 12.5 Gr','KINDER',NULL,15.00,16.50)
,(37000185109,'Papas Fritas Crema y Cebolla Pringles 40 Gr','PRINGLES',NULL,61.49,65.90)
,(38000185069,'Papas Fritas Barbacoa Pringles 160 Gr','PRINGLES',NULL,105.60,146.00)
,(38000185083,'Papas Fritas Pizza Pringles 124 Gr','PRINGLES',NULL,105.60,146.00)
,(38000185199,'Papas Fritas Sabor Jamon Pringles 124 Gr','PRINGLES',NULL,105.60,146.00)
,(38000201172,'Papa Fritas Original Navidad Pringles 149 Gr','PRINGLES',NULL,80.00,132.99)
,(38000845246,'Papas Fritas Sabor Original Pringles 67 Gr','PRINGLES',NULL,98.99,119.00)
,(38000846748,'Papas Fritas Cebolla Pringles 40 Gr','PRINGLES',NULL,30.00,66.00)
,(40000017325,'Mani con Chocolate M&M 150 Gr','M&M',NULL,149.00,169.00)
,(41789001833,'Sopa Instantanea Queso Maruchan 64 Gr','MARUCHAN',NULL,65.90,85.00)
,(41789001918,'Sopa Instantanea Pollo Maruchan 64 Gr','MARUCHAN',NULL,65.90,76.99)
,(41789002915,'Sopa Instantanea Pollo Maruchan 85 Gr','MARUCHAN',NULL,33.90,41.00)
,(400000003108,'Chocolate M&M 52 Gr','M&M',NULL,51.50,61.00)
,(400000003276,'Mani con Chocolate M&M 49 Gr','M&M',NULL,22.80,55.90)
,(7501059273252,'Cafe en Capsulas Expreso NesCafe Dolce Gusto 16 Un 96 Gr','NESCAFÉ',NULL,382.19,499.90)
,(7501059273283,'Cafe en Capsulas Chococino NesCafe Dolce Gusto 16 Un 270 Gr','NESCAFÉ',NULL,384.90,499.90)
,(7506174500207,'Barrita de Chocolate con Leche Relleno de Caramelo y Mani Snickers 52 Gr','SNICKERS',NULL,41.50,49.80)
,(7613032396350,'Cafe en Capsulas Cortado Expreso Macchiato NesCafe Dolce Gusto 159 Gr','NESCAFÉ',NULL,382.19,500.00)
,(7613033174728,'Cafe en Capsulas Au Lait NesCafe Dolce Gusto 16 Un 160 Gr','NESCAFÉ',NULL,382.19,443.00)
,(7613034235374,'Cafe Suave Doypack Dolca 170 Gr','NESCAFÉ',NULL,119.00,202.00)
,(7613034235404,'Cafe Instantaneo Torrado Dolca NesCafe 170 Gr','NESCAFÉ',NULL,119.00,206.99)
,(7613034412904,'Cereales Integrales con forma de Frutas Trix 230 Gr','TRIX',NULL,128.00,141.74)
,(7613034449993,'Cacao en Polvo Instantaneo Nesquik 360 Gr','NESQUIK',NULL,57.69,99.90)
,(7613034450074,'Cacao en Polvo Instantaneo Nesquik 180 Gr','NESQUIK',NULL,31.70,62.00)
,(7613034453600,'Cacao en Polvo Instantaneo Nesquik 800 Gr','NESQUIK',NULL,197.99,198.33)
,(7613034497062,'Pure de Papas Cremoso Instantaneo Maggi 125 Gr','MAGGI',NULL,45.10,60.00)
,(7613034679215,'Leche en Polvo Modificada La Lechera 800 Gr','LA LECHERA',NULL,179.00,266.58)
,(7613035067837,'Cafe Instantaneo Suave NesCafe Dolca 170 Gr','NESCAFÉ',NULL,139.99,263.90)
,(7613035068377,'Cafe Instantaneo Torrado NesCafe Dolca 100 Gr','NESCAFÉ',NULL,146.00,210.00)
,(7613035068391,'Cafe Instantaneo NesCafe Dolca 170 Gr','NESCAFÉ',NULL,165.19,263.90)
,(7613035068414,'Cafe Instantaneo Torrado Suave NesCafe Dolca 50 Gr','NESCAFÉ',NULL,74.00,96.99)
,(7613035070981,'Cafe Instantaneo Suave NesCafe Dolca 100 Gr','NESCAFÉ',NULL,123.00,180.99)
,(7613035161566,'Cafe Cortado Instantaneo NesCafe Dolca 125 Gr','NESCAFÉ',NULL,70.34,108.00)
,(7613035161580,'Cafe Mokaccino Instantaneo NesCafe Dolca 125 Gr','NESCAFÉ',NULL,56.00,108.00)
,(7613035190467,'Saborizador con Bolsa para Pollo al Limon Maggi 24 Gr','MAGGI',NULL,45.00,60.47)
,(7613035191792,'Saborizador Pollo de Hierba de Campo en Sobre Maggi 24 Gr','MAGGI',NULL,40.00,60.47)
,(7613035375093,'Leche en Polvo Instantanea Soluble Modificada Softpack Svelty 400 Gr','NESTLÉ',NULL,104.00,185.00)
,(7613035504202,'Saborizador para Carne Criolla Maggi 23 Gr','MAGGI',NULL,45.00,60.47)
,(7622210649225,'Galletitas Clasicas Surtidas Terrabusi Variedad 400 Gr','TERRABUSI',NULL,44.90,78.00)
,(7622210649249,'Galletitas Surtidas Chocolate Terrabusi Variedad 300 Gr','TERRABUSI',NULL,31.99,80.00)
,(7622210649263,'Galletitas Surtidas Terrabusi Variedad Dorada 300 Gr','TERRABUSI',NULL,49.90,69.00)
,(7622210782373,'Galletitas de Vainilla Milka 158 Gr','MILKA',NULL,39.99,55.00)
,(7622300335076,'Galletitas Habanitos Dulces Ban~adas Terrabusi 60 Gr','TERRABUSI',NULL,42.90,60.00)
,(7622300398569,'Galletitas Anillos Glaseados Originales Terrabusi 160 Gr','TERRABUSI',NULL,27.69,43.00)
,(7622300724184,'Fideos Foratti Terrabusi 500 Gr','TERRABUSI',NULL,34.39,39.49)
,(7622300724245,'Fideos Tirabuzones Terrabusi 500 Gr','TERRABUSI',NULL,34.39,38.99)
,(7622300724283,'Fideos Ave Maria Terrabusi 500 Gr','TERRABUSI',NULL,34.39,38.99)
,(7622300724542,'Fideos Spaghetti al Huevo Don Felipe 500 Gr','DON FELIPE',NULL,46.90,61.49)
,(7622300739485,'Flan para Preparar Chocolate Royal 80 Gr','ROYAL',NULL,30.90,34.90)
,(7622300742584,'Alfajor de Chocolate Terrabusi 6 Un 300 Gr','TERRABUSI',NULL,87.99,116.00)
,(7622300742645,'Galletitas Vainilla con Relleno de Limon Duquesa 115 Gr','DUQUESA',NULL,25.00,48.00)
,(7622300742676,'Galletitas Chocolate Lincoln 153 Gr','LINCOLN',NULL,22.00,32.90)
,(7622300742713,'Galletitas Sabor Coco Lincoln 162 Gr','LINCOLN',NULL,32.90,44.50)
,(7622300800239,'Chocolate Leche Milka 25 Gr','MILKA',NULL,34.32,38.90)
,(7622300800260,'Chocolate con Leche Leger Milka 45 Gr','MILKA',NULL,61.31,71.50)
,(7622300800277,'Chocolate con Almendras Milka Leger 45 Gr','MILKA',NULL,61.31,71.50)
,(7622300800284,'Chocolate Combinado Milka Leger 45 Gr','MILKA',NULL,61.50,71.50)
,(7622300800291,'Chocolate de Leche Milka 100 Gr','MILKA',NULL,81.59,112.00)
,(7622300813833,'Polvo para Preparar Postre Dulce de Leche Royal Manjares Light 40 Gr','ROYAL',NULL,31.90,35.90)
,(7622300829087,'Galletitas Vainilla Boca de Dama Terrabusi 160 Gr','TERRABUSI',NULL,29.49,33.90)
,(7622300829520,'Galletitas Surtidas Terrabusi Variedad 600 Gr','TERRABUSI',NULL,68.90,87.90)
,(7622300829544,'Galletitas Surtidas Terrabusi Variedad Dorada 300 Gr','TERRABUSI',NULL,49.90,67.50)
,(7622300829568,'Galletitas Surtidas Chocolate Terrabusi Variedad 300 Gr','TERRABUSI',NULL,49.90,67.50)
,(7622300829629,'Galletitas Leche Terrabusi Manon 178 Gr','MANÓN',NULL,27.74,48.00)
,(7622300829643,'Galletitas Vainilla Lincoln Angry Birds 153 Gr','LINCOLN',NULL,25.00,54.00)
,(7622300829728,'Galletitas Chocolate con Relleno de Limon Melba 120 Gr','MELBA',NULL,25.00,48.00)
,(7622300836610,'Galletitas Pepitos Mini 50 Gr','PEPITOS',NULL,16.99,23.90)
,(7622300840259,'Galletitas Clasicas Cerealitas 200 Gr','CEREALITAS',NULL,30.00,64.00)
,(7622300840525,'Galletitas con Salvado Cerealitas 202 Gr','CEREALITAS',NULL,37.19,51.50)
,(7622300841461,'Galletitas Chocolate con Relleno de Vainilla Oreo Mini 50 Gr','OREO',NULL,20.99,32.00)
,(7622300844943,'Chocolate con Leche Milka 67 Gr','MILKA',NULL,49.00,81.50)
,(7622300847210,'Galletitas Clasicas Express 108 Gr','EXPRESS',NULL,14.50,32.00)
,(7622300847234,'Galletitas Clasicas Express Pack 3 Un 324 Gr','EXPRESS',NULL,48.79,65.90)
,(7622300847265,'Galletitas Clasicas Express Pack 5 Un 540 Gr','EXPRESS',NULL,58.90,104.00)
,(7622300847364,'Galletitas Light Express Pack 3 Un 330 Gr','EXPRESS',NULL,60.90,77.00)
,(7622300848934,'Galletitas Chocolate con Relleno de Vainilla Oreo Mini 150 Gr','OREO',NULL,59.10,73.00)
,(7622300856137,'Galletitas Ban~adas en Chocolate Oreo 204 Gr','OREO',NULL,55.00,189.00)
,(7622300856151,'Galletitas con Cacao Relleno Vainilla + Cobertura Chocolate Blanco Oreo 204 Gr','OREO',NULL,55.00,189.00)
,(7622300864934,'Galletitas Chocolate con Relleno de Vainilla Oreo 117 Gr','OREO',NULL,24.90,60.00)
,(7622300864958,'Galletitas Chocolate con Relleno de Vainilla Oreo 351 Gr','OREO',NULL,91.89,116.00)
,(7622300865535,'Gelatina Frutilla Royal 40 Gr','ROYAL',NULL,22.40,32.90)
,(7622300865573,'Gelatina Naranja Royal 40 Gr','ROYAL',NULL,26.99,28.00)
,(7622300865580,'Polvo para Preparar Gelatina Frutilla Royal Light 25 Gr','ROYAL',NULL,27.59,39.60)
,(7622300865610,'Polvo para Preparar Gelatina Frutos Rojos Royal Light 25 Gr','ROYAL',NULL,29.19,37.90)
,(7622300865658,'Gelatina sin Sabor Royal 14 Gr','ROYAL',NULL,22.89,27.00)
,(7622300869878,'Galletitas Chocolate con Relleno de Vainilla Oreo Golden 117 Gr','OREO',NULL,24.90,42.90)
,(7622300869892,'Galletitas Vainilla Relleno Vainilla Oreo Golden Pack 3 Un 351 Gr','OREO',NULL,84.90,119.00)
,(7622300869915,'Galletitas Chocolate con Relleno de Chocolate Oreo 117 Gr','OREO',NULL,24.90,42.90)
,(7622300871499,'Flan para Preparar Chocolate Royal 60 Gr','ROYAL',NULL,25.99,34.90)
,(7622300871550,'Flan para Preparar Vainilla Royal 60 Gr','ROYAL',NULL,19.19,25.90)
,(7622300871611,'Polvo para Mousse Chocolate Royal 65 Gr','ROYAL',NULL,43.99,51.50)
,(7622300871888,'Postre Vainilla Royal 75 Gr','ROYAL',NULL,21.99,30.90)
,(7622300872038,'Polvo para Preparar Postre Chocolate Royal Light 50 Gr','ROYAL',NULL,30.44,36.90)
,(7622300872786,'Galletitas Granola Cerealitas 231 Gr','CEREALITAS',NULL,46.00,75.00)
,(7622300990114,'Chocolate Milka 150 Gr','MILKA',NULL,154.99,189.00)
,(7790040001855,'Galletitas Sabor Miel Melitas 170 Gr','MELITAS',NULL,33.90,45.00)
,(7790040003569,'Galletitas Saladas Tipo Snacks Rex 75 Gr','REX',NULL,28.65,39.00)
,(7790040003606,'Galletitas Kesitas 75 Gr','KESITAS',NULL,28.65,45.00)
,(7790040008175,'Galletitas Obleas Clasicas Opera 220 Gr','OPERA',NULL,55.64,69.90)
,(7790040102989,'Galletitas Vainilla con Relleno de Chocolate Formis Pack 3 Un 324 Gr','FORMIS',NULL,33.50,81.50)
,(7790040109902,'Galletitas Barbacoa Saladix 150 Gr','SALADIX',NULL,48.71,64.50)
,(7790040110748,'Galletitas Arcor Rocklets 118 Gr','ARCOR',NULL,35.58,43.99)
,(7790040111004,'Galletitas Sabor Chocolate Chocolinas 250 Gr','CHOCOLINAS',NULL,34.39,51.90)
,(7790040173200,'Snacks Sabor Queso Kesitas 125 Gr','KESITAS',NULL,50.99,62.00)
,(7790040174801,'Galletitas Sabor Chocolate con Relleno de Vainilla Macucas 123 Gr','MACUCAS',NULL,10.37,41.50)
,(7790040177307,'Galletitas Sabor Vainilla Fortificada Vocacion 170 Gr','VOCACIÓN',NULL,22.00,39.00)
,(7790040177505,'Galletitas Vainilla Clasicas Vocacion Pack 3 Un 465 Gr','VOCACIÓN',NULL,32.99,71.50)
,(7790040178007,'Galletitas Sabor Vainilla Acarameladas Vocacion Pack 3 Un 450 Gr','VOCACIÓN',NULL,33.50,71.50)
,(7790040258013,'Barra de Cereales Rellena con Manzana Cereal Mix 32 Gr','CEREAL MIX',NULL,19.60,36.00)
,(7790040258211,'Barra de Cereal Rellena con Frutilla Cereal Mix 26 Gr','CEREAL MIX',NULL,19.60,33.90)
,(7790040258402,'Galletitas Dulces Sabor Vainilla con Leche y Chocolate Cindor 120 Gr','CINDOR',NULL,33.38,48.00)
,(7790040314207,'Galletitas Sabor Vainilla con Relleno de Frutilla Lia 80 Gr','LIA',NULL,6.99,12.90)
,(7790040333901,'Galletitas Clasicas Mediatarde 110 Gr','MEDIA TARDE',NULL,8.69,11.01)
,(7790040374607,'Snacks Jamon y Queso Saladix 80 Gr','SALADIX',NULL,35.69,47.50)
,(7790040377905,'Galletitas Crackers Criollitas Pack 5 Un 100 Gr','CRIOLLITAS',NULL,46.50,83.00)
,(7790040378506,'Barra de Cereales Tradicional Cereal Mix 6 Un 138 Gr','CEREAL MIX',NULL,88.90,149.00)
,(7790040385702,'Bizcochos Recetas de La Abuela Azucarados Arcor 200 Gr','ARCOR',NULL,22.35,32.00)
,(7790040439009,'Galletitas Vainilla con Relleno de Frutilla Mana 165 Gr','MANÁ',NULL,29.18,43.00)
,(7790040439108,'Galletitas Sabor Limon Arcor Mana 165 Gr','MANÁ',NULL,29.18,36.70)
,(7790040439306,'Galletitas Chocolate con Relleno de Vainilla Mana 165 Gr','MANÁ',NULL,29.18,46.00)
,(7790040455306,'Barra de Cereales Light con Yogur Frutilla Cereal Mix 168 Gr','CEREAL MIX',NULL,88.90,140.00)
,(7790040455719,'Barra de Cereales con Yogur Frutilla Cereal Mix 168 Gr','CEREAL MIX',NULL,88.90,145.00)
,(7790040534308,'Galletitas Chocolate con Relleno de Dulce de Leche Formis 72 Gr','FORMIS',NULL,15.99,30.00)
,(7790040534407,'Galletitas Vainilla con Relleno de Chocolate Sonrisas 72 Gr','FORMIS',NULL,12.50,24.99)
,(7790040534506,'Galletitas Chocolate Formis Bots 108 Gr','FORMIS',NULL,23.61,35.00)
,(7790040534803,'Galletitas Vainilla con Relleno de Chocolate Formis 108 Gr','ARCOR',NULL,25.67,35.00)
,(7790040535602,'Galletitas Vainilla Azucaradas Arcor 80 Gr','ARCOR',NULL,10.91,15.99)
,(7790040536005,'Galletitas Coco Azucaradas Arcor 80 Gr','ARCOR',NULL,10.91,17.40)
,(7790040551602,'Galletitas con Relleno Black Formis 108 Gr','FORMIS',NULL,26.90,29.90)
,(7790040567009,'Galletitas con Mix de Cereal Hogaren~as Arcor Pack 3 Un 528 Gr','ARCOR',NULL,82.90,110.00)
,(7790040569706,'Galletitas 7 Semillas Arcor Hogaren~as 181 Gr','HOGAREÑAS',NULL,25.49,45.00)
,(7790040614000,'Alfajores Triple Bon o Bon 60 Gr','BON O BON',NULL,28.76,42.90)
,(7790040656109,'Galletitas Vainilla con Relleno de Frambuesa Sonrisas Picaras 84 Gr','SONRISAS',NULL,21.90,42.00)
,(7790040667204,'Galletitas Avena y Pasas Cereal Mix 230 Gr','CEREAL MIX',NULL,52.49,70.00)
,(7790040667303,'Galletitas con Semillas y Chips Cereal Mix 230 Gr','CEREAL MIX',NULL,52.49,70.00)
,(7790040669109,'Galletitas con Salvado Arcor Hogaren~as 200 Gr','HOGAREÑAS',NULL,26.99,45.00)
,(7790040677005,'Galletitas Chocolate Tortitas 125 Gr','TORTITAS',NULL,28.19,32.50)
,(7790040711105,'Galletitas Surtido Diversion 400 Gr','ARCOR',NULL,36.89,70.00)
,(7790040713703,'Galletitas con Avena y Pasas de Uva Cereal Mix 180 Gr','CEREAL MIX',NULL,39.68,51.50)
,(7790040719804,'Galletitas Original Rex 125 Gr','REX',NULL,51.02,62.00)
,(7790040720107,'Galletitas Sabor Vainilla Livianas Mana 145 Gr','MANÁ',NULL,27.50,40.00)
,(7790040720206,'Galletitas Vainilla con Leche Liviana Mana 145 Gr','MANÁ',NULL,27.50,30.90)
,(7790040720503,'Galletitas Coco Arcor Mana 145 Gr','MANÁ',NULL,27.50,30.90)
,(7790040720800,'Galletitas Vainilla Mana Pack 3 Un 393 Gr','MANÁ',NULL,50.39,75.90)
,(7790040756809,'Galletitas con Chips de Chocolate Choco Chips 135 Gr','CHOCO CHIPS',NULL,25.89,30.99)
,(7790040872202,'Galletitas Sandwich Traviata Pack 3 Un 303 Gr','TRAVIATA',NULL,36.90,67.00)
,(7790040872400,'Galletitas Sandwich Traviata Pack 5 Un 505 Gr','TRAVIATA',NULL,56.90,86.90)
,(7790040887602,'Galletitas Sandwich Mediatarde Pack 3 321 Gr','MEDIA TARDE',NULL,35.70,64.00)
,(7790040887909,'Galletitas Sandwich Mediatarde Pack 5 Un 535 Gr','MEDIA TARDE',NULL,37.50,92.00)
,(7790040929807,'Galletitas Coco Coquitas 170 Gr','COQUITAS',NULL,31.90,38.80)
,(7790040929906,'Galletitas Chocolate Chocolinas 170 Gr','CHOCOLINAS',NULL,26.20,54.00)
,(7790040930100,'Galletitas Vainilla con Relleno de Limon Mellizas Pack 3 Un 336 Gr','MELLIZAS',NULL,75.89,82.90)
,(7790040930209,'Galletitas Sabor Vainilla con Relleno de Limon Mellizas 112 Gr','MELLIZAS',NULL,27.29,40.00)
,(7790040930308,'Galletitas Vainilla Relleno de Almendra Amor Bagley Pack 3 Un 336 Gr','AMOR',NULL,74.50,82.90)
,(7790040930506,'Galletitas Chocolate con Relleno de Vainilla Bagley Rumba 112 Gr','RUMBA',NULL,26.45,41.00)
,(7790040931305,'Galletitas Frambuesa Sonrisas 354 Gr','SONRISAS',NULL,54.99,82.90)
,(7790040932005,'Galletitas Obleas Opera 220 Gr','OPERA',NULL,62.31,69.90)
,(7790040932609,'Galletitas de Vainilla Sabor Frutilla Merengadas Pack 3 Un 279 Gr','MERENGADAS',NULL,74.50,84.00)
,(7790040932708,'Galletitas de Vainilla Sabor Frutilla Merengadas 93 Gr','MERENGADAS',NULL,23.90,40.00)
,(7790040946101,'Galletitas Crackers Mas Grande Criollitas 169 Gr','CRIOLLITAS',NULL,29.18,40.00)
,(7790040991606,'Tostadas Originales Criollitas 200 Gr','CRIOLLITAS',NULL,51.96,65.00)
,(7790040994904,'Galletitas Vainilla con Caramelo de Vainilla Porten~itas 130 Gr','PORTEÑITAS',NULL,23.90,27.50)
,(7790040996304,'Alfajor Negro B & N 50 Gr','BAGLEY',NULL,24.90,29.60)
,(7790040999404,'Galletitas con Salvado Fibro Active Bagley 214 Gr','BAGLEY',NULL,25.15,25.99)
,(7790040999503,'Galletitas con Salvado Fibro Active Bagley 642 Gr','BAGLEY',NULL,62.90,87.90)
,(7790045000181,'Galletitas con Salvado Naturalmente Granix Pack 3 Un 810 Gr','GRANIX',NULL,113.91,129.00)
,(7790045000587,'Galletitas Avena Almendras y Coco Granix 145 Gr','GRANIX',NULL,42.90,57.50)
,(7790045001188,'Galletitas con Avena y Pasas Frutigran Granix 250 Gr','GRANIX',NULL,45.90,59.00)
,(7790045001195,'Galletitas Salvado Frutigran Granix 250 Gr','GRANIX',NULL,44.09,51.50)
,(7790045001201,'Galletitas con Salvado Frutigran Granix 500 Gr','GRANIX',NULL,75.99,87.90)
,(7790045001379,'Galletitas Sandwich Naturalmente Granix Pack 3 Un 600 Gr','GRANIX',NULL,73.50,105.00)
,(7790045001591,'Copos de Maiz Naturales Granix 400 Gr','GRANIX',NULL,60.36,79.00)
,(7790045823100,'Galletitas con Sesamo Naturalmente Granix 185 Gr','GRANIX',NULL,36.99,42.90)
,(7790045823445,'Galletitas con Semillas de Trigo Partido Naturalmente Granix 185 Gr','GRANIX',NULL,31.38,41.00)
,(7790045823452,'Galletitas Tropical Frutigran Granix 250 Gr','GRANIX',NULL,42.90,62.00)
,(7790045823889,'Galletitas con Semillas de Lino Naturalmente Granix 185 Gr','GRANIX',NULL,36.99,42.90)
,(7790045824893,'Galletitas con Avena Soja y Chips de Chocolate Frutigran Granix 255 Gr','FRUTIGRAN',NULL,44.90,62.00)
,(7790045825395,'Galletitas con Chia y Amaranto Naturalmente Granix 240 Gr','GRANIX',NULL,44.90,61.50)
,(7790045825401,'Galletitas Vainilla con Sesamo Amaranto y Girasol Granix 260 Gr','GRANIX',NULL,44.90,61.50)
,(7790060007967,'Choclo Desgranado Granja Del Sol 300 Gr','GRANJA DEL SOL',NULL,60.00,93.99)
,(7790060008889,'Aceite de Oliva Puro Comestible Cocinero 1 Lt','COCINERO',NULL,200.50,479.00)
,(7790060009329,'Aceite de Oliva Comestible Cocinero 500 Ml','COCINERO',NULL,197.99,259.00)
,(7790060023684,'Aceite de Girasol Cocinero 1.5 Lt','COCINERO',NULL,79.89,98.50)
,(7790060054954,'Yerba Mate con Palo Chamigo 1 Kg','CHAMIGO',NULL,69.99,96.55)
,(7790070012050,'Aceite de Girasol Cocinero 900 Ml','COCINERO',NULL,25.90,65.00)
,(7790070226372,'Aceite de Oliva Extra Virgen Cocinero 1 Lt','COCINERO',NULL,139.80,466.00)
,(7790070228048,'Aderezo para Ensaladas Reducido en Sodio Cocinero Light 500 Ml','COCINERO',NULL,33.89,63.00)
,(7790070228680,'Aceite de Oliva Cocinero Fritolim 120 Gr','COCINERO',NULL,93.96,145.00)
,(7790070317469,'Lucchettinis Espinaca y Queso Lucchetti 500 Gr','LUCCHETTI',NULL,106.99,118.00)
,(7790070317476,'Lasagna Matarazzo 500 Gr','MATARAZZO',NULL,127.52,152.00)
,(7790070317483,'Lasagna Matarazzo 250 Gr','MATARAZZO',NULL,64.35,78.00)
,(7790070317674,'Fideos Tallarines Fortificados Favorita 500 Gr','FAVORITA',NULL,28.49,28.49)
,(7790070317735,'Fideos Coditos Rayados Favorita 500 Gr','FAVORITA',NULL,25.99,28.49)
,(7790070318060,'Fideos Tirabuzones con 3 Vegetales Matarazzo 500 Gr','MATARAZZO',NULL,53.99,60.00)
,(7790070318077,'Fideos Mostacholes 3 Vegetales Matarazzo 500 Gr','MATARAZZO',NULL,56.27,60.00)
,(7790070318114,'Fideos Tallarin Don Vicente 500 Gr','DON VICENTE',NULL,57.00,76.49)
,(7790070318121,'Fideos Cinta Fetuccini Don Vicente 500 Gr','DON VICENTE',NULL,57.00,76.49)
,(7790070318138,'Fideos Cinta Caserito Don Vicente 500 Gr','DON VICENTE',NULL,57.00,76.49)
,(7790070318176,'Fideos Tirabuzones Don Vicente 500 Gr','DON VICENTE',NULL,57.00,76.49)
,(7790070318282,'Fideos Spaghetti Lucchetti 500 Gr','LUCCHETTI',NULL,24.49,44.49)
,(7790070318299,'Fideos Tallarin Lucchetti 500 Gr','LUCCHETTI',NULL,24.49,44.49)
,(7790070318305,'Fideos Bucattini Lucchetti 500 Gr','LUCCHETTI',NULL,33.90,47.49)
,(7790070318329,'Fideos Tirabuzones Lucchetti 500 Gr','LUCCHETTI',NULL,30.00,44.49)
,(7790070318336,'Fideos Mostacholes Lucchetti 500 Gr','LUCCHETTI',NULL,30.00,44.49)
,(7790070318374,'Fideos Ave Maria Lucchetti 500 Gr','LUCCHETTI',NULL,30.00,57.50)
,(7790070318381,'Fideos Letritas Lucchetti 500 Gr','LUCCHETTI',NULL,41.49,46.00)
,(7790070318398,'Fideos Dedalitos Lucchetti 500 Gr','LUCCHETTI',NULL,30.00,51.50)
,(7790070318596,'Fideos Mostacholes Rayado Matarazzo 500 Gr','MATARAZZO',NULL,39.90,61.50)
,(7790070318602,'Fideos Tirabuzones Matarazzo 500 Gr','MATARAZZO',NULL,39.90,61.50)
,(7790070318619,'Fideos Coditos Rayados Matarazzo 500 Gr','MATARAZZO',NULL,39.90,61.50)
,(7790070318633,'Fideos Penne Rigate Matarazzo 500 Gr','MATARAZZO',NULL,45.49,69.99)
,(7790070318640,'Fideos Spaghetti Matarazzo 500 Gr','MATARAZZO',NULL,36.89,61.50)
,(7790070318657,'Fideos Tallarines Matarazzo 500 Gr','MATARAZZO',NULL,39.90,61.50)
,(7790070318671,'Fideos Nido Fettuccine Matarazzo 500 Gr','MATARAZZO',NULL,45.90,70.49)
,(7790070318749,'Fideos Spaghetti con Morron Matarazzo 500 Gr','MATARAZZO',NULL,53.99,60.00)
,(7790070318756,'Fideos Spaghetti con Espinaca Matarazzo 500 Gr','MATARAZZO',NULL,39.90,60.00)
,(7790070318886,'Vitina Clasica Lucchetti 250 Gr','VITINA',NULL,40.49,45.00)
,(7790070320001,'Fideos Cabellos de Angel Matarazzo 500 Gr','MATARAZZO',NULL,45.90,70.49)
,(7790070320032,'Fideos Municiones Matarazzo 500 Gr','MATARAZZO',NULL,45.49,69.99)
,(7790070320292,'Fideos Tallarines Fortificados Favorita 500 Gr','FAVORITA',NULL,28.49,32.00)
,(7790070320308,'Fideos Tirabuzones Fortificados Favorita 500 Gr','FAVORITA',NULL,28.49,32.00)
,(7790070320322,'Fideos Coditos Rayados Fortificados Favorita 500 Gr','FAVORITA',NULL,25.99,28.99)
,(7790070321978,'Mostachol Regio 500 Gr','REGIO',NULL,17.69,21.58)
,(7790070410139,'Polvo Bizcochuelo Vainilla Exquisita 540 Gr','EXQUISITA',NULL,66.41,83.00)
,(7790070410146,'Polvo Bizcochuelo Chocolate Exquisita 540 gr','EXQUISITA',NULL,80.42,97.00)
,(7790070410603,'Arroz Largo Fino en Bolsa Lucchetti 1 Kg','LUCCHETTI',NULL,32.90,45.99)
,(7790070410610,'Arroz Largo Fino 0000 Lucchetti 500 Gr','LUCCHETTI',NULL,20.00,25.29)
,(7790070410627,'Polvo para Preparar Brownies Chocolate Exquisita 425 Gr','EXQUISITA',NULL,85.04,104.00)
,(7790070410719,'Gelatina Sabor Frutilla Light Vitaminas y Zinc Exquisita 25 Gr','EXQUISITA',NULL,29.50,42.00)
,(7790070410726,'Gelatina Cereza Light Vitaminas y Zinc Exquisita 30 Gr','EXQUISITA',NULL,29.50,42.00)
,(7790070410917,'Gelatina Durazno Light Vitaminas y Zinc Exquisita 30 Gr','EXQUISITA',NULL,29.60,42.00)
,(7790070411181,'Postre Chocolate en Polvo Exquisita Placeres Light 65 Gr','EXQUISITA',NULL,27.60,34.10)
,(7790070411198,'Postre Dulce de Leche en Polvo Exquisita Placeres Light 60 Gr','EXQUISITA',NULL,27.60,34.00)
,(7790070411334,'Bizcocho de Arroz Dulce Gallo 50 Gr','GALLO',NULL,26.99,34.20)
,(7790070411341,'Bizcocho de Arroz Salado Gallo 50 Gr','GALLO',NULL,17.50,31.99)
,(7790070411358,'Bizcocho de Arroz Dulce Crocante Gallo Snacks 100 Gr','GALLO SNACKS',NULL,31.90,55.00)
,(7790070411365,'Bizcocho de Arroz Salado Gallo 100 Gr','GALLO',NULL,31.90,58.00)
,(7790070411679,'Helado para Preparar Vainilla Exquisita 52 Gr','EXQUISITA',NULL,29.18,39.00)
,(7790070411686,'Helado Chocolate con Vitaminas Exquisita 55 Gr','EXQUISITA',NULL,29.18,35.00)
,(7790070411693,'Helado Frutilla con Vitaminas Exquisita 52 Gr','EXQUISITA',NULL,29.18,39.00)
,(7790070411716,'Arroz Gallo Oro 1 Kg','GALLO',NULL,52.90,83.90)
,(7790070411723,'Arroz Doble Carolina Gallo 1 Kg','GALLO',NULL,105.99,116.00)
,(7790070411754,'Risotto 4 Quesos Gallo 200 Gr','GALLO',NULL,87.99,97.00)
,(7790070411778,'Risotto a la Espan~ola Gallo 200 Gr','GALLO',NULL,87.99,97.00)
,(7790070411792,'Arroz Carnaroli Gallo 500 Gr','GALLO',NULL,83.99,94.00)
,(7790070411815,'Arroz Parbolizado Lucchetti 1 Kg','LUCCHETTI',NULL,55.99,59.99)
,(7790070411822,'Arroz Parbolizado Lucchetti 500 Gr','LUCCHETTI',NULL,30.99,35.10)
,(7790070411839,'Arroz Parbolizado Gallo 500 Gr','GALLO',NULL,40.52,46.00)
,(7790070411877,'Arroz Largo Fino 00000 Gallo 500 Gr','GALLO',NULL,32.55,39.90)
,(7790070412355,'Flan para Preparar Dulce de Leche Fortificado Exquisita 60 Gr','EXQUISITA',NULL,25.19,34.00)
,(7790070412362,'Polvo para Bizcochuelo Naranja Exquisita 540 Gr','EXQUISITA',NULL,66.41,83.00)
,(7790070412379,'Polvo para Preparar Bizcochuelo Limon Exquisita 540 Gr','EXQUISITA',NULL,66.41,83.00)
,(7790070412386,'Polvo Bizcochuelo Coco Exquisita 580 Gr','EXQUISITA',NULL,66.41,83.00)
,(7790070412447,'Bizcochitos Tortita Negra Gallo Snacks 50 Gr','GALLO SNACKS',NULL,26.99,34.20)
,(7790070412478,'Postre Vainilla con Vitaminas y Zinc en Polvo Exquisita 80 Gr','EXQUISITA',NULL,25.19,34.10)
,(7790070412485,'Postre Chocolate 8 Vitaminas y Zinc en Polvo Exquisita 80 Gr','EXQUISITA',NULL,25.19,34.10)
,(7790070412492,'Postre Dulce de Leche con Vitaminas y Zinc en Polvo Exquisita 80 Gr','EXQUISITA',NULL,25.19,29.99)
,(7790070412515,'Torta con Chips de Chocolate 8 Vitaminas en Polvo Exquisita 540 Gr','EXQUISITA',NULL,74.90,107.00)
,(7790070413185,'Galletitas Oblea de Arroz Gallo Snacks Chocobar 60 Gr','GALLO SNACKS',NULL,52.50,75.00)
,(7790070414182,'Bizcochitos de Arroz Queso Gallo Snacks 100 Gr','GALLO SNACKS',NULL,41.99,55.00)
,(7790070416780,'Polvo para Preparar Gelatina Cereza Vitaminas y Zinc Exquisita 40 Gr','EXQUISITA',NULL,24.87,26.49)
,(7790070506641,'Premezcla para Pizza Lucchetti 500 Gr','LUCCHETTI',NULL,52.00,58.00)
,(7790070506658,'Premezcla para N~oquis de Papa Lucchetti 400 Gr','LUCCHETTI',NULL,69.99,77.00)
,(7790070506665,'Premezcla para Chipa Lucchetti 250 Gr','LUCCHETTI',NULL,83.59,95.00)
,(7790070506924,'Harina de Trigo 0000 Fortificada Blancaflor 1 Kg','BLANCAFLOR',NULL,46.50,58.00)
,(7790070506979,'Yerba Mate Saborizada con Hierbas Serranas Cruz de Malta 500 Gr','CRUZ DE MALTA',NULL,63.19,81.90)
,(7790070507099,'Yerba Mate con Palo Ecopack Cruz de Malta 500 Gr','CRUZ DE MALTA',NULL,51.99,79.00)
,(7790070507105,'Yerba Mate con Palo Ecopack Cruz de Malta 1 Kg','CRUZ DE MALTA',NULL,92.90,161.00)
,(7790070507112,'Yerba Mate con Palo Ecopack Nobleza Gaucha 500 Gr','NOBLEZA GAUCHA',NULL,57.59,70.00)
,(7790070507129,'Yerba Mate con Palo Ecopack Nobleza Gaucha 1 Kg','NOBLEZA GAUCHA',NULL,107.99,136.00)
,(7790070507228,'Harina Leudante Blancaflor 1 Kg','BLANCAFLOR',NULL,43.09,58.00)
,(7790070507235,'Harina de Trigo 000 con Vita Zinc Favorita 1 Kg','FAVORITA',NULL,28.99,34.49)
,(7790070507341,'Jugo de Limon Minerva 500 Ml','MINERVA',NULL,70.86,97.50)
,(7790070507372,'Jugo de Limon Minerva 250 Ml','MINERVA',NULL,37.68,53.00)
,(7790070936479,'Cafe Instantaneo Seleccion Arlistan 170 Gr','ARLISTÁN',NULL,119.00,184.99)
,(7790070936493,'Cafe Seleccion Arlistan 100 Gr','ARLISTÁN',NULL,100.22,139.99)
,(7790070936516,'Cafe Instantaneo Arlistan 50 Gr','ARLISTÁN',NULL,67.99,78.89)
,(7790071070127,'Tostadas Riera Light 200 Gr','RIERA',NULL,48.90,63.00)
,(7790071090118,'Tostadas Clasicas Dulces Riera 200 Gr','RIERA',NULL,42.90,58.00)
,(7790071090309,'Tostadas Clasicas Riera 200 Gr','RIERA',NULL,42.90,44.90)
,(7790071090316,'Tostadas Clasicas Integrales Riera 200 Gr','RIERA',NULL,42.90,51.44)
,(7790072000093,'Sal Light en Paquete Celusal 470 Gr','CELUSAL',NULL,102.00,149.00)
,(7790072001014,'Sal Fina en Paquete Celusal 500 Gr','CELUSAL',NULL,20.29,28.90)
,(7790072001038,'Sal Fina en Salero Celusal 500 Gr','CELUSAL',NULL,70.50,105.00)
,(7790072002080,'Sal Fina en Estuche Celusal 500 Gr','CELUSAL',NULL,26.90,43.30)
,(7790080043839,'Leche en Polvo Entera Caja Sancor 800 Gr','SANCOR',NULL,260.99,309.00)
,(7790080044188,'Leche en Polvo Descremada San Regim 500 Gr','SAN REGIM',NULL,165.19,249.00)
,(7790127000016,'Pickles Mixtos en Vinagre en Frasco Vanoli 200 Gr','VANOLI',NULL,64.49,98.00)
,(7790127000085,'Aceitunas Verdes en Frasco Vanoli 200 Gr','VANOLI',NULL,67.50,107.00)
,(7790127000160,'Ajies en Vinagre en Frasco Vanoli 110 Gr','VANOLI',NULL,71.19,87.50)
,(7790127000269,'Aceitunas Verdes en Salmuera en Doypack Vanoli 180 Gr','VANOLI',NULL,30.00,47.50)
,(7790127000320,'Aceitunas Rellenas Descarozadas Vanoli 140 Gr','VANOLI',NULL,44.50,59.99)
,(7790127000740,'Salsa de Soja Vanoli 190 Cc','VANOLI',NULL,53.96,62.90)
,(7790127000764,'Chimichurri Vanoli 190 Cc','VANOLI',NULL,53.54,61.50)
,(7790130000034,'Vinagre de Alcohol Menoyo 500 Cc','MENOYO',NULL,24.30,34.00)
,(7790130000058,'Vinagre de Alcohol Menoyo 1 Lt','MENOYO',NULL,45.56,51.00)
,(7790139002121,'Aceto Balsamico Favinco 500 Ml','FAVINCO',NULL,83.99,123.00)
,(7790139003258,'Vinagre de Manzana Casalta 500 Ml','CASALTA',NULL,37.90,61.50)
,(7790139003265,'Vinagre de Manzana Clasico Casalta 1 Lt','CASALTA',NULL,56.90,82.90)
,(7790139003289,'Vinagre de Vino Casalta 250 Cc','CASALTA',NULL,24.90,37.00)
,(7790139003296,'Vinagre de Vino Clasico Casalta 500 Ml','CASALTA',NULL,32.50,45.90)
,(7790139003418,'Aceto Balsamico Reduccion Casalta 400 Ml','CASALTA',NULL,174.99,209.00)
,(7790139003425,'Aceto Balsamico Favinco 250 Ml','FAVINCO',NULL,61.49,71.50)
,(7790150006122,'Cafe Molido Torrado Clasico Equilibrado La Virginia 250 Gr','LA VIRGINIA',NULL,62.00,109.00)
,(7790150006153,'Cafe Molido Torrado Clasico Equilibrado La Virginia 500 Gr','LA VIRGINIA',NULL,123.00,178.99)
,(7790150023051,'Pimienta Negra Molida en Sobre Alicante 25 Gr','ALICANTE',NULL,26.49,29.90)
,(7790150025055,'Pimenton en Paquete La Virginia Alicante 25 Gr','ALICANTE',NULL,25.90,29.90)
,(7790150045077,'Condimento para Pizza Alicante 50 Gr','ALICANTE',NULL,47.49,54.50)
,(7790150050040,'Nuez Moscada Entera Alicante 20 Gr','ALICANTE',NULL,52.90,61.50)
,(7790150052457,'Esencia de Vainilla Alicante 100 Cc','ALICANTE',NULL,51.12,62.90)
,(7790150056059,'Perejil Deshidratado Alicante 25 Gr','ALICANTE',NULL,47.50,54.50)
,(7790150067062,'Salsa Blanca Alicante 40 Gr','ALICANTE',NULL,33.50,44.40)
,(7790150068076,'Provenzal Alicante 50 Gr','ALICANTE',NULL,61.50,67.50)
,(7790150080115,'Cafe en Saquitos Mi Cafe La Virginia 20 Un 140 Gr','LA VIRGINIA',NULL,108.00,149.00)
,(7790150080603,'Te en Saquitos Manzanilla La Virginia 25 Un','LA VIRGINIA',NULL,29.90,44.50)
,(7790150098073,'Ajo Triturado en Sobre Alicante 50 Gr','ALICANTE',NULL,32.90,37.90)
,(7790150100356,'Cafe Instantaneo Torrado Clasico Equilibrado La Virginia 170 Gr','LA VIRGINIA',NULL,134.70,209.00)
,(7790150100752,'Cafe Instantaneo Torrado Clasico Equilibrado La Virginia 100 Gr','LA VIRGINIA',NULL,99.74,139.00)
,(7790150101353,'Cafe Instantaneo Torrado Suave La Virginia 170 Gr','LA VIRGINIA',NULL,134.70,209.00)
,(7790150102039,'Cafe Instantaneo Suave Torrado Balanceado La Virginia 100 Gr','LA VIRGINIA',NULL,99.74,139.00)
,(7790150115039,'Cafe en Saquitos Mi Cafe La Virginia 20 Un 140 Gr','LA VIRGINIA',NULL,109.90,138.90)
,(7790150160701,'Cafe Cappuccino Instantaneo Tradicional Doy Pack La Virginia 125 Gr','LA VIRGINIA',NULL,62.67,92.90)
,(7790150160800,'Cafe Cappuccino Instantaneo Mix Tradicional Doypack La Virginia 275 Gr','LA VIRGINIA',NULL,146.99,199.00)
,(7790150160909,'Cafe Cappuccino Instantaneo Light Doy Pack La Virginia 100 Gr','LA VIRGINIA',NULL,66.31,97.50)
,(7790150211625,'La Virginia Molienda Controlada en Saquitos 25 Un','LA VIRGINIA',NULL,19.90,28.90)
,(7790150211700,'La Virginia Molienda Controlada en Saquitos 50 Un','LA VIRGINIA',NULL,35.79,55.85)
,(7790150211779,'Te Molienda Controlada La Virginia 100 Un','LA VIRGINIA',NULL,72.90,115.00)
,(7790150240274,'Te con Limon en Saquitos Ensobrados La Virginia 20 Un','LA VIRGINIA',NULL,44.50,65.90)
,(7790150310137,'Te Boldo en Saquitos La Virginia 25 Un','LA VIRGINIA',NULL,36.89,54.50)
,(7790150321027,'Te en Saquitos Tilo Manzanilla y Cedron Molienda La Virginia 25 Un','LA VIRGINIA',NULL,28.90,54.50)
,(7790150330135,'Te en Saquitos Ensobrados Manzanilla La Virginia 25 Un','LA VIRGINIA',NULL,29.89,44.50)
,(7790150331064,'Te en Saquitos Manzanilla con Anis La Virginia 25 Un','LA VIRGINIA',NULL,39.90,61.50)
,(7790150360200,'Te en Saquitos Mezcla de Hierbas La Virginia 25 Un','LA VIRGINIA',NULL,33.90,51.50)
,(7790150411339,'Ajo Triturado Alicante 50 Gr','ALICANTE',NULL,32.49,44.93)
,(7790150430330,'Bicarbonato de Sodio en Sobre Alicante 50 Gr','ALICANTE',NULL,18.49,24.62)
,(7790150445341,'Chimichurri Deshidratado Naturalidad Intacta Alicante 50 Gr','ALICANTE',NULL,49.99,75.27)
,(7790150460450,'Coco Rallado Alicante 50 Gr','ALICANTE',NULL,32.60,46.92)
,(7790150470305,'Condimento para Arroz Naturalidad Intacta Alicante 25 Gr','ALICANTE',NULL,22.89,29.66)
,(7790150490396,'Condimento para Pizza Naturalidad Intacta Alicante 25 Gr','ALICANTE',NULL,21.00,34.28)
,(7790150490457,'Condimento para Pizza Naturalidad Intacta Alicante 50 Gr','ALICANTE',NULL,43.99,62.99)
,(7790150496046,'Condimento para Tuco y Guiso Naturalidad Intacta Alicante 25 Gr','ALICANTE',NULL,18.49,33.27)
,(7790150497043,'Condimento para Milanesas Naturalidad Intacta Alicante 25 Gr','ALICANTE',NULL,26.49,35.69)
,(7790150535233,'Nuez Moscada Molida Naturalidad Intacta Alicante 25 Gr','ALICANTE',NULL,51.99,72.90)
,(7790150536254,'Nuez Moscada Entera Naturalidad Intacta Alicante 20 Gr','ALICANTE',NULL,52.90,62.36)
,(7790150540152,'Oregano Naturalidad Intacta Alicante 25 Gr','ALICANTE',NULL,34.49,48.50)
,(7790150540350,'Oregano Naturalidad Intacta Alicante 50 Gr','ALICANTE',NULL,58.99,86.61)
,(7790150545256,'Perejil Deshidratado Naturalidad Intacta Alicante 25 Gr','ALICANTE',NULL,39.99,54.50)
,(7790150550540,'Pimienta Blanca Molida Naturalidad Intacta Alicante 25 Gr','ALICANTE',NULL,42.89,56.58)
,(7790150555392,'Pimienta Negra Molida Naturalidad Intacta Alicante 25 Gr','ALICANTE',NULL,24.99,29.90)
,(7790150564806,'Pimenton Seleccionado Extra Dulce Alicante 25 Gr','ALICANTE',NULL,25.90,31.28)
,(7790150565391,'Pimenton Naturalidad Intacta Alicante 50 Gr','ALICANTE',NULL,35.99,55.64)
,(7790150570340,'Provenzal Deshidratado Naturalidad Intacta Alicante 50 Gr','ALICANTE',NULL,44.99,71.28)
,(7790150695159,'Cafe Cappuccino Instantaneo La Virginia 125 Gr','LA VIRGINIA',NULL,82.90,92.90)
,(7790150696262,'Te en Saquitos Tilo con Manzanilla y Cedron La Virginia 25 Un','LA VIRGINIA',NULL,36.90,54.50)
,(7790150696798,'Cafe Molido Torrado Equilibrado La Virginia 250 Gr','LA VIRGINIA',NULL,74.90,109.00)
,(7790150696804,'Cafe Molido Torrado Equilibrado La Virginia 500 Gr','LA VIRGINIA',NULL,135.90,178.99)
,(7790150696828,'Cafe Cappuccino Instantaneo La Virginia 210 Gr','LA VIRGINIA',NULL,84.50,189.00)
,(7790150696873,'Oregano Alicante 25 Gr','ALICANTE',NULL,39.90,45.90)
,(7790150696880,'Pimenton en Paquete Alicante 25 Gr','ALICANTE',NULL,25.90,29.90)
,(7790150830857,'Cacao en Polvo Fortificado Plus Chocolino 180 Gr','CHOCOLINO',NULL,25.00,47.03)
,(7790158021509,'Miel Liquida Pote Aleluya 190 Gr','ALELUYA',NULL,65.99,82.90)
,(7790170009035,'Cafe Molido Clasico Torrado Intenso La Morenita 250 Gr','LA MORENITA',NULL,79.90,95.90)
,(7790170901902,'Cafe Molido Torrado Clasico Equilibrado La Morenita 500 Gr','LA MORENITA',NULL,153.99,209.00)
,(7790170903227,'Cafe en Saquito La Morenita 20 Un','LA MORENITA',NULL,59.89,105.00)
,(7790170904637,'Cafe Instantaneo Torrado Intenso La Morenita 170 Gr','LA MORENITA',NULL,143.10,199.00)
,(7790270197700,'Galletitas Vocacion 465 Gr','VOCACIÓN',NULL,61.50,71.50)
,(7790270336307,'Galletitas Clasicas Mediatarde Pack 3 Un 330 Gr','MEDIA TARDE',NULL,25.00,45.90)
,(7790272000831,'Aceite Rocio Vegetal Girasol en Aerosol Natura 120 Cc','NATURA',NULL,83.15,119.00)
,(7790272001005,'Aceite de Girasol Natura 900 Ml','NATURA',NULL,49.89,62.00)
,(7790272001029,'Aceite de Girasol Natura 1.5 Lt','NATURA',NULL,81.99,99.00)
,(7790272001050,'Aceite de Girasol Natura 500 Ml','NATURA',NULL,36.39,44.40)
,(7790272006178,'Aceite de Oliva Extra Virgen Clasico Comestible Natura 500 Ml','NATURA',NULL,302.99,369.00)
,(7790310003022,'Tostaditas de Harina de Trigo Jamon Twistos 112 Gr','TWISTOS',NULL,47.89,65.00)
,(7790310003626,'Galletitas Pizza Especial Jamon Kes Bun 100 Gr','KES BUN',NULL,29.90,37.00)
,(7790310004326,'Palitos Salados Pehuamar 200 Gr','PEHUAMAR',NULL,61.99,74.00)
,(7790310004340,'Palitos Queso Pehuamar 90 Gr','PEHUAMAR',NULL,28.90,36.00)
,(7790310004357,'Palitos Salados Queso Pehuamar 200 Gr','PEHUAMAR',NULL,55.00,66.99)
,(7790310005507,'Papas Fritas Pehuamar 105 Gr','PEHUAMAR',NULL,30.00,57.50)
,(7790310005873,'Papas Fritas Acanaladas Pehuamar 105 Gr','PEHUAMAR',NULL,30.00,57.50)
,(7790310006870,'Nachos Tostitos en Bolsa Doritos 200 Gr','DORITOS',NULL,79.99,88.00)
,(7790310006993,'Snack Palitos Rueditas Pep 84 Gr','PEP',NULL,30.70,41.00)
,(7790310922316,'Papas Fritas Clasicas Lays 150 Gr','LAYS',NULL,56.00,75.49)
,(7790310981191,'Papas Fritas Clasicas Lays 250 Gr','LAYS',NULL,107.00,113.99)
,(7790310981962,'Papas Fritas Acanaladas Pehuamar 180 Gr','PEHUAMAR',NULL,64.00,76.00)
,(7790360720115,'Picadillo de Carne en Lata Swift 90 Gr','SWIFT',NULL,16.00,33.99)
,(7790360720382,'Pate de Foie Swift Lata 90 Gr','SWIFT',NULL,17.90,42.00)
,(7790360965189,'Pate de Foie con Hongos en Lata Swift 85 Gr','SWIFT',NULL,37.50,61.00)
,(7790360965721,'Pate de Foie a La Pimienta en Lata Swift 85 Gr','SWIFT',NULL,37.50,66.99)
,(7790380005612,'Chocolate sin Azucar Georgalos 70 Gr','GEORGALOS',NULL,78.99,102.90)
,(7790380015949,'Caramelo Tutti Frutti Flynn Paff 16 Un','FLYNN PAFF',NULL,28.76,51.50)
,(7790380016922,'Pasta de Mani en Tableta Nucrem Georgalos 84 Gr','GEORGALOS',NULL,27.92,33.00)
,(7790387000849,'Mate Cocido en Saquitos Taragui 25 Un','TARAGUÍ',NULL,22.99,42.90)
,(7790387000856,'Mate Cocido en Saquitos Taragui 50 Un','TARAGUÍ',NULL,42.90,81.00)
,(7790387001785,'Te Verde Ensobrado Taragui 20 Un','TARAGUÍ',NULL,51.50,64.50)
,(7790387010213,'Te en Hebras Blen Especial Taragui 180 Gr','TARAGUÍ',NULL,93.00,119.00)
,(7790387010305,'Te en Saquitos Filtro Especial Taragui 25 Un','TARAGUÍ',NULL,15.99,42.00)
,(7790387010312,'Te en Saquitos Filtro Especial Taragui 50 Un','TARAGUÍ',NULL,42.90,72.90)
,(7790387010329,'Te en Saquitos Filtro Especial Taragui 100 Un','TARAGUÍ',NULL,49.25,131.00)
,(7790387010589,'Mate Cocido en Saquitos Union 25 Un','UNIÓN',NULL,22.99,42.00)
,(7790387010602,'Mate Cocido en Saquitos Union 100 Un','UNIÓN',NULL,105.00,153.00)
,(7790387013160,'Yerba Mate Bajo Contenido de Polvo Origen Controlado Taragui 500 Gr','TARAGUÍ',NULL,88.90,100.00)
,(7790387013443,'Yerba Mate sin Palo Metalizado Taragui 1 Kg','TARAGUÍ',NULL,182.90,190.99)
,(7790387013450,'Yerba Mate sin Palo Metalizado Taragui 500 Gr','TARAGUÍ',NULL,94.90,113.00)
,(7790387013511,'Yerba Mate Liviana Taragui 1 Kg','TARAGUÍ',NULL,161.90,197.00)
,(7790387013528,'Yerba Mate Liviana Taragui 500 Gr','TARAGUÍ',NULL,85.90,120.00)
,(7790387014167,'Yerba Mate Bajo Contenido de Polvo Metalizado Union 500 Gr','UNIÓN',NULL,57.00,80.90)
,(7790387014310,'Yerba Mate Suave Origen Controlado Union 1 Kg','UNIÓN',NULL,154.00,180.00)
,(7790387014419,'Yerba Mate Cachamai Hierbas Serranas Union 500 Gr','UNIÓN',NULL,90.00,131.00)
,(7790387015508,'Yerba Mate Campo Origen Controlado La Merced 500 Gr','LA MERCED',NULL,103.99,143.00)
,(7790387015515,'Yerba Mate Monte Origen Controlado La Merced 500 Gr','LA MERCED',NULL,103.99,142.00)
,(7790387015522,'Yerba Mate con Palo Barbacua Especial La Merced 500 Gr','LA MERCED',NULL,125.00,142.00)
,(7790387015539,'Yerba Mate Campo Monte La Merced 500 Gr','LA MERCED',NULL,103.99,143.00)
,(7790387113228,'Yerba Mate Saborizada con Naranja de Oriente Taragui 500 Gr','TARAGUÍ',NULL,81.90,113.00)
,(7790387113310,'Yerba Mate con Palo Origen Controlado Taragui 1 Kg','TARAGUÍ',NULL,109.00,152.99)
,(7790387113327,'Yerba Mate con Palo Origen Controlado Taragui 500 Gr','TARAGUÍ',NULL,77.00,99.00)
,(7790387711820,'Te en Saquitos Ensobrados Manzanilla Silvestre Taragui 25 Un','TARAGUÍ',NULL,25.00,64.00)
,(7790387711844,'Te en Saquitos Tilo Silvestre Taragui 25 Un','TARAGUÍ',NULL,44.00,106.00)
,(7790387712018,'Te en Saquitos Ensobrado Mezcla de Hierbas Silvestre Taragui 25 Un','TARAGUÍ',NULL,31.99,68.00)
,(7790407031075,'Chocolate Blanco Familiar Aguila 100 Gr','ÁGUILA',NULL,43.80,94.50)
,(7790407031273,'Chocolate para Taza Semiamargo Aguila 150 Gr','ÁGUILA',NULL,99.74,114.00)
,(7790407031532,'Gotas de Chocolate Semiamargo Aguila 150 Gr','ÁGUILA',NULL,97.00,104.00)
,(7790407031556,'Ban~o Repostero de Chocolate Semiamargo Aguila 150 Gr','ÁGUILA',NULL,71.81,114.00)
,(7790407031648,'Ban~o Repostero de Chocolate con Leche Aguila 150 Gr','ÁGUILA',NULL,71.81,154.00)
,(7790411000531,'Yerba Mate Especial Suave Rosamonte 1 Kg','ROSAMONTE',NULL,96.59,96.63)
,(7790480000371,'Yerba Mate con Palo Trilam La Tranquera 500 Gr','LA TRANQUERA',NULL,43.19,67.90)
,(7790480008254,'Te en Saquitos Green Hills 25 Un','GREEN HILLS',NULL,23.59,41.90)
,(7790480008766,'Mate Cocido en Saquitos La Tranquera 25 Un','LA TRANQUERA',NULL,19.49,27.00)
,(7790480008773,'Mate Cocido en Saquitos La Tranquera 50 Un','LA TRANQUERA',NULL,35.99,59.90)
,(7790480088942,'Mate Cocido en Saquitos La Tranquera 100 Un','LA TRANQUERA',NULL,75.00,139.00)
,(7790500005065,'Duraznos al Natural Silvia 820 Gr','SILVIA',NULL,37.99,49.44)
,(7790500005652,'Duraznos Cubeteados Silvia 820 Gr','SILVIA',NULL,37.99,49.44)
,(7790503198412,'Arroz Integral Dos Hermanos 1 Kg','DOS HERMANOS',NULL,44.09,71.50)
,(7790503198450,'Arroz Largo Fino 00000 en Bolsa Dos Hermanos 1 Kg','DOS HERMANOS',NULL,39.57,65.90)
,(7790503198474,'Arroz Parbolizado Dos Hermanos 1 Kg','DOS HERMANOS',NULL,39.89,77.50)
,(7790524000244,'Mani Salado sin Piel Croppers 120 Gr','CROPPERS',NULL,51.19,74.50)
,(7790550000157,'Cafe Molido Torrado en Bolsa Cabrales 250 Gr','CABRALES',NULL,71.99,119.00)
,(7790550000164,'Cafe Molido Torrado en Bolsa Cabrales 500 Gr','CABRALES',NULL,139.99,219.00)
,(7790550010125,'Cafe en Saquitos Cabrales 20 Un','CABRALES',NULL,89.00,98.50)
,(7790550017391,'Cafe Tostado en Capsulas Deciso Cabrales 10 Un','CABRALES',NULL,259.00,318.00)
,(7790580032807,'Galletitas Oblea Rellena Cofler Block 35 Gr','COFLER',NULL,24.90,27.90)
,(7790580033408,'Chocolate con Leche Cofler Air 55 Gr','COFLER',NULL,62.90,72.00)
,(7790580033507,'Chocolate con Leche con Almendras Cofler 55 Gr','COFLER',NULL,61.50,65.90)
,(7790580060602,'Caramelos Masticables Sugus 150 Gr','SUGUS',NULL,43.19,61.50)
,(7790580101107,'Caramelos Masticable Surtidos Mogul 150 Gr','MOGUL',NULL,38.39,55.90)
,(7790580103248,'Caramelos Cremino Surtidos Arcor 150 Gr','ARCOR',NULL,51.65,62.90)
,(7790580103361,'Chocolate Aireado con Leche Cofler 27 Gr','COFLER',NULL,34.85,45.90)
,(7790580103385,'Chocolate Aireado Mixto Cofler 27 Gr','COFLER',NULL,34.85,39.90)
,(7790580103392,'Chocolate Aireado con Almendras Cofler 27 Gr','COFLER',NULL,34.85,45.90)
,(7790580103422,'Chocolate con Leche Relleno con Chocolate Blanco Cofler Air 55 Gr','COFLER',NULL,62.90,71.50)
,(7790580103446,'Chocolates con Leche Aireado Cofler 100 Gr','COFLER',NULL,98.37,119.00)
,(7790580103477,'Chocolates con Almendras Aireado Cofler 100 Gr','COFLER',NULL,89.13,119.00)
,(7790580103552,'Chocolate con Confites Cofler 100 Gr','COFLER',NULL,101.99,119.00)
,(7790580105013,'Tableta de Chocolate con Leche y Mani Cofler Block 170 Gr','COFLER',NULL,111.00,149.00)
,(7790580106935,'Bombon de Frutas Surtidas Mogul 500 Gr','MOGUL',NULL,119.00,144.05)
,(7790580107192,'Bombon Seleccion Arcor 258 Gr','ARCOR',NULL,162.53,219.00)
,(7790580108489,'Crema Bon o Bon 290 Gr','BON O BON',NULL,104.15,119.00)
,(7790580109882,'Chocolate Amargo Extra Fino Aguila 150 Gr','ÁGUILA',NULL,101.00,159.00)
,(7790580110413,'Mix de Frutos Secos y Semillas Nutritivo Natural Break 27 Gr','ARCOR',NULL,17.40,61.50)
,(7790580110444,'Mix Frutas Secas y Semillas Natural Break Liviano 24 Gr','REXONA',NULL,39.00,61.50)
,(7790580117771,'Bombon Chocolinas Bon O Bon 270 Gr','BON O BON',NULL,141.74,180.00)
,(7790580151201,'Caramelos Butter Toffees Originales 150 Gr','BUTTER TOFFEES',NULL,56.48,68.00)
,(7790580151300,'Caramelos Chocolate Butter Toffees 150 Gr','BUTTER TOFFEES',NULL,56.48,92.00)
,(7790580160807,'Aceite de Maiz Arcor 900 Ml','ARCOR',NULL,99.00,126.00)
,(7790580199913,'Gomitas Ositos Arcor Mogul 30 Gr','MOGUL',NULL,12.90,15.40)
,(7790580200114,'Gomitas Tiburon Mogul 30 Gr','MOGUL',NULL,12.90,14.50)
,(7790580221904,'Pure de Tomate Tetrabrik Arcor 520 Gr','ARCOR',NULL,21.50,30.90)
,(7790580236519,'Polvo Bizcochuelo Marmolado Godet 500 Gr','GODET',NULL,69.98,81.50)
,(7790580259600,'Gomitas Dientes Mogul 150 Gr','MOGUL',NULL,61.50,78.00)
,(7790580327415,'Chocolate Confitado Rocklets 40 Gr','ROCKLETS',NULL,24.90,31.50)
,(7790580327613,'Mani con Chocolate Rocklets 40 Gr','ROCKLETS',NULL,22.90,31.50)
,(7790580346515,'Obleas Rellenas Ban~ada con Chocolate con Leche Bon o Bon 30 Gr','BON O BON',NULL,23.82,30.30)
,(7790580346706,'Obleas Chocolate Bon o Bon Pack 3 Un 90 Gr','BON O BON',NULL,62.90,77.00)
,(7790580387006,'Galletitas Pizza Saladix 30 Gr','SALADIX',NULL,14.70,26.99)
,(7790580387105,'Galletitas Jamon Saladix 30 Gr','SALADIX',NULL,14.70,22.99)
,(7790580401504,'Turron de Mani Arcor 10 Un','ARCOR',NULL,105.00,130.00)
,(7790580402907,'Cacao en Polvo Arcor 360 Gr','ARCOR',NULL,42.51,55.90)
,(7790580405618,'Gomitas Tiburoncito Mogul 80 Gr','MOGUL',NULL,32.99,42.58)
,(7790580405625,'Gomitas Frutales Mogul 80 Gr','MOGUL',NULL,32.99,42.58)
,(7790580436902,'Gomitas Anillos Mogul 250 Gr','ARCOR',NULL,60.79,84.00)
,(7790580439002,'Galletitas Rellenas Mana 165 Gr','MANÁ',NULL,31.90,45.00)
,(7790580439101,'Galletitas Rellenas con Limon Mana 165 Gr','MANÁ',NULL,31.90,35.90)
,(7790580470005,'Caramelos Relleno Miel Arcor 150 Gr','ARCOR',NULL,54.06,77.00)
,(7790580470203,'Caramelos Menta Chocolate Arcor 150 Gr','ARCOR',NULL,43.19,68.00)
,(7790580470500,'Caramelos Rellenos Frutal Arcor 150 Gr','ARCOR',NULL,43.19,68.00)
,(7790580470609,'Caramelos Menta Cristal Arcor 150 Gr','ARCOR',NULL,39.19,68.00)
,(7790580509309,'Mermelada de Ciruela en Frasco Arcor 454 Gr','ARCOR',NULL,46.49,71.50)
,(7790580509408,'Mermelada de Damasco Frasco Arcor 454 Gr','ARCOR',NULL,46.50,66.00)
,(7790580509507,'Mermelada de Durazno Frasco Arcor 454 Gr','ARCOR',NULL,46.50,53.79)
,(7790580509606,'Mermelada de Frutilla Frasco Arcor 454 Gr','ARCOR',NULL,64.90,92.90)
,(7790580509804,'Mermelada de Naranja Frasco Arcor 454 Gr','ARCOR',NULL,46.50,66.00)
,(7790580511302,'Mermelada de Durazno Light en Frasco Arcor 390 Gr','ARCOR',NULL,51.50,81.50)
,(7790580511500,'Mermelada de Ciruela Light Frasco Arcor 390 Gr','ARCOR',NULL,54.90,81.50)
,(7790580511609,'Mermelada de Naranja Light Frasco Arcor 390 Gr','ARCOR',NULL,54.90,81.50)
,(7790580511708,'Mermelada de Frutilla Light Frasco Arcor 390 Gr','ARCOR',NULL,73.50,102.90)
,(7790580547202,'Galletitas Vainilla con Relleno de Chocolate Formis 108 Gr','FORMIS',NULL,26.90,29.90)
,(7790580567903,'Tomate Pelado Perita en Lata Arcor 400 Gr','ARCOR',NULL,24.31,38.50)
,(7790580602314,'Gomitas Eucalipto Mogul 250 Gr','MOGUL',NULL,59.99,82.00)
,(7790580602406,'Gomitas Conitos Frutales Arcor 250 Gr','ARCOR',NULL,59.99,82.00)
,(7790580607210,'Chocolate Leche Arcor 25 Gr','ARCOR',NULL,21.41,24.90)
,(7790580607418,'Chocolate Blanco Arcor 25 Gr','ARCOR',NULL,19.50,23.80)
,(7790580626419,'Polvo Bizcochuelo Vainilla Godet 480 Gr','GODET',NULL,55.53,72.90)
,(7790580660000,'Polenta Instantanea Prestopronta Arcor 500 Gr','ARCOR',NULL,25.00,46.61)
,(7790580697303,'Galletitas Pizza Saladix 100 Gr','SALADIX',NULL,35.69,54.00)
,(7790580716707,'Galletitas Jamon Saladix 100 Gr','SALADIX',NULL,35.69,52.00)
,(7790580716806,'Galletitas Calabresa Saladix 100 Gr','SALADIX',NULL,35.69,54.00)
,(7790580717407,'Galletitas Parmesano Saladix 100 Gr','SALADIX',NULL,35.69,54.00)
,(7790580763305,'Galletitas Vainilla Mana 145 Gr','MANÁ',NULL,27.50,30.90)
,(7790580955809,'Salsa Pomarola Doypack Arcor 340 Gr','ARCOR',NULL,27.90,36.30)
,(7790580956400,'Salsa Portuguesa Doypack Arcor 340 Gr','ARCOR',NULL,23.90,36.30)
,(7790580956509,'Salsa Filetto Doypack Arcor 340 Gr','ARCOR',NULL,23.90,36.30)
,(7790580980009,'Choclo Amarillo en Granos en Lata Arcor 300 Gr','ARCOR',NULL,38.42,58.00)
,(7790580980801,'Choclo Amarillo Cremoso en Lata Arcor 300 Gr','ARCOR',NULL,35.90,47.50)
,(7790580980900,'Porotos Alubia Remojados Arcor 300 Gr','ARCOR',NULL,38.90,51.50)
,(7790580981907,'Garbanzos Secos Remojados Arcor 300 Gr','ARCOR',NULL,28.00,41.99)
,(7790580983307,'Jardinera Arcor 300 Gr','ARCOR',NULL,27.41,28.49)
,(7790673000027,'Vinagre de Vino Favinco 500 Cc','FAVINCO',NULL,28.50,35.90)
,(7790673000119,'Vinagre de Vino Favinco 1 Lt','FAVINCO',NULL,39.50,52.00)
,(7790673000126,'Vinagre de Manzana Favinco 1 Lt','FAVINCO',NULL,41.90,62.90)
,(7790710000102,'Yerba Mate con Hierbas Serranas Cbse 500 Gr','CBSE',NULL,54.99,72.90)
,(7790710000126,'Yerba Mate con Hierbas Serranas Cbse 1 Kg','CBSE',NULL,94.99,130.90)
,(7790710000157,'Yerba Mate Elaborada con Palo Limon Cbse 500 Gr','CBSE',NULL,54.99,83.00)
,(7790710000188,'Yerba Mate Compuesta Manzana Silueta Cbse 500 Gr','CBSE',NULL,54.90,72.90)
,(7790742104205,'Dulce de Leche Colonial La Serenisima 250 Gr','LA SERENÍSIMA',NULL,53.69,63.00)
,(7790742104403,'Dulce de Leche Colonial La Serenisima 1 Kg','LA SERENÍSIMA',NULL,160.00,184.99)
,(7790742106308,'Leche en Polvo Entera Fortificada en Caja La Serenisima 500 Gr','LA SERENÍSIMA',NULL,136.00,189.00)
,(7790742106605,'Leche en Polvo Descremada Fortificada La Serenisima 400 Gr','LA SERENÍSIMA',NULL,136.89,169.00)
,(7790742140609,'Dulce de Leche Colonial con Calcio La Serenisima 400 Gr','LA SERENÍSIMA',NULL,68.90,92.00)
,(7790742144607,'Dulce de Leche Fuente de Calcio La Serenisima 400 Gr','LA SERENÍSIMA',NULL,48.89,79.39)
,(7790742187703,'Dulce de Leche Fuente de Calcio Ser 400 Gr','SER',NULL,89.90,101.00)
,(7790802000010,'Yerba Mate Tradicional Romance 500 Gr','ROMANCE',NULL,57.21,77.90)
,(7790802000027,'Yerba Mate Tradicional Romance 1 Kg','ROMANCE',NULL,82.39,96.81)
,(7790957000538,'Alfajor Blanco Mini Dulce de Leche Jorgito 6 Un 155 Gr','JORGITO',NULL,57.60,96.00)
,(7790957000545,'Alfajor Chocolate Jorgito 6 Un','JORGITO',NULL,57.60,96.00)
,(7791004000044,'Sal Entrefina en Estuche Celusal 1 Kg','CELUSAL',NULL,40.50,57.50)
,(7791004000051,'Sal Entrefina en Paquete Celusal 1 Kg','CELUSAL',NULL,34.50,47.50)
,(7791004000082,'Sal Gruesa en Estuche Celusal 1 Kg','CELUSAL',NULL,34.50,51.50)
,(7791004000099,'Sal Gruesa en Paquete Celusal 1 Kg','CELUSAL',NULL,29.89,44.50)
,(7791004000105,'Sal Gruesa en Salero Celusal 1 Kg','CELUSAL',NULL,45.38,136.00)
,(7791058010662,'Dulce de Leche Familiar Pote Manfrey 400 Gr','MANFREY',NULL,43.39,58.90)
,(7791078022737,'Aritos de Cereales Frutales Granix 130 Gr','GRANIX',NULL,41.57,51.50)
,(7791100000283,'Azucar Blanca Refinada Premium Chango 1 Kg','CHANGO',NULL,33.10,36.40)
,(7791100000481,'Azucar Tipo A Chango 1 Kg','CHANGO',NULL,29.90,38.00)
,(7791118000619,'Malta Instantanea Fortificada Maltife 100 Gr','MALTIFE',NULL,92.89,105.00)
,(7791120001338,'Tostaditas de Arroz Clasicas Molinos Ala 120 Gr','MOLINOS ALA',NULL,25.00,27.50)
,(7791120031557,'Arroz Largo Fino 00000 en Bolsa Molinos Ala 1 Kg','MOLINOS ALA',NULL,39.90,49.49)
,(7791120031564,'Arroz Largo Fino 00000 Molinos Ala 500 Gr','MOLINOS ALA',NULL,22.89,24.90)
,(7791120032554,'Arroz Largo Fino 53 1 Kg','53',NULL,29.90,44.50)
,(7791249008720,'Chocolate Cadbury Dairy Milk 72 Gr','CADBURY',NULL,82.90,91.00)
,(7791249010549,'Postre de Mani en Barra Mantecol 25 Gr','MANTECOL',NULL,11.99,16.50)
,(7791249420867,'Postre de Mani Mantecol 250 Gr','MANTECOL',NULL,72.90,117.99)
,(7791249451472,'Pasta de Mani Mantecol 110 Gr','MANTECOL',NULL,40.60,59.00)
,(7791249451656,'Chocolate con Mani Shot 90 Gr','SHOT',NULL,76.64,90.00)
,(7791476005912,'Barra Crocante de Arroz Egran 70 Gr','EGRAN',NULL,27.60,35.90)
,(7791476005936,'Barra Crocante de Arroz con Chocolate Egran 70 Gr','EGRAN',NULL,27.60,35.90)
,(7791476006216,'Barra de Arroz Inflado Egran 60 Gr','EGRAN',NULL,39.89,51.99)
,(7791476006230,'Barra Crocante de Arroz con Chocolate Egran 60 Gr','EGRAN',NULL,39.89,51.99)
,(7791476006247,'Barra Crocante de Arroz Mani y Miel Egran 60 Gr','EGRAN',NULL,39.89,57.00)
,(7791476039009,'Maiz Blanco Molido Egran 500 Gr','EGRAN',NULL,42.72,54.50)
,(7791476056006,'Porotos Bolitas Egran 500 Gr','EGRAN',NULL,44.50,54.59)
,(7791476057003,'Porotos Alubia Egran 500 Gr','EGRAN',NULL,54.49,61.50)
,(7791476059007,'Porotos de Soja Egran 500 Gr','EGRAN',NULL,27.08,32.90)
,(7791476063004,'Poroto Colorados Egran 500 Gr','EGRAN',NULL,51.49,58.26)
,(7791675304007,'Aceitunas Verdes Rellenas en Doy Pack Castell 180 Gr','CASTELL',NULL,58.99,71.50)
,(7791708001231,'Pan Hamburguesas con Sesamo Veneziana 210 Gr','VANEZIANA',NULL,38.90,45.00)
,(7791708001248,'Pan Hamburguesas Americana Veneziana 210 Gr','VENEZIANA',NULL,32.18,33.19)
,(7791708001323,'Pan Hamburguesas Practipan 210 Gr','PRACTIPAN',NULL,28.99,29.96)
,(7791708001361,'Grisines La Veneziana 180 Gr','LA VENEZIANA',NULL,27.50,35.69)
,(7791708001378,'Pan para Panchos Veneziana 210 Gr','VENEZIANA',NULL,32.18,33.19)
,(7791708001569,'Tostadas Saladas con Gluten Veneziana Light 200 Gr','VENEZIANA',NULL,54.49,66.00)
,(7791708001576,'Tostadas Light sin Sal con Gluten Veneziana 200 Gr','VENEZIANA',NULL,39.60,66.00)
,(7791708001583,'Tostadas con Alto Contenido de Gluten Veneziana Light 200 Gr','VENEZIANA',NULL,54.49,66.00)
,(7791708002023,'Grisines Trisalvado Dietetico La Veneziana 180 Gr','LA VENEZIANA',NULL,23.38,36.00)
,(7791708617432,'Tostadas Dulces Light Veneziana 200 Gr','VENEZIANA',NULL,54.49,64.00)
,(7791866000381,'Ketchup Natura 250 Gr','NATURA',NULL,48.81,58.00)
,(7791866000558,'Mayonesa Liviana Doypack Cada Dia 237 Gr','CADA DÍA',NULL,24.90,27.90)
,(7791866001203,'Mayonesa Doypack Natura 237 Gr','NATURA',NULL,27.90,53.00)
,(7791866001364,'Mayonesa Doypack Natura 500 Ml','NATURA',NULL,51.89,85.90)
,(7791866001579,'Mayonesa con Aceite Oliva Extra Virgen Mayoliva 455 Gr','MAYOLIVA',NULL,31.50,109.00)
,(7791866003283,'Mayonesa con Aceite de Oliva Mayoliva 227 Gr','MAYOLIVA',NULL,25.50,55.90)
,(7791866004211,'Mostaza Doypack Natura 250 Gr','NATURA',NULL,28.60,38.10)
,(7791885000096,'Palmito en Trozos Cumana 396 Gr','CUMANÁ',NULL,62.90,73.00)
,(7791936001225,'Merengue Blanco Lyvian Sferco 100 Gr','LYVIAN',NULL,27.90,31.17)
,(7792070000556,'Aceitunas Verdes Descarozadas en Salmuera Nucete 360 Gr','NUCETE',NULL,75.90,92.00)
,(7792070001072,'Aceitunas Verdes Rellenas Nucete 100 Gr','NUCETE',NULL,27.90,33.99)
,(7792070001089,'Aceitunas Verdes en Salmuera Nucete 330 Gr','NUCETE',NULL,42.90,51.50)
,(7792070001102,'Aceitunas Verdes Descarozadas en Salmuera Nucete 150 Gr','NUCETE',NULL,47.49,58.00)
,(7792070001119,'Aceitunas Verdes en Salmuera Nucete 180 Gr','NUCETE',NULL,34.99,51.50)
,(7792070001126,'Aceitunas Verdes Rellenas con Morron en Salmuera Nucete 300 Gr','NUCETE',NULL,40.99,55.90)
,(7792070001287,'Aceitunas Verdes en Rodajas en Salmuera Nucete 300 Gr','NUCETE',NULL,51.49,55.90)
,(7792070004523,'Aceitunas Verdes en Salmuera Nucete 330 Gr','NUCETE',NULL,71.50,83.00)
,(7792070053088,'Pimientos Morrones Nucete 220 Gr','NUCETE',NULL,71.50,82.90)
,(7792129000346,'Edulcorante Liquido con Sacarina Sucaryl 180 Cc','SUCARYL',NULL,30.69,94.50)
,(7792129000742,'Edulcorante en Sobres Equalsweet 50 Un','EQUALSWEET',NULL,62.89,71.50)
,(7792129000759,'Edulcorante con Aspartamo en Sobres Equalsweet 100 Un','EQUALSWEET',NULL,83.00,119.00)
,(7792129001114,'Edulcorante Liquido con Sacarina Chucker 500 Cc','CHUKER',NULL,63.90,115.00)
,(7792129001299,'Edulcorante en Pastillas con Sacatina Sucaryl 300 Un','SUCARYL',NULL,173.00,229.00)
,(7792170000012,'Aritos de Avena y Miel Fortificados Honey Nuts Quaker 200 Gr','QUAKER',NULL,69.00,73.90)
,(7792170000449,'Galletitas Vainilla con Chips de Chocolate Toddy 210 Gr','TODDY',NULL,44.49,49.00)
,(7792170000647,'Avena Instantanea Fortificada Quaker 400 Gr','QUAKER',NULL,60.90,85.00)
,(7792170000678,'Avena Tradicional Fortificada Quaker 500 Gr','QUAKER',NULL,65.90,88.00)
,(7792170000708,'Avena Instantanea Fortificada Quaker 700 Gr','QUAKER',NULL,92.90,121.00)
,(7792170000975,'Cuadritos de Avena Quaker 300 Gr','QUAKER',NULL,127.90,166.00)
,(7792170001217,'Galletitas Avena Miel Quaker 197 Gr','QUAKER',NULL,24.90,29.90)
,(7792170007196,'Polenta Instantanea Magica Quaker 500 Gr','QUAKER',NULL,29.66,30.55)
,(7792170555123,'Galletas Avena y Pasas Quaker 157 Gr','QUAKER',NULL,39.49,43.00)
,(7792180001528,'Harina de Trigo 000 Can~uelas 1 Kg','CAÑUELAS',NULL,23.29,38.90)
,(7792180001641,'Aceite de Girasol Can~uelas 900 Cc','CAÑUELAS',NULL,49.90,65.00)
,(7792180001665,'Aceite de Girasol Can~uelas 1.5 Lt','CAÑUELAS',NULL,85.50,99.00)
,(7792180004567,'Harina con Levadura Pizza Pureza 1 Kg','PUREZA',NULL,27.99,66.00)
,(7792180004833,'Premezcla para Pizza Clasica Mama Cocina Plus 500 Gr','MAMÁ COCINA',NULL,45.49,61.50)
,(7792180004871,'Harina de Trigo 0000 Ultra Refinada Pureza 1 Kg','PUREZA',NULL,30.00,51.00)
,(7792180004888,'Harina Leudante Ultrarefinada Pureza 1 Kg','PUREZA',NULL,34.64,50.62)
,(7792180004901,'Harina con Levadura para Pan Casero Pureza 1 Kg','PUREZA',NULL,47.99,62.00)
,(7792180006448,'Galletitas Crackers Clasicas Paseo 300 Gr','PASEO',NULL,23.90,62.90)
,(7792180006455,'Mini Crackers Salvado Paseo 300 Gr','PASEO',NULL,61.55,62.90)
,(7792200000128,'Bizcochitos Agridulces 9 de Oro 200 Gr','9 DE ORO',NULL,22.90,39.90)
,(7792200000319,'Bizcochos Azucarados 9 de Oro 210 Gr','9 DE ORO',NULL,22.90,39.90)
,(7792200000791,'Galletitas Sconcitos 9 de Oro 200 Gr','9 DE ORO',NULL,20.50,39.90)
,(7792200020096,'Galletitas con Salvado Paseo 300 Gr','PASEO',NULL,61.55,62.90)
,(7792222041055,'Oblea Ban~ada en Chocolate con Leche Nugaton 27 Gr','NUGATÓN',NULL,25.50,28.90)
,(7792222041062,'Chocolate Blanco Nugaton 27 Gr','NUGATÓN',NULL,25.50,28.90)
,(7792280000346,'Te Rosa en Saquitos Ensobrados Cachamai 20 Un','CACHAMAI',NULL,39.99,51.50)
,(7792280001466,'Yerba Mate Rosa Cachamate 500 Gr','CACHAMATE',NULL,62.90,89.00)
,(7792280002890,'Yerba Mate Mezcla de Hierbas Serranas Cachamate 500 Gr','CACHAMATE',NULL,62.90,89.00)
,(7792360071518,'Galletitas Vainilla Cubierta Chocolate Donuts 78 Gr','BONAFIDE',NULL,49.49,71.50)
,(7792360072447,'Chocolate con Leche Nugaton 27 Gr','NUGATÓN',NULL,23.90,28.90)
,(7792360072485,'Chocolate Nugaton Black 27 Gr','NUGATÓN',NULL,25.50,28.90)
,(7792390620021,'Capellettis Relleno Carne con Vitamina E Giacomo Capelettini 500 Gr','GIACOMO',NULL,110.99,139.00)
,(7792390620229,'Capellettis Relleno Queso Jamon con Vitamina E Giacomo Capelettini 500 Gr','GIACOMO',NULL,110.99,139.00)
,(7792540250450,'Azucar Molida Superior Ledesma 1 Kg','LEDESMA',NULL,33.50,36.30)
,(7792540260138,'Azucar Molida Clasica Ledesma 1 Kg','LEDESMA',NULL,23.27,30.50)
,(7792710000052,'Yerba Mate Premium Amanda 500 Gr','AMANDA',NULL,81.90,91.13)
,(7792710000182,'Yerba Mate sin TACC Amanda 500 Gr','AMANDA',NULL,54.90,80.99)
,(7792860003033,'Caramelos Pico Dulce Tradicion Lheritier 150 Gr','LHERITIER',NULL,52.99,64.50)
,(7792860007963,'Caramelo Masticable Pico Dulce 150 Gr','PICO DULCE',NULL,59.00,71.50)
,(7792878000017,'Azucar Domino 1 Kg','DOMINÓ',NULL,27.70,31.00)
,(7792900000121,'Sal Gruesa en Estuche Dos Anclas 1 Kg','DOS ANCLAS',NULL,29.99,37.90)
,(7792900000145,'Sal Entrefina en Estuche Dos Anclas 1 Kg','DOS ANCLAS',NULL,37.50,61.50)
,(7792900000428,'Sal Fina en Paquete Trilaminado Dos Anclas 500 Gr','DOS ANCLAS',NULL,23.50,32.99)
,(7792900006895,'Sal Entrefina en Paquete Dos Anclas 1 Kg','DOS ANCLAS',NULL,31.28,47.50)
,(7792900006949,'Sal Gruesa en Paquete Dos Anclas 1 Kg','DOS ANCLAS',NULL,28.34,44.50)
,(7792900009025,'Pimienta Blanca Molida Dos Anclas 25 Gr','DOS ANCLAS',NULL,39.99,49.88)
,(7792900009049,'Pimenton Extra Dos Anclas 50 Gr','DOS ANCLAS',NULL,35.69,51.50)
,(7792900009124,'Sal Fina Light en Estuche Dos Anclas 500 Gr','DOS ANCLAS',NULL,68.59,75.90)
,(7792900009162,'Pimienta Negra Molida Dos Anclas 25 Gr','DOS ANCLAS',NULL,38.70,61.50)
,(7792900092683,'Sal Entrefina Parrillera en Estuche Dos Anclas 500 Gr','DOS ANCLAS',NULL,13.50,25.90)
,(7792900092751,'Sal Fina en Paquete Estrella de Mar 500 Gr','ESTRELLA DE MAR',NULL,17.24,26.90)
,(7792900092843,'Chimichurri Deshidratado Dos Anclas 25 Gr','DOS ANCLAS',NULL,20.49,26.90)
,(7792900092898,'Azucar Impalpable Dos Anclas 250 Gr','DOS ANCLAS',NULL,34.64,68.00)
,(7792900809007,'Jugo de Limon en Botella Dos Anclas 500 Cc','DOS ANCLAS',NULL,58.90,92.90)
,(7792900809014,'Jugo de Limon en Botella Dos Anclas 1 Lt','DOS ANCLAS',NULL,94.99,169.00)
,(7793046007210,'Mermelada de Durazno sin Azucar Cormillot 390 Gr','CORMILLOT',NULL,68.90,104.00)
,(7793046007296,'Mermelada de Frutilla sin Azucar Cormillot 390 Gr','CORMILLOT',NULL,84.90,119.00)
,(7793046007753,'Mermelada de Damasco Light Frasco Cormillot 390 Gr','CORMILLOT',NULL,47.90,72.99)
,(7793046008002,'Mermelada de Membrillo Dulcor 500 Gr','DULCOR',NULL,25.00,41.50)
,(7793046008019,'Mermelada de Durazno Pote Dulcor 500 Gr','DULCOR',NULL,25.00,41.50)
,(7793046008026,'Mermelada de Zapallo Dulcor 500 Gr','DULCOR',NULL,25.00,41.50)
,(7793046008057,'Mermelada de Damasco Pote Dulcor 500 Gr','DULCOR',NULL,25.00,41.50)
,(7793046008071,'Mermelada de Ciruela Dulcor 500 Gr','DULCOR',NULL,25.00,41.50)
,(7793046008095,'Mermelada de Frutilla Familiar Dulcor 500 Gr','DULCOR',NULL,34.90,46.99)
,(7793046008613,'Mermelada de Durazno Diet 500 Gr','DULCOR',NULL,33.99,52.90)
,(7793046008651,'Mermelada de Damasco Light Dulcor 420 Gr','DULCOR',NULL,34.90,52.90)
,(7793046008675,'Mermelada de Ciruela Light Dulcor 420 Gr','DULCOR',NULL,33.99,52.90)
,(7793046008699,'Mermelada de Frutilla Light Pote Dulcor 420 Gr','DULCOR',NULL,33.99,52.90)
,(7793360000089,'Tomate Pelado Perita en Lata La Campagnola 400 Gr','LA CAMPAGNOLA',NULL,35.06,42.90)
,(7793360000140,'Pulpa de Tomate Tetrabrik Salsati La Campagnola 520 Gr','LA CAMPAGNOLA',NULL,17.50,35.90)
,(7793360000485,'Pure de Tomate en Tetrabrik La Campagnola 520 Gr','LA CAMPAGNOLA',NULL,25.50,41.00)
,(7793360000645,'Extracto de Tomate Triple La Campagnola 180 Gr','LA CAMPAGNOLA',NULL,44.00,64.50)
,(7793360002038,'Durazno BC La Campagnola 800 Gr','LA CAMPAGNOLA',NULL,81.00,164.00)
,(7793360002151,'Pera en Mitades Comunes La Campagnola 820 Gr','LA CAMPAGNOLA',NULL,90.00,159.00)
,(7793360002175,'Coctel de Fruta BC La Campagnola 800 Gr','LA CAMPAGNOLA',NULL,150.66,191.00)
,(7793360002236,'Pera en Mitades La Campagnola BC 800 Gr','LA CAMPAGNOLA',NULL,137.22,169.00)
,(7793360004452,'Mermelada de Naranja La Campagnola 454 Gr','LA CAMPAGNOLA',NULL,51.90,71.50)
,(7793360004759,'Dulce de Leche Poncho Negro 450 Gr','PONCHO NEGRO',NULL,118.28,153.00)
,(7793360004988,'Mermelada de Arandanos BC La Campagnola 390 Gr','LA CAMPAGNOLA',NULL,105.00,119.00)
,(7793360005060,'Atun en Lomitos en Aceite La Campagnola 170 Gr','LA CAMPAGNOLA',NULL,90.00,151.00)
,(7793360005084,'Atun en Lomitos al Natural La Campagnola 170 Gr','LA CAMPAGNOLA',NULL,90.00,116.00)
,(7793360006142,'Caballa al Natural La Campagnola 300 Gr','LA CAMPAGNOLA',NULL,110.24,139.00)
,(7793360012501,'Mermelada de Casis. Frutilla y Mora BC La Campagnola 390 Gr','BC',NULL,105.00,119.00)
,(7793360674105,'Mayonesa Light Doypack BC La Campagnola 500 Gr','BC',NULL,40.99,61.50)
,(7793360674204,'Mayonesa en Doypack BC La Campagnola Light 250 Gr','BC',NULL,25.50,34.99)
,(7793360730504,'Caballa en Aceite y Agua La Campagnola 300 Gr','LA CAMPAGNOLA',NULL,129.00,139.00)
,(7793360800009,'Mermelada de Durazno Frasco La Campagnola 454 Gr','LA CAMPAGNOLA',NULL,51.90,75.00)
,(7793360800900,'Mermelada de Frutilla La Campagnola 454 Gr','LA CAMPAGNOLA',NULL,69.50,107.00)
,(7793360801709,'Mermelada de Ciruela Frasco La Campagnola 454 Gr','LA CAMPAGNOLA',NULL,45.29,74.99)
,(7793360805707,'Mermelada de Frambuesa Frasco La Campagnola 454 Gr','LA CAMPAGNOLA',NULL,71.50,107.00)
,(7793360811401,'Mermelada de Durazno Diet BC La Campagnola 390 Gr','LA CAMPAGNOLA',NULL,55.49,69.00)
,(7793360812101,'Mermelada de Frutilla BC La Campagnola 390 Gr','LA CAMPAGNOLA',NULL,74.50,119.00)
,(7793360815003,'Mermelada de Ciruela Diet BC La Campagnola 390 Gr','LA CAMPAGNOLA',NULL,55.50,88.00)
,(7793360815201,'Mermelada de Naranja Diet BC La Campagnola 390 Gr','LA CAMPAGNOLA',NULL,55.50,92.90)
,(7793360816604,'Mermelada de Arandano BC La Campagnola 390 Gr','LA CAMPAGNOLA',NULL,79.50,119.00)
,(7793360826306,'Mermelada de Frutos Rojos BC La Campagnola 390 Gr','LA CAMPAGNOLA',NULL,84.00,119.00)
,(7793360835803,'Mermelada de Frambuesa BC La Campagnola 390 Gr','LA CAMPAGNOLA',NULL,80.50,119.00)
,(7793360927508,'Salsa Pomarola Doypack Salsati 340 Gr','SALSATI',NULL,33.59,39.60)
,(7793360927607,'Salsa Portuguesa Doypack Salsati 340 Gr','SALSATI',NULL,29.00,38.90)
,(7793360927805,'Salsa de Tomate Pizza Doypack Salsati 340 Gr','SALSATI',NULL,30.96,39.60)
,(7793360933905,'Salsa de Tomate Ajo Cebolla Doypack Salsati La Campagnola 340 Gr','LA CAMPAGNOLA',NULL,31.99,39.90)
,(7793360982309,'Arvejas Secas Remojadas La Campagnola 300 Gr','LA CAMPAGNOLA',NULL,28.50,31.90)
,(7793360984907,'Choclo Cremoso Amarillo en Lata La Campagnola 300 Gr','LA CAMPAGNOLA',NULL,43.56,51.50)
,(7793360986208,'Jardinera de Verduras La Campagnola 300 Gr','LA CAMPAGNOLA',NULL,42.90,47.50)
,(7793360986307,'Jardinera con Choclo La Campagnola 300 Gr','LA CAMPAGNOLA',NULL,41.50,55.90)
,(7793360987205,'Lentejas Secas Remojadas Lata La Campagnola 320 Gr','LA CAMPAGNOLA',NULL,39.99,57.50)
,(7793433000459,'Sal Condimentada con Hierbas Finas Genser 250 Gr','GENSER',NULL,198.99,229.00)
,(7793433000480,'Sal Fina Modificada 66% Menos de Sodio Genser 90 Gr','GENSER',NULL,97.49,109.00)
,(7793433000497,'Sal Fina Modificada 66% Menos de Sodio Genser 300 Gr','GENSER',NULL,179.00,199.00)
,(7793670000052,'Yerba Mate Compuesta con Hierbas Serranas Verdeflor 500 Gr','VERDEFLOR',NULL,45.89,65.09)
,(7793704000225,'Yerba Mate con Palo Playadito 1 Kg','PLAYADITO',NULL,115.00,160.00)
,(7793704000232,'Yerba Mate con Palo Playadito 500 Gr','PLAYADITO',NULL,62.90,93.00)
,(7793890001020,'Pan para Panchos Fargo 210 Gr','FARGO',NULL,62.99,74.01)
,(7793890001044,'Pan Hamburguesas con Sesamo Fargo 210 Gr','FARGO',NULL,48.99,74.01)
,(7793890001150,'Pan para Panchos Lactal 210 Gr','LACTAL',NULL,49.99,63.93)
,(7794000000568,'Mayonesa Doypack Rik 242 Gr','RI-K',NULL,23.00,33.90)
,(7794000000575,'Mayonesa Doypack Rik 485 Gr','RI-K',NULL,52.50,64.50)
,(7794000551725,'Caldo Para Saborizar Carne Knorr 30 Gr','KNORR',NULL,31.90,38.90)
,(7794000554726,'Caldo Para Saborizar Verduras Knorr 30 Gr','KNORR',NULL,31.90,40.70)
,(7794000594159,'Caldo de Verduras en Sobre Knorr Light 5 Un','KNORR',NULL,31.90,36.99)
,(7794000595507,'Saborizador para Pollo con Limon y Oregano Knorr 25 Gr','KNORR',NULL,30.00,58.00)
,(7794000595514,'Saborizador para Carne Cebolla y Ajo Knorr 25 Gr','KNORR',NULL,30.00,52.90)
,(7794000595910,'Saborizador para Pollo Tipo Criollo Knorr 25 Gr','KNORR',NULL,30.00,58.00)
,(7794000595927,'Saborizador para Carne Romero y Tomillo Knorr 23 Gr','KNORR',NULL,30.00,52.90)
,(7794000596672,'Sopa Verduras de Primavera Knorr Quick 5 Un','KNORR QUICK',NULL,81.50,92.90)
,(7794000597136,'Saborizador para Pollo con Hierbas y Aji Knorr 23 Gr','KNORR',NULL,30.00,52.90)
,(7794000597235,'Salsa 4 Quesos Deshidratada Knorr 35 Gr','KNORR',NULL,51.99,61.50)
,(7794000597303,'Fideos con Salsa Verdeo Knorr 211 Gr','KNORR',NULL,125.99,159.00)
,(7794000597310,'Arroz Primavera Knorr Listo 197 Gr','KNORR',NULL,79.00,139.00)
,(7794000597372,'Fideos con Mix Vegetales con Queso Knorr 197 Gr','KNORR',NULL,122.99,159.00)
,(7794000597471,'Sopa Crema de Pollo y Verdeo Knorr Quick 5 Un 75 Gr','KNORR',NULL,64.00,97.49)
,(7794000597518,'Caldo de Verduras con Recetas Knorr 12 Un','KNORR',NULL,42.99,62.25)
,(7794000597525,'Caldo de Gallina con Recetas Knorr 12 Un','KNORR',NULL,48.90,62.25)
,(7794000597532,'Caldo de Carne con Recetas Knorr 12 Un','KNORR',NULL,48.90,62.99)
,(7794000597648,'Caldo de Gallina Knorr 6 Un','KNORR',NULL,25.00,42.50)
,(7794000597723,'Caldo de Verduras Wilde 12 Un','WILDE',NULL,37.50,45.90)
,(7794000597730,'Caldo de Gallina Wilde 12 Un','WILDE',NULL,37.50,45.90)
,(7794000598263,'Sopa Crema de Esparragos Knorr 63 Gr','KNORR',NULL,34.00,58.00)
,(7794000598294,'Sopa Crema de Pollo Knorr 64 Gr','KNORR',NULL,46.90,52.99)
,(7794000598317,'Sopa Crema de Arvejas con Jamon Knorr 64 Gr','KNORR',NULL,34.00,52.99)
,(7794000598355,'Sopa Crema de Zapallo Knorr 70 Gr','KNORR',NULL,34.00,58.00)
,(7794000598379,'Sopa Crema de Choclo Knorr 67 Gr','KNORR',NULL,34.00,54.00)
,(7794000598393,'Sopa Crema de Verdura Verduras Knorr 60 Gr','KNORR',NULL,34.00,58.00)
,(7794000598478,'Sopa de Choclo Knorr Quick Light 5 Un','KNORR QUICK',NULL,81.99,92.90)
,(7794000598485,'Sopa de Arveja Light Knorr Quick 5 Un','KNORR QUICK',NULL,78.00,92.90)
,(7794000598492,'Sopa de Pollo con Vegetales Knorr Quick Light 5 Un','KNORR QUICK',NULL,63.90,91.00)
,(7794000598508,'Sopa de Zapallo Knorr Quick 5 Un','KNORR QUICK',NULL,75.19,92.90)
,(7794000598515,'Sopa de Zapallo Knorr Quick Light 5 Un','KNORR QUICK',NULL,63.90,91.00)
,(7794000598539,'Sopa de Arvejas Knorr Quick 5 Un','KNORR QUICK',NULL,81.50,92.90)
,(7794000598553,'Sopa de Choclo Knorr Quick 5 Un','KNORR QUICK',NULL,75.19,92.90)
,(7794000598560,'Sopa de Esparragos Light Knorr Quick 5 Un','KNORR QUICK',NULL,82.89,92.90)
,(7794000598584,'Sopa de Pollo Knorr Quick 5 Un','KNORR QUICK',NULL,46.00,90.00)
,(7794000598591,'Sopa de Vegetales Light Knorr Quick 5 Un','KNORR QUICK',NULL,75.19,92.90)
,(7794000598751,'Pure de Papas Instantaneo Knorr 200 Gr','KNORR',NULL,81.00,96.90)
,(7794000598805,'Pure de Papas Instantaneo Knorr 125 Gr','KNORR',NULL,37.90,64.56)
,(7794000598829,'Salsa Pomarola Tradicional Knorr 340 Gr','KNORR',NULL,34.89,42.30)
,(7794000598836,'Salsa Bolognesa Knorr 340 Gr','KNORR',NULL,35.49,42.30)
,(7794000598843,'Salsa Filetto Knorr 340 Gr','KNORR',NULL,34.90,42.30)
,(7794000598874,'Salsa de Pizza Knorr 340 Gr','KNORR',NULL,34.90,42.30)
,(7794000598881,'Salsa de Pizza Knorr 200 Gr','KNORR',NULL,23.79,29.28)
,(7794000598898,'Salsa Portuguesa Doypack Knorr 340 Gr','KNORR',NULL,35.49,46.19)
,(7794000598911,'Salsa Pomarola Light Knorr 340 Gr','KNORR',NULL,35.49,45.90)
,(7794000599000,'Saborizador en Cubos Crema de Verdeo Knorr 4 Un 38 Gr','KNORR',NULL,36.74,44.50)
,(7794000599048,'Saborizador en Cubos Albahaca y Ajo Knorr 4 Un 38 Gr','KNORR',NULL,36.74,44.50)
,(7794000599062,'Saborizador en Cubos Azafran y Tomillo Knorr 4 Un 38 Gr','KNORR',NULL,36.99,44.50)
,(7794000599123,'Saborizador de Tomate Cebolla y Morron Knorr 4 Un 30 Gr','KNORR',NULL,36.74,44.50)
,(7794000599154,'Saborizador Provenzal Knorr 30 Gr','KNORR',NULL,36.74,44.50)
,(7794000599185,'Almidon de Maiz Maizena 520 Gr','MAIZENA',NULL,86.99,105.00)
,(7794000958548,'Ketchup Fanacoa 250 Gr','FANACOA',NULL,32.90,35.89)
,(7794000958555,'Mostaza Doypack Fanacoa 250 Gr','FANACOA',NULL,25.61,31.90)
,(7794000959293,'Salsa Barbacoa Hellmanns 400 Gr','HELLMANNS',NULL,91.00,130.00)
,(7794000959712,'Mayonesa Suave con Limon Doypack Hellmanns 242 Gr','HELLMANNS',NULL,24.59,44.50)
,(7794000959729,'Mayonesa Suave con Limon Doypack Hellmanns 485 Gr','HELLMANNS',NULL,60.00,76.95)
,(7794000959866,'Mayonesa Doypack Fanacoa 237 Gr','FANACOA',NULL,20.90,30.90)
,(7794000959897,'Mayonesa Doypack Fanacoa 950 Gr','FANACOA',NULL,64.90,97.50)
,(7794000959903,'Mayonesa Doypack Fanacoa 475 Gr','FANACOA',NULL,30.00,54.50)
,(7794000959927,'Salsa Golf sin TACC Fanacoa 250 Gr','FANACOA',NULL,25.00,41.50)
,(7794000960022,'Mayonesa Clasica Doypack Hellmanns 118 Gr','HELLMANNS',NULL,21.30,26.90)
,(7794000960077,'Mayonesa Clasica Doypack Hellmanns 950 Gr','HELLMANNS',NULL,99.90,144.89)
,(7794000960084,'Mayonesa en Frasco Hellmanns 475 Gr','HELLMANNS',NULL,94.90,109.00)
,(7794000960091,'Mayonesa Doypack Hellmanns 475 Gr','HELLMANNS',NULL,49.90,76.00)
,(7794000960107,'Mayonesa Doypack Hellmanns 237 Gr','HELLMANNS',NULL,25.19,39.57)
,(7794000960138,'Mayonesa Light Doypack Hellmanns 475 Gr','HELLMANNS',NULL,72.50,102.90)
,(7794000960145,'Mayonesa Light Doypack Hellmanns 237 Gr','HELLMANNS',NULL,44.61,52.90)
,(7794000960152,'Mayonesa Light Doypack Hellmanns 950 Gr','HELLMANNS',NULL,149.00,189.00)
,(7794000960206,'Mayonesa con Aceite Oliva Doypack Hellmanns 475 Gr','HELLMANNS',NULL,83.99,102.90)
,(7794000960244,'Ketchup con Tomate Hellmanns 500 Gr','HELLMANNS',NULL,59.39,119.00)
,(7794000960251,'Ketchup con Tomate Hellmanns 400 Gr','HELLMANNS',NULL,65.69,129.00)
,(7794000960275,'Ketchup con Tomate Hellmanns 250 Gr','HELLMANNS',NULL,49.08,54.00)
,(7794000960435,'Mostaza Original Doypack Savora 250 Gr','SAVORA',NULL,25.00,40.94)
,(7794000960442,'Mostaza Original Doypack Savora 500 Gr','SAVORA',NULL,68.90,92.90)
,(7794000960466,'Mostaza Original Savora 200 Gr','SAVORA',NULL,31.69,66.00)
,(7794000960480,'Mostaza con Miel Savora 220 Gr','SAVORA',NULL,85.90,119.00)
,(7794520431125,'Papas Fritas Corte Americano KrachItos 120 Gr','KRACHITOS',NULL,51.50,58.00)
,(7794520442657,'Papas Fritas Corte Americano Krachitos 65 Gr','KRACHITOS',NULL,32.90,44.00)
,(7794520443654,'Papas Fritas Pay Krachitos 65 Gr','KRACHITOS',NULL,32.90,41.00)
,(7794600002337,'Fideos Cintas Nido Fetucchine Don Felipe 500 Gr','DON FELIPE',NULL,46.90,92.00)
,(7794612065719,'Chocolate de Almendras con Mani Hamlet 45 Gr','HAMLET',NULL,19.50,21.93)
,(7794612066310,'Chocolate Blanco Hamlet 45 Gr','HAMLET',NULL,19.50,21.93)
,(7794906000020,'Premezcla para Pizza en Caja Kapac 500 Gr','KAPAC',NULL,65.90,71.50)
,(7794910056556,'Fideos Tirabuzones Canale 500 Gr','CANALE',NULL,21.51,24.20)
,(7794910056600,'Fideos Tallarin Canale 500 Gr','CANALE',NULL,21.51,27.00)
,(7794940000109,'Edulcorante en Sobres Hileret Clasico 50 Un','HILERET',NULL,43.04,62.90)
,(7794940000208,'Edulcorante en Sobres Hileret Sweet 100 Un','HILERET',NULL,76.90,129.00)
,(7794940000536,'Azucar Light Doypack Hileret 500 Gr','HILERET',NULL,43.29,82.90)
,(7794940000796,'Edulcorante Liquido Hileret Zucra 200 Cc','HILERET',NULL,103.41,159.00)
,(7794940000819,'Edulcorante Liquido Hileret Clasico 250 Cc','HILERET',NULL,47.13,71.50)
,(7794940000826,'Edulcorante Liquido Hileret Clasico 500 Cc','HILERET',NULL,71.39,119.00)
,(7794940000840,'Edulcorante Liquido Hileret Sweet 400 Cc','HILERET',NULL,83.50,139.00)
,(7794940000857,'Edulcorante Liquido Hileret Zucra 200 Cc','HILERET',NULL,57.00,92.90)
,(7794980901268,'Aji Molido Yuspe 50 Gr','YUSPE',NULL,26.89,30.96)
,(7794980913407,'Pimenton Extra Yuspe 25 Gr','YUSPE',NULL,13.49,18.49)
,(7794980913438,'Pimenton Extra Yuspe 50 Gr','YUSPE',NULL,25.89,30.86)
,(7794980931272,'Oregano en Hojas Yuspe 50 Gr','YUSPE',NULL,41.49,60.99)
,(7794980938240,'Condimento para Arroz Yuspe 25 Gr','YUSPE',NULL,15.99,21.62)
,(7794980938738,'Chimichurri Yuspe 50 Gr','YUSPE',NULL,36.89,44.30)
,(7795184006001,'Mermelada de Durazno Light Frasco Noel 390 Gr','NOEL',NULL,44.00,51.50)
,(7795735000328,'Bizcochitos con Grasa Salados Don Satur 200 Gr','DON SATUR',NULL,18.49,35.00)
,(7795735000342,'Bizcocho Negrito Don Satur 200 Gr','DON SATUR',NULL,24.49,25.00)
,(7795933204023,'Champignones Enteros Bahia 400 Gr','BAHÍA',NULL,88.50,107.40)
,(7796373001135,'Salsa Chocolate La Parmesana 300 Gr','LA PARMESANA',NULL,85.25,119.00)
,(7796373002293,'Aji Molido en Bolsa La Parmesana 50 Gr','LA PARMESANA',NULL,28.64,28.64)
,(7796373002361,'Nuez Moscada Molida La Parmesana 25 Gr','LA PARMESANA',NULL,57.99,67.50)
,(7796373002446,'Ajo en Polvo en Bolsa La Parmesana 25 Gr','LA PARMESANA',NULL,20.78,32.00)
,(7796373002453,'Perejil en Bolsa La Parmesana 25 Gr','LA PARMESANA',NULL,37.89,42.90)
,(7796373002682,'Chimichurri Tradicional en Frasco La Parmesana 300 Gr','LA PARMESANA',NULL,62.39,69.90)
,(7796373113005,'Albahaca en Bolsa La Parmesana 20 Gr','LA PARMESANA',NULL,22.99,27.90)
,(7796989007729,'Pan para Panchos Bimbo 210 Gr','BIMBO',NULL,48.99,74.01)
,(7796989008740,'Pan Hamburguesas Artesanal Bimbo 240 Gr','BIMBO',NULL,73.50,81.00)
,(7797330102384,'Galletas de Arroz Dulces Multicereal Grandiet 100 Gr','GRANDIET',NULL,36.90,37.90)
,(7797394001104,'Huevo de Chocolate con Sorpresa Lei Kinder 20 Gr','KINDER',NULL,55.00,55.00)
,(7797470000588,'Pure de Tomate Marolio 520 Gr','MAROLIO',NULL,17.89,20.00)
,(7797470007426,'Arvejas Secas Remojadas Marolio 200 Gr','MAROLIO',NULL,13.50,23.49)
,(7798031154177,'Fideos Foratti Knorr 500 Gr','KNORR',NULL,17.50,54.50)
,(7798031470024,'Fideos Fusilli de Maiz y Arroz sin TACC Wakas 250 Gr','WAKAS',NULL,75.38,82.90)
,(7798031471113,'Pochoclo Manteca Marloms 100 Gr','MARLOMS',NULL,42.51,51.50)
,(7798031471120,'Pochoclo Sweet Marloms 100 Gr','MARLOMS',NULL,26.50,53.00)
,(7798061190312,'Aceite de Oliva Extra Virgen Oliovita 500 Ml','OLIOVITA',NULL,263.99,319.00)
,(7798082740107,'Galletitas Coco Anillos Tia Maruca 250 Gr','TÍA MARUCA',NULL,25.00,54.50)
,(7798082740176,'Galletitas Palmeritas Tia Maruca 200 Gr','TÍA MARUCA',NULL,25.92,61.50)
,(7798082740237,'Tostadas Bay Biscuit Tia Maruca 140 Gr','TÍA MARUCA',NULL,30.44,61.50)
,(7798082741098,'Fajitas Clasicas Tia Maruca 150 Gr','TÍA MARUCA',NULL,26.62,52.00)
,(7798082742224,'Fajitas Queso Tia Maruca 150 Gr','TÍA MARUCA',NULL,27.46,52.00)
,(7798082743726,'Tostadas de Arroz Clasicas sin TACC Tia Maruca 120 Gr','TÍA MARUCA',NULL,21.20,55.00)
,(7798094170114,'Fideos de Arroz sin TACC Soyarroz 300 Gr','SOYARROZ',NULL,102.90,105.00)
,(7798113151278,'Galletitas Sabor Vainilla con Relleno de Vainilla Dale 81 Gr','DALE MARÍA',NULL,10.28,12.50)
,(7798113151308,'Galletitas de Agua Argentitas Pack 3 Un 306 Gr','ARGENTITAS',NULL,24.90,42.90)
,(7798113151377,'Galletitas Chocolate Rellena de Vainilla Toddy 115 Gr','TODDY',NULL,19.00,30.00)
,(7798128410063,'Galletitas Sabor Queso Aglu 150 Gr','AGLU',NULL,61.50,61.50)
,(7798128410070,'Galletitas Sabor Limon Aglu 150 Gr','AGLU',NULL,51.50,52.90)
,(7798128410209,'Harina de Arroz Integral Abucel 500 Gr','ABUCEL',NULL,44.50,44.50)
,(7798138552012,'Arvejas Secas Remojadas Molto 350 Gr','MOLTO',NULL,16.50,22.90)
,(7798153810494,'Cacao en Polvo con Vitamina Toddy 180 Gr','TODDY',NULL,31.89,32.87)
,(7798171730026,'Chips de Batata Nuestros Sabores Yukitas 80 Gr','YUKITAS',NULL,36.90,37.90)
,(7798191490146,'Harina de Arroz Integral Abucel 500 Gr','ABUCEL',NULL,44.50,44.50)
,(7798199770011,'Galletitas de Arroz Clasica Crisppino 90 Gr','SIN MARCA',NULL,29.90,32.90)
,(7798199770042,'Galletitas de Arroz Sabor Queso sin TACC Crisppino Minis 50 Gr','CRISPPINO',NULL,23.90,30.20)
,(7802000009266,'Papas Fritas Original Lays Stax 140 Gr','LAYS',NULL,124.90,149.00)
,(7802000009303,'Papas Fritas Cheddar Lays Maxx 154 Gr','LAYS',NULL,124.90,136.00)
,(7861002900117,'Bombon Ferrero Rocher 100 Gr','FERRERO ROCHER',NULL,175.00,194.00)
,(7891000105412,'Caldo de Verduras Maggi 12 Un','MAGGI',NULL,34.00,45.00)
,(7891000105436,'Caldo de Carne Maggi 12 Un','MAGGI',NULL,39.90,45.00)
,(7891000105450,'Caldo de Gallina Maggi 12 Un','MAGGI',NULL,34.00,45.00)
,(7891000108796,'Cafe Instantaneo Clasico NesCafe 100 Gr','NESCAFÉ',NULL,214.19,241.99)
,(7891000108819,'Cafe Instantaneo Clasico NesCafe 170 Gr','NESCAFÉ',NULL,245.00,399.00)
,(7891008118353,'Bombones Surtido Garoto 300 Gr','GAROTO',NULL,111.00,277.00)
,(7891167011724,'Atun en Trozos al Natural Gomes Da Costa 120 Gr','GOMES DA COSTA',NULL,79.99,129.00)
,(7891167011755,'Atun en Lomitos al Natural Gomes Da Costa 170 Gr','GOMES DA COSTA',NULL,86.90,121.00)
,(7891167011779,'Atun Desmenuzado al Natural Gomes Da Costa 120 Gr','GOMES DA COSTA',NULL,54.89,64.63)
,(7891167021013,'Sardina en Aceite Gomes Da Costa 84 Gr','GOMES DA COSTA',NULL,51.99,77.50)
,(7891167021020,'Sardina en Salsa de Tomate Gomes Da Costa 125 Gr','GOMES DA COSTA',NULL,51.99,77.50)
,(7896423419276,'Confites de Chocolate M&M 30 Gr','M&M',NULL,51.50,55.90)
,(7898024390107,'Bombones Caja Ferrero Rocher 200 Gr','FERRERO ROCHER',NULL,300.00,331.00)
,(8410134006700,'Aceitunas Rellenas con Cebollita en Lata Fragata 85 Gr','FRAGATA',NULL,61.50,61.50)
,(8410134107063,'Aceitunas Rellenas con Alcaparra en Lata Fragata 85 Gr','FRAGATA',NULL,61.50,61.50)
GO

select count(*)as cant_prod from  producto
select count(distinct(marca))as dist_marcas from  producto

select * from producto



EXECUTE dbo.GenerateInsert @ObjectName = N'producto';
k