package service;

import com.google.gson.annotations.SerializedName;

public class ProductoSucursal {

    @SerializedName("codigoDeBarras")
    private String codigoDeBarras;

    @SerializedName("precio")
    private float precio;

    private String validoDesde;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("marca")
    private String marca;

    @SerializedName("mejorPrecio")
    private boolean mejorPrecio;

    public float getPrecio() { return precio; }

    public void setPrecio(float precio) { this.precio = precio; }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    public String getValidoDesde() {
        return validoDesde;
    }

    public void setValidoDesde(String validoDesde) {
        this.validoDesde = validoDesde;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public boolean isMejorPrecio() {
        return mejorPrecio;
    }

    public void setMejorPrecio(boolean mejorOpcion) {
        this.mejorPrecio = mejorOpcion;
    }
}
