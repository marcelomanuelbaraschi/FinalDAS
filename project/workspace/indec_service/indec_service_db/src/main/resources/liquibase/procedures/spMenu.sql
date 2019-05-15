CREATE PROCEDURE spMenu
AS
BEGIN
    SELECT M.nombreMenu, M.idMenu ,M.dia, P.nombrePlato, P.preparacion, P.idPlato, P.imagenPlato, I.nombreIngrediente, IP.descripcion
        FROM menu M
        JOIN plato P
        ON M.idMenu = P.idMenu
        JOIN ingredientes_plato IP
        ON P.idPlato = IP.idPlato
        JOIN ingrediente I
        ON IP.idIngrediente = I.idIngrediente
    ORDER BY P.idPlato
END