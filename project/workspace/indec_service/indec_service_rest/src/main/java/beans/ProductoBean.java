package beans;

import com.google.gson.annotations.SerializedName;
import db.Bean;

public class ProductoBean  implements Bean {

    @SerializedName("id")
    private long id;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("idComercial")
    private Long idComercial;
    @SerializedName("idCategoria")
    private Long idCategoria;
    @SerializedName("nombreMarca")
    private String nombreMarca;
    @SerializedName("imagen")
    private String imagen;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getIdComercial() {
        return idComercial;
    }

    public void setIdComercial(Long idComercial) {
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





}
