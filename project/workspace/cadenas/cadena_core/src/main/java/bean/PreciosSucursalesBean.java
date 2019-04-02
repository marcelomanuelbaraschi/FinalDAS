package bean;


import com.google.gson.annotations.SerializedName;
import db.Bean;

public class PreciosSucursalesBean implements Bean {

    @SerializedName("idSucursal")
    private Long idSucursal;

    @SerializedName("codigoProducto")
    private String codigoProducto;

    @SerializedName("precio")
    private Float precio;

    public Long getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Long idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }
}
