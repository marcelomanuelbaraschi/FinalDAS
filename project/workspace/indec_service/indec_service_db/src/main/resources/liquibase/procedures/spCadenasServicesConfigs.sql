CREATE PROCEDURE spCadenasServicesConfigs AS
BEGIN
 SELECT  idConfig, config.idCadena, cad.nombreCadena, nombreTecnologia, url
    FROM  cadenaServiceConfig config
    JOIN cadena cad
    ON config.idCadena = cad.idCadena
END
