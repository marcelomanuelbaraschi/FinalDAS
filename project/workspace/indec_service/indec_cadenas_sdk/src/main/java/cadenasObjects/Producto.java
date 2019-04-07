package cadenasObjects;

import com.google.gson.annotations.SerializedName;

public class Producto {

    @SerializedName("codigoProducto")
    private String codigoProducto;

    @SerializedName("precio")
    private Float precio;

    @SerializedName("mejorPrecio")
    private Boolean mejorPrecio;

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

    public Boolean getMejorPrecio() {
        return mejorPrecio;
    }

    public void setMejorPrecio(Boolean mejorPrecio) {
        this.mejorPrecio = mejorPrecio;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigoProducto='" + codigoProducto + '\'' +
                ", precio=" + precio +
                ", mejorPrecio=" + mejorPrecio +
                '}';
    }



}

