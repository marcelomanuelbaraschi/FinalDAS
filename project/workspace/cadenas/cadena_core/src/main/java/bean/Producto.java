package bean;


import com.google.gson.annotations.SerializedName;
import db.Bean;


public class Producto implements Bean {


    @SerializedName("codigoDeBarras")
    private String codigoDeBarras;

    @SerializedName("precio")
    private Float precio;

    @SerializedName("validoDesde")
    private String validoDesde;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("marca")
    private String marca;

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
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
}
