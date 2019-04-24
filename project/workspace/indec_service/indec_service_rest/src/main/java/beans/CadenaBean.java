package beans;

import com.google.gson.annotations.SerializedName;
import repository.db.Bean;

public class CadenaBean  implements Bean {

    @SerializedName("id")
    private Long id;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("imagen")
    private String imagen;

    public Long getId() {   return id;   }

    public void setId(Long id) {  this.id = id;   }

    public String getNombre() {   return nombre;   }

    public void setNombre(String nombre) {   this.nombre = nombre;   }

    public String getImagen() {    return imagen;   }

    public void setImagen(String imagen) {    this.imagen = imagen;   }

    @Override
    public String toString() {
        return "CadenaBean{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", imagen='" + imagen + '\'' +
                '}';
    }
}
