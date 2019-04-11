package cadenasObjects;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Sucursal {

    @SerializedName("direccion")
    private String direccion;

    @SerializedName("idSucursal")
    private Long idSucursal;

    @SerializedName("idCadena")
    private Long idCadena;

    @SerializedName("lat")
    private String lat;

    @SerializedName("lng")
    private String lng;

    @SerializedName("nombreSucursal")
    private String nombreSucursal;

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

    public Long getIdCadena() {
        return idCadena;
    }

    public void setIdCadena(Long idCadena) {
        this.idCadena = idCadena;
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

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
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


    @Override
    public String toString() {
        return "Sucursal{" +
                "direccion='" + direccion + '\'' +
                ", idSucursal=" + idSucursal +
                ", idCadena=" + idCadena +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", nombreSucursal='" + nombreSucursal + '\'' +
                ", productos=" + productos +
                ", mejorOpcion=" + mejorOpcion +
                '}';
    }



}
