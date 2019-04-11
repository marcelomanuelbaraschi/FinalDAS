CREATE TABLE productoSucursal (
    idSucursal               BIGINT         NOT NULL
   ,idComercial           VARCHAR (100)  NOT NULL
   ,activo                   CHAR           NOT NULL
   ,fechaUltimaActualizacion DATETIME       DEFAULT  GETDATE()
   ,precio                   REAL           NOT NULL
   ,CONSTRAINT rango_activo CHECK (activo IN ('S','N'))
   ,PRIMARY KEY (idSucursal,idComercial)
   ,FOREIGN KEY (idComercial) REFERENCES producto (idComercial)
   ,FOREIGN KEY (idSucursal) REFERENCES sucursal (idSucursal)
 )
GO
