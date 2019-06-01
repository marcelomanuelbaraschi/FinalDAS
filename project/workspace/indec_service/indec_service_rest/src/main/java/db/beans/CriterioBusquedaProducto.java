package db.beans;

import com.google.gson.annotations.SerializedName;
import db.Bean;

public class CriterioBusquedaProducto implements Bean {

    @SerializedName("idCategoria")
    private Short idCategoria;
    @SerializedName("marca")
    private String marca;

    public String getMarca() { return marca; }

    public void setMarca(String marca) { this.marca = marca; }

    public Short getIdCategoria() { return idCategoria; }

    public void setIdCategoria(Short idCategoria) { this.idCategoria = idCategoria; }

}
