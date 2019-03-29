CREATE TABLE localidad (
   id BIGINT IDENTITY (1,1)
  ,idProvincia BIGINT NOT NULL
  ,nombre   VARCHAR(255) NOT NULL
  ,FOREIGN KEY (idProvincia) REFERENCES provincia (id)
  ,PRIMARY KEY (id)
);
GO