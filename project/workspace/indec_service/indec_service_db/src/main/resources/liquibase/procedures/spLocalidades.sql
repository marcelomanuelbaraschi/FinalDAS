CREATE PROCEDURE spLocalidades(
      @codigoEntidadFederal VARCHAR(10)
) AS
BEGIN
  IF @codigoEntidadFederal IS NULL
    BEGIN
         SELECT codigoEntidadFederal, nombreLocalidad
         FROM localidad
    END
  ELSE
    BEGIN
     SELECT codigoEntidadFederal, nombreLocalidad
      FROM localidad l
        WHERE l.codigoEntidadFederal = @codigoEntidadFederal
    END
END
