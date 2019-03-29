CREATE TABLE cadena (
	  id BIGINT IDENTITY (1,1)
	, nombre VARCHAR(50) NOT NULL
	, PRIMARY KEY(id)
    , UNIQUE (nombre)
);
GO