CREATE TABLE producto (
	  idProducto SMALLINT IDENTITY (1,1)
    , codigoDeBarras VARCHAR (100) NOT NULL
    , idCategoria SMALLINT NOT NULL
	, nombreProducto VARCHAR(100) NOT NULL
    , idMarca SMALLINT NOT NULL
    , imagenProducto VARCHAR(200) NULL
    , idIngrediente SMALLINT NOT NULL
    , FOREIGN KEY (idCategoria) REFERENCES categoriaProducto(idCategoria)
    , FOREIGN KEY (idMarca) REFERENCES marcaProducto(idMarca)
    , FOREIGN KEY (idIngrediente) REFERENCES ingrediente(idIngrediente)
    , UNIQUE (codigoDeBarras)
	, PRIMARY KEY(idProducto)
)
GO