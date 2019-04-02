package cadenasObjects;

import com.google.gson.annotations.SerializedName;

public class InfoSucursal {

    @SerializedName("direccion")
    private String direccion;

    @SerializedName("lat")
    private String lat;

    @SerializedName("lng")
    private String lng;

    @SerializedName("sucursalNombre")
    private String sucursalNombre;

    @SerializedName("codigoEntidadFederal")
    private String codigoEntidadFederal;

    public String getCodigoEntidadFederal() {
        return codigoEntidadFederal;
    }

    public void setCodigoEntidadFederal(String codigoEntidadFederal) {
        this.codigoEntidadFederal = codigoEntidadFederal;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    @Override
    public String toString() {
        return "InfoSucursal{" +
                "direccion='" + direccion + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", sucursalNombre='" + sucursalNombre + '\'' +
                ", codigoEntidadFederal='" + codigoEntidadFederal + '\'' +
                '}';
    }
}
