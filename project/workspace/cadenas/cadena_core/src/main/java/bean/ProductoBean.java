package bean;


import com.google.gson.annotations.SerializedName;
import db.Bean;

public class ProductoBean implements Bean {

    @SerializedName("idComercial")
    private String idComercial;

    @SerializedName("precio")
    private Float precio;

    public String getIdComercial() {
        return idComercial;
    }

    public void setIdComercial(String idComercial) {
        this.idComercial = idComercial;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

}
