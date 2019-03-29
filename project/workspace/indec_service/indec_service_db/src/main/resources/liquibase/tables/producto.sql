CREATE TABLE producto (
	  id BIGINT IDENTITY (1,1)
	, nombre VARCHAR(50) NOT NULL
    , nombreCategoria VARCHAR(50) NOT NULL
    , nombreMarca VARCHAR(50) NOT NULL
    , imagen VARCHAR(200) NULL
    , FOREIGN KEY(nombreCategoria) REFERENCES categoriaProducto(nombre)
    , FOREIGN KEY(nombreMarca) REFERENCES marcaProducto(nombre)
	, PRIMARY KEY(id)
);
GO
