INSERT INTO cadena_params_config (id_cadena, tecnologia, nombreParametro, valor)
VALUES
       (1, 'AXIS', 'endpointUrl', 'http://localhost:8000/supermercado_axis_one/services/SupermercadoAxisOne.SupermercadoAxisOneHttpEndpoint/')--Walmart
      ,(1, 'AXIS','targetNameSpace','http://ws.SupermercadoAxisOne/')--Walmart
      ,(2, 'CXF' ,'wsdlUrl','http://localhost:8003/supermercado_cxf_one/services/supermercado_cxf_one?wsdl')--Jumbo
      ,(3, 'REST','url','http://localhost:8001/supermercado_rest_one/supermercadoRestOne')--Carrefour