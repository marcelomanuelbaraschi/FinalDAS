CREATE PROCEDURE SP_GETPRECIOSSUCURSALES (@codigoEntidadFederal VARCHAR(10), @localidad  VARCHAR (100) ,@codigos VARCHAR(MAX))
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
                FROM string_split(@codigos, ',')

        SELECT ps.idSucursal
              ,s.sucursalNombre
              ,s.direccion
              ,s.lat
              ,s.lng
              ,ps.codigoProducto
              ,ps.precio
            FROM productoSucursal ps
                JOIN sucursal s
                ON ps.idSucursal = s.idSucursal
                AND s.localidad = @localidad
                AND s.codigoEntidadFederal = @codigoEntidadFederal
            WHERE
                     ps.codigoProducto IN (SELECT * FROM @tcodigos)
                 AND ps.activo = 'S'
END
GO