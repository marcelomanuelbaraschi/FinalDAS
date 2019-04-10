INSERT INTO cadenaServiceConfig (idCadena, nombreCadena,tecnologia,url)
VALUES
 --     ('Walmart',  'SOAP','http://localhost:8000/cadena_axis_one/services/CadenaAxisOne?wsdl')
      (4, 'Libertad', 'REST','http://localhost:8001/cadena_rest_one/cadenaRestOne')
    , (5, 'Disco',    'REST','http://localhost:8002/cadena_rest_two/cadenaRestTwo')
  --  , ('Jumbo',    'SOAP',  'http://localhost:8003/cadena_cxf_one/services/cadena_cxf_one?wsdl')
 --   , ('Carrefour','SOAP','http://localhost:8004/cadena_cxf_two/services/cadena_cxf_two?wsdl')