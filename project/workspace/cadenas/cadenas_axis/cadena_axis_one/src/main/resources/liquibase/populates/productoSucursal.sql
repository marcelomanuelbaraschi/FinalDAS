INSERT INTO productoSucursal (idSucursal,idComercial,activo,precio)
VALUES
 (1,'7791708001231','S',47.00,veneziana)
,(1,'7791708001248','S',48.00,veneziana)
,(1,'7791708001378','S',93.00,veneziana)
,(1,'7793890001020','S',101.00,fargo)
,(1,'7791708611652','S',78.00,veneziana)
,(1,'7790040887909','S',56.00,Mediatarde)
,(1,'7790040946101','S',59.00,criollitas)
,(1,'7790040999404','S',88.00,bagley)
,(1,'7790040999503','S',47.58,bagley)
,(1,'7622210649225','S',13.25,terrabusi)
,(1,'7622300742676','S',87.66,Lincoln)
,(1,'7622300829629','N',86.66,terrabusi)
,(1,'7622300841461','S',87.77,oreo)
,(1,'7790070410610','S',88.89,lucchetti)
,(1,'7790070411716','S',50.22,gallo)
,(1,'7790070411723','S',79.50,gallo)
,(1,'7790070411822','S',32.89,LUCCHETTI)
,(1,'7790070411877','S',44.22,gallo)

INSERT INTO productoSucursal (idSucursal,idComercial,activo,precio)
VALUES
 (2,'7791708001231','S',41.00)
,(2,'7791708001248','S',57.00)
,(2,'7791708001378','S',95.00)
,(2,'7793890001020','N',99.00)
,(2,'7791708611652','S',49.00)
,(2,'7790040887909','S',57.00)
,(2,'7790040946101','S',48.00)
,(2,'7790040999404','S',55.00)
,(2,'7790040999503','S',47.58)
,(2,'7622210649225','N',78.45)
,(2,'7622300742645','S',18.25)
,(2,'7622300742676','S',19.66)
,(2,'7622300829629','S',56.66)
,(2,'7622300841461','S',77.47)
,(2,'7790070410610','S',87.89)
,(2,'7790070411716','S',40.22)
,(2,'7790070411723','S',99.50)
,(2,'7790070411822','S',22.89)
,(2,'7790070411877','S',44.22)



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


-------------------------------------------Inserts----------------------------------
INSERT INTO  provincia (idProvincia,codigoEntidadFederal,nombre)
VALUES
 (1,'AR-A','Salta')
,(2,'AR-B','Provincia de Buenos Aires')
,(3,'AR-C','Capital Federal')
,(4,'AR-D','San Luis')
,(5,'AR-E','Entre Ríos')
,(6,'AR-F','La Rioja')
,(7,'AR-G','Santiago del Estero')
,(8,'AR-H','Chaco')
,(9,'AR-J','San Juan')
,(10,'AR-K','Catamarca')
,(11,'AR-L','La Pampa')
,(12,'AR-M','Mendoza')
,(13,'AR-N','Misiones')
,(14,'AR-P','Formosa')
,(15,'AR-Q','Neuquén')
,(16,'AR-R','Río Negro')
,(17,'AR-S','Santa Fe')
,(18,'AR-T','Tucumán')
,(19,'AR-U','Chubut')
,(20,'AR-V','Tierra del Fuego')
,(21,'AR-W','Corrientes')
,(22,'AR-X','Córdoba')
,(23,'AR-Y','Jujuy')
,(24,'AR-Z','Santa Cruz')

INSERT INTO localidad (idLocalidad,nombre,idProvincia) VALUES
(1,'Capital',22)

INSERT INTO marca (idMarca,nombre) VALUES
 (1,'Veneziana')
,(2,'Fargo')
,(3,'Mediatarde')
,(4,'Criollitas')
,(5,'Bagley')
,(6,'Terrabusi')
,(7,'Lincoln')
,(8,'Oreo')
,(9,'Lucchetti')
,(10,'Gallo')

INSERT INTO sucursal(idSucursal,nombre,cuit,idProvincia,idLocalidad,direccion,telefono,email,lat,lng)
VALUES
  (1,'Disco Velez I','30-53707910-6',22,1,'Av. Velez Sarsfield 132','0810-777-8888','discoemail@gmail.com','-31.416599','-64.187568')
 ,(2,'Disco Estrada','30-53707910-6',22,1,'Jose Manuel Estrada 66','0351 433-3004','discoemail@gmail.com','-31.427744','-64.187982')


INSERT INTO productoSucursal
(idSucursal,codigoDeBarras,activo)

 (41.00,2,'7791708001231')
,(57.00,2,'7791708001248')
,(95.00,2,'7791708001378')
,(99.00,2,'7793890001020')
,(49.00,2,'7791708611652')
,(57.00,2,'7790040887909')
,(48.00,2,'7790040946101')
,(55.00,2,'7790040999404')
,(47.58,2,'7790040999503')
,(78.45,2,'7622210649225')
,(18.25,2,'7622300742645')
,(19.66,2,'7622300742676')
,(56.66,2,'7622300829629')
,(77.47,2,'7622300841461')
,(87.89,2,'7790070410610')
,(40.22,2,'7790070411716')
,(99.50,2,'7790070411723')
,(22.89,2,'7790070411822')
,(44.22,2,'7790070411877')