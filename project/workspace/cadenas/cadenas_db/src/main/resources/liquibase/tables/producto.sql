CREATE TABLE producto (
     codigoDeBarras VARCHAR (100) NOT NULL
    ,nombre VARCHAR (100) NOT NULL
    ,idMarca BIGINT NOT NULL
    ,PRIMARY KEY (codigoDeBarras)
    ,FOREIGN KEY (idMarca) REFERENCES marca (idMarca)
)