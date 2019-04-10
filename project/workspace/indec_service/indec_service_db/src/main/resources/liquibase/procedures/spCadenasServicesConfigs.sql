CREATE PROCEDURE spCadenasServicesConfigs AS
BEGIN
 SELECT  id, idCadena, nombreCadena ,tecnologia, url
    FROM  cadenaServiceConfig
END
