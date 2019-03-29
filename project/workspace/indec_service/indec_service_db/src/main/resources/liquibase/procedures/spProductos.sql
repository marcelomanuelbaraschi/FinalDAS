CREATE PROCEDURE spProductos(
      @idCategoria BIGINT
) AS
BEGIN
  IF @idCategoria IS NULL
    BEGIN
         RAISERROR('El parametro @idCategoria es null', 15, 1)
    END
  ELSE
    BEGIN
     SELECT id, idComercial, idCategoria, nombre, nombreMarca, imagen
        FROM producto p
        WHERE p.idCategoria = @idCategoria
    END
END;
