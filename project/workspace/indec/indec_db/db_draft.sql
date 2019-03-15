use db_indec;
create database db_indec;
drop database db_indec;

CREATE TABLE tecnologia (
	  nombre VARCHAR(20) 
	, PRIMARY KEY(nombre)
);
INSERT INTO tecnologia (nombre) 
    VALUES ('REST')
         , ('CXF')
         , ('AXIS');


CREATE TABLE tecnologia_param (
      tecnologia VARCHAR(20) NOT NULL
	, nombreParametro VARCHAR(200) NOT NULL --wsdlUrl , endpointUrl , targetNameSpace
	, PRIMARY KEY (tecnologia, nombreParametro)
	, FOREIGN KEY (tecnologia) REFERENCES tecnologia (nombre)
);

INSERT INTO tecnologia_param (tecnologia, nombreParametro)
VALUES
      ('CXF',  'wsdlUrl')
    , ('REST', 'url')
    , ('AXIS', 'endpointUrl')
    , ('AXIS', 'targetNameSpace')
  

CREATE TABLE cadena (
	  id BIGINT IDENTITY (1,1)
	, nombre VARCHAR(50) NOT NULL
	, PRIMARY KEY(id)
);

INSERT INTO cadena (nombre)
VALUES
      ('Walmart')   --supermercado_axis_one
    , ('Jumbo')     --supermercado_cxf_one
    , ('Carrefour') --supermercado_rest_one


CREATE TABLE cadena_tecnologia (
	  id_cadena BIGINT NOT NULL
	, tecnologia VARCHAR(20) NOT NULL
	, PRIMARY KEY(id_cadena, tecnologia)
	, FOREIGN KEY(id_cadena)  REFERENCES cadena(id)
	, FOREIGN KEY(tecnologia) REFERENCES tecnologia(nombre)
);

INSERT INTO cadena_tecnologia(id_cadena, tecnologia)
    VALUES
          (1, 'AXIS')
        , (2, 'CXF')
        , (3, 'REST')


CREATE TABLE cadena_params_config (
	  id BIGINT IDENTITY (1,1)
	, id_cadena BIGINT NOT NULL
	, tecnologia VARCHAR(20) NOT NULL
	, nombreParametro  VARCHAR(200)  NOT NULL
	, valor VARCHAR(500) NOT   NULL
	, PRIMARY KEY(id)
	, FOREIGN KEY(id_cadena,  tecnologia) REFERENCES cadena_tecnologia(id_cadena, tecnologia)
	, FOREIGN KEY(tecnologia, nombreParametro) REFERENCES tecnologia_param (tecnologia, nombreParametro)
);

GO
INSERT INTO cadena_params_config (id_cadena, tecnologia, nombreParametro, valor)
VALUES
       (1, 'AXIS', 'endpointUrl', 'http://localhost:8000/supermercado_axis_one/services/SupermercadoAxisOne.SupermercadoAxisOneHttpEndpoint/')--Walmart
      ,(1, 'AXIS','targetNameSpace','http://ws.SupermercadoAxisOne/')--Walmart
      ,(2, 'CXF' ,'wsdlUrl','http://localhost:8003/supermercado_cxf_one/services/supermercado_cxf_one?wsdl')--Jumbo
      ,(3, 'REST','url','http://localhost:8001/supermercado_rest_one/supermercadoRestOne')--Carrefour
GO


CREATE OR ALTER PROCEDURE get_cadena_service_configs  @id_cadena BIGINT 
AS 
BEGIN
    IF  (@id_cadena IS NULL)
	BEGIN
		SELECT cpc.id_cadena as idCadena, c.nombre as nombreCadena, cpc.nombreParametro, cpc.tecnologia, cpc.valor
		FROM cadena_params_config cpc 
		JOIN cadena_tecnologia ct
		ON  ct.id_cadena = cpc.id_cadena
		AND ct.tecnologia = cpc.tecnologia
		JOIN cadena c
		ON c.id = cpc.id_cadena
	END
	ELSE
	BEGIN
		SELECT cpc.id_cadena as idCadena, c.nombre as nombreCadena, cpc.nombreParametro, cpc.tecnologia, cpc.valor
		FROM cadena_params_config cpc 
		JOIN cadena_tecnologia ct
		ON  ct.id_cadena = cpc.id_cadena
		AND ct.tecnologia = cpc.tecnologia
		JOIN cadena c
		ON c.id = cpc.id_cadena
		WHERE cpc.id_cadena = @id_cadena
	END
END
GO


EXEC get_cadena_service_configs @id_cadena = null --validar unicidad de (idCadena,nombreCadena)
GO


CREATE OR ALTER PROCEDURE get_cadenas 
AS
	SELECT C.id, C.nombre, CT.tecnologia
		FROM cadena C
		JOIN cadena_tecnologia CT 
		ON C.id = CT.id_cadena
GO


EXEC get_cadenas
GO
