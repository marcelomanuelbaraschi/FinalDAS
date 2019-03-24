DROP DATABASE  IF EXISTS db_indec_service
CREATE DATABASE db_indec_service

DROP TABLE IF EXISTS tecnologia
DROP TABLE IF EXISTS cadenaServiceConfig

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
      ('Walmart','SOAP','http://localhost:8000/supermercado_axis_one/services/SupermercadoAxisOne?wsdl')
    , ('Jumbo','SOAP',  'http://localhost:8003/supermercado_cxf_one/services/supermercado_cxf_one?wsdl')
    , ('Carrefour','REST','http://localhost:8001/supermercado_rest_one/supermercadoRestOne')
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







