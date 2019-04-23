CREATE TABLE localidad(
     nombreLocalidad  VARCHAR (100) NOT NULL
    ,codigoEntidadFederal VARCHAR(10) NOT NULL
    ,PRIMARY KEY (codigoEntidadFederal,nombreLocalidad)
    ,FOREIGN KEY (codigoEntidadFederal) REFERENCES provincia (codigoEntidadFederal)
)
GO