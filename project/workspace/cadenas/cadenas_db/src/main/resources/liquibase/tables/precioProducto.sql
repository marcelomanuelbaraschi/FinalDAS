CREATE TABLE precioProducto (
    idPrecio SMALLINT IDENTITY (1,1)
   ,precio REAL NOT NULL
   ,idSucursal SMALLINT NOT NULL
   ,codigoDeBarras VARCHAR (100) NOT NULL
   ,validoDesde DATETIME DEFAULT GETDATE()
   ,PRIMARY KEY (idPrecio)
   ,FOREIGN KEY (idSucursal,codigoDeBarras) REFERENCES producto_sucursal (idSucursal,codigoDeBarras)
)
