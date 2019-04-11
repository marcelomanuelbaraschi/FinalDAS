CREATE TABLE sucursal(
       idSucursal BIGINT NOT NULL
      ,nombreSucursal  VARCHAR (100) NOT NULL
      ,codigoEntidadFederal VARCHAR(10) NOT NULL
      ,localidad  VARCHAR (100) NOT NULL
      ,direccion  VARCHAR (100) NOT NULL
      ,lat VARCHAR (30) NOT NULL
      ,lng VARCHAR (30) NOT NULL
      ,PRIMARY KEY (idSucursal)
      ,FOREIGN KEY (codigoEntidadFederal,localidad) REFERENCES localidad (codigoEntidadFederal,nombreLocalidad)
      ,UNIQUE (direccion)
)
