package bean;


import com.google.gson.annotations.SerializedName;
import db.Bean;

public class ProductoBean implements Bean {


    @SerializedName("codigoProducto")
    private String codigoProducto;

    @SerializedName("precio")
    private Float precio;

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
