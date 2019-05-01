CREATE TABLE sucursal (
     idSucursal BIGINT NOT NULL
    ,nombre VARCHAR (100) NOT NULL
    ,cuit VARCHAR(20) NOT NULL
    ,idProvincia BIGINT NOT NULL
    ,idLocalidad BIGINT NOT NULL
    ,direccion VARCHAR (100) NOT NULL
    ,telefono VARCHAR (20) NOT NULL
    ,email VARCHAR (100) NOT NULL
    ,lat VARCHAR (30) NOT NULL
    ,lng VARCHAR (30) NOT NULL
    ,PRIMARY KEY (idSucursal)
    ,FOREIGN KEY (idProvincia,idLocalidad) REFERENCES localidad (idProvincia,idLocalidad)
)