CREATE PROCEDURE get_marcas(
    @nombre_marca	VARCHAR (100)
) AS
SELECT *
FROM marca
WHERE nombre_marca = @nombre_marca