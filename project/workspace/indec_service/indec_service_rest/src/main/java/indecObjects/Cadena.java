package indecObjects;

import com.google.gson.annotations.SerializedName;

import cadenasObjects.PreciosSucursal;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cadena {

    @SerializedName("id")
    private Long id;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("sucursales")
    private List<PreciosSucursal> sucursales;

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

    public List<PreciosSucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<PreciosSucursal> sucursales) {
        this.sucursales = sucursales;
    }


}
