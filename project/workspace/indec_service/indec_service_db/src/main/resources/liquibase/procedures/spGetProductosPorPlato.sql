CREATE PROCEDURE spGetProductosPorPlato (@idPlato SMALLINT )
AS
BEGIN
    IF (@idPlato IS NULL)
      BEGIN
         RAISERROR('El parametro @idPlato es null', 15, 1)
      END

    SELECT PR.codigoDeBarras
        FROM plato P
        JOIN ingredientes_plato IP
        ON P.idPlato = IP.idPlato
        AND @idPlato = P.idPlato
        JOIN ingrediente I
        ON IP.idIngrediente = I.idIngrediente
        JOIN ṕroducto PR
        ON PR.idIngrediente = I.idIngrediente
END
