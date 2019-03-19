CREATE OR ALTER PROCEDURE get_cadena_service_configs  @id_cadena BIGINT
AS
BEGIN
    IF  (@id_cadena IS NULL)
	BEGIN
		SELECT cpc.id_cadena as idCadena, c.nombre as nombreCadena, cpc.nombreParametro, cpc.tecnologia, cpc.valor
		FROM cadena_params_config cpc
		JOIN cadena_tecnologia ct
		ON  ct.id_cadena = cpc.id_cadena
		AND ct.tecnologia = cpc.tecnologia
		JOIN cadena c
		ON c.id = cpc.id_cadena
	END
	ELSE
	BEGIN
		SELECT cpc.id_cadena as idCadena, c.nombre as nombreCadena, cpc.nombreParametro, cpc.tecnologia, cpc.valor
		FROM cadena_params_config cpc
		JOIN cadena_tecnologia ct
		ON  ct.id_cadena = cpc.id_cadena
		AND ct.tecnologia = cpc.tecnologia
		JOIN cadena c
		ON c.id = cpc.id_cadena
		WHERE cpc.id_cadena = @id_cadena
	END
END
GO