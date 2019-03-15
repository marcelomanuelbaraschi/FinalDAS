CREATE TABLE tecnologia_param (
      tecnologia VARCHAR(20) NOT NULL
	, nombreParametro VARCHAR(200) NOT NULL --wsdlUrl , endpointUrl , targetNameSpace
	, PRIMARY KEY (tecnologia, nombreParametro)
	, FOREIGN KEY (tecnologia) REFERENCES tecnologia (nombre)
);