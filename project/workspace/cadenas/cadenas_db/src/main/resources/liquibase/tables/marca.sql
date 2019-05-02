CREATE TABLE marca (
     idMarca BIGINT NOT NULL
    ,nombre VARCHAR (100) NOT NULL
    ,PRIMARY KEY (idMarca)
    ,UNIQUE (nombre)
)
