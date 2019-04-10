CREATE TABLE cadena (
	  id BIGINT NOT NULL
	, nombre VARCHAR(50) NOT NULL
	, imagen VARCHAR(100) NULL
	, PRIMARY KEY(id)
    , UNIQUE (nombre)
);
GO