package sdkObjects;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Sucursal {

    @SerializedName("idSucursal")
    private Long idSucursal;

    @SerializedName("nombreSucursal")
    private String nombreSucursal;

    @SerializedName("direccion")
    private String direccion;

    @SerializedName("latitud")
    private String latitud;

    @SerializedName("longitud")
    private String longitud;

    @SerializedName("email")
    private String email;

    @SerializedName("telefono")
    private String telefono;

    @SerializedName("cuit")
    private String cuit;

    @SerializedName("localidad")
    private String localidad;

    @SerializedName("provincia")
    private String provincia;

    @SerializedName("codigoEntidadFederal")
    private String codigoEntidadFederal;

    @SerializedName("idCadena")
    private Long idCadena;

    @SerializedName("productos")
    private List<Producto> productos;

    @SerializedName("CantidadDeProductosConPrecioMasBajo")
    private Long CantidadDeProductosConPrecioMasBajo;

    @SerializedName("mejorOpcion")
    private boolean mejorOpcion;

    public Long getCantidadDeProductosConPrecioMasBajo() {
        return CantidadDeProductosConPrecioMasBajo;
    }

    public void setCantidadDeProductosConPrecioMasBajo(Long cantidadDeProductosConPrecioMasBajo) {
        CantidadDeProductosConPrecioMasBajo = cantidadDeProductosConPrecioMasBajo;
    }

    public boolean isMejorOpcion() {
        return mejorOpcion;
    }

    public void setMejorOpcion(boolean mejorOpcion) {
        this.mejorOpcion = mejorOpcion;
    }

    public Long getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Long idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCodigoEntidadFederal() {
        return codigoEntidadFederal;
    }

    public void setCodigoEntidadFederal(String codigoEntidadFederal) {
        this.codigoEntidadFederal = codigoEntidadFederal;
    }

    public Long getIdCadena() {
        return idCadena;
    }

    public void setIdCadena(Long idCadena) {
        this.idCadena = idCadena;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
