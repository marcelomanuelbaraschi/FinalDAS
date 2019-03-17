use db_indec;

CREATE TABLE tecnologia (
	  nombre VARCHAR(20)
	, PRIMARY KEY(nombre)
);

INSERT INTO tecnologia (nombre)
    VALUES ('SOAP')
         , ('REST')


CREATE TABLE cadenaServiceConfig (
	  id BIGINT IDENTITY (1,1)
	, nombre VARCHAR(50) NOT NULL
	, tecnologia VARCHAR(20) NOT NULL
    , url VARCHAR(200) NOT NULL
	, FOREIGN KEY(tecnologia) REFERENCES tecnologia(nombre)
	, PRIMARY KEY(id)
);

INSERT INTO cadenaServiceConfig (nombre,tecnologia,url)
VALUES
      ('Walmart','SOAP','http://localhost:8000/supermercado_axis_one/services/SupermercadoAxisOne?wsdl')
    , ('Jumbo','SOAP',  'http://localhost:8003/supermercado_cxf_one/services/supermercado_cxf_one?wsdl')
    , ('Carrefour','REST','http://localhost:8001/supermercado_rest_one/supermercadoRestOne')

select * from cadenaServiceConfig
