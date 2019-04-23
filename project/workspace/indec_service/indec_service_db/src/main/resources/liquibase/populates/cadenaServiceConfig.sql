INSERT INTO cadenaServiceConfig (idCadena, nombreCadena,tecnologia,url)
VALUES
      (1, 'Walmart',  'SOAP','http://localhost:8003/cadena_cxf_one/services/cadena_cxf_one?wsdl')
    , (4, 'Libertad', 'SOAP','http://localhost:8000/cadena_axis_one/services/CadenaAxisOne?wsdl')
    , (5, 'Disco',    'REST','http://localhost:8001/cadena_rest_one/cadenaRestOne')
  --  , ('Jumbo',    'SOAP',  'http://localhost:8003/cadena_cxf_one/services/cadena_cxf_one?wsdl')
 --   , ('Carrefour','SOAP','http://localhost:8004/cadena_cxf_two/services/cadena_cxf_two?wsdl')