CREATE PROCEDURE spCategoriasProducto AS
BEGIN
 SELECT idCategoria, nombreCategoria ,urlImagenCategoria
    FROM categoriaProducto
END
