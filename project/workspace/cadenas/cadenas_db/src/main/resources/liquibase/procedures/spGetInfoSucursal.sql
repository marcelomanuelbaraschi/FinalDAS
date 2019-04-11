CREATE PROCEDURE SP_GETINFOSUCURSAL (@idSucursal BIGINT )
AS
BEGIN
  IF (@idSucursal IS NULL)
      BEGIN
         RAISERROR('El parametro @idSucursal es null', 15, 1)
      END

  SELECT s.nombreSucursal, s.direccion, s.localidad, s.codigoEntidadFederal, s.lat, s.lng
    FROM sucursal s
    WHERE s.idSucursal = @idSucursal
END
GO