package ws;


import com.google.gson.annotations.SerializedName;
public class  Categoria{

    @SerializedName("nombre")
    private String nombre;
    @SerializedName("imagen")
    private String imagen;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

}