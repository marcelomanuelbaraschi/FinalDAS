package cadenasObjects;

import com.google.gson.annotations.SerializedName;

public class InfoSucursal {

    @SerializedName("direccion")
    private String direccion;

    @SerializedName("lat")
    private String lat;

    @SerializedName("lng")
    private String lng;

    @SerializedName("nombreSucursal")
    private String nombreSucursal;

    @SerializedName("codigoEntidadFederal")
    private String codigoEntidadFederal;
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

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public String getCodigoEntidadFederal() {
        return codigoEntidadFederal;
    }

    public void setCodigoEntidadFederal(String codigoEntidadFederal) {
        this.codigoEntidadFederal = codigoEntidadFederal;
    }

    @Override
    public String toString() {
        return "InfoSucursal{" +
                "direccion='" + direccion + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", nombreSucursal='" + nombreSucursal + '\'' +
                ", codigoEntidadFederal='" + codigoEntidadFederal + '\'' +
                '}';
    }


}
