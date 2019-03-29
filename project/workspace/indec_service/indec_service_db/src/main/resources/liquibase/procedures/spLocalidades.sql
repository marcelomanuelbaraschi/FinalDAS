CREATE PROCEDURE spLocalidades(
      @idProvincia BIGINT
) AS
BEGIN
  IF @idProvincia IS NULL
    BEGIN
         SELECT id, idProvincia, nombre
         FROM localidad
    END
  ELSE
    BEGIN
     SELECT id, idProvincia, nombre
      FROM localidad l
        WHERE l.idProvincia = @idProvincia
    END
END;
