CREATE PROCEDURE SP_GETSUCURSALES (@codigoEntidadFederal VARCHAR(10), @localidad  VARCHAR (100) )
AS
BEGIN

  IF (@codigoEntidadFederal IS NULL) OR (TRIM(@codigoEntidadFederal) = '')
  BEGIN
        RAISERROR('El parametro @codigoEntidadFederal es null o vacio', 15, 1)
  END

  IF (@localidad IS NULL) OR (TRIM(@localidad) = '')
  BEGIN
        RAISERROR('El parametro @localidad es null o vacio', 15, 1)
  END

  SELECT s.direccion, s.idSucursal, s.lat, s.lng, s.sucursalNombre
    FROM sucursal s
    WHERE s.codigoEntidadFederal = @codigoEntidadFederal
    AND s.localidad = @localidad
END
GO