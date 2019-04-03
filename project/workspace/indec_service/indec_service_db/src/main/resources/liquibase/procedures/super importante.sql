use db_cadena

GO
CREATE FUNCTION [dbo].[SplitList] (@list VARCHAR(MAX), @separator VARCHAR(MAX) = ';')
RETURNS @table TABLE (Value VARCHAR(MAX))
AS BEGIN
  DECLARE @position INT, @previous INT
  SET @list = @list + @separator
  SET @previous = 1
  SET @position = CHARINDEX(@separator, @list)
  WHILE @position > 0 
  BEGIN
    IF @position - @previous > 0
      INSERT INTO @table VALUES (SUBSTRING(@list, @previous, @position - @previous))
    IF @position >= LEN(@list) BREAK
      SET @previous = @position + 1
      SET @position = CHARINDEX(@separator, @list, @previous)
  END
  RETURN
END
GO

SELECT * FROM dbo.SplitList('1,2,3,4,5', ',')
--health(identificador)
--sucursales(identificador,codigoEntidadFederal,localidad) --retorna lista de sucursales


--precio  (identificador,codigoEntidadFederal,localidad,barcodes []) --retorna lista de productos + precio total

EXEC SP_GETPRECIOSSUCURSAL @codigoEntidadFederal = 'AR-X',@localidad ='Capital', @codigos= '7798113151278,7798113151377'

EXEC SP_GETPRECIOSSUCURSAL @codigoEntidadFederal = 'AR-X',@localidad =' ', @codigos= '   '

GO
CREATE OR ALTER PROCEDURE SP_GETPRECIOSSUCURSAL (@codigoEntidadFederal VARCHAR(10), @localidad  VARCHAR (100) ,@codigos VARCHAR(MAX))
AS
SET NOCOUNT ON
BEGIN

  IF (@codigoEntidadFederal IS NULL) OR (TRIM(@codigoEntidadFederal) = '')
    BEGIN
         RAISERROR('El parametro @codigoEntidadFederal es null o vacio', 15, 1)
    END
  
  IF (@localidad IS NULL) OR (TRIM(@localidad) = '')
  BEGIN
        RAISERROR('El parametro @localidad es null o vacio', 15, 1)
  END

  IF (@codigos IS NULL) OR (TRIM(@codigos) = '')
  BEGIN
        RAISERROR('El parametro @codigos es null o vacio', 15, 1)
  END


    DECLARE @tcodigos TABLE (id  VARCHAR (100));
        INSERT INTO @tcodigos (id)
            SELECT *  
                FROM dbo.SplitList(@codigos, ',') 

        SELECT ps.idSucursal 
              ,ps.codigoProducto 
              ,ps.precio
            FROM productoSucursal ps 
                JOIN sucursal s
                ON s.codigoEntidadFederal = @codigoEntidadFederal
                AND s.localidad = @localidad
            WHERE 
                     ps.codigoProducto IN (SELECT * FROM @tcodigos)
                 AND ps.activo = 'S'
END
GO

CREATE OR ALTER PROCEDURE SP_GETINFOSUCURSAL (@idSucursal BIGINT )
AS
BEGIN
  IF (@idSucursal IS NULL) 
      BEGIN
         RAISERROR('El parametro @idSucursal es null', 15, 1)
      END

  SELECT s.sucursalNombre, s.direccion, s.localidad, s.codigoEntidadFederal, s.lat, s.lng 
    FROM sucursal s 
    WHERE s.idSucursal = @idSucursal 
END
GO

EXECUTE SP_GETINFOSUCURSAL @idSucursal = 1
GO

DROP PROCEDURE SP_GETSUCURSALES
CREATE OR ALTER PROCEDURE SP_GETSUCURSALES (@codigoEntidadFederal VARCHAR(10), @localidad  VARCHAR (100) )
AS
BEGIN

  IF (@codigoEntidadFederal IS NULL) OR (TRIM(@codigoEntidadFederal) = '')
  BEGIN
        RAISERROR('El parametro @codigoEntidadFederal es null o vacio', 15, 1)
  END
  
  IF (@localidad IS NULL) OR (TRIM(@localidad) = '')
  BEGIN
        RAISERROR('El parametro @localidad es null o vacio', 15, 1)
  END

  SELECT s.direccion, s.idSucursal, s.lat, s.lng, s.sucursalNombre
    FROM sucursal s 
    WHERE s.codigoEntidadFederal = @codigoEntidadFederal 
    AND s.localidad = @localidad 
END
GO

EXECUTE SP_GETSUCURSALES @codigoEntidadFederal = 'AR-X', @localidad = 'Capital'
GO
--precios (identificador,codigoEntidadFederal,localidad,barcodes []) --retorna lista de listas de productos + precios totales
--sucursalInfo (identificador,idSucursal) --info

DROP TABLE productoSucursal
DROP table producto
DROP TABLE sucursal
DROP TABLE localidad
DROP TABLE provincia
GO


CREATE TABLE producto (
      codigoProducto VARCHAR (100) NOT NULL
    , PRIMARY KEY (codigoProducto)
)
GO

INSERT INTO producto (codigoProducto)
VALUES
 ('7798082740107')
,('7798082740176')
,('7798113151278')
,('7798113151377')
,('77903518')
GO

CREATE TABLE provincia (
  codigoEntidadFederal VARCHAR (10) NOT NULL,
  nombreProvincia VARCHAR (50) NOT NULL,
  PRIMARY KEY (codigoEntidadFederal)
)
GO

INSERT INTO  provincia (codigoEntidadFederal,nombreProvincia)
VALUES
('AR-X','Córdoba')
GO

CREATE TABLE localidad(
     nombreLocalidad  VARCHAR (100) NOT NULL
    ,codigoEntidadFederal VARCHAR(10) NOT NULL
    ,PRIMARY KEY (codigoEntidadFederal,nombreLocalidad)  
    , FOREIGN KEY (codigoEntidadFederal) REFERENCES provincia (codigoEntidadFederal)
)
GO

INSERT INTO localidad (nombreLocalidad,codigoEntidadFederal)
VALUES ('Capital','AR-X')

CREATE TABLE sucursal(
       idSucursal BIGINT NOT NULL  
      ,localidad  VARCHAR (100) NOT NULL
      ,codigoEntidadFederal VARCHAR(10) NOT NULL
      ,sucursalNombre  VARCHAR (100) NOT NULL
      ,direccion  VARCHAR (100) NOT NULL
      ,lat VARCHAR (30) NOT NULL
      ,lng VARCHAR (30) NOT NULL
      ,PRIMARY KEY (idSucursal)
      ,FOREIGN KEY (codigoEntidadFederal,localidad) REFERENCES localidad (codigoEntidadFederal,nombreLocalidad)
      ,UNIQUE (direccion)
)
GO

INSERT INTO sucursal (idSucursal, localidad,codigoEntidadFederal,sucursalNombre,direccion,lat,lng)
VALUES
(1,'Capital','AR-X','Mini Libertad Caraffa','Av. Emilio Caraffa 2683','-31.387203000000003','-64.21509499999999')
,(2,'Capital','AR-X','Mini Libertad Fader','Av. Fernando Fader 3903','31.375555','-64.231105')
GO

CREATE TABLE productoSucursal ( 
    idSucursal               BIGINT         NOT NULL
   ,codigoProducto           VARCHAR (100)  NOT NULL
   ,activo                   CHAR           NOT NULL
   ,fechaUltimaActualizacion DATETIME       DEFAULT  GETDATE ( )
   ,precio                   REAL NOT NULL
   ,CONSTRAINT rango_activo CHECK (activo IN ('S','N'))
   ,PRIMARY KEY (idSucursal,codigoProducto)
   ,FOREIGN KEY (codigoProducto) REFERENCES producto (codigoProducto) 
   ,FOREIGN KEY (idSucursal) REFERENCES sucursal (idSucursal) 
 ) 
 GO

 INSERT INTO productoSucursal  (idSucursal,codigoProducto,activo,precio)
 VALUES 
 (1,'7798082740107','S',54.50)
,(1,'7798082740176','S',61.50)
,(1,'7798113151278','S',12.50)
,(1,'7798113151377','S',30.00)
,(1,'77903518','S',40.00)
GO

 INSERT INTO productoSucursal  (idSucursal,codigoProducto,activo,precio)
 VALUES 
 (2,'7798082740107','S',56.50)
,(2,'7798082740176','S',59.50)
,(2,'7798113151278','S',14.50)
,(2,'7798113151377','S',30.00)
GO

SELECT * FROM productoSucursal
 





/*
DROP TABLE  tipoSucursal
GO
CREATE table tipoSucursal(
   id BIGINT IDENTITY (1,1)
  ,tipo VARCHAR (20) NOT NULL
  ,PRIMARY KEY (id) 
  ,UNIQUE (tipo)
)
GO */

GO

 






