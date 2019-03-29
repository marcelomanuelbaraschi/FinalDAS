CREATE TABLE tecnologia (
	   id BIGINT IDENTITY (1,1)
      ,nombre VARCHAR(20) NOT NULL
	  ,PRIMARY KEY(id)
      ,UNIQUE (nombre)
);
GO