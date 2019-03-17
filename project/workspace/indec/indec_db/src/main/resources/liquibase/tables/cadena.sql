CREATE TABLE cadena (
	  id BIGINT IDENTITY (1,1)
	, nombre VARCHAR(50) NOT NULL
	, tecnologia VARCHAR(20) NOT NULL
    , url VARCHAR(200) NOT NULL
	, FOREIGN KEY(tecnologia) REFERENCES tecnologia(nombre)
	, PRIMARY KEY(id)
);

