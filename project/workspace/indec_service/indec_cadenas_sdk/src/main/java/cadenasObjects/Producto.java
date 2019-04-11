package cadenasObjects;

import com.google.gson.annotations.SerializedName;

public class Producto {

    @SerializedName("idComercial")
    private String idComercial;

    @SerializedName("precio")
    private Float precio;

    @SerializedName("mejorPrecio")
    private Boolean mejorPrecio;

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

    public Boolean getMejorPrecio() {
        return mejorPrecio;
    }

    public void setMejorPrecio(Boolean mejorPrecio) {
        this.mejorPrecio = mejorPrecio;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idComercial='" + idComercial + '\'' +
                ", precio=" + precio +
                ", mejorPrecio=" + mejorPrecio +
                '}';
    }


}

