CREATE  PROCEDURE SP_GETPRECIOSSUCURSAL (@codigoEntidadFederal VARCHAR(10), @localidad  VARCHAR (100) ,@codigos VARCHAR(MAX))
AS
    SET NOCOUNT ON
BEGIN

  IF (@codigoEntidadFederal IS NULL) OR (TRIM(@codigoEntidadFederal) = '')
    BEGIN
         RAISERROR('El parametro @codigoEntidadFederal es null o vacio', 15, 1)
    END

  IF (@localidad IS NULL) OR (TRIM(@localidad) = '')
  BEGIN
        RAISERROR('El parametro @localidad es null o vacio', 15, 1)
  END

  IF (@codigos IS NULL) OR (TRIM(@codigos) = '')
  BEGIN
        RAISERROR('El parametro @codigos es null o vacio', 15, 1)
  END


    DECLARE @tcodigos TABLE (id  VARCHAR (100))
        INSERT INTO @tcodigos (id)
            SELECT *
                FROM dbo.splitList(@codigos, ',')

        SELECT ps.idSucursal
              ,ps.codigoProducto
              ,ps.precio
            FROM productoSucursal ps
                JOIN sucursal s
                ON s.codigoEntidadFederal = @codigoEntidadFederal
                AND s.localidad = @localidad
            WHERE
                     ps.codigoProducto IN (SELECT * FROM @tcodigos)
                 AND ps.activo = 'S'
END
GO