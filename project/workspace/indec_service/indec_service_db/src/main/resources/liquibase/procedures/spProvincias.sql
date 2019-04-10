CREATE PROCEDURE spProvincias
AS
  BEGIN
   SELECT  codigoEntidadFederal, nombreProvincia
      FROM  provincia
  END
