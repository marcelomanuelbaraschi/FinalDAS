CREATE PROCEDURE spLocalidades AS
BEGIN
     SELECT codigoEntidadFederal, nombreLocalidad
      FROM localidad l
      JOIN provincia p
      ON l.idProvincia = p.idProvincia

END
