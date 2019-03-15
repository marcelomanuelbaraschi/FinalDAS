package beans;

import com.google.gson.annotations.SerializedName;
import db.Bean;

public class CadenaServiceConfigBean  implements Bean {

    @SerializedName("idCadena")
    private Long idCadena;
    @SerializedName("nombreCadena")
    private String nombreCadena;
    @SerializedName("nombreParametro")
    private String nombreParametro;
    @SerializedName("tecnologia")
    private String tecnologia;
    @SerializedName("valor")
    private String valor;

    public Long getIdCadena() {
        return idCadena;
    }

    public void setIdCadena(Long idCadena) {
        this.idCadena = idCadena;
    }

    public String getNombreParametro() {
        return nombreParametro;
    }

    public String getNombreCadena() {
        return nombreCadena;
    }

    public void setNombreCadena(String nombreCadena) {
        this.nombreCadena = nombreCadena;
    }

    public void setNombreParametro(String nombreParametro) {
        this.nombreParametro = nombreParametro;
    }

    public String getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    @Override
    public String toString() {
        return "CadenaServiceConfigBean{" +
                "idCadena=" + idCadena +
                ", nombreCadena='" + nombreCadena + '\'' +
                ", nombreParametro='" + nombreParametro + '\'' +
                ", tecnologia='" + tecnologia + '\'' +
                ", valor='" + valor + '\'' +
                '}';
    }

}
