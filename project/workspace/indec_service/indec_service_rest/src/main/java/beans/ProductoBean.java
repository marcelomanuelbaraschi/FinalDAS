package beans;

import com.google.gson.annotations.SerializedName;
import db.Bean;

public class ProductoBean  implements Bean {

    @SerializedName("nombre")
    private String nombre;
    @SerializedName("idComercial")
    private String idComercial;
    @SerializedName("idCategoria")
    private Long idCategoria;
    @SerializedName("nombreCategoria")
    private String nombreCategoria;
    @SerializedName("nombreMarca")
    private String nombreMarca;
    @SerializedName("imagen")
    private String imagen;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdComercial() {
        return idComercial;
    }

    public void setIdComercial(String idComercial) {
        this.idComercial = idComercial;
    }

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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombreCategoria() {     return nombreCategoria;  }

    public void setNombreCategoria(String nombreCategoria) {     this.nombreCategoria = nombreCategoria;  }
}
