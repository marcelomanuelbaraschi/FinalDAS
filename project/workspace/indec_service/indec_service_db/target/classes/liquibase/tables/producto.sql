CREATE TABLE producto (
	  id BIGINT IDENTITY (1,1)
    , idComercial BIGINT NOT NULL
    , idCategoria BIGINT NOT NULL
	, nombre VARCHAR(100) NOT NULL
    , nombreMarca VARCHAR(50) NOT NULL
    , precio_desde decimal (5,2)
    , precio_hasta decimal (5,2)
    , imagen VARCHAR(200) NULL
    , FOREIGN KEY(idCategoria) REFERENCES categoriaProducto(id)
    , FOREIGN KEY(nombreMarca) REFERENCES marcaProducto(nombre)
	, PRIMARY KEY(id)
);
GO

