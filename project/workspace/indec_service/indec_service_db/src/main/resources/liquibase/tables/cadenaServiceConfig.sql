CREATE TABLE cadenaServiceConfig (
	  id BIGINT IDENTITY (1,1)
	, idCadena BIGINT NOT NULL
	, nombreCadena VARCHAR(50) NOT NULL
	, tecnologia VARCHAR(20) NOT NULL
    , url VARCHAR(200) NOT NULL
	, FOREIGN KEY(tecnologia) REFERENCES tecnologia(nombre)
	, FOREIGN KEY(idCadena) REFERENCES cadena(id)
    , FOREIGN KEY(nombreCadena) REFERENCES cadena(nombre)
	, PRIMARY KEY(id)
);
GO
