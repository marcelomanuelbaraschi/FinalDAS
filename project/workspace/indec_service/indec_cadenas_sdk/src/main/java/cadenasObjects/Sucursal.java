package cadenasObjects;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Sucursal {

    @SerializedName("direccion")
    private String direccion;

    @SerializedName("idSucursal")
    private Long idSucursal;

    @SerializedName("lat")
    private String lat;

    @SerializedName("lng")
    private String lng;

    @SerializedName("sucursalNombre")
    private String sucursalNombre;

    @SerializedName("productos")
    private List<Producto> productos;

    @SerializedName("mejorOpcion")
    private Boolean mejorOpcion;


    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Long getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Long idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getSucursalNombre() {
        return sucursalNombre;
    }

    public void setSucursalNombre(String sucursalNombre) {
        this.sucursalNombre = sucursalNombre;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }


    public Boolean getMejorOpcion() {
        return mejorOpcion;
    }

    public void setMejorOpcion(Boolean mejorOpcion) {
        this.mejorOpcion = mejorOpcion;
    }
}
