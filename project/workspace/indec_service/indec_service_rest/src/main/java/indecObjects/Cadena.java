package indecObjects;

import cadenasObjects.Sucursal;
import com.google.gson.annotations.SerializedName;
import repository.db.Bean;


import java.util.List;

public class Cadena  implements Bean {

    @SerializedName("id")
    private Long id;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("sucursales")
    private List<Sucursal> sucursales;

    @SerializedName("disponibilidad")
    private String disponibilidad;



    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

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

    public List<Sucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }


}
