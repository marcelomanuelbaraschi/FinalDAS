package db.beans;

import com.google.gson.annotations.SerializedName;
import db.Bean;

public class Producto implements Bean {

    @SerializedName("nombreProducto")
    private String nombreProducto;
    @SerializedName("codigoDeBarras")
    private String codigoDeBarras;
    @SerializedName("idCategoria")
    private Long idCategoria;
    @SerializedName("nombreCategoria")
    private String nombreCategoria;
    @SerializedName("nombreMarca")
    private String nombreMarca;
    @SerializedName("imagenProducto")
    private String imagenProducto;

    public String getNombreProducto() {       return nombreProducto;   }

    public void setNombreProducto(String nombreProducto) {       this.nombreProducto = nombreProducto;   }

    public String getCodigoDeBarras() {       return codigoDeBarras;   }

    public void setCodigoDeBarras(String codigoDeBarras) {       this.codigoDeBarras = codigoDeBarras;   }

    public String getImagenProducto() {       return imagenProducto;   }

    public void setImagenProducto(String imagenProducto) {       this.imagenProducto = imagenProducto;   }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }


    public String getNombreCategoria() {     return nombreCategoria;  }

    public void setNombreCategoria(String nombreCategoria) {     this.nombreCategoria = nombreCategoria;  }
}
