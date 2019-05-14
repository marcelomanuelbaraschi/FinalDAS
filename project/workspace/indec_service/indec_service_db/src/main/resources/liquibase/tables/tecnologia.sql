CREATE TABLE tecnologia (
	   idTecnologia BIGINT IDENTITY (1,1)
      ,nombreTecnologia VARCHAR(20) NOT NULL
	  ,PRIMARY KEY(idTecnologia)
      ,UNIQUE (nombreTecnologia)
)
GO