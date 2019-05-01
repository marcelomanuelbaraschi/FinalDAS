CREATE TABLE precio (
    idPrecio BIGINT IDENTITY (1,1)
   ,precio REAL NOT NULL
   ,idSucursal BIGINT NOT NULL
   ,codigoDeBarras VARCHAR (100) NOT NULL
   ,validoDesde DATETIME DEFAULT GETDATE()
   ,PRIMARY KEY (idPrecio)
   ,FOREIGN KEY (idSucursal,codigoDeBarras) REFERENCES productoSucursal (idSucursal,codigoDeBarras)
)
