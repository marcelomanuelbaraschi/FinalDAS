CREATE TABLE localidad (
     idLocalidad BIGINT NOT NULL
    ,nombre VARCHAR (100) NOT NULL
    ,idProvincia BIGINT NOT NULL
    ,PRIMARY KEY (idProvincia,idLocalidad)
    ,FOREIGN KEY (idProvincia) REFERENCES provincia (idProvincia)
)