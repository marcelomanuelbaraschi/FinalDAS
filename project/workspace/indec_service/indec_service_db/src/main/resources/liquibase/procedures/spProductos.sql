CREATE PROCEDURE spProductos(
      @idCategoria BIGINT
) AS
BEGIN
  IF @idCategoria IS NULL
    BEGIN
      SELECT p.idComercial, p.idCategoria, c.nombre AS nombreCategoria, p.nombre, p.nombreMarca, p.imagen
         FROM producto p
         JOIN categoriaProducto c
         ON p.idCategoria = c.id
    END
  ELSE
    BEGIN
     SELECT p.idComercial, p.idCategoria, c.nombre AS nombreCategoria, p.nombre, p.nombreMarca, p.imagen
        FROM producto p
        JOIN categoriaProducto c
        ON p.idCategoria = c.id
        WHERE p.idCategoria = @idCategoria
    END
END