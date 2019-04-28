package service;

import beans.*;
import indecObjects.Cadena;

import java.util.List;

public interface IndecService {
    public List<CategoriaProductoBean> categorias()throws IndecServiceException;
    public List<Cadena> compararPrecios(final String codigoentidadfederal,final String localidad,final String codigos)throws IndecServiceException;
    public List<Cadena> sucursales (final String codigoentidadfederal,final String localidad)throws IndecServiceException;
    public List<ProductoBean> productos(final Long idCategoria)throws IndecServiceException;
    public List<ProvinciaBean> provincias()throws IndecServiceException;
    public List<LocalidadBean> localidades(final String codigoentidadfederal)throws IndecServiceException;
    public List<CadenaBean> cadenas()throws IndecServiceException;
    public List<CadenaServiceConfigBean> configs()throws IndecServiceException ;

}
