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


    DECLARE @tcodigos TABLE (idComercial  VARCHAR (100))
        INSERT INTO @tcodigos (idComercial)
            SELECT *
                FROM string_split(@codigos, ',')

        SELECT suc.idSucursal AS idSucursal
              ,suc.nombre AS nombreSucursal
              ,suc.direccion AS direccion
              ,suc.lat AS latitud
              ,suc.lng AS longitud
              ,suc.email AS email
              ,suc.telefono AS telefono
              ,suc.cuit AS cuit
              ,loc.nombre AS localidad
              ,prov.nombre AS provincia
              ,MAX(pre.precio) AS precio
              ,prod.codigoDeBarras AS codigoDeBarras
              ,prod.nombre AS nombreProducto
            FROM productoSucursal ps
                JOIN sucursal suc
                    ON ps.idSucursal = suc.idSucursal
                JOIN localidad loc
                    ON suc.idLocalidad = loc.idLocalidad
                    AND loc.nombre = @localidad
                JOIN provincia prov
                    ON prov.idProvincia = suc.idProvincia
                    AND prov.codigoEntidadFederal = @codigoEntidadFederal
                JOIN precio pre
                    ON pre.idSucursal = ps.idSucursal
                    AND pre.codigoDeBarras = ps.codigoDeBarras
                JOIN producto prod
                    ON prod.codigoDeBarras = ps.codigoDeBarras
                JOIN marca marc
                    ON prod.idMarca = marc.idMarca
            WHERE
                 ps.codigoDeBarras IN (SELECT * FROM @tcodigos)
                 AND ps.activo = 'S'
            GROUP BY suc.idSucursal
                    ,suc.nombre
                    ,suc.direccion
                    ,suc.lat
                    ,suc.lng
                    ,suc.email
                    ,suc.telefono
                    ,suc.cuit
                    ,loc.nombre
                    ,prov.nombre
                    ,pre.precio
                    ,prod.codigoDeBarras
                    ,prod.nombre
END
