CREATE TABLE producto (
	  id BIGINT IDENTITY (1,1)
    , idComercial VARCHAR (100) NOT NULL
    , idCategoria BIGINT NOT NULL
	, nombre VARCHAR(100) NOT NULL
    , nombreMarca VARCHAR(50) NOT NULL
    , imagen VARCHAR(200) NULL
    , FOREIGN KEY(idCategoria) REFERENCES categoriaProducto(id)
    , FOREIGN KEY(nombreMarca) REFERENCES marcaProducto(nombre)
    , UNIQUE (idComercial)
	, PRIMARY KEY(id)
);
GO

