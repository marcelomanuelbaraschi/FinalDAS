CREATE TABLE cadenaServiceConfig (
	  idConfig SMALLINT IDENTITY (1,1)
	, idCadena SMALLINT NOT NULL
	, nombreTecnologia VARCHAR(20) NOT NULL
    , url VARCHAR(200) NOT NULL
	, FOREIGN KEY(nombreTecnologia) REFERENCES tecnologia(nombreTecnologia)
	, FOREIGN KEY(idCadena) REFERENCES cadena(idCadena)
	, PRIMARY KEY(idConfig)
)
GO