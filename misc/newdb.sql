DROP DATABASE db_cadena
GO

CREATE DATABASE db_cadena
GO

USE db_cadena 
GO
DROP TABLE marca
go
DROP TABLE precio 
GO
DROP TABLE productoSucursal 
GO
DROP TABLE sucursal 
GO
DROP TABLE localidad 
GO
DROP TABLE provincia
GO
DROP TABLE producto 
GO

CREATE TABLE marca (
     idMarca BIGINT NOT NULL
    ,nombre VARCHAR (100) NOT NULL
    ,PRIMARY KEY (idMarca)
    ,UNIQUE (nombre)
)

CREATE TABLE producto (
     codigoDeBarras VARCHAR (100) NOT NULL
    ,nombre VARCHAR (100) NOT NULL
    ,idMarca BIGINT NOT NULL
    ,PRIMARY KEY (codigoDeBarras)
    ,FOREIGN KEY (idMarca) REFERENCES marca (idMarca)
)

CREATE TABLE provincia (
     idProvincia BIGINT NOT NULL
    ,codigoEntidadFederal VARCHAR (10) NOT NULL
    ,nombre VARCHAR (50) NOT NULL
    ,PRIMARY KEY (idProvincia)
    ,UNIQUE (codigoEntidadFederal)
    ,UNIQUE (nombre)
)


CREATE TABLE localidad (
     idLocalidad BIGINT NOT NULL
    ,nombre VARCHAR (100) NOT NULL
    ,idProvincia BIGINT NOT NULL
    ,PRIMARY KEY (idProvincia,idLocalidad)
    ,FOREIGN KEY (idProvincia) REFERENCES provincia (idProvincia)
)

CREATE TABLE sucursal (
     idSucursal BIGINT NOT NULL
    ,nombre VARCHAR (100) NOT NULL
    ,cuit VARCHAR(11) NOT NULL
    ,idProvincia BIGINT NOT NULL
    ,idLocalidad BIGINT NOT NULL
    ,direccion VARCHAR (100) NOT NULL
    ,telefono VARCHAR (12) NOT NULL
    ,email VARCHAR (100) NOT NULL
    ,lat VARCHAR (30) NOT NULL
    ,lng VARCHAR (30) NOT NULL
    ,PRIMARY KEY (idSucursal)
    ,FOREIGN KEY (idProvincia,idLocalidad) REFERENCES localidad (idProvincia,idLocalidad)
)


CREATE TABLE productoSucursal (
    idSucursal BIGINT NOT NULL
   ,codigoDeBarras VARCHAR (100) NOT NULL
   ,activo CHAR NOT NULL
   ,CONSTRAINT rango_activo CHECK (activo IN ('S','N'))
   ,PRIMARY KEY (idSucursal,codigoDeBarras)
   ,FOREIGN KEY (codigoDeBarras) REFERENCES producto (codigoDeBarras)
   ,FOREIGN KEY (idSucursal) REFERENCES sucursal (idSucursal)
 )

CREATE TABLE precio (
    idPrecio BIGINT IDENTITY (1,1)
   ,precio REAL NOT NULL
   ,idSucursal BIGINT NOT NULL
   ,codigoDeBarras VARCHAR (100) NOT NULL
   ,validoDesde DATETIME DEFAULT GETDATE()
   ,PRIMARY KEY (idPrecio)
   ,FOREIGN KEY (idSucursal,codigoDeBarras) REFERENCES productoSucursal (idSucursal,codigoDeBarras)
)

DROP PROCEDURE SP_GETSUCURSALES
GO
CREATE PROCEDURE SP_GETSUCURSALES (@codigoEntidadFederal VARCHAR(10), @localidad  VARCHAR (100))
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

  SELECT suc.idSucursal AS idSucursal
        ,suc.nombre AS nombreSucursal
        ,suc.cuit AS cuit
        ,suc.telefono AS telefono
        ,suc.email AS email
        ,suc.direccion AS direccion
        ,suc.lat AS latitud
        ,suc.lng AS longitud
        ,prov.nombre AS provincia
        ,prov.codigoEntidadFederal AS codigoEntidadFederal
        ,loc.nombre AS localidad
    FROM sucursal suc
    JOIN localidad loc
        ON suc.idLocalidad = loc.idLocalidad
        AND suc.idProvincia = loc.idProvincia
    JOIN provincia prov
        ON  prov.idProvincia = suc.idProvincia
    WHERE prov.codigoEntidadFederal = @codigoEntidadFederal
    AND loc.nombre = @localidad
END

DROP PROCEDURE SP_GETPRECIOSSUCURSALES
GO

GO
CREATE PROCEDURE SP_GETPRECIOSSUCURSALES (@codigoEntidadFederal VARCHAR(10), @localidad  VARCHAR (100) ,@codigos VARCHAR(MAX))
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


    DECLARE @tcodigos TABLE (idComercial  VARCHAR (100))
        INSERT INTO @tcodigos (idComercial)
            SELECT *
                FROM string_split(@codigos, ',')

        SELECT suc.idSucursal AS idSucursal
              ,suc.nombre AS nombreSucursal
              ,suc.direccion AS direccion
              ,suc.lat AS latitud
              ,suc.lng AS longitud
              ,suc.email AS email
              ,suc.telefono AS telefono
              ,suc.cuit AS cuit
              ,loc.nombre AS localidad
              ,prov.nombre AS provincia
              ,MAX(pre.precio) AS precio
              ,prod.codigoDeBarras AS codigoDeBarras
              ,prod.nombre AS nombreProducto
            FROM productoSucursal ps
                JOIN sucursal suc
                    ON ps.idSucursal = suc.idSucursal
                JOIN localidad loc 
                    ON suc.idLocalidad = loc.idLocalidad
                    AND loc.idLocalidad = @localidad
                JOIN provincia prov
                    ON prov.idProvincia = suc.idProvincia
                    AND prov.codigoEntidadFederal = @codigoEntidadFederal
                JOIN precio pre 
                    ON pre.idSucursal = ps.idSucursal
                    AND pre.codigoDeBarras = ps.codigoDeBarras
                JOIN producto prod 
                    ON prod.codigoDeBarras = ps.codigoDeBarras
                JOIN marca marc
                    ON prod.idMarca = marc.idMarca
            WHERE
                 ps.codigoDeBarras IN (SELECT * FROM @tcodigos)
                 AND ps.activo = 'S'
            GROUP BY suc.idSucursal
                    ,suc.nombre 
                    ,suc.direccion 
                    ,suc.lat 
                    ,suc.lng 
                    ,suc.email 
                    ,suc.telefono 
                    ,suc.cuit 
                    ,loc.nombre 
                    ,prov.nombre 
                    ,pre.precio
                    ,prod.codigoDeBarras 
                    ,prod.nombre
END