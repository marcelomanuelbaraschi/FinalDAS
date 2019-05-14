CREATE PROCEDURE spLocalidades(
      @codigoEntidadFederal VARCHAR(10)
) AS
BEGIN
     SELECT codigoEntidadFederal, nombreLocalidad
      FROM localidad l
      JOIN provincia p
      ON l.idProvincia = p.idProvincia
        WHERE p.codigoEntidadFederal = @codigoEntidadFederal

END
