CREATE TABLE cadena_tecnologia (
	  id_cadena BIGINT NOT NULL
	, tecnologia VARCHAR(20) NOT NULL
	, PRIMARY KEY(id_cadena, tecnologia)
	, FOREIGN KEY(id_cadena)  REFERENCES cadena(id)
	, FOREIGN KEY(tecnologia) REFERENCES tecnologia(nombre)
);