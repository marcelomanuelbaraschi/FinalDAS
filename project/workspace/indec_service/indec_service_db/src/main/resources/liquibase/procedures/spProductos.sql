CREATE  PROCEDURE spProductos(@idCategoria SMALLINT,@codigos VARCHAR(MAX),@keyword VARCHAR(20))
 AS
 BEGIN
        DECLARE @pattern VARCHAR(20)
        SET @pattern = '%'+LOWER(TRIM(@keyword))+'%'

        DECLARE @tcodigos TABLE (codigoDeBarras  VARCHAR (100))
        INSERT INTO @tcodigos (codigoDeBarras)
            SELECT *
                FROM string_split(@codigos, ',')

        IF (@idCategoria IS NULL AND @codigos IS NULL  AND @keyword IS NULL)
        BEGIN
          SELECT p.codigoDeBarras, p.idCategoria, c.nombreCategoria, p.nombreProducto, m.nombreMarca, p.imagenProducto
             FROM producto p
             JOIN categoriaProducto c
             ON p.idCategoria = c.idCategoria
             JOIN marcaProducto m
             ON p.idMarca = m.idMarca
        END
        ELSE
            BEGIN
                WITH r1 AS
                (
                    SELECT p.codigoDeBarras, p.idCategoria, c.nombreCategoria, p.nombreProducto, m.nombreMarca, p.imagenProducto
                    FROM producto p
                    JOIN categoriaProducto c
                    ON p.idCategoria = c.idCategoria
                    JOIN marcaProducto m
                    ON p.idMarca = m.idMarca
                    AND (@idCategoria IS NOT NULL)
                    WHERE c.idCategoria = @idCategoria
                )
                ,r2 AS
                (
                    SELECT p.codigoDeBarras, p.idCategoria, c.nombreCategoria, p.nombreProducto, m.nombreMarca, p.imagenProducto
                    FROM producto p
                    JOIN categoriaProducto c
                    ON p.idCategoria = c.idCategoria
                    JOIN marcaProducto m
                    ON p.idMarca = m.idMarca
                    AND (@keyword IS NOT NULL)
                    WHERE LOWER(TRIM(p.nombreProducto)) LIKE @pattern
                        OR LOWER(TRIM(m.nombreMarca)) LIKE @pattern
                        OR LOWER(TRIM(c.nombreCategoria)) LIKE @pattern
                )
                ,r3 AS
                (
                    SELECT p.codigoDeBarras, p.idCategoria, c.nombreCategoria, p.nombreProducto, m.nombreMarca, p.imagenProducto
                    FROM producto p
                    JOIN categoriaProducto c
                    ON p.idCategoria = c.idCategoria
                    JOIN marcaProducto m
                    ON p.idMarca = m.idMarca
                    WHERE p.codigoDeBarras IN (SELECT * FROM @tcodigos)
                )
                SELECT * FROM  r1
                UNION
                SELECT * FROM  r2
                UNION
                SELECT * FROM  r3
            END
 END