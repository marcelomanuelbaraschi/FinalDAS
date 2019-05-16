package beans;

import sdkObjects.Sucursal;
import com.google.gson.annotations.SerializedName;
import db.Bean;



import java.util.List;

public class CadenaBean implements Bean {

    @SerializedName("idCadena")
    private Integer idCadena;

    @SerializedName("nombreCadena")
    private String nombreCadena;

    @SerializedName("sucursales")
    private List<Sucursal> sucursales;

    @SerializedName("disponibilidad")
    private String disponibilidad;

    @SerializedName("imagenCadena")
    private String imagenCadena;

    public Integer getIdCadena() {      return idCadena;   }

    public void setIdCadena(Integer idCadena) {       this.idCadena = idCadena;   }

    public String getNombreCadena() {       return nombreCadena;   }

    public void setNombreCadena(String nombreCadena) {       this.nombreCadena = nombreCadena;   }

    public List<Sucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getImagenCadena() {      return imagenCadena;   }

    public void setImagenCadena(String imagenCadena) {      this.imagenCadena = imagenCadena;   }
}
