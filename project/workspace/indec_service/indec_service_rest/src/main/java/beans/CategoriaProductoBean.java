package beans;


import com.google.gson.annotations.SerializedName;
import db.Bean;

public class CategoriaProductoBean  implements Bean {

    @SerializedName("id")
    private Long id;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("urlImagen")
    private String urlImagen;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrlImagen() { return urlImagen; }

    public void setUrlImagen(String urlImagen) { this.urlImagen = urlImagen; }

}
