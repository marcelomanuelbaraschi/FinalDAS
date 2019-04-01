CREATE TABLE productoSucursal (
    idSucursal               BIGINT         NOT NULL
   ,codigoProducto           VARCHAR (100)  NOT NULL
   ,activo                   CHAR           NOT NULL
   ,fechaUltimaActualizacion DATETIME       DEFAULT  GETDATE()
   ,precio                   DECIMAL (6,2)  NOT NULL
   ,CONSTRAINT rango_activo CHECK (activo IN ('S','N'))
   ,PRIMARY KEY (idSucursal,codigoProducto)
   ,FOREIGN KEY (codigoProducto) REFERENCES producto (codigoProducto)
   ,FOREIGN KEY (idSucursal) REFERENCES sucursal (idSucursal)
 )
GO
