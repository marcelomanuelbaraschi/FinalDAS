package beans;

import com.google.gson.annotations.SerializedName;
import db.Bean;

public class LocalidadBean implements Bean {
    @SerializedName("idProvincia")
    private Long idProv;
    @SerializedName("nombre")
    private String nombre;

    public Long getIdProv() {
        return idProv;
    }

    public void setIdProv(Long idProv) {
        this.idProv = idProv;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
