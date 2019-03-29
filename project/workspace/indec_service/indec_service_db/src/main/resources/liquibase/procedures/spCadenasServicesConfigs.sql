CREATE PROCEDURE spCadenasServicesConfigs AS
BEGIN
 SELECT  id, nombreCadena ,tecnologia, url
    FROM  cadenaServiceConfig
END
GO