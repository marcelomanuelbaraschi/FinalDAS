package beans;

import com.google.gson.annotations.SerializedName;
import db.Bean;

public class CadenaBean implements Bean {
    @SerializedName("id")
    private Long id;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("tecnologia")
    private String tecnologia;

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

    public String getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    @Override
    public String toString() {
        return "CadenaBean{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tecnologia='" + tecnologia + '\'' +
                '}';
    }
}
